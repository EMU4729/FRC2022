package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSub;

public class ClimberUp extends CommandBase {
  private final ClimberSub climber;

  public ClimberUp(ClimberSub climberSub) {
    climber = climberSub;
    addRequirements(climberSub);
  }

  @Override
  public void initialize() {
    // TODO: Implement this properly
    climber.set(-0.5);
  }

}
