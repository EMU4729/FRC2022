package frc.robot.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.StorageSub;
import frc.robot.utils.AsyncTimer;
import frc.robot.utils.AutoCommand;
import frc.robot.utils.logger.Logger;

public class Auto extends CommandBase {
  private final Constants constants = Constants.getInstance();
  private final DriveSub drive;
  private final IntakeSub intake;
  private final StorageSub storage;

  private List<AutoCommand> commands = new ArrayList<>();
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
    try (BufferedReader br = new BufferedReader(new FileReader(constants.autoCommandsPath))) {
      String line;

      while ((line = br.readLine()) != null) {
        commands.add(new AutoCommand(line));
      }

      commandIterator = commands.iterator();
      currentCommand = commandIterator.next();
    } catch (FileNotFoundException e) {
      Logger.error("Auto : " + e.toString());
    } catch (IOException e) {
      Logger.error("Auto : " + e.toString());
    }
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
    switch (currentCommand.name) {
      case "driveTank":
        try {
          double leftSpeed = Double.parseDouble(currentCommand.args.get(0));
          double rightSpeed = Double.parseDouble(currentCommand.args.get(1));
          drive.tank(leftSpeed, rightSpeed);
        } catch (NumberFormatException e) {
          Logger.warn("Auto : Invalid double command args " + currentCommand.args);
        }
        nextCommand();
        break;
      case "driveArcade":
        try {
          double speed = Double.parseDouble(currentCommand.args.get(0));
          double steering = Double.parseDouble(currentCommand.args.get(1));
          drive.arcade(speed, steering);
        } catch (NumberFormatException e) {
          Logger.warn("Auto : Invalid double command args " + currentCommand.args);
        }
        nextCommand();
        break;
      case "driveOff":
        drive.off();
        nextCommand();
        break;
      case "storageRun":
        try {
          double speed = Double.parseDouble(currentCommand.args.get(0));
          storage.setConveyorSpeed(speed);
        } catch (NumberFormatException e) {
          Logger.warn("Auto : Invalid double command args " + currentCommand.args);
        }
        nextCommand();
        break;
      case "intakeRun":
        try {
          double speed = Double.parseDouble(currentCommand.args.get(0));
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
            waitTimer = new AsyncTimer(duration);
          } catch (NumberFormatException e) {
            Logger.warn("Auto : Invalid double command args " + currentCommand.args);
          }
          break;
        }
        if (waitTimer.isFinished()) {
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
    drive.off();
    intake.setSpinSpeed(0);
    storage.setConveyorSpeed(0);
  }
}
