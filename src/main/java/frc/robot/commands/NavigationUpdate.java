package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;

public class NavigationUpdate extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public NavigationUpdate() {
    addRequirements(subsystems.navigation);
  }

  @Override
  public void initialize() {
    // TODO: Implement this
  }

}
