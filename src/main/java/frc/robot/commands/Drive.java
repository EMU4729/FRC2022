package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Variables;
import frc.robot.subsystems.DriveSub;

public class Drive extends CommandBase {
  private final Constants constants = Constants.getInstance();
  private final Variables variables = Variables.getInstance();
  private final DriveSub drive;
  private final XboxController controller;
  private final boolean useTank = SmartDashboard.getBoolean("useTank", false);

  private double speedMultiplier;

  public Drive(DriveSub drive, XboxController controller) {
    this.drive = drive;
    this.controller = controller;

    addRequirements(drive);
  }

  @Override
  public void initialize() {
    if(DriverStation.isAutonomous()){speedMultiplier = variables.teleopSpeedMultiplier;}
    else{speedMultiplier = variables.autoSpeedMultiplier;}

  }

  @Override
  public void execute() {
    if (useTank) {
      double leftSpeed = controller.getLeftY();
      double rightSpeed = controller.getRightY();
      drive.tank(leftSpeed * speedMultiplier, rightSpeed * speedMultiplier);
    } else {
      double speed = controller.getLeftY();
      double steering = controller.getRightX();

      // If needed, make the teleop speed multiplier affect steering, too
      drive.arcade(speed * speedMultiplier, steering);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
