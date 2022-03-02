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
          new InstantCommand(storage.setConveyorSpeed(10), storage),
          new SequentialCommandGroup(
            new WaitUntilCommand(storage.limitSwitch),
            ConditionalCommand(
              new WaitUntilCommand(ballIsThere(StorageColorSensor.TOP)).withTimeout(10),
              new WaitUntilCommand(ballIsThere(StorageColorSensor.BOTTOM)).withTimeout(10),
              () -> ballCount == 0),
            new InstantCommand(storage.setConveyorSpeed(0), storage),
            ballCount++
          )),
        new InstantCommand(intake.setSpinSpeed(0), intake),
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
    return () -> storage.getBall(sens).equals(BallType.NONE);
  }
}
