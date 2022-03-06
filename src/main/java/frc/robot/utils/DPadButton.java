package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;

public class DPadButton extends Button {
  private Joystick joystick;
  private Direction direction;

  public static enum Direction {
    UP(0),
    RIGHT(90),
    DOWN(180),
    LEFT(270);

    public int value;

    private Direction(int value) {
      this.value = value;
    }
  }

  public boolean get() {
    int input = joystick.getPOV();
    return (input == direction.value)
        || (input == (direction.value + 45) % 360)
        || (input == (direction.value + 315) % 360);
  }
}
