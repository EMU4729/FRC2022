package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.structures.TalonSRXMotorController;

/**
 * Drive Subsystem.
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
   * Starts tank drive.
   * Similar to MoveTank from ev3dev2.
   * 
   * @param leftSpeed  The speed of the left motors, between -1 and 1.
   * @param rightSpeed The speed of the right motors, between -1 and 1.
   */
  public void tank(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed, rightSpeed);
  }

  /**
   * Starts arcade drave.
   * Similar to MoveSteering from ev3dev2.
   * 
   * @param speed    Speed between -1 and 1.
   * @param steering Steering value between -1 and 1.
   */
  public void arcade(double speed, double steering) {
    drive.arcadeDrive(speed, steering);
  }
}
