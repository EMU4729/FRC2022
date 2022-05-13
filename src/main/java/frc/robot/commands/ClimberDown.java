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

  public ClimberDown() {
    addRequirements(subsystems.climber);
  }

  @Override
  public void initialize() {
    Logger.info("ClimberRun : Start : Down");
    subsystems.climber.set(0.5);
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    Logger.info("ClimberRun : End");
    subsystems.climber.set(0);
  }
}