package frc.robot.auto;

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

import frc.robot.Constants;
import frc.robot.utils.logger.Logger;

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
  public static ArrayList<AutoLine> updateAndGetAuto() {
    updateInternalAuto();
    return readInternalAuto();
  }

  /**
   * Updates the internal auto commands file from the USB.
   */
  public static void updateInternalAuto() {
    // fetches correct paths for the specific auto to be run
    String[] pathUSB = getAutoFilePaths(AutoFilePathLocation.USB);
    String[] pathInternalT = getAutoFilePaths(AutoFilePathLocation.Internal);
    String pathInternal;
    if (pathUSB != null && pathInternalT != null && pathInternalT.length >= 1) {
      pathInternal = pathInternalT[0];
    } else {
      return;
    }

    for (String pathString : pathUSB) {
      Path usbPath = Paths.get(pathString);
      Path internalPath = Paths.get(pathInternal);
      try {
        Files.copy(usbPath, internalPath, StandardCopyOption.REPLACE_EXISTING);
        break;
      } catch (IOException e) {
        Logger.warn("AutoFiles : USB -> Internal : Copy Failed : " + e.toString());
        continue;
      }
    }
  }

  /**
   * Reads and parses the internal auto command file.
   * 
   * @return The parsed auto command data.
   */
  public static ArrayList<AutoLine> readInternalAuto() {
    return readInternalAuto(constants.REPEAT_LIMIT_AUTO_READ);
  }

  public static ArrayList<AutoLine> readInternalAuto(int retries) {
    try {
      String[] pathInternalT = getAutoFilePaths(AutoFilePathLocation.Internal);
      String pathInternal;
      if (pathInternalT != null && pathInternalT.length >= 1) {
        pathInternal = pathInternalT[0];
      } else {
        throw new IOException("File Path Not Found");
      }

      BufferedReader br = new BufferedReader(new FileReader(pathInternal));
      String line;
      ArrayList<AutoLine> commands = new ArrayList<>();

      while ((line = br.readLine()) != null) {
        line.strip();
        if (line.isEmpty() || line.startsWith("#")) {
          continue;
        }
        commands.add(new AutoLine(line));
      }
      br.close();
      return commands;
    } catch (FileNotFoundException e) {
      if (retries > 0) {
        Logger.error("AutoFiles : Internal Storage Auto File Not Found : " + e +
            "reattemping : Try-" + (retries - constants.REPEAT_LIMIT_AUTO_READ) + " Of-"
            + constants.REPEAT_LIMIT_AUTO_READ);
        return readInternalAuto(retries - 1);
      } else {
        Logger.error("AutoFiles : Internal Storage Auto File Not Found : " + e + "Terminating : use Backup Auto");
        return readHardCoded();
      }
    } catch (IOException e) {
      if (retries > 0) {
        Logger.error("AutoFiles : Internal Storage Auto Read Error : " + e +
            "reattemping : Try-" + (retries - constants.REPEAT_LIMIT_AUTO_READ) + " Of-"
            + constants.REPEAT_LIMIT_AUTO_READ);
        return readInternalAuto(retries - 1);
      } else {
        Logger.error("AutoFiles : Internal Storage Auto Read Error : " + e + "Terminating : use Backup Auto");
        return readHardCoded();
      }
    }
  }

  /**
   * Gets the hardcoded auto commands. Only to be used if all else fails.
   * 
   * @return The hardcoded auto command data.
   */
  public static ArrayList<AutoLine> readHardCoded() {
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
            .map(line -> new AutoLine(line))
            .collect(Collectors.toList()));

  }

  /**
   * gets current use auto and adds appropriate filepath.
   * Todo : add systems for multiple auto
   * 
   * @param filePathLocation which file path location to use, see
   *                         {@link AutoFilePathLocation} for possible options
   * @return array of filepaths appended with autofile name
   */
  private static String[] getAutoFilePaths(AutoFilePathLocation filePathLocation) {
    String[] autoPaths;
    String fileName = constants.PATH_AUTO_FILE_NAME;

    switch (filePathLocation) {
      case USB:
        autoPaths = new String[constants.PATH_USB.length];
        for (int i = 0; i < autoPaths.length && i < constants.PATH_USB.length; i++) {
          autoPaths[i] = constants.PATH_USB[i] + fileName;
        }
        break;
      case Internal:
        autoPaths = new String[1];
        autoPaths[0] = constants.PATH_INTERNAL + fileName;
        break;
      default:
        Logger.error("Auto Command FilePath : FilePath does not exist : " + filePathLocation);
        return null;
    }
    return autoPaths;
  }

  /**
   * Represents all possible locations for the auto files
   */
  private static enum AutoFilePathLocation {
    USB,
    Internal
  }

}
