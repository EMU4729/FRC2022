package frc.robot.utils;

/**
 * Utility class to clamp values. Useful for making sure you don't accidentally
 * overwork a motor.
 */
public class Clamper {

  /**
   * Limits the max and min value of the input num
   * 
   * @param num number to be rationalised
   * @param min minimum allowed value for num
   * @param max maximum allowed value for num
   * @return the rationalised number
   */
  public static double clamp(double num, double min, double max) {
    if (num > max) {
      return max;
    } else if (num < min) {
      return min;
    }
    return num;
  }

  /**
   * Limits the max and min value of the input num to the Unit Interval (0 -> 1)
   * 
   * @param num number to be rationalised
   * @return the rationalised number
   */
  public static double unit(double num) {
    return clamp(num, 0, 1);
  }

  /**
   * Limits the max and min value of the input num to the absolute Unit Interval
   * (-1 -> 1)
   * 
   * @param num number to be rationalised
   * @return the rationalised number
   */
  public static double absUnit(double num) {
    return clamp(num, -1, 1);
  }

}
