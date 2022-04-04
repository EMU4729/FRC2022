package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSub;

public class TeleopDrive extends CommandBase {
  private final Constants constants = Constants.getInstance();
  private final DriveSub drive;
  private final XboxController controller;
  private final boolean useTank = SmartDashboard.getBoolean("useTank", false);

  public TeleopDrive(DriveSub drive, XboxController controller) {
    this.drive = drive;
    this.controller = controller;

    addRequirements(drive);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    if (useTank) {
      double leftSpeed = controller.getLeftY();
      double rightSpeed = controller.getRightY();
      drive.tank(leftSpeed * constants.TeleopSpeedMultiplier, rightSpeed * constants.TeleopSpeedMultiplier);
    } else {
      double speed = controller.getLeftY();
      double steering = controller.getRightX();

      // If needed, make the teleop speed multiplier affect steering, too
      drive.arcade(speed * constants.TeleopSpeedMultiplier, steering);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
