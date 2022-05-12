package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.utils.AsyncTimer;
import frc.robot.utils.logger.Logger;

/**
 * Command that opens the ball stop.
 * 
 * @apiNote The hardware for this is not currently made and so this should not
 *          be used.
 */
public class BallStopOpen extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();
  private AsyncTimer timer;

  public BallStopOpen() {
    addRequirements(subsystems.ballStop);
  }

  @Override
  public void initialize() {
    if (!subsystems.ballStop.isOpen) {
      timer = new AsyncTimer(1000);
      subsystems.ballStop.set(-0.5);
    }
  }

  @Override
  public boolean isFinished() {
    if (!subsystems.ballStop.isOpen) {
      return timer.isFinished();
    } else {
      return true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    if (!subsystems.ballStop.isOpen) {
      subsystems.ballStop.set(0);
      subsystems.ballStop.isOpen = true;
      Logger.info("Ball stop opened");
    }
    Logger.warn("Ball stop open failed : Ball stop already open");
  }

}
