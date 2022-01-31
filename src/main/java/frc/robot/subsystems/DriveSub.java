package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Drive Subsystem.
 * Handles all drive functionality.
 */
public class DriveSub extends SubsystemBase {
  private final WPI_TalonSRX leftMaster = new WPI_TalonSRX(Constants.leftFrontMotorPort);
  private final WPI_TalonSRX leftSlave = new WPI_TalonSRX(Constants.leftBackMotorPort);
  private final WPI_TalonSRX rightMaster = new WPI_TalonSRX(Constants.rightFrontMotorPort);
  private final WPI_TalonSRX rightSlave = new WPI_TalonSRX(Constants.rightBackMotorPort);

  // TODO: Add gyro and odometry here

  public DriveSub() {
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);
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

}
