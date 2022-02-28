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
    String tempLogFileName = consts.FilePath + strDate + ".txt";
    File logFile = new File(tempLogFileName);

    try {
      for (int i = 1; !logFile.createNewFile(); i++) {
        if (i > 10) {
          fileCreationFailed = true;
          System.out.println("error : Log file creation failed : time out");
          break;
        }
        tempLogFileName = consts.FilePath + strDate + "_(" + i + ")" + ".txt";
        logFile = new File(tempLogFileName);
      }
    } catch (IOException e) {
      System.out.println("error : Log file creation failed : " + e.toString());
    }

    logFileName = tempLogFileName;
  }

  public static Logger getInstance() {
    if (!instance.isPresent()) {
      instance = Optional.of(new Logger());
    }
    return instance.get();
  }

  public static void header(String content) {
    Logger logger = getInstance();
    LogLine logLine = new LogLine(content, LogLevel.HEADER);
    System.out.println(logLine.toString());
    logger.logCache.add(logLine);
  }

  public static void info(String content) {
    Logger logger = getInstance();
    LogLine logLine = new LogLine(content, LogLevel.INFO);
    System.out.println(logLine.toString());
    logger.logCache.add(logLine);
  }

  public static void warn(String content) {
    Logger logger = getInstance();
    LogLine logLine = new LogLine(content, LogLevel.WARN);
    System.out.println(logLine.toString());
    logger.logCache.add(logLine);
  }

  public static void error(String content) {
    Logger logger = getInstance();
    LogLine logLine = new LogLine(content, LogLevel.ERROR);
    System.out.println(logLine.toString());
    logger.logCache.add(logLine);
  }

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
      System.out.println("error : write to log file failed : " + e.toString());
    }
  }
}
