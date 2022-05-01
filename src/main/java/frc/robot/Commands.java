package frc.robot;

import java.util.Optional;

import frc.robot.commands.Auto;
import frc.robot.commands.AutoDriveArcade;
import frc.robot.commands.ClimberDown;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.DriveInvert;
import frc.robot.commands.AutoDriveStraight;
import frc.robot.commands.AutoDriveTank;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.NavigationUpdate;
import frc.robot.commands.StorageReverse;
import frc.robot.commands.StorageRun;
import frc.robot.commands.StorageShoot;
import frc.robot.commands.TeleopDrive;

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
  // public final BallStopOpen ballStopOpenCommand = new BallStopOpen(ballStopSub);
  // public final BallStopClose ballStopCloseCommand = new BallStopClose(ballStopSub);
  public final Auto auto = new Auto();
  public final AutoDriveStraight autoDriveStraight = new AutoDriveStraight();
  public final AutoDriveArcade autoDriveArcade = new AutoDriveArcade();
  public final AutoDriveTank autoDriveTank = new AutoDriveTank();
  public final TeleopDrive teleop = new TeleopDrive();
  public final DriveInvert driveInvert = new DriveInvert();
  public final IntakeRun intakeRun = new IntakeRun();
  public final NavigationUpdate navigationUpdate = new NavigationUpdate();
  public final StorageRun storageRun = new StorageRun();
  public final StorageShoot storageShoot = new StorageShoot();
  public final StorageReverse storageReverse = new StorageReverse();
}
