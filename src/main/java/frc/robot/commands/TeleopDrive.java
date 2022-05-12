package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Subsystems;
import frc.robot.Variables;

/**
 * The Teleop Command.
 */
public class TeleopDrive extends CommandBase {
  private final Variables variables = Variables.getInstance();
  private final Subsystems subsystems = Subsystems.getInstance();
  private final OI oi = OI.getInstance();
  private final boolean useTank = SmartDashboard.getBoolean("useTank", false);

  private double speedMultiplier;

  public TeleopDrive() {
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
      // TODO: also make this make sense
      double steering = -oi.controller.getRightX();

      // If needed, make the teleop speed multiplier affect steering, too
      // TODO: Actually find the actual error behind the confusion that is this
      subsystems.drive.arcade(steering * speedMultiplier, speed);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
