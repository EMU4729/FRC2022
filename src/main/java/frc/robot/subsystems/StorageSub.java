package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

// import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.Clamper;

/**
 * Storage Subsystem.
 * Handles the ball conveyor belt and scoring into the low goal.
 */
public class StorageSub extends SubsystemBase {
  private final Constants constants = Constants.getInstance();
  private final WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(constants.STORAGE_CONVEYER_MOTOR_PORT);
  private final WPI_TalonSRX shooterMotor = new WPI_TalonSRX(constants.STORAGE_SHOOTER_MOTOR_PORT);
  private final ColorSensorV3 bottomColorSensor = new ColorSensorV3(constants.BOTTOM_COLOR_SENSOR_PORT);
  private final ColorSensorV3 topColorSensor = new ColorSensorV3(constants.STORAGE_SENSOR_COLOR_TOP);
  // private final DigitalInput limitSwitch = new
  // DigitalInput(constants.LimitSwitchChannel);

  public final Color teamColor;
  public final Color oppColor;

  public StorageSub() {
    Alliance alliance = DriverStation.getAlliance();
    if (alliance == Alliance.Red) {
      teamColor = Color.kRed;
      oppColor = Color.kBlue;
    } else {
      teamColor = Color.kBlue;
      oppColor = Color.kRed;
    }
  }

  @Override
  public void periodic() {
  }

  /**
   * Converts from {@link StorageColorSensor} to {@link ColorSensorV3}.
   * 
   * @param location The StorageColorSensor.
   * @return The ColorSensorV3 instance.
   */
  private ColorSensorV3 getColorSensor(ColorSensor location) {
    switch (location) {
      case TOP:
        return topColorSensor;
      case BOTTOM:
        return bottomColorSensor;
      default:
        throw new Error("Impossible scenario - this will never happen");
    }
  }

  /**
   * Sets the conveyor speed.
   * 
   * @param speed Speed between -1 and 1
   */
  public void setConveyorSpeed(double speed) {
    speed = Clamper.absUnit(speed);
    conveyorMotor.set(speed);
  }

  /**
   * Sets the shooter speed.
   * 
   * @param speed Speed between -1 and 1
   */
  public void setShooterSpeed(double speed) {
    speed = Clamper.absUnit(speed);
    shooterMotor.set(speed);
  }

  /**
   * Gets the current color detected by the given color sensor.
   * 
   * @param location The color sensor to use.
   * @return The color detected by the color sensor.
   */
  public Color getColor(ColorSensor location) {
    return getColorSensor(location).getColor();
  }

  /**
   * Gets the current ball type detected by the given color sensor.
   * 
   * @param location
   * @return The ball type detected by the color sensor as a {@link BallType}.
   */
  public BallType getBall(ColorSensor location) {
    Color ballColor = getColor(location);
    if (ballColor == teamColor) {
      return BallType.TEAM;
    }
    if (ballColor == oppColor) {
      return BallType.OPP;
    }
    return BallType.NONE;
  }

  /** Enum representing storage color sensor positions */
  public enum ColorSensor {
    TOP,
    BOTTOM
  }

  /** Enum representing ball colors */
  public enum BallType {
    TEAM,
    OPP,
    NONE
  }
}