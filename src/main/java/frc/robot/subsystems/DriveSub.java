package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Variables;
import frc.robot.utils.logger.Logger;

/**
 * Drive Subsystem.
 * Handles all drive functionality.
 */
public class DriveSub extends SubsystemBase {
  private final Constants constants = Constants.getInstance();
  private final WPI_TalonSRX leftMaster = new WPI_TalonSRX(constants.leftFrontMotorPort);
  private final WPI_TalonSRX leftSlave = new WPI_TalonSRX(constants.leftBackMotorPort);
  private final WPI_TalonSRX rightMaster = new WPI_TalonSRX(constants.rightFrontMotorPort);
  private final WPI_TalonSRX rightSlave = new WPI_TalonSRX(constants.rightBackMotorPort);
  private final Variables vars = Variables.getInstance();

  public DriveSub() {
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    // change this if needed
    leftMaster.setInverted(true);
    rightMaster.setInverted(true);
    rightSlave.setInverted(true);
  }

  /**
   * Activates tank drive. Similar to MoveTank from ev3dev.
   * 
   * @param leftSpeed  The left speed.
   * @param rightSpeed The right speed.
   */
  public void tank(double leftSpeed, double rightSpeed) {
    int direction = vars.invertDriveDirection ? 1 : -1;
    Logger.info("Drive : Run : Speed Left=" + (leftSpeed * direction)+", Speed Right="+
        (rightSpeed * direction));
    leftSpeed = ratSpeed(leftSpeed);
    rightSpeed = ratSpeed(rightSpeed);
    leftMaster.set(leftSpeed * direction*0.7);
    rightMaster.set(rightSpeed * direction*0.7);
  }

  /**
   * Activates arcade drive. Similar to MoveSteering from ev3dev.
   * 
   * @param speed    The speed
   * @param steering The steering
   */
  public void arcade(double speed, double steering) {
    int direction = vars.invertDriveDirection ? 1 : -1;
    double leftSpeed = (speed + steering * direction);
    double rightSpeed = (speed - steering * direction);
    tank(leftSpeed, rightSpeed);
  }

  /**
   * Stop all motors.
   */
  public void off() {
    tank(0, 0);
  }

  private double ratSpeed(double s){
    if(s > 1.0){
      s = 1.0;
    } else if(s < -1.0){
      s = -1.0;
    }
    return s;
  }

}
