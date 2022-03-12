package frc.robot.utils.logger;

import edu.wpi.first.wpilibj.Timer;

/**
 * Represents a line in the logger.
 */
public class LogLine {
  private final Level level;
  private final String content;

  /**
   * Enum for all possible levels of a {@link LogLine}
   */
  public static enum Level {
    HEADER,
    INFO,
    WARN,
    ERROR
  }

  /**
   * LogLine constructor
   * 
   * @param content The content of the log
   * @param level   The log level (e.g. info, warning, error)
   * @return LogLine instance
   */
  public LogLine(String content, Level level) {
    this.level = level;
    this.content = content;
  }

  /**
   * Converts the LogLine to a string that can be written to a log file.
   * 
   * @return Formatted LogLine string
   */
  public String toString() {
    String levelString = "[" + level.toString() + "]";
    String timeString = String.valueOf((int) (Timer.getFPGATimestamp()));

    return String.format("%s %s %s", levelString, timeString, content);
  }

}