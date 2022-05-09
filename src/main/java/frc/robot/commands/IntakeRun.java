package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;
import frc.robot.utils.logger.Logger;

public class IntakeRun extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public IntakeRun() {
    addRequirements(subsystems.intake);
  }

  @Override
  public void initialize() {
    System.out.println("intake");
    Logger.info("IntakeRun : Start : Forward");
    subsystems.intake.setSpinSpeed(-0.5);
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
    Logger.info("IntakeRun : End");
    subsystems.intake.setSpinSpeed(0);
  }
}