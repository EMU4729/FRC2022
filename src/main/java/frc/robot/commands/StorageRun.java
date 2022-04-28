package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.logger.Logger;
import frc.robot.subsystems.StorageSub;

public class StorageRun extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public StorageRun() {
    addRequirements(subsystems.storage);
  }

  @Override
  public void initialize() {
    Logger.info("StorageRun : Start");
    subsystems.storage.setConveyorSpeed(0.2);
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