package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Variables;
import frc.robot.logger.Logger;

public class DriveReverseDirection extends CommandBase {
  private final Variables vars;

  public DriveReverseDirection() {
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
    Logger.info("drive direction reversed : drive forward = " +
        (vars.invertDriveDirection ? "normal" : "reversed"));
  }

}
