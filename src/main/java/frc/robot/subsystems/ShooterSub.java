package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.Clamper;

/**
 * Shooter Subsystem.
 * Handles the shooting of the balls.
 */
public class ShooterSub extends SubsystemBase {
  private final Constants constants = Constants.getInstance();
  private final WPI_VictorSPX motor = new WPI_VictorSPX(constants.STORAGE_SHOOTER_MOTOR_PORT);

  public ShooterSub() {
  }

    /**
   * Sets the shooter speed.
   * 
   * @param speed Speed between -1 and 1
   */
  public void setShooterSpeed(double speed) {
      speed = Clamper.absUnit(speed);
      motor.set(speed);
  }

  @Override
  public void periodic() {
  }
}