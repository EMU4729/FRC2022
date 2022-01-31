package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStopSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.NavigationSub;

public class Auto extends CommandBase {
  private final DriveSub drive;
  private final BallStopSub ballStop;
  private final IntakeSub intake;
  private final NavigationSub navigation;

  public Auto(DriveSub driveSub, BallStopSub ballStopSub, IntakeSub intakeSub, NavigationSub navigationSub) {
    drive = driveSub;
    ballStop = ballStopSub;
    intake = intakeSub;
    navigation = navigationSub;
    addRequirements(driveSub, ballStopSub, intakeSub, navigationSub);
  }

  @Override
  public void initialize() {
    // TODO: Implement this
  }

  @Override
  public void execute() {
    // TODO: Implement this
  }

  @Override
  public boolean isFinished() {
    // TODO: Implement this
    return false;
  }
}
