package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Commands;
import frc.robot.Subsystems;
import frc.robot.utils.logger.Logger;
import frc.robot.utils.AsyncTimer;

public class AutoFacade {
  private final Subsystems subsystems = Subsystems.getInstance();
  private final Commands commands = Commands.getInstance();
  private final CommandScheduler scheduler = CommandScheduler.getInstance();

  private AsyncTimer waitTimer;

  public AutoFacade() {
  }

  public void driveTank(AutoLine currentCommand) {
    double leftSpeed = currentCommand.getDouble(0);
    double rightSpeed = currentCommand.getDouble(0);
    commands.autoDriveTank.leftSpeed = leftSpeed;
    commands.autoDriveTank.rightSpeed = rightSpeed;
    Logger.info("Auto : DriveTank : Left Speed=" + leftSpeed + ", Right Speed=" + rightSpeed);
    scheduler.schedule(true, commands.autoDriveTank);
  }

  public void driveArcade(AutoLine currentCommand) {
    double speed = currentCommand.getDouble(0);
    double steering = currentCommand.getDouble(1);
    commands.autoDriveArcade.speed = speed;
    commands.autoDriveArcade.steering = steering;
    Logger.info("Auto : DriveArcade : Speed=" + speed + ", steer=" + steering);

    // If needed, make auto speed multiplier also affects steering
    scheduler.schedule(true, commands.autoDriveArcade);
  }

  public void driveStraight(AutoLine currentCommand) {
    double speed = currentCommand.getDouble(0);
    commands.autoDriveStraight.speed = speed;
    commands.autoDriveArcade.steering = subsystems.navigation.getAngle();
    scheduler.schedule(true, commands.autoDriveStraight);
  }

  public void driveOff() {
    String stopped = "";
    if (commands.autoDriveArcade.isScheduled()) {
      commands.autoDriveArcade.end(true);
      stopped = "Arcade";
    } else if (commands.autoDriveTank.isScheduled()) {
      commands.autoDriveTank.end(true);
      stopped = "Tank";
    } else if (commands.autoDriveStraight.isScheduled()) {
      commands.autoDriveStraight.end(true);
      stopped = "Straight";
    }
    Logger.info("Auto : Drive Off : Stopped = " + stopped);
  }

  public void storageRun() {
    storageStop();
    scheduler.schedule(true, commands.storageRun);
  }

  public void storageShoot() {
    storageStop();
    scheduler.schedule(true, commands.storageShoot);
  }

  public void storageReverse() {
    storageStop();
    scheduler.schedule(true, commands.storageReverse);
  }

  public void storageStop() {
    String stopped = "none";
    if (commands.storageRun.isScheduled()) {
      commands.storageRun.end(true);
      stopped = "Slow";
    } else if (commands.storageShoot.isScheduled()) {
      commands.storageShoot.end(true);
      stopped = "Fast";
    } else if (commands.storageReverse.isScheduled()) {
      commands.storageReverse.end(true);
      stopped = "Reverse";
    }
    Logger.info("Auto : Storage Stop : Stopped = " + stopped);
  }

  public void intakeRun() {
    scheduler.schedule(true, commands.intakeRun);
  }

  public void intakeStop() {
    Logger.info("Auto : Intake Stop");
    if (commands.intakeRun.isScheduled())
      commands.intakeRun.end(true);
  }

  public boolean waitFor(AutoLine currentCommand) {
    if (waitTimer == null) {
      int duration = currentCommand.getInt(0);
      Logger.info("Auto : Wait For : Time=" + duration);
      waitTimer = new AsyncTimer(duration);
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