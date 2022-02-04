package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.StorageSub;

public class StorageRun extends CommandBase {
  private final StorageSub storage;

  public StorageRun(StorageSub storageSub) {
    storage = storageSub;
    addRequirements(storageSub);
  }

  @Override
  public void initialize() {
    storage.setConveyorSpeed(0.5);
  }

  @Override
  public void end(boolean interrupted) {
    storage.setConveyorSpeed(0);
  }
}
