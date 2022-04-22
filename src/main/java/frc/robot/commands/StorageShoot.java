package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.logger.Logger;
import frc.robot.subsystems.StorageSub;

public class StorageShoot extends CommandBase {
  private final StorageSub storage;

  public StorageShoot(StorageSub storage) {
    this.storage = storage;

    addRequirements(storage);
  }

  @Override
  public void initialize() {
    Logger.info("StorageShoot : Start");
    storage.setConveyorSpeed(1);
    storage.setShooterSpeed(1);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    Logger.info("StorageShoot : End");
    storage.setConveyorSpeed(0);
    storage.setShooterSpeed(0);
  }
}