package frc.robot.utils.logger;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Date;
import java.util.Calendar;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import frc.robot.Constants;

/**
 * Handles robot-wide logging.
 */
public class Logger {
  private static Optional<Logger> instance = Optional.empty();
  private ArrayList<LogLine> logCache = new ArrayList<LogLine>();
  private final String logFileName;
  private final Constants consts = Constants.getInstance();
  private boolean fileCreationFailed = false;

  private Logger() {
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
    String strDate = dateFormat.format(date);
    String tempLogFileName = consts.driveLetter + strDate + ".txt";
    File logFile = new File(tempLogFileName);

    try {
      for (int i = 1; !logFile.createNewFile(); i++) {
        if (i > 10) {
          fileCreationFailed = true;
          System.out.println(
              new LogLine("Logfile creation failed - timeout", LogLine.Level.ERROR).toString());
          break;
        }
        tempLogFileName = consts.driveLetter + strDate + "_(" + i + ")" + ".txt";
        logFile = new File(tempLogFileName);
      }
    } catch (IOException e) {
      fileCreationFailed = true;
      System.out.println(
          new LogLine("Logfile creation failed: " + e.toString(), LogLine.Level.ERROR).toString());
    }

    logFileName = tempLogFileName;
  }

  /**
   * Gets the logger instance.
   * 
   * @return A {@link Logger} instance.
   */
  public static Logger getInstance() {
    if (!instance.isPresent()) {
      instance = Optional.of(new Logger());
    }
    return instance.get();
  }

  /**
   * Logs a line with the HEADER level.
   * 
   * @param content The content of the log.
   */
  public static void header(String content) {
    Logger logger = getInstance();
    LogLine logLine = new LogLine(content, LogLine.Level.HEADER);
    System.out.println(logLine.toString());
    logger.logCache.add(logLine);
  }

  /**
   * Logs a line with the INFO level.
   * 
   * @param content The content of the log.
   */
  public static void info(String content) {
    Logger logger = getInstance();
    LogLine logLine = new LogLine(content, LogLine.Level.INFO);
    System.out.println(logLine.toString());
    logger.logCache.add(logLine);
  }

  /**
   * Logs a line with the WARN level.
   * 
   * @param content The content of the log.
   */
  public static void warn(String content) {
    Logger logger = getInstance();
    LogLine logLine = new LogLine(content, LogLine.Level.WARN);
    System.out.println(logLine.toString());
    logger.logCache.add(logLine);
  }

  /**
   * Logs a line with the ERROR level.
   * 
   * @param content The content of the log.
   */
  public static void error(String content) {
    Logger logger = getInstance();
    LogLine logLine = new LogLine(content, LogLine.Level.ERROR);
    System.out.println(logLine.toString());
    logger.logCache.add(logLine);
  }

  /**
   * Persists the log to a file on the RoboRIO.
   */
  public void save() {
    if (logCache.isEmpty() || fileCreationFailed) {
      return;
    }
    try {
      FileWriter logWriter = new FileWriter(logFileName, true);
      while (!logCache.isEmpty()) {
        logWriter.write(logCache.get(0).toString());
        logWriter.write(System.getProperty("line.separator"));
        logCache.remove(0);
      }
      logWriter.close();
    } catch (IOException e) {
      System.out.println(
          new LogLine("Writing to logfile failed: " + e.toString(), LogLine.Level.ERROR).toString());
    }
  }
}
