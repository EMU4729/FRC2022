package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.BallType;

/**
 * Storage Subsystem.
 * Handles the ball conveyor belt and scoring into the low goal.
 */
public class StorageSub extends SubsystemBase {
  private final Constants constants = Constants.getInstance();
  private final WPI_TalonSRX motor = new WPI_TalonSRX(constants.conveyorMotorPort);
  private final ColorSensorV3 bottomColorSensor = new ColorSensorV3(constants.bottomColorSensorPort); // TODO: Do stuff
  private final ColorSensorV3 topColorSensor = new ColorSensorV3(constants.topColorSensorPort);
  private final DigitalInput limitSwitch = new DigitalInput(constants.limitSwitchChannel);

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
   * Sets the conveyor speed.
   * 
   * @param speed Speed between -1 and 1
   */
  public void setConveyorSpeed(double speed) {
    motor.set(speed);
  }

  /**
   * Gets the current color detected by the top color sensor.
   * 
   * @return The color detected by the color sensor.
   */
  public Color getTopColor() {
    return topColorSensor.getColor();
  }

  /**
   * Gets the current color detected by the bottom color sensor.
   * 
   * @return The color detected by the color sensor.
   */
  public Color getBottomColor() {
    return bottomColorSensor.getColor();
  }

  /**
   * Gets the current ball type detected by the top color sensor.
   * 
   * @return The ball type detected by the color sensor as a {@link BallType}.
   */
  public BallType getTopBall() {
    Color ballColor = getTopColor();
    if (ballColor == teamColor) {
      return BallType.TEAM;
    }
    if (ballColor == oppColor) {
      return BallType.OPP;
    }
    return BallType.NONE;
  }

  /**
   * Gets the current ball type detected by the bottom color sensor.
   * 
   * @return The ball type detected by the color sensor as a {@link BallType}.
   */
  public BallType getBottomBall() {
    Color ballColor = getBottomColor();
    if (ballColor == teamColor) {
      return BallType.TEAM;
    }
    if (ballColor == oppColor) {
      return BallType.OPP;
    }
    return BallType.NONE;
  }
}