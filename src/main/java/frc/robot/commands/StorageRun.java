package frc.robot.commands;

import java.time.Duration;
import java.time.Instant;

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
  private boolean vibrate;
  private boolean isFinished;
  private Instant vibrateTimer;

  public StorageRun(StorageSub storage, BallStopSub ballStop, XboxController controller) {
    this.storage = storage;
    this.ballStop = ballStop;
    this.controller = controller;

    Alliance alliance = DriverStation.getAlliance();
    if (alliance == Alliance.Red) {
      teamColor = Color.kRed;
      oppColor = Color.kBlue;
    } else {
      teamColor = Color.kBlue;
      oppColor = Color.kRed;
    }

    addRequirements(storage);
  }

  private long getVibrateTimerDuration() {
    return Duration.between(vibrateTimer, Instant.now()).toMillis();
  }

  private void startVibrate() {
    vibrate = true;
    vibrateTimer = Instant.now();
    controller.setRumble(RumbleType.kLeftRumble, 1);
  }

  @Override
  public void initialize() {
    vibrateTimer = Instant.now();
    vibrate = false;
    isFinished = false;
  }

  @Override
  public void execute() {
    if (vibrate && getVibrateTimerDuration() >= 200) {
      vibrate = false;
      isFinished = true;
      // Stopping vibration not required - handled in end()
      return;
    } else if (vibrate) {
      return;
    }

    if (storage.getTopColor() == oppColor) {
      startVibrate();
      return;
    }
    // TODO: Open ball stop here
    storage.setConveyorSpeed(0.5);
    if (storage.getTopColor() != teamColor) {
      // nvm
      storage.setConveyorSpeed(0);
      // TODO: Close ball stop here
      startVibrate();
      return;
    }

    storage.setConveyorSpeed(0);
    // TODO: close ball stop here
    startVibrate();

    // TODO: Finish this later
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }

  @Override
  public void end(boolean interrupted) {
    vibrate = false;
    storage.setConveyorSpeed(0);
    // TODO: close ball stop
    controller.setRumble(RumbleType.kLeftRumble, 0);
    // idk
  }
}
