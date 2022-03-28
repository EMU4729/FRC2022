package frc.robot.utils;

/**
 * Utility class to clamp values. Useful for making sure you don't accidentally
 * overwork a motor.
 */
public class Clamper {
  public final double min;
  public final double max;

  /**
   * Makes a new clamper instance.
   * 
   * @param min The minimum clamp value
   * @param max The maximum clamp value
   */
  public Clamper(double min, double max) {
    this.min = min;
    this.max = max;
  }

  /**
   * Clamps a value to the bounds specified when the clamper instance was
   * constructed
   * 
   * @param value The value to be clamped
   * @return The clamped value
   */
  public double clamp(double value) {
    return clamp(value, min, max);
  }

  /**
   * Clamps a value to the bounds specified
   * 
   * @param value The value to be clamped
   * @param min   The lower bound for the clamp
   * @param max   The upper bound for the clamp
   * @return The clamped value
   */
  public static double clamp(double value, double min, double max) {
    if (value < min) {
      return min;
    }
    if (value > max) {
      return max;
    }
    return value;
  }

}
