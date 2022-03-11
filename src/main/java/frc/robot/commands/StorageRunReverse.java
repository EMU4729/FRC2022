package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.StorageSub;

public class StorageRunReverse extends CommandBase {
  private final StorageSub storage;

  public StorageRunReverse(StorageSub storage) {
    this.storage = storage;

    addRequirements(storage);
  }

  @Override
  public void initialize() {
    storage.setConveyorSpeed(-0.2);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    storage.setConveyorSpeed(0);
  }
}
