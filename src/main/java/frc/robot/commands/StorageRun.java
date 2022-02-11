package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStopSub;
import frc.robot.subsystems.StorageSub;

public class StorageRun extends CommandBase {
  private final StorageSub storage;
  private final BallStopSub ballStop;
  private final XboxController controller;
  private final Color teamColor;
  private final Color oppColor;

  public StorageRun(StorageSub storage, BallStopSub ballStop, XboxController controller) {
    this.storage = storage;
    this.ballStop = ballStop;

    Alliance alliance = DriverStation.getAlliance();
    if (alliance == Alliance.Red) {
      teamColor = Color.kRed;
      oppColor = Color.kBlue;
    } else {
      teamColor = Color.kBlue;
      oppColor = Color.kRed;
    }

    this.controller = controller;
    addRequirements(storage);
  }

  @Override
  public void initialize() {
    storage.setConveyorSpeed(0.5);
  }

  @Override
  public void execute() {
    if (storage.getTopColor() == oppColor) {
      controller.setRumble(RumbleType.kLeftRumble, 1);
      return;
    }
    controller.setRumble(RumbleType.kLeftRumble, 0);
    // Open ball stop
    storage.setConveyorSpeed(0.5);

    // TODO: Finish this later
  }

  @Override
  public void end(boolean interrupted) {
    storage.setConveyorSpeed(0);
  }
}
