// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.auto.AutoFacade;
import frc.robot.commands.Auto;
import frc.robot.commands.ClimberDown;
import frc.robot.commands.ClimberUp;
// import frc.robot.commands.BallStopOpen;
// import frc.robot.commands.BallStopClose;
import frc.robot.commands.Teleop;
import frc.robot.commands.DriveInvert;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.NavigationUpdate;
import frc.robot.commands.StorageRun;
import frc.robot.commands.StorageShoot;
import frc.robot.commands.StorageReverse;

// import frc.robot.subsystems.BallStopSub;
import frc.robot.subsystems.ClimberSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.NavigationSub;
import frc.robot.subsystems.StorageSub;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Constants constants = Constants.getInstance();

  private final XboxController controller = new XboxController(constants.DEVICE_PORT_XBOX_CONTROLLER);
  private final JoystickButton leftBumperButton = new JoystickButton(controller,
      Button.kLeftBumper.value);
  private final JoystickButton startButton = new JoystickButton(controller,
      Button.kStart.value);
  private final JoystickButton rightBumperButton = new JoystickButton(controller,
      Button.kRightBumper.value);
  private final JoystickButton bButton = new JoystickButton(controller, Button.kB.value);
  private final JoystickButton xButton = new JoystickButton(controller, Button.kX.value);
  private final POVButton dPadUpButton = new POVButton(controller, 0);
  private final POVButton dPadDownButton = new POVButton(controller, 180);

  private final DriveSub driveSub = new DriveSub();
  private final ClimberSub climberSub = new ClimberSub();
  private final IntakeSub intakeSub = new IntakeSub();
  private final NavigationSub navigationSub = new NavigationSub();
  private final StorageSub storageSub = new StorageSub();
  // private final BallStopSub ballStopSub = new BallStopSub();

  private final ClimberDown climberDownCommand = new ClimberDown(climberSub);
  private final ClimberUp climberUpCommand = new ClimberUp(climberSub);
  // private final BallStopOpen ballStopOpenCommand = new
  // BallStopOpen(ballStopSub);
  // private final BallStopClose ballStopCloseCommand = new
  // BallStopClose(ballStopSub);
  private final Teleop teleopCommand = new Teleop(driveSub, controller);
  private final DriveInvert driveInvertCommand = new DriveInvert();
  private final IntakeRun intakeRunCommand = new IntakeRun(intakeSub);
  private final NavigationUpdate navigationUpdateCommand = new NavigationUpdate(navigationSub);
  private final StorageRun storageRunCommand = new StorageRun(storageSub);
  private final StorageShoot storageShootCommand = new StorageShoot(storageSub);
  private final StorageReverse storageReverseCommand = new StorageReverse(storageSub);

  private final AutoFacade autoFacade = new AutoFacade(
      driveSub, intakeRunCommand, navigationUpdateCommand,
      storageRunCommand, storageShootCommand, storageReverseCommand);
  private final Auto autoCommand = new Auto(autoFacade);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Run Intake
    leftBumperButton.whenHeld(intakeRunCommand);

    // Shoot Storage
    rightBumperButton.whenHeld(storageShootCommand);

    // Run Storage
    xButton.whenHeld(storageRunCommand);

    // Reverse Storage
    bButton.whenHeld(storageReverseCommand);

    // Invert Drive
    startButton.whenPressed(driveInvertCommand);

    // Climber Up/Down
    dPadUpButton.whenHeld(climberUpCommand);
    dPadDownButton.whenHeld(climberDownCommand);

    // Drive bindings handled in driveCommand
  }

  /**
   * Use this to pass the teleop command to the main {@link Robot} class.
   *
   * @return the command to run in teleop
   */
  public Command getTeleopCommand() {
    return teleopCommand;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoCommand;
  }
}
