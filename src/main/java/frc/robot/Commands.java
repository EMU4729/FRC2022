package frc.robot;

import java.util.Optional;

import frc.robot.commands.Auto;
import frc.robot.commands.ClimberDown;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.DriveInvert;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.NavigationUpdate;
import frc.robot.commands.StorageReverse;
import frc.robot.commands.StorageRun;
import frc.robot.commands.StorageShoot;
import frc.robot.commands.Teleop;

/**
 * Commands - Use this class to initialize and access commands globally.
 */
public class Commands {
  private static Optional<Commands> instance = Optional.empty();

  private Commands() {
  }

  public static Commands getInstance() {
    if (!instance.isPresent()) {
      instance = Optional.of(new Commands());
    }
    return instance.get();
  }

  public final ClimberDown climberDown = new ClimberDown();
  public final ClimberUp climberUp = new ClimberUp();
  // public final BallStopOpen ballStopOpenCommand = new
  // BallStopOpen(ballStopSub);
  // public final BallStopClose ballStopCloseCommand = new
  // BallStopClose(ballStopSub);
  public final Auto auto = new Auto();
  public final Teleop teleop = new Teleop();
  public final DriveInvert driveInvert = new DriveInvert();
  public final DriveStraight driveStraight = new DriveStraight();
  public final IntakeRun intakeRun = new IntakeRun();
  public final NavigationUpdate navigationUpdate = new NavigationUpdate();
  public final StorageRun storageRun = new StorageRun();
  public final StorageShoot storageShoot = new StorageShoot();
  public final StorageReverse storageReverse = new StorageReverse();
}
