package frc.robot.utils;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import frc.robot.utils.logger.Logger;

/**
 * Utility data structure for parsing auto command lines.
 */
public class AutoCommand {
  public String name;
  public List<String> args;

  /**
   * Creates a new AutoCommand.
   * 
   * @param line The auto command line.
   */
  public AutoCommand(String line) {
    Logger.info("auto command get line : Start");

    List<String> words = Arrays.asList(line.split("\\s"));
    words = new ArrayList<String>(words);

    Logger.info("words length : "+words.size()+" words = "+words.toString());

    try {
      name = words.remove(0);
    } catch(UnsupportedOperationException e){
      Logger.error("AutoCommand : Tried To Remove From Unsuported List : "+e);
    } catch(IndexOutOfBoundsException e){
      Logger.error("AutoCommand : index to remove not found : "+e);
    }
    args = words;
    
    Logger.info("auto command get line : Finish");
  }

}
