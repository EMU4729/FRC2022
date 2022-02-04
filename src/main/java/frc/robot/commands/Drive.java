package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

public class Drive extends CommandBase {
  private final DriveSub drive;
  private final double speed;
  private final double steering;

  public Drive(DriveSub driveSub, double leftJoystickY, double rightJoystickX) {
    drive = driveSub;
    speed = leftJoystickY;
    steering = rightJoystickX;
    addRequirements(driveSub);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    drive.arcade(speed, steering);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
