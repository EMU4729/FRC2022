package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.logger.Logger;
import frc.robot.subsystems.ClimberSub;
import frc.robot.utils.AsyncTimer;

public class ClimberUp extends CommandBase {
  private final ClimberSub climber;
  private AsyncTimer timer;

  public ClimberUp(ClimberSub climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    if (!climber.isUp) {
      timer = new AsyncTimer(1000);
      climber.set(0.5);
    }
  }

  @Override
  public boolean isFinished() {
    if (!climber.isUp) {
      return timer.isFinished();
    } else {
      return true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    if (!climber.isUp) {
      climber.set(0);
      climber.isUp = true;
      Logger.info("ClimberUp : finished");
    }
    Logger.warn("ClimberUp : failed - climber already up");
  }
}
