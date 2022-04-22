package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.logger.Logger;
import frc.robot.subsystems.StorageSub;

public class StorageReverse extends CommandBase {
  private final StorageSub storage;

  public StorageReverse(StorageSub storage) {
    this.storage = storage;

    addRequirements(storage);
  }

  @Override
  public void initialize() {
    Logger.info("StorageReverse : Start");
    storage.setConveyorSpeed(-0.2);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    Logger.info("StorageReverse : End");
    storage.setConveyorSpeed(0);
  }
}