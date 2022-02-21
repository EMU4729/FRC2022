// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;

import edu.wpi.first.wpilibj.I2C;

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
  public final int leftFrontMotorPort = 6;
  public final int rightFrontMotorPort = 11;
  public final int leftBackMotorPort = 5;
  public final int rightBackMotorPort = 12;
  public final int leftEncoderPortA = 69420; // TODO: Update this
  public final int leftEncoderPortB = 69420; // TODO: Update this
  public final int rightEncoderPortA = 69420; // TODO: Update this
  public final int rightEncoderPortB = 69420; // TODO: Update this

  // Other Motors
  public final int intakeMotorPort = 69420; // TODO: Update this
  public final int conveyorMotorPort = 69420; // TODO: Update this
  public final int climberMotorPort = 69420; // TODO: Update this
  public final int ballStopMotorPort = 69420; // TODO: Update this

  // Controller
  public final int controllerPort = 69420; // TODO: Update this

  // Storage Color Sensor
  public final I2C.Port topColorSensorPort = I2C.Port.kMXP; // TODO: Update this
  public final I2C.Port bottomColorSensorPort = I2C.Port.kMXP; // TODO: Update this

  // Auto Commands Text File Path
  public final String autoCommandsPath = "u/autoCommands.txt"; // TODO: Update this?
}
