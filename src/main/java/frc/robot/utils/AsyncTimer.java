package frc.robot.utils;

import java.time.Duration;
import java.time.Instant;

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
    this.start = Instant.now();
    this.duration = duration;
  }

  /**
   * Returns the number of milliseconds until the timer has finished.
   * 
   * @return The number of ms until the timer has finished.
   */
  public long timeUntilFinished() {
    return Duration.between(start, Instant.now()).toMillis();
  }

  /**
   * Checks if the timer has finished.
   * 
   * @return true if the timer's duration has been elapsed and false if not.
   */
  public boolean isFinished() {
    return timeUntilFinished() >= duration;
  }
}
