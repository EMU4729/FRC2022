package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSub;
import frc.robot.utils.logger.Logger;

public class ClimberDown extends CommandBase {
  private final ClimberSub climber;

  public ClimberDown(ClimberSub climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    Logger.info("Climber : Start : Direction=DOWN");
    climber.set(-1);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    Logger.info("Climber : End");
    climber.set(0);
  }
}
