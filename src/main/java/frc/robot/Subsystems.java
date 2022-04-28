package frc.robot;

import java.util.Optional;

import frc.robot.subsystems.ClimberSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.NavigationSub;
import frc.robot.subsystems.StorageSub;

/**
 * Subsystems - Use this class to initialize and access all subsystems globally.
 */
public class Subsystems {
  private static Optional<Subsystems> instance = Optional.empty();

  private Subsystems() {
  }

  public static Subsystems getInstance() {
    if (!instance.isPresent()) {
      instance = Optional.of(new Subsystems());
    }
    return instance.get();
  }

  public final DriveSub drive = new DriveSub();
  public final ClimberSub climber = new ClimberSub();
  public final IntakeSub intake = new IntakeSub();
  public final NavigationSub navigation = new NavigationSub();
  public final StorageSub storage = new StorageSub();
  // public final BallStopSub ballStop = new BallStopSub();
}
