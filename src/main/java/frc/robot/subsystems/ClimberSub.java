package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.Clamper;

/**
 * Climber Subsystem.
 * Handles all climber functionality.
 */
public class ClimberSub extends SubsystemBase {
  private final Constants constants = Constants.getInstance();
  private final WPI_VictorSPX motor = new WPI_VictorSPX(constants.CLIMBER_MOTOR_PORT);
  public boolean isUp = false;

  @Override
  public void periodic() {
  }

  /**
   * Sets climber motor speed.
   * 
   * @param speed Speed between -1 and 1.
   */
  public void set(double speed) {
    speed = Clamper.absUnit(speed);
    motor.set(speed);
  }

}
