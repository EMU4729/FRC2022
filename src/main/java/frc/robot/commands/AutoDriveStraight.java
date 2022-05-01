package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;

public class AutoDriveStraight extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  public double targetAngle;

  public double speed;

  public AutoDriveStraight() {
    addRequirements(subsystems.drive, subsystems.navigation);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    subsystems.drive.arcade(
        speed, subsystems.navigation.proportionalStraightAdjustment(targetAngle));
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
