package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Commands;
import frc.robot.Subsystems;
import frc.robot.Variables;
import frc.robot.logger.Logger;
import frc.robot.utils.AsyncTimer;

public class AutoFacade {
  private final Variables variables = Variables.getInstance();
  private final Subsystems subsystems = Subsystems.getInstance();
  private final Commands commands = Commands.getInstance();
  private final CommandScheduler scheduler = CommandScheduler.getInstance();

  private AsyncTimer waitTimer;

  public AutoFacade() {
  }

  public void driveTank(AutoLine currentCommand) {
    try {
      double leftSpeed = Double.parseDouble(currentCommand.args.get(0));
      double rightSpeed = Double.parseDouble(currentCommand.args.get(1));
      Logger.info("Auto : DriveTank : Left Speed=" + leftSpeed + ", Right Speed=" + rightSpeed);
      subsystems.drive.tank(leftSpeed * variables.autoSpeedMultiplier, rightSpeed * variables.autoSpeedMultiplier);
    } catch (NumberFormatException e) {
      Logger.warn("Auto : Invalid double command args " + currentCommand.args);
    }
  }

  public void driveArcade(AutoLine currentCommand) {
    try {
      double speed = Double.parseDouble(currentCommand.args.get(0));
      double steering = Double.parseDouble(currentCommand.args.get(1));
      Logger.info("Auto : DriveArcade : Speed=" + speed + ", steer=" + steering);

      // If needed, make auto speed multiplier also affects steering
      subsystems.drive.arcade(speed * variables.autoSpeedMultiplier, steering);
    } catch (NumberFormatException e) {
      Logger.warn("Auto : Invalid double command args " + currentCommand.args);
    }
  }

  public void driveStraight(AutoLine currentCommand) {
    try {
      double speed = Double.parseDouble(currentCommand.args.get(0));

    } catch (NumberFormatException e) {
      Logger.warn("Auto : Invalid double command args " + currentCommand.args);
    }
  }

  public void driveOff() {
    subsystems.drive.off();
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
    String stopped = "";
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