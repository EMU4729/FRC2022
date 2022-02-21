package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSub;

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
  }
}
