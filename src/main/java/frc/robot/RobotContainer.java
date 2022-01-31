// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.Auto;
import frc.robot.commands.BallStopClose;
import frc.robot.commands.BallStopOpen;
import frc.robot.commands.ClimberDown;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveReverseDirection;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.NavigationUpdate;
// import frc.robot.commands.Shoot;
import frc.robot.commands.StorageRunForward;

import frc.robot.subsystems.BallStopSub;
import frc.robot.subsystems.ClimberSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.NavigationSub;
import frc.robot.subsystems.StorageSub;
import frc.robot.subsystems.VisionSub;

import edu.wpi.first.wpilibj2.command.Command;

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
  private final DriveSub driveSub = new DriveSub();
  private final ClimberSub climberSub = new ClimberSub();
  private final IntakeSub intakeSub = new IntakeSub();
  private final VisionSub visionSub = new VisionSub();
  private final NavigationSub navigationSub = new NavigationSub();
  private final StorageSub storageSub = new StorageSub();
  private final BallStopSub ballStopSub = new BallStopSub();

  private final Auto autoCommand = new Auto(driveSub, ballStopSub, intakeSub, navigationSub);
  private final BallStopClose ballStopCloseCommand = new BallStopClose(ballStopSub);
  private final BallStopOpen ballStopOpenCommand = new BallStopOpen(ballStopSub);
  private final ClimberDown climberDownCommand = new ClimberDown(climberSub);
  private final ClimberUp climberUpCommand = new ClimberUp(climberSub);
  private final Drive driveCommand = new Drive(driveSub);
  private final DriveReverseDirection driveReverseDirectionCommand = new DriveReverseDirection(driveSub);
  private final IntakeRun intakeRunCommand = new IntakeRun(intakeSub);
  private final NavigationUpdate navigationUpdateCommand = new NavigationUpdate(navigationSub);
  // private final Shoot shootCommand = new Shoot();
  private final StorageRunForward storageRunForwardCommand = new StorageRunForward(storageSub);

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
