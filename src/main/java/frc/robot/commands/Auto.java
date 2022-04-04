package frc.robot.commands;

import java.util.ArrayList;
import java.util.Iterator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.Variables;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.StorageSub;
import frc.robot.utils.AsyncTimer;
import frc.robot.utils.AutoCommand;
import frc.robot.utils.AutoFiles;
import frc.robot.utils.logger.Logger;

public class Auto extends CommandBase {
  private final Constants constants = Constants.getInstance();
  private final Variables variables = Variables.getInstance();
  private final AutoFacade autoFac = new AutoFacade();

  private final Drive driveCommand;
  private final IntakeRun intakeRunCommand;
  private final NavigationUpdate navigationUpdateCommand;
  private final StorageRun storageRunCommand;
  private final StorageRunFast storageRunFastCommand;
  private final StorageRunReverse storageRunReverseCommand;

  CommandScheduler scheduler = CommandScheduler.getInstance();
  private ArrayList<AutoCommand> commands = new ArrayList<>();
  private Iterator<AutoCommand> commandIterator;
  private AutoCommand currentCommand;
  private boolean isFinished = false;
  private AsyncTimer waitTimer;

  public Auto(Drive driveCommand, IntakeRun intakeRunCommand, NavigationUpdate navigationUpdateCommand,
      StorageRun storageRunCommand, StorageRunFast storageRunFastCommand, 
      StorageRunReverse storageRunReverseCommand) {
    this.driveCommand = driveCommand;
    this.intakeRunCommand = intakeRunCommand;
    this.navigationUpdateCommand = navigationUpdateCommand;
    this.storageRunCommand = storageRunCommand;
    this.storageRunFastCommand = storageRunFastCommand;
    this.storageRunReverseCommand = storageRunReverseCommand;
      
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
        autoFac.driveTank(currentCommand);
        nextCommand();
        break;
      case "driveArcade":                         //drive robot arcade
        autoFac.driveArcade(currentCommand);
        nextCommand();
        break;
      case "driveOff":                            //stop robot
        autoFac.driveOff();
        Logger.info("Auto : Drive Stop");
        nextCommand();
        break;
      case "storageRunSlow":                      //run storage slow
        autoFac.storageRunSlow();
        nextCommand();
        break;
      case "storageRunFast":                      //run storage fast
        autoFac.storageRunFast();
        nextCommand();
        break;
      case "storageRunReverse":                   //run storage in reverse
        autoFac.storageRunReverse();
        nextCommand();
        break;
      case "storageStop":                         //stop storage
        autoFac.storageStop();
        nextCommand();
        break;
      case "intakeRun":                           //run intake
        autoFac.intakeRun();
        nextCommand();
        break;
      case "intakeStop":                          //stop intake
        autoFac.intakeStop();
        nextCommand();
        break;
      case "wait":                                //wait for time
        if(autoFac.waitFor(currentCommand)){
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
    autoFac.storageStop();
    autoFac.intakeStop();
    autoFac.driveOff();
  }
}