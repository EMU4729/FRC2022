package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.Clamper;

/**
 * Subsystem that handles all ball stop functionality.
 * 
 * @apiNote The hardware for this is currently not made, so don't use this.
 */
public class BallStopSub extends SubsystemBase {
  private final Constants constants = Constants.getInstance();
  private final WPI_TalonSRX motor = new WPI_TalonSRX(constants.STORAGE_BALLSTOP_MOTOR_PORT);
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
    speed = Clamper.absUnit(speed);
    motor.set(speed);
  }
}