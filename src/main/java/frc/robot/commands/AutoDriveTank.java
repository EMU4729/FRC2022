package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;

public class AutoDriveTank extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public double leftSpeed;
  public double rightSpeed;

  public AutoDriveTank() {
    addRequirements(subsystems.drive);
  }

  @Override
  public void initialize() {
    subsystems.drive.tank(leftSpeed, rightSpeed);

  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    subsystems.drive.off();
  }

}
