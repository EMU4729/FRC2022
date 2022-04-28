package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Subsystems;
import frc.robot.Variables;
import frc.robot.subsystems.DriveSub;

public class Teleop extends CommandBase {
  private final Variables variables = Variables.getInstance();
  private final Subsystems subsystems = Subsystems.getInstance();
  private final OI oi = OI.getInstance();
  private final boolean useTank = SmartDashboard.getBoolean("useTank", false);

  private double speedMultiplier;

  public Teleop() {
    addRequirements(subsystems.drive);
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
      double leftSpeed = Math.pow(oi.controller.getLeftY(), variables.inputCurveExponent);
      double rightSpeed = Math.pow(oi.controller.getRightY(), variables.inputCurveExponent);
      subsystems.drive.tank(leftSpeed * speedMultiplier, rightSpeed * speedMultiplier);
    } else {
      double speed = Math.pow(oi.controller.getLeftY(), variables.inputCurveExponent);
      double steering = oi.controller.getRightX();

      // If needed, make the teleop speed multiplier affect steering, too
      subsystems.drive.arcade(speed * speedMultiplier, steering);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
