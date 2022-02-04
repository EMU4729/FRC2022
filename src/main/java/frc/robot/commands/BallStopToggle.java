package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStopSub;

import java.time.Duration;
import java.time.Instant;

public class BallStopToggle extends CommandBase {
  private final BallStopSub ballStop;
  private boolean isOpen = false;
  private Instant timer;

  public BallStopToggle(BallStopSub ballStopSub) {
    ballStop = ballStopSub;
    addRequirements(ballStopSub);
  }

  @Override
  public void initialize() {
    timer = Instant.now();
    if (isOpen) {
      ballStop.set(-0.5);
    } else {
      ballStop.set(0.5);
    }
  }

  @Override
  public boolean isFinished() {
    long howLong = Duration.between(timer, Instant.now()).toMillis();
    if (howLong > 1000) {
      return true;
    }
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    ballStop.set(0);
  }
}
