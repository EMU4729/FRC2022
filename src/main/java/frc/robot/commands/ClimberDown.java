package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSub;
import frc.robot.utils.AsyncTimer;
import frc.robot.utils.logger.Logger;

public class ClimberDown extends CommandBase {
  private final ClimberSub climber;
  private AsyncTimer timer;

  public ClimberDown(ClimberSub climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    if (climber.isUp) {
      timer = new AsyncTimer(1000);
      climber.set(-0.5);
    }
  }

  @Override
  public boolean isFinished() {
    if (climber.isUp) {
      return timer.isFinished();
    } else {
      return true;
    }

  }

  @Override
  public void end(boolean interrupted) {
    if (climber.isUp) {
      climber.set(0);
      climber.isUp = false;
      Logger.info("ClimberDown : finished");
    }
    Logger.warn("ClimberDown : Failed - Climber already down");
  }
}
