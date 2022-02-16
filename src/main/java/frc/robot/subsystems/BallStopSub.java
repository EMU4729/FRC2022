package frc.robot.subsystems;

import java.time.Duration;
import java.time.Instant;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallStopSub extends SubsystemBase {
  private final WPI_TalonSRX motor = new WPI_TalonSRX(Constants.ballStopMotorPort);
  private boolean isOpen = false;
  private Instant timer = Instant.now();
  private boolean isToggling = false;
  private boolean isMoving = false;

  @Override
  public void periodic() {
    // Nothing is happening
    if (!isToggling) {
      return;
    }

    // Moving has finished
    if (isMoving && Duration.between(timer, Instant.now()).toMillis() >= 1000) {
      motor.set(0);
      isToggling = false;
      isOpen = !isOpen;
      isMoving = false;
      return;
    }

    // is toggling and moving has started (i.e. timer has started)
    if (isMoving) {
      return;
    }

    // is toggling but is not moving (i.e. timer hasn't started)
    timer = Instant.now();
    if (isOpen) {
      motor.set(0.5);
    } else {
      motor.set(-0.5);
    }
    isMoving = true;
  }

  /**
   * Sets the speed of the ball stop motor
   * 
   * @param speed Speed between -1 and 1
   */
  public void set(double speed) {
    motor.set(speed);
  }

  /**
   * Toggles the ball stop.
   */
  public void toggle() {
    isToggling = true;
  }

}
