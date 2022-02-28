package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStopSub;
import frc.robot.utils.AsyncTimer;
import frc.robot.utils.logger.Logger;

public class BallStopOpen extends CommandBase {
  private final BallStopSub ballStop;
  private AsyncTimer timer;

  public BallStopOpen(BallStopSub ballStop) {
    this.ballStop = ballStop;
    addRequirements(ballStop);
  }

  @Override
  public void initialize() {
    if (!ballStop.isOpen) {
      timer = new AsyncTimer(1000);
      ballStop.set(-0.5);
    }
  }

  @Override
  public boolean isFinished() {
    if (!ballStop.isOpen) {
      return timer.isFinished();
    } else {
      return true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    if (!ballStop.isOpen) {
      ballStop.set(0);
      ballStop.isOpen = true;
      Logger.info("Ball stop opened");
    }
    Logger.warn("Ball stop open failed : Ball stop already open");
  }

}
