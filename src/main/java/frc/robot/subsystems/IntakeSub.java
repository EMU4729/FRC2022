package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.structures.TalonSRXMotorController;

public class IntakeSub extends SubsystemBase {
  private final TalonSRXMotorController motor = new TalonSRXMotorController(Constants.intakeMotorPort);

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
