package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.structures.TalonSRXMotorController;

public class IntakeSub extends SubsystemBase {
  private final TalonSRXMotorController motor = new TalonSRXMotorController(Constants.intakeMotorPort);
  private final double MOTOR_SPEED = 0.5; // TODO: Adjust this

  @Override
  public void periodic() {
  }

  public void startSpin() {
    motor.set(MOTOR_SPEED);
  }

  public void stopSpin() {
    motor.stopMotor();
  }
}
