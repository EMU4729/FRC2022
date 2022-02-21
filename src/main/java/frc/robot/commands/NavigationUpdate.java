package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NavigationSub;

public class NavigationUpdate extends CommandBase {
  private final NavigationSub navigation;

  public NavigationUpdate(NavigationSub navigation) {
    this.navigation = navigation;
    addRequirements(navigation);
  }

  @Override
  public void initialize() {
    // TODO: Implement this
  }

}
