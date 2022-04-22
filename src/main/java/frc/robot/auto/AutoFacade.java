package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Variables;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.NavigationUpdate;
import frc.robot.commands.StorageReverse;
import frc.robot.commands.StorageRun;
import frc.robot.commands.StorageShoot;
import frc.robot.logger.Logger;
import frc.robot.subsystems.DriveSub;
import frc.robot.utils.AsyncTimer;

public class AutoFacade {
  private final Variables variables = Variables.getInstance();

  private final DriveSub driveSub;
  private final IntakeRun intakeRunCommand;
  private final StorageRun storageRunCommand;
  private final StorageShoot storageShootCommand;
  private final StorageReverse storageReverseCommand;

  CommandScheduler scheduler = CommandScheduler.getInstance();
  private AsyncTimer waitTimer;

  public AutoFacade(DriveSub driveSub, IntakeRun intakeRunCommand, NavigationUpdate navigationUpdateCommand,
      StorageRun storageRunCommand, StorageShoot storageRunFastCommand,
      StorageReverse storageRunReverseCommand) {
    this.driveSub = driveSub;
    this.intakeRunCommand = intakeRunCommand;
    this.storageRunCommand = storageRunCommand;
    this.storageShootCommand = storageRunFastCommand;
    this.storageReverseCommand = storageRunReverseCommand;
  }

  public void driveTank(AutoCommand currentCommand) {
    try {
      double leftSpeed = Double.parseDouble(currentCommand.args.get(0));
      double rightSpeed = Double.parseDouble(currentCommand.args.get(1));
      Logger.info("Auto : DriveTank : Left Speed=" + leftSpeed + ", Right Speed=" + rightSpeed);
      driveSub.tank(leftSpeed * variables.autoSpeedMultiplier, rightSpeed * variables.autoSpeedMultiplier);
    } catch (NumberFormatException e) {
      Logger.warn("Auto : Invalid double command args " + currentCommand.args);
    }
  }

  public void driveArcade(AutoCommand currentCommand) {
    try {
      double speed = Double.parseDouble(currentCommand.args.get(0));
      double steering = Double.parseDouble(currentCommand.args.get(1));
      Logger.info("Auto : DriveArcade : Speed=" + speed + ", steer=" + steering);

      // If needed, make auto speed multiplier also affects steering
      driveSub.arcade(speed * variables.autoSpeedMultiplier, steering);
    } catch (NumberFormatException e) {
      Logger.warn("Auto : Invalid double command args " + currentCommand.args);
    }
  }

  public void driveOff() {

  }

  public void storageRun() {
    storageStop();
    scheduler.schedule(true, storageRunCommand);
  }

  public void storageShoot() {
    storageStop();
    scheduler.schedule(true, storageShootCommand);
  }

  public void storageReverse() {
    storageStop();
    scheduler.schedule(true, storageReverseCommand);
  }

  public void storageStop() {
    String stopped = "";
    if (storageRunCommand.isScheduled()) {
      storageRunCommand.end(true);
      stopped = "Slow";
    } else if (storageShootCommand.isScheduled()) {
      storageShootCommand.end(true);
      stopped = "Fast";
    } else if (storageReverseCommand.isScheduled()) {
      storageReverseCommand.end(true);
      stopped = "Reverse";
    }
    Logger.info("Auto : Storage Stop : Stopped = " + stopped);
  }

  public void intakeRun() {
    scheduler.schedule(true, intakeRunCommand);
  }

  public void intakeStop() {
    Logger.info("Auto : Intake Stop");
    if (intakeRunCommand.isScheduled())
      intakeRunCommand.end(true);
  }

  public boolean waitFor(AutoCommand currentCommand) {
    if (waitTimer == null) {
      try {
        int duration = Integer.parseInt(currentCommand.args.get(0));
        Logger.info("Auto : Wait For : Time=" + duration);
        waitTimer = new AsyncTimer(duration);

      } catch (NumberFormatException e) {
        Logger.warn("Auto : Invalid double command args " + currentCommand.args);
      }
      return false;
    }
    if (waitTimer.isFinished()) {
      Logger.info("Auto : Wait For : Finished");
      waitTimer = null;
      return true;
    }
    return false;
  }
}