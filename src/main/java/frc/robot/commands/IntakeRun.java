package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSub;

public class IntakeRun extends CommandBase {
  private final IntakeSub intake;

  public IntakeRun(IntakeSub intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.setSpinSpeed(0.5);
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    intake.setSpinSpeed(0);
  }
}
