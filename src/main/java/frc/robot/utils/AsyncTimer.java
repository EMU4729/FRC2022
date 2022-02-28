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
   * Checks if the timer has finished.
   * 
   * @return true if the timer's duration has been elapsed and false if not.
   */
  public boolean isFinished() {
    return Duration.between(start, Instant.now()).toMillis() >= duration;
  }
}
