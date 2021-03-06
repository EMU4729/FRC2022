package frc.robot;

import java.util.Optional;

import frc.robot.commands.AutoDriveArcade;
import frc.robot.commands.ClimberDown;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.DriveInvert;
import frc.robot.commands.AutoDriveStraight;
import frc.robot.commands.AutoDriveTank;
import frc.robot.commands.IntakeRun;
// import frc.robot.commands.NavigationUpdate;
import frc.robot.commands.StorageReverse;
import frc.robot.commands.StorageRun;
import frc.robot.commands.ShooterRun;

/**
 * Commands - Use this class to initialize and access commands globally.
 */
public class Commands {
  private static Optional<Commands> instance = Optional.empty();

  private Commands() {
    System.out.println("commands init");
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
  public final AutoDriveStraight autoDriveStraight = new AutoDriveStraight();
  public final AutoDriveArcade autoDriveArcade = new AutoDriveArcade();
  public final AutoDriveTank autoDriveTank = new AutoDriveTank();
  public final DriveInvert driveInvert = new DriveInvert();
  public final IntakeRun intakeRun = new IntakeRun();
  // public final NavigationUpdate navigationUpdate = new NavigationUpdate();
  public final StorageRun storageRun = new StorageRun();
  public final ShooterRun shooterRun = new ShooterRun();
  public final StorageReverse storageReverse = new StorageReverse();
}
