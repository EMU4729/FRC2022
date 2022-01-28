package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.structures.TalonSRXMotorController;

/**
 * Storage Subsystem.
 * Handles the ball conveyor belt and scoring into the low goal.
 */
public class StorageSub extends SubsystemBase {
  private final TalonSRXMotorController motor = new TalonSRXMotorController(Constants.conveyorMotorPort);

  @Override
  public void periodic() {
  }

  /**
   * Sets the conveyor speed.
   * 
   * @param speed Speed between -1 and 1
   */
  public void setConveyorSpeed(double speed) {
    motor.set(speed);
  }
}
