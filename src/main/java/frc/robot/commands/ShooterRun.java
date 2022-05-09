package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.utils.logger.Logger;

public class ShooterRun extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public ShooterRun() {
    addRequirements(subsystems.shooter);
  }

  @Override
  public void initialize() {
    Logger.info("StorageShoot : Start");
    subsystems.shooter.setShooterSpeed(1);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    Logger.info("StorageShoot : End");
    subsystems.shooter.setShooterSpeed(0);
  }
}