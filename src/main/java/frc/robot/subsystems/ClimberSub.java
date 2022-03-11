package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Climber Subsystem.
 * Handles all climber functionality.
 */
public class ClimberSub extends SubsystemBase {
  private final Constants constants = Constants.getInstance();
  private final WPI_VictorSPX motor = new WPI_VictorSPX(constants.climberMotorPort);
  public ClimberSub (){
    super();
    motor.setNeutralMode(NeutralMode.Brake);
  }
  @Override
  public void periodic() {
  }

  /**
   * Sets climber motor speed.
   * 
   * @param speed Speed between -1 and 1.
   */
  public void set(double speed) {
    motor.set(speed * constants.climberMotorInversionFactor);
  }

}
