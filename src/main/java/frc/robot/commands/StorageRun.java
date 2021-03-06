package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.utils.logger.Logger;

/**
 * Command to run the storage.
 */
public class StorageRun extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public StorageRun() {
    addRequirements(subsystems.storage);
  }

  @Override
  public void initialize() {
    Logger.info("StorageRun : Start");
    subsystems.storage.setConveyorSpeed(1);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    subsystems.storage.setConveyorSpeed(0);
    Logger.info("StorageRun : End");
  }
}