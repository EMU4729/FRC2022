package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.utils.logger.Logger;

/**
 * Command to raise the climber.
 */
public class ClimberUp extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public ClimberUp() {
    addRequirements(subsystems.climber);
  }

  @Override
  public void initialize() {
    Logger.info("ClimberRun : Start : Up");
    subsystems.climber.set(-0.5);
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
    Logger.info("climberRun : End");
    subsystems.climber.set(0);
  }
}