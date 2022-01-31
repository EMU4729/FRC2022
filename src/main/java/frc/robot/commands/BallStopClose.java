package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStopSub;

public class BallStopClose extends CommandBase {
  private final BallStopSub ballStop;

  public BallStopClose(BallStopSub ballStopSub) {
    ballStop = ballStopSub;
    addRequirements(ballStopSub);
  }

  @Override
  public void initialize() {
    // TODO: Implement this properly
    ballStop.set(-0.5);
  }
}
