package frc.robot.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import frc.robot.utils.logger.Logger;
import frc.robot.Constants;

/**
 * Utility class for handling all auto command files.
 */
public class AutoFiles {
  public static final Constants constants = Constants.getInstance();

  /**
   * Updates internal auto command files from USB and parses them.
   * 
   * @return The parsed auto command data
   */
  public static ArrayList<AutoCommand> updateAndGetAuto() {
    updateInternalAuto();
    return readInternalAuto();
  }

  /**
   * Updates the internal auto commands file from the USB.
   */
  public static void updateInternalAuto() {
    for (String pathString : constants.autoUsbPaths) {
      Path usbPath = Paths.get(pathString);
      Path internalPath = Paths.get(constants.autoInternalPath);
      try {
        Files.copy(usbPath, internalPath, StandardCopyOption.REPLACE_EXISTING);
        break;
      } catch (IOException e) {
        Logger.warn("AutoFiles : " + e.toString());
        continue;
      }
    }
  }

  /**
   * Reads and parses the internal auto command file.
   * 
   * @return The parsed auto command data.
   */
  public static ArrayList<AutoCommand> readInternalAuto() {
    try {
      BufferedReader br = new BufferedReader(new FileReader(constants.autoInternalPath));
      String line;
      ArrayList<AutoCommand> commands = new ArrayList<>();

      while ((line = br.readLine()) != null) {
        line.strip();
        if (line.isEmpty() || line.startsWith("#")) {
          continue;
        }
        commands.add(new AutoCommand(line));
      }
      return commands;
    } catch (FileNotFoundException e) {
      Logger.error("AutoFiles : Internal Storage Auto File Not Found : " + e);
      return readHardCoded();
    } catch (IOException e) {
      Logger.error("AutoFiles : Internal Storage Auto Read Error : " + e);
      return readHardCoded();
    }
  }

  /**
   * Gets the hardcoded auto commands. Only to be used if all else fails.
   * 
   * @return The hardcoded auto command data.
   */
  public static ArrayList<AutoCommand> readHardCoded() {
    return new ArrayList<>(
        Arrays.asList(
            "driveArcade -0.7 0",
            "intakeRun 0.5",
            "wait 1000",
            "storageRun 0.5",
            "wait 700",
            "storageRun 0",
            "intakeRun 0",
            "driveArcade 0.7 0",
            "wait 2800",
            "storageRun 1",
            "wait 1000",
            "driveOff",
            "storageRun 0")
            .stream()
            .map(line -> new AutoCommand(line))
            .collect(Collectors.toList()));

  }

}
