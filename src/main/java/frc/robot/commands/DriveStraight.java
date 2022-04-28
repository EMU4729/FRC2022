package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems;

public class DriveStraight extends CommandBase {
  private final Subsystems subsystems = Subsystems.getInstance();

  private double targetAngle;

  public double speed;

  public DriveStraight() {
    addRequirements(subsystems.drive, subsystems.navigation);
  }

  @Override
  public void initialize() {
    targetAngle = subsystems.navigation.getAngle();
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
