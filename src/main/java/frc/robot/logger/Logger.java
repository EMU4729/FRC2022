package frc.robot.logger;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
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
  private final ReentrantLock saveLock = new ReentrantLock();
  private final String logFileName;
  private final Constants constants = Constants.getInstance();
  private boolean fileCreationFailed = false;
  private boolean logPause = false;

  private Logger() {
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
    String strDate = dateFormat.format(date);
    String tempLogFileName = constants.PATH_USB + strDate + ".txt";
    File logFile = new File(tempLogFileName);

    try {
      for (int i = 1; !logFile.createNewFile(); i++) {
        if (i > constants.REPEAT_LIMIT_LOGGER_CREATION) {
          fileCreationFailed = true;
          System.out.println("error : Log file creation failed : time out");
          break;
        }
        tempLogFileName = constants.PATH_USB + strDate + "_(" + i + ")" + ".txt";
        logFile = new File(tempLogFileName);
      }
    } catch (IOException e) {
      System.out.println(new LogLine("error : Log file creation failed : " + e.toString(), LogLevel.WARN).toString());
      fileCreationFailed = true;
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
    LogLine logLine = new LogLine(content, LogLevel.HEADER);
    System.out.println(logLine.toString());
    addLine(logLine);
  }

  public static void info(String content) {
    LogLine logLine = new LogLine(content, LogLevel.INFO);
    addLine(logLine);
  }

  public static void warn(String content) {
    LogLine logLine = new LogLine(content, LogLevel.WARN);
    addLine(logLine);
  }

  public static void error(String content) {
    LogLine logLine = new LogLine(content, LogLevel.ERROR);
    System.out.println(logLine.toString());
    addLine(logLine);
  }

  private static void addLine(LogLine line) {
    Logger logger = getInstance();
    logger.saveLock.lock();
    logger.logCache.add(line);
    logger.saveLock.unlock();

  }

  public void initializeSaveThread() {
    new Thread(() -> {
      try {
        // TODO: Implement interrupting the thread
        while (true) {
          if (!logPause)
            save();
          Thread.sleep(5);
        }
      } catch (InterruptedException e) {
        Logger.error("Logger : Save thread interrupted : " + e);
      }
    }).start();
  }

  /**
   * pause the logger temporarily
   */
  public void pause() {
    logPause = true;
    save();
  }

  /**
   * restart the logger
   */
  public void unPause() {
    logPause = false;
  }

  public void save() {
    saveLock.lock();
    if (logCache.isEmpty() || fileCreationFailed) {
      if (!logCache.isEmpty() && fileCreationFailed) {
        while (!logCache.isEmpty()) {
          System.out.println(logCache.remove(0).toString());
        }
      }
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
    } finally {
      saveLock.unlock();
    }
  }
}
