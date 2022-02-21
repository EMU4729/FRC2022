package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

public class DriveReverseDirection extends CommandBase {
  private final DriveSub drive;

  public DriveReverseDirection(DriveSub drive) {
    this.drive = drive;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    // TODO: Implement this
  }

}
