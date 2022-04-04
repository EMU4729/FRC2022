package frc.robot.commands;

import java.util.ArrayList;
import java.util.Iterator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.StorageSub;
import frc.robot.utils.AsyncTimer;
import frc.robot.utils.AutoCommand;
import frc.robot.utils.AutoFiles;
import frc.robot.utils.logger.Logger;

public class Auto extends CommandBase {
  private final Constants constants = Constants.getInstance();
  private final DriveSub drive;
  private final IntakeSub intake;
  private final StorageSub storage;

  private ArrayList<AutoCommand> commands = new ArrayList<>();
  private Iterator<AutoCommand> commandIterator;
  private AutoCommand currentCommand;
  private boolean isFinished = false;
  private AsyncTimer waitTimer;

  public Auto(DriveSub drive, IntakeSub intake, StorageSub storage) {
    this.drive = drive;
    this.intake = intake;
    this.storage = storage;
    addRequirements(drive, intake, storage);
  }

  @Override
  public void initialize() {
    commands = AutoFiles.updateAndGetAuto();
    commandIterator = commands.iterator();
    currentCommand = commandIterator.next();
    Logger.info("Auto code read : Backup");
  }

  public void nextCommand() {
    if (!commandIterator.hasNext()) {
      isFinished = true;
      return;
    }
    currentCommand = commandIterator.next();
  }

  @Override
  public void execute() {
    if (currentCommand == null) {
      isFinished = true;
      Logger.error("Äuto : Command equal to Null");
      return;
    }
    switch (currentCommand.name) {
      case "driveTank":
        try {
          double leftSpeed = Double.parseDouble(currentCommand.args.get(0));
          double rightSpeed = Double.parseDouble(currentCommand.args.get(1));
          Logger.info("Auto : DriveTank : Left Speed=" + leftSpeed + ", Right Speed=" + rightSpeed);
          drive.tank(leftSpeed * constants.AutoSpeedMultiplier, rightSpeed * constants.AutoSpeedMultiplier);
        } catch (NumberFormatException e) {
          Logger.warn("Auto : Invalid double command args " + currentCommand.args);
        }
        nextCommand();
        break;
      case "driveArcade":
        try {
          double speed = Double.parseDouble(currentCommand.args.get(0));
          double steering = Double.parseDouble(currentCommand.args.get(1));
          Logger.info("Auto : DriveArcade : Speed=" + speed + ", steer=" + steering);

          // If needed, make auto speed multiplier also affects steering
          drive.arcade(speed * constants.AutoSpeedMultiplier, steering);
        } catch (NumberFormatException e) {
          Logger.warn("Auto : Invalid double command args " + currentCommand.args);
        }
        nextCommand();
        break;
      case "driveOff":
        drive.off();
        Logger.info("Auto : Drive Stop");
        nextCommand();
        break;
      case "storageRun":
        try {
          double speed = Double.parseDouble(currentCommand.args.get(0));
          Logger.info("Auto : Storage Run : Speed=" + speed);
          storage.setConveyorSpeed(speed);
        } catch (NumberFormatException e) {
          Logger.warn("Auto : Invalid double command args " + currentCommand.args);
        }
        nextCommand();
        break;
      case "intakeRun":
        try {
          double speed = Double.parseDouble(currentCommand.args.get(0));
          Logger.info("Auto : Intake Run : Speed=" + speed);
          intake.setSpinSpeed(speed);
        } catch (NumberFormatException e) {
          Logger.warn("Auto : Invalid double command args " + currentCommand.args);
        }
        nextCommand();
        break;
      case "wait":
        if (waitTimer == null) {
          try {
            int duration = Integer.parseInt(currentCommand.args.get(0));
            Logger.info("Auto : Wait For : Time=" + duration);
            waitTimer = new AsyncTimer(duration);
          } catch (NumberFormatException e) {
            Logger.warn("Auto : Invalid double command args " + currentCommand.args);
          }
          break;
        }
        if (waitTimer.isFinished()) {
          Logger.info("Auto : Wait For : Finished");
          waitTimer = null;
          nextCommand();
          break;
        }
        break;
      default:
        Logger.warn("Auto : Invalid command name " + currentCommand.name);
        nextCommand();
        break;
    }
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }

  @Override
  public void end(boolean interrupted) {
    Logger.info("Auto code : End");
    drive.off();
    intake.setSpinSpeed(0);
    storage.setConveyorSpeed(0);
  }
}