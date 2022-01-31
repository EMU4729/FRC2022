package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Climber Subsystem.
 * Handles all climber functionality.
 */
public class ClimberSub extends SubsystemBase {
  private final WPI_TalonSRX motor = new WPI_TalonSRX(Constants.climberMotorPort);

  @Override
  public void periodic() {
  }

  /**
   * Sets climber motor speed.
   * 
   * @param speed Speed between -1 and 1.
   */
  public void set(double speed) {
    motor.set(speed);
  }

}
