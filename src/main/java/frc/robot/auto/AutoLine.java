package frc.robot.auto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import frc.robot.utils.logger.Logger;

/**
 * Utility data structure for parsing auto command lines.
 */
public class AutoLine {
  public String name;
  public ArrayList<String> args;

  /**
   * Creates a new AutoCommand.
   * 
   * @param line The auto command line.
   */
  public AutoLine(String line) {
    ArrayList<String> words = new ArrayList<String>(Arrays.asList(line.split("\\s*")));
    name = words.remove(0);
    args = words;
  }

  public double getDouble(int index) {
    try {
      return Double.parseDouble(args.get(index));
    } catch (NumberFormatException e) {
      Logger.warn("Auto : Invalid double arg : " + args.get(index));
      return 0;
    }
  }

  public int getInt(int index) {
    try {
      return Integer.parseInt(args.get(index));
    } catch (NumberFormatException e) {
      Logger.warn("Auto : Invalid int arg : " + args.get(index));
      return 0;
    }
  }
}
