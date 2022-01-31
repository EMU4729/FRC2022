package frc.robot.commands;

import com.ctre.phoenix.led.StrobeAnimation;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.StorageSub;

public class StorageRunForward extends CommandBase {
  private final StorageSub storage;

  public StorageRunForward(StorageSub storageSub) {
    storage = storageSub;
    addRequirements(storageSub);
  }

  @Override
  public void initialize() {
    // TODO: Implement this properly
    storage.setConveyorSpeed(0.5);
  }

}
