package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

public class Drive extends CommandBase {
  private final DriveSub drive;

  public Drive(DriveSub driveSub) {
    drive = driveSub;
    addRequirements(driveSub);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    // TODO: Drive tank using input values
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
