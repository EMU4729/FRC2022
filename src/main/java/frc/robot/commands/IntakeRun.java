package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.StorageSub;

public class IntakeRun extends CommandBase {
  private final IntakeSub intake;
  private final StorageSub storage;

  private int ballCount = 0;
  private boolean finished = false;

  public IntakeRun(IntakeSub intake, StorageSub storage) {
    this.intake = intake;
    this.storage = storage;
    addRequirements(intake);
    addRequirements(storage);
  }

  @Override
  public void initialize() {
    if(storage.getTopColor() == Color.kBlue || storage.getTopColor() == Color.kRed){
      if(storage.getTopColor() == Color.kBlue || storage.getTopColor() == Color.kRed){
        ballCount = 2;
      } else{
        ballCount = 1;
      }
    }
  }

  @Override
  public void execute(){
    new ConditionalCommand(
      new ConditionalCommand(
        new StartEndCommand(
          () -> storage.setConveyorSpeed(0.5), 
          () -> storage.setConveyorSpeed(0), 
          storage)
            .withTimeout(5)
            .until(storage.getTopColor() == DriverStation.getAlliance()), 
        storage.getTopColor() == DriverStation.getAlliance()),
      new ConditionalCommand(
        new ParallelCommandGroup(
          new storage.setConveyorSpeed(10),
          new SequentialCommandGroup(
            new WaitUntilCommand(storage.limitSwitch),
            ConditionalCommand(
              //while top sensor not triggered
              //while bottom sensor not triggered
              ballCount == 0),
            new InstantCommand(storage.setConveyorSpeed(0), storage),
            ballCount++
          )),
        new InstantCommand(intake.setSpinSpeed(0), intake),
        ballCount >= 2), 
      ballCount >= 1);
  }

  @Override
  public boolean isFinished() {
    return finished;
  }

  @Override
  public void end(boolean interrupted) {
    intake.setSpinSpeed(0);
  }
}
