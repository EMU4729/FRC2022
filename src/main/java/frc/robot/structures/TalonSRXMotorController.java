package frc.robot.structures;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

/**
 * MotorController implementation for TalonSRX.
 */
public class TalonSRXMotorController implements MotorController {
  private TalonSRX motor;

  /**
   * TalonSRXMotorController constructor.
   * 
   * @param port The TalonSRX motor port.
   */
  public TalonSRXMotorController(int port) {
    motor = new TalonSRX(port);
  }

  public void disable() {
    motor.set(TalonSRXControlMode.Disabled, 0);
  }

  public double get() {
    return motor.getMotorOutputPercent();
  }

  public boolean getInverted() {
    return motor.getInverted();
  }

  public void set(double speed) {
    motor.set(TalonSRXControlMode.PercentOutput, speed);
  }

  public void setInverted(boolean isInverted) {
    motor.setInverted(isInverted);
  }

  public void stopMotor() {
    motor.set(TalonSRXControlMode.PercentOutput, 0);
  }
}
