package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStopSub;
import frc.robot.subsystems.StorageSub;
import frc.robot.utils.AsyncTimer;

public class StorageRun extends CommandBase {
  private final StorageSub storage;
  private final BallStopSub ballStop;
  private final XboxController controller;
  private boolean vibrate;
  private boolean isFinished;
  private AsyncTimer vibrateTimer;

  public StorageRun(StorageSub storage, BallStopSub ballStop, XboxController controller) {
    this.storage = storage;
    this.ballStop = ballStop;
    this.controller = controller;

    addRequirements(storage);
  }

  private void startVibrate() {
    vibrate = true;
    vibrateTimer = new AsyncTimer(200);
    controller.setRumble(RumbleType.kLeftRumble, 1);
  }

  @Override
  public void initialize() {
    vibrateTimer = new AsyncTimer(200);
    vibrate = false;
    isFinished = false;
  }

  @Override
  public void execute() {
    if (vibrate && vibrateTimer.isFinished()) {
      vibrate = false;
      isFinished = true;
      // Stopping vibration not required - handled in end()
      return;
    } else if (vibrate) {
      return;
    }

    if (storage.getTopColor() == storage.oppColor) {
      startVibrate();
      return;
    }
    // TODO: Open ball stop here
    storage.setConveyorSpeed(0.5);
    if (storage.getTopColor() != storage.teamColor) {
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
