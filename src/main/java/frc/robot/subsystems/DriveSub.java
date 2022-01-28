package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.structures.TalonSRXMotorController;

/**
 * Drive Subsystem
 * Handles all drive functionality.
 */
public class DriveSub extends SubsystemBase {
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(
      new TalonSRXMotorController(Constants.leftFrontMotorPort),
      new TalonSRXMotorController(Constants.leftBackMotorPort));

  private final MotorControllerGroup rightMotors = new MotorControllerGroup(
      new TalonSRXMotorController(Constants.rightFrontMotorPort),
      new TalonSRXMotorController(Constants.rightBackMotorPort));

  private final DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  @Override
  public void periodic() {
  }

  /**
   * Sets the speed of all 4 motors.
   * 
   * @param leftSpeed  The speed of the left motors, between -1 and 1.
   * @param rightSpeed The speed of the right motors, between -1 and 1.
   */
  public void setSpeed(double leftSpeed, double rightSpeed) {
    leftMotors.set(leftSpeed);
    rightMotors.set(rightSpeed);
  }
}
