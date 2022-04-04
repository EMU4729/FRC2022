package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallStopSub extends SubsystemBase {
  private final Constants constants = Constants.getInstance();
  private final WPI_TalonSRX motor = new WPI_TalonSRX(constants.BallStopMotorPort);
  public boolean isOpen = false;

  @Override
  public void periodic() {
  }

  /**
   * Sets the speed of the ball stop motor
   * 
   * @param speed Speed between -1 and 1
   */
  public void set(double speed) {
    motor.set(speed);
  }
}