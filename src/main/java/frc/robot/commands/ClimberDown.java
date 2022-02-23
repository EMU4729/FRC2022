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
    // TODO: Implement this properly
    climber.set(-0.5);
    System.out.println("ClimberDown : started");
    Logger.info("ClimberDown : started");  
  }

  @Override
  public boolean isFinished() {
    return false;

  }

  @Override
  public void end(boolean interrupted) {
    climber.set(0);
    System.out.println("ClimberDown : finished");
    Logger.info("ClimberDown : finished");  
  }
}

