package frc.robot.structures;

import java.util.Arrays;
import java.util.List;

public class AutoCommand {
  public String name;
  public List<String> args;

  public AutoCommand(String line) {
    List<String> words = Arrays.asList(line.split("\\s*"));
    name = words.remove(0);
    args = words;
  }

}
