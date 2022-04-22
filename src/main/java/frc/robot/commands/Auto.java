package frc.robot.commands;

import java.util.ArrayList;
import java.util.Iterator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.auto.AutoCommand;
import frc.robot.auto.AutoFacade;
import frc.robot.auto.AutoFiles;
import frc.robot.logger.Logger;
import frc.robot.utils.AsyncTimer;

public class Auto extends CommandBase {
  private final AutoFacade autoFacade;

  CommandScheduler scheduler = CommandScheduler.getInstance();
  private ArrayList<AutoCommand> commands = new ArrayList<>();
  private Iterator<AutoCommand> commandIterator;
  private AutoCommand currentCommand;
  private boolean isFinished = false;
  private AsyncTimer waitTimer;

  public Auto(AutoFacade autoFacade) {
    this.autoFacade = autoFacade;
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
      Logger.error("Auto : Command equal to Null");
      return;
    }
    switch (currentCommand.name) {
      case "driveTank":
        autoFacade.driveTank(currentCommand);
        nextCommand();
        break;
      case "driveArcade": // drive robot arcade
        autoFacade.driveArcade(currentCommand);
        nextCommand();
        break;
      case "driveOff": // stop robot
        autoFacade.driveOff();
        Logger.info("Auto : Drive Stop");
        nextCommand();
        break;
      case "storageRunSlow": // run storage slow
        autoFacade.storageRun();
        nextCommand();
        break;
      case "storageRunFast": // run storage fast
        autoFacade.storageShoot();
        nextCommand();
        break;
      case "storageRunReverse": // run storage in reverse
        autoFacade.storageReverse();
        nextCommand();
        break;
      case "storageStop": // stop storage
        autoFacade.storageStop();
        nextCommand();
        break;
      case "intakeRun": // run intake
        autoFacade.intakeRun();
        nextCommand();
        break;
      case "intakeStop": // stop intake
        autoFacade.intakeStop();
        nextCommand();
        break;
      case "wait": // wait for time
        if (autoFacade.waitFor(currentCommand)) {
          nextCommand();
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
    autoFacade.storageStop();
    autoFacade.intakeStop();
    autoFacade.driveOff();
  }
}