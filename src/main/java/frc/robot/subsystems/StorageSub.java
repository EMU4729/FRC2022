package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Storage Subsystem.
 * Handles the ball conveyor belt and scoring into the low goal.
 */
public class StorageSub extends SubsystemBase {
  private final WPI_TalonSRX motor = new WPI_TalonSRX(Constants.conveyorMotorPort);
  private final ColorSensorV3 colorSensor = new ColorSensorV3(Constants.colorSensorPort); // TODO: Do stuff with this

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

  /**
   * Gets the current color detected by the color sensor.
   * 
   * @return The color detected by the color sensor.
   */
  public Color getColor() {
    return colorSensor.getColor();
  }
}
