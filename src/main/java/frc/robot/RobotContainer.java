// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.Auto;
import frc.robot.commands.ClimberDown;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveReverseDirection;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.StorageRun;
import frc.robot.commands.StorageRunFast;
import frc.robot.subsystems.ClimberSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
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

  private final XboxController controller = new XboxController(constants.controllerPort);
  private final JoystickButton leftBumperButton = new JoystickButton(controller,
      Button.kLeftBumper.value);
  private final JoystickButton startButton = new JoystickButton(controller,
      Button.kStart.value);
  private final JoystickButton rightBumperButton = new JoystickButton(controller,
      Button.kRightBumper.value);
  private final JoystickButton bButton = new JoystickButton(controller, Button.kB.value);
  private final POVButton dPadUpButton = new POVButton(controller, 0);
  private final POVButton dPadDownButton = new POVButton(controller, 180);

  private final DriveSub driveSub = new DriveSub();
  private final ClimberSub climberSub = new ClimberSub();
  private final IntakeSub intakeSub = new IntakeSub();
  private final StorageSub storageSub = new StorageSub();

  private final Auto autoCommand = new Auto(driveSub, intakeSub, storageSub);
  private final ClimberDown climberDownCommand = new ClimberDown(climberSub);
  private final ClimberUp climberUpCommand = new ClimberUp(climberSub);
  private final Drive driveCommand = new Drive(driveSub, controller); // TODO: Fix this
  private final DriveReverseDirection driveReverseDirectionCommand = new DriveReverseDirection();
  private final IntakeRun intakeRunCommand = new IntakeRun(intakeSub);
  private final StorageRun storageRunCommand = new StorageRun(storageSub);
  private final StorageRunFast storageRunFastCommand = new StorageRunFast(storageSub);

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

    // Run Storage
    rightBumperButton.whenHeld(storageRunCommand);

    // Run Storage Fast
    bButton.whenHeld(storageRunFastCommand);

    // Reverse Drive Direction
    startButton.whenPressed(driveReverseDirectionCommand);

    // Climber Up/Down
    dPadUpButton.whenHeld(climberUpCommand);
    dPadDownButton.whenHeld(climberDownCommand);

    // Drive bindings handled in driveCommand.
  }

  /**
   * Use this to pass the teleop command to the main {@link Robot} class.
   *
   * @return the command to run in teleop
   */
  public Command getTeleopCommand() {
    return driveCommand;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }
}
