package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.utils.AsyncTimer;
import frc.robot.utils.logger.Logger;

/**
 * Command that lowers the climber.
 */
public class ClimberDown extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();
  private AsyncTimer timer;

  public ClimberDown() {
    addRequirements(subsystems.climber);
  }

  @Override
  public void initialize() {
    if (subsystems.climber.isUp) {
      timer = new AsyncTimer(1000);
      subsystems.climber.set(-0.5);
    }
  }

  @Override
  public boolean isFinished() {
    if (subsystems.climber.isUp) {
      return timer.isFinished();
    } else {
      return true;
    }

  }

  @Override
  public void end(boolean interrupted) {
    if (subsystems.climber.isUp) {
      subsystems.climber.set(0);
      subsystems.climber.isUp = false;
      Logger.info("subsystems.climberDown : finished");
    }
    Logger.warn("subsystems.climberDown : Failed - Climber already down");
  }
}
