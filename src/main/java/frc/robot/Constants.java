// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;

/**
 * Constants - use this class to store any port ids, file paths, or basically
 * anything that will not change.
 */
public final class Constants {
  private static Optional<Constants> instance = Optional.empty();

  private Constants() {
  }

  public static Constants getInstance() {
    if (!instance.isPresent()) {
      instance = Optional.of(new Constants());
    }
    return instance.get();
  }

  // Drive Ports
  public final int leftFrontMotorPort = 1;
  public final int rightFrontMotorPort = 4;
  public final int leftBackMotorPort = 2;
  public final int rightBackMotorPort = 5;

  // Motor Inversion Options
  public final int climberMotorInversionFactor = 1;
  public final int conveyorMotorInversionFactor = -1;
  public final int intakeMotorInversionFactor = -1;

  // Other Motors
  public final int intakeMotorPort = 6;
  public final int conveyorMotorPort = 7;
  public final int climberMotorPort = 8;

  // Controller
  public final int controllerPort = 0;

  // Auto Commands Text File Path
  public final String driveLetter = "u//";
  public final String autoCommandsPath = driveLetter + "autoCommands.txt"; // TODO: Update this?
}
