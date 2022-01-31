package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NavigationSub;

public class NavigationUpdate extends CommandBase {
  private final NavigationSub navigation;

  public NavigationUpdate(NavigationSub navigationSub) {
    navigation = navigationSub;
    addRequirements(navigationSub);
  }

  @Override
  public void initialize() {
    // TODO: Implement this
  }

}
