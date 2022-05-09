package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.utils.logger.Logger;

public class StorageShoot extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public StorageShoot() {
    addRequirements(subsystems.storage);
  }

  @Override
  public void initialize() {
    Logger.info("StorageShoot : Start");
    subsystems.storage.setShooterSpeed(1);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    Logger.info("StorageShoot : End");
    subsystems.storage.setShooterSpeed(0);
  }
}