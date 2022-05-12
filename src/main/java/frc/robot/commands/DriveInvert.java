package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Variables;
import frc.robot.utils.logger.Logger;

/**
 * Command to invert the drive - in case the robot is not facing the driver.
 */
public class DriveInvert extends CommandBase {
  private final Variables vars;

  public DriveInvert() {
    vars = Variables.getInstance();
  }

  @Override
  public void initialize() {
    vars.invertDriveDirection = !vars.invertDriveDirection;
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interupted) {
    Logger.info("DriveInvert : drive forward = " +
        (vars.invertDriveDirection ? "normal" : "reversed"));
  }

}
