package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.utils.logger.Logger;

public class StorageReverse extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public StorageReverse() {
    addRequirements(subsystems.storage);
  }

  @Override
  public void initialize() {
    Logger.info("StorageReverse : Start");
    subsystems.storage.setConveyorSpeed(-0.2);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    Logger.info("StorageReverse : End");
    subsystems.storage.setConveyorSpeed(0);
  }
}