package frc.robot.auto;

import java.util.Arrays;
import java.util.List;

/**
 * Utility data structure for parsing auto command lines.
 */
public class AutoLine {
  public String name;
  public List<String> args;

  /**
   * Creates a new AutoCommand.
   * 
   * @param line The auto command line.
   */
  public AutoLine(String line) {
    List<String> words = Arrays.asList(line.split("\\s*"));
    name = words.remove(0);
    args = words;
  }

}
