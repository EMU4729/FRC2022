package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Constants;
import frc.robot.utils.Clamper;

/**
 * Intake Subsystem.
 * Handles all intake functionality.
 */
public class IntakeSub extends SubsystemBase {
  private final Constants constants = Constants.getInstance();
  private final WPI_VictorSPX motor = new WPI_VictorSPX(constants.INTAKE_MOTOR_PORT);

  @Override
  public void periodic() {
  }

  /**
   * Sets the speed of the intake spinner.
   * 
   * @param speed Speed between -1 and 1
   */
  public void setSpinSpeed(double speed) {
    speed = Clamper.absUnit(speed);
    System.out.println("speed : "+speed);
    motor.set(speed);
  }
}
