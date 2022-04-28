package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;

public class AutoDriveArcade extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public double speed;
  public double steering;

  public AutoDriveArcade() {
    addRequirements(subsystems.drive);
  }

  @Override
  public void initialize() {
    subsystems.drive.arcade(speed, steering);

  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    subsystems.drive.off();
  }

}
