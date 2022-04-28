package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.logger.Logger;
import frc.robot.utils.AsyncTimer;

public class BallStopClose extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();
  private AsyncTimer timer;

  public BallStopClose() {
    addRequirements(subsystems.ballStop);
  }

  @Override
  public void initialize() {
    if (subsystems.ballStop.isOpen) {
      timer = new AsyncTimer(1000);
      subsystems.ballStop.set(0.5);
    }
  }

  @Override
  public boolean isFinished() {
    if (subsystems.ballStop.isOpen) {
      return timer.isFinished();
    } else {
      return true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    if (subsystems.ballStop.isOpen) {
      subsystems.ballStop.set(0);
      subsystems.ballStop.isOpen = false;
      Logger.info("Ball stop closed");
    }
    Logger.warn("Ball stop close failed : Ball stop already closed");
  }
}
