package frc.robot.commands;

import java.util.ArrayList;
import java.util.Iterator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import frc.robot.Constants;
import frc.robot.Variables;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.StorageSub;
import frc.robot.utils.AsyncTimer;
import frc.robot.utils.AutoCommand;
import frc.robot.utils.AutoFiles;
import frc.robot.utils.logger.Logger;

public class AutoFacade{
  private final Constants constants = Constants.getInstance();
  private final Variables variables = Variables.getInstance();
  
  private final Drive driveCommand;
  private final IntakeRun intakeRunCommand;
  private final NavigationUpdate navigationUpdateCommand;
  private final StorageRun storageRunCommand;
  private final StorageRunFast storageRunFastCommand;
  private final StorageRunReverse storageRunReverseCommand;

  CommandScheduler scheduler = CommandScheduler.getInstance();
  private ArrayList<AutoCommand> commands = new ArrayList<>();
  private Iterator<AutoCommand> commandIterator;
  private boolean isFinished = false;
  private AsyncTimer waitTimer;

  public AutoFacade(Drive driveCommand, IntakeRun intakeRunCommand, NavigationUpdate navigationUpdateCommand,
      StorageRun storageRunCommand, StorageRunFast storageRunFastCommand, 
      StorageRunReverse storageRunReverseCommand) {
    this.driveCommand = driveCommand;
    this.intakeRunCommand = intakeRunCommand;
    this.navigationUpdateCommand = navigationUpdateCommand;
    this.storageRunCommand = storageRunCommand;
    this.storageRunFastCommand = storageRunFastCommand;
    this.storageRunReverseCommand = storageRunReverseCommand;
  }

  public void driveTank(AutoCommand currentCommand){
    try {
      double leftSpeed = Double.parseDouble(currentCommand.args.get(0));
      double rightSpeed = Double.parseDouble(currentCommand.args.get(1));
      Logger.info("Auto : DriveTank : Left Speed=" + leftSpeed + ", Right Speed=" + rightSpeed);
      drive.tank(leftSpeed * variables.AutoSpeedMultiplier, rightSpeed * constants.AutoSpeedMultiplier);
    } catch (NumberFormatException e) {
      Logger.warn("Auto : Invalid double command args " + currentCommand.args);
    }
  }
  public void driveArcade(AutoCommand currentCommand){
    try {
      double speed = Double.parseDouble(currentCommand.args.get(0));
      double steering = Double.parseDouble(currentCommand.args.get(1));
      Logger.info("Auto : DriveArcade : Speed=" + speed + ", steer=" + steering);

      // If needed, make auto speed multiplier also affects steering
      drive.arcade(speed * variables.AutoSpeedMultiplier, steering);
    } catch (NumberFormatException e) {
      Logger.warn("Auto : Invalid double command args " + currentCommand.args);
    }
  }
  public void driveOff(){

  }

  public void storageRunSlow(){
    storageStop();
    scheduler.schedule(true,storageRunCommand);
  }
  public void storageRunFast(){
    storageStop();
    scheduler.schedule(true,storageRunFastCommand);
  }
  public void storageRunReverse(){
    storageStop();
    scheduler.schedule(true,storageRunReverseCommand);
  }
  public void storageStop(){
    String stopped = "";
    if(storageRunCommand.isScheduled()){
        storageRunCommand.end(true);
        stopped = "Slow";
    } else if(storageRunFastCommand.isScheduled()){
        storageRunFastCommand.end(true);
        stopped = "Fast";
    } else if(storageRunReverseCommand.isScheduled()){
        storageRunReverseCommand.end(true);
        stopped = "Reverse";
    }
    Logger.info("Auto : Storage Stop : Stopped = "+stopped);
  }

  public void intakeRun(){  
    scheduler.schedule(true, intakeRunCommand);
  }
  public void intakeStop(){   
    Logger.info("Auto : Intake Stop");
    if(intakeRunCommand.isScheduled())
      intakeRunCommand.end(true);
  }

  public boolean waitFor(AutoCommand currentCommand){
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