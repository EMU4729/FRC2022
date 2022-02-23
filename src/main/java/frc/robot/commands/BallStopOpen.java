package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStopSub;
import frc.robot.utils.logger.Logger;

import java.time.Duration;
import java.time.Instant;

public class BallStopOpen extends CommandBase {
  private final BallStopSub ballStop;
  private Instant timer;

  public BallStopOpen(BallStopSub ballStop) {
    this.ballStop = ballStop;
    addRequirements(ballStop);
  }

  @Override
  public void initialize() {
    if (!ballStop.isOpen) {
      timer = Instant.now();
      ballStop.set(-0.5);
    }
  }

  @Override
  public boolean isFinished() {
    if (!ballStop.isOpen) {
      long howLong = Duration.between(timer, Instant.now()).toMillis();
      if (howLong > 1000) {
        return true;
      }
      return false;
    } else {
      return true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    if (!ballStop.isOpen) {
      ballStop.set(0);
      ballStop.isOpen = true;
      System.out.println("Ball stop opened");
      Logger.info("Ball stop opened");
    }
    Logger.warn("Ball stop open failed : Ball stop already open");
  }

}
