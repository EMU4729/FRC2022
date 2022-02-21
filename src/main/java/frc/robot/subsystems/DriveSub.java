package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Drive Subsystem.
 * Handles all drive functionality.
 */
public class DriveSub extends SubsystemBase {
  private static final Constants constants = Constants.getInstance();
  private final WPI_TalonSRX leftMaster = new WPI_TalonSRX(constants.leftFrontMotorPort);
  private final WPI_TalonSRX leftSlave = new WPI_TalonSRX(constants.leftBackMotorPort);
  private final WPI_TalonSRX rightMaster = new WPI_TalonSRX(constants.rightFrontMotorPort);
  private final WPI_TalonSRX rightSlave = new WPI_TalonSRX(constants.rightBackMotorPort);

  // TODO: Add gyro and odometry here

  public DriveSub() {
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    // change this if needed
    leftMaster.setInverted(true);
  }

  /**
   * Activates tank drive. Similar to MoveTank from ev3dev.
   * 
   * @param leftSpeed  The left speed.
   * @param rightSpeed The right speed.
   */
  public void tank(double leftSpeed, double rightSpeed) {
    leftMaster.set(leftSpeed);
    rightMaster.set(rightSpeed);
  }

  /**
   * Activates arcade drive. Similar to MoveSteering from ev3dev.
   * 
   * @param speed    The speed
   * @param steering The steering
   */
  public void arcade(double speed, double steering) {
    // TODO: Improve values
    double leftSpeed = (speed + steering) / 2;
    double rightSpeed = (speed - steering) / 2;
    tank(leftSpeed, rightSpeed);
  }

  /**
   * Stop all motors.
   */
  public void off() {
    tank(0, 0);
  }

}
