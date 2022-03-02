package frc.robot.commands;

import java.util.function.BooleanSupplier;

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
import frc.robot.utils.BallType;
import frc.robot.utils.StorageColorSensor;

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
    if(ballIsThere(StorageColorSensor.TOP).getAsBoolean()){
      if(ballIsThere(StorageColorSensor.BOTTOM).getAsBoolean()){
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
        null,
        new StartEndCommand(
          () -> storage.setConveyorSpeed(0.5), 
          () -> storage.setConveyorSpeed(0), 
          storage
        )
          .withTimeout(10)
          .withInterrupt(ballIsTeam(StorageColorSensor.TOP)),
          ballIsTeam(StorageColorSensor.TOP)),
      new ConditionalCommand(
        new ParallelCommandGroup(
          new InstantCommand(storage::setSpeedIntake, storage),
          new SequentialCommandGroup(
            new WaitUntilCommand(storage.limitSwitch()),
            new ConditionalCommand(
              new WaitUntilCommand(ballIsThere(StorageColorSensor.TOP)),
              new WaitUntilCommand(ballIsThere(StorageColorSensor.BOTTOM)),
              () -> ballCount == 0).withTimeout(10),
            new InstantCommand(storage::stopConveyor, storage),
            new InstantCommand(() -> ballCount++, null)
          )),
        new InstantCommand(intake::stopIntake, intake),
        () -> ballCount >= 2), 
      () -> ballCount >= 1);

      new ConditionalCommand(
        null,
        new StartEndCommand(
          () -> storage.setConveyorSpeed(0.5), 
          () -> storage.setConveyorSpeed(0), 
          storage
        )
          .withTimeout(10)
          .withInterrupt(ballIsTeam(StorageColorSensor.TOP)),
          ballIsTeam(StorageColorSensor.TOP));
  }


  @Override
  public boolean isFinished() {
    return finished;
  }

  @Override
  public void end(boolean interrupted) {
    intake.setSpinSpeed(0);
  }

  private BooleanSupplier ballIsTeam(StorageColorSensor sens){
    return () -> storage.getBall(sens).equals(BallType.TEAM);
  }

  private BooleanSupplier ballIsThere(StorageColorSensor sens){
    return () -> !storage.getBall(sens).equals(BallType.NONE);
  }
}
