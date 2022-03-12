package frc.robot.utils;

import java.time.Duration;
import java.time.Instant;

import frc.robot.utils.logger.Logger;

/**
 * Class to help with timing things asynchronously.
 */
public class AsyncTimer {
  private Instant start;
  private int duration;

  /**
   * Creates a new AsyncTimer and starts it.
   * 
   * @param duration
   */
  public AsyncTimer(int duration) {
    Logger.info("AsyncTimer : Start : Duration="+duration);
    this.start = Instant.now();
    this.duration = duration;
  }

  /**
   * Checks if the timer has finished.
   * 
   * @return true if the timer's duration has been elapsed and false if not.
   */
  public boolean isFinished() {
    Logger.info("AsyncTimer : End");
    return Duration.between(start, Instant.now()).toMillis() >= duration;
  }
}
