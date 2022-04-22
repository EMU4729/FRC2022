package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Variables;
import frc.robot.subsystems.DriveSub;

public class Drive extends CommandBase {
  private final Variables variables = Variables.getInstance();
  private final DriveSub drive;
  private final XboxController controller;
  private final boolean useTank = SmartDashboard.getBoolean("useTank", false);

  private double speedMultiplier;

  public Drive(DriveSub drive, XboxController controller) {
    this.drive = drive;
    this.controller = controller;

    addRequirements(drive);
  }

  @Override
  public void initialize() {
    if (DriverStation.isAutonomous()) {
      speedMultiplier = variables.teleopSpeedMultiplier;
    } else {
      speedMultiplier = variables.autoSpeedMultiplier;
    }

  }

  @Override
  public void execute() {
    // Speed Input Curve: s = c^x
    // Where s is output speed, c is joystick value and x is a input curve exponent
    // constant.

    if (useTank) {
      double leftSpeed = Math.pow(controller.getLeftY(), variables.inputCurveExponent);
      double rightSpeed = Math.pow(controller.getRightY(), variables.inputCurveExponent);
      drive.tank(leftSpeed * speedMultiplier, rightSpeed * speedMultiplier);
    } else {
      double speed = Math.pow(controller.getLeftY(), variables.inputCurveExponent);
      double steering = controller.getRightX();

      // If needed, make the teleop speed multiplier affect steering, too
      drive.arcade(speed * speedMultiplier, steering);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
