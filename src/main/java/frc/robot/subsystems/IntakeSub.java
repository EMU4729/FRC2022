package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

/**
 * Intake Subsystem.
 * Handles all intake functionality.
 */
public class IntakeSub extends SubsystemBase {
  private static final Constants constants = Constants.getInstance();
  private final WPI_TalonSRX motor = new WPI_TalonSRX(constants.intakeMotorPort);

  @Override
  public void periodic() {
  }

  /**
   * Sets the speed of the intake spinner.
   * 
   * @param speed Speed between -1 and 1
   */
  public void setSpinSpeed(double speed) {
    motor.set(speed);
  }
}
