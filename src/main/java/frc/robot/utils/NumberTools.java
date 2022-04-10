package frc.robot.utils;

/**
 * Utility class to clamp values. Useful for making sure you don't accidentally
 * overwork a motor.
 */
public class NumberTools {

  /** limitRange <p>
   * Limits the max and min value of the input num
   * 
   * @param num number to be rationalised
   * @param min minimum allowed value for num
   * @param max maximum allowed value for num
   * @return
   */
  public static double limitRange(double num, double min, double max){
    if      (num>max) {return max;}
    else if (num<min) {return min;}
    return num;
  }

  /**limitRangeUnit <p>
   * Limits the max and min value of the input num to the Unit Interval (0 -> 1)
   * 
   * @param num
   * @return
   */
  public static double limitRangeUnit(double num){
    return limitRange(num,0,1);
  }

  /**limitRangeUnit <p>
   * Limits the max and min value of the input num to the absolute Unit Interval (-1 -> 1)
   * 
   * @param num
   * @return
   */
  public static double limitRangeAbsUnit(double num){
    return limitRange(num,-1,1);
  }

}
