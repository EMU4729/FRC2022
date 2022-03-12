package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;
import frc.robot.utils.logger.Logger;

public class Drive extends CommandBase {
  private final DriveSub drive;
  private final XboxController controller;
  private final boolean useTank = SmartDashboard.getBoolean("useTank", false);

  public Drive(DriveSub drive, XboxController controller) {
    this.drive = drive;
    this.controller = controller;

    addRequirements(drive);
  }

  @Override
  public void initialize() {
    //Logger.info("Drive : Set Up : Mode="+(useTank ? "Tank" : "Arcade"));

  }

  @Override
  public void execute() {
    if (useTank) {
      if(Timer.getFPGATimestamp() % 5 == 0){ Logger.info("Drive : Controller Left="+controller.getLeftY()+", Right="+controller.getRightY());}
      double leftSpeed = controller.getLeftY();
      double rightSpeed = controller.getRightY();
      drive.tank(leftSpeed, rightSpeed);
    } else {
      if(Timer.getFPGATimestamp() % 5 == 0){Logger.info("Drive : Controller X(sp)="+controller.getRightX()+", Y(st)="+controller.getLeftY());}
      double speed = controller.getLeftY();
      double steering = controller.getRightX();
      drive.arcade(speed, steering);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
