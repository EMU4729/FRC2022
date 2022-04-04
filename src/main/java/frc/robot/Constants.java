// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Map;
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

  // Envars
  public final Map<String, String> env = System.getenv();

  // Drive Ports
  public final int LeftFrontMotorPort = 6;
  public final int RightFrontMotorPort = 11;
  public final int LeftBackMotorPort = 5;
  public final int RightBackMotorPort = 12;
  public final int LeftEncoderPortA = 69420; // TODO: Update this
  public final int LeftEncoderPortB = 69420; // TODO: Update this
  public final int RightEncoderPortA = 69420; // TODO: Update this
  public final int RightEncoderPortB = 69420; // TODO: Update this

  // Other Motors
  public final int IntakeMotorPort = 69420; // TODO: Update this
  public final int ConveyorMotorPort = 69420; // TODO: Update this
  public final int ClimberMotorPort = 69420; // TODO: Update this
  public final int BallStopMotorPort = 69420; // TODO: Update this

  // Drive Speed Multipliers
  public final double TeleopSpeedMultiplier = 1;
  public final double AutoSpeedMultiplier = 1;

  // Controller
  public final int ControllerPort = 69420; // TODO: Update this

  // Storage Color Sensor
  public final I2C.Port TopColorSensorPort = I2C.Port.kMXP; // TODO: Update this
  public final I2C.Port BottomColorSensorPort = I2C.Port.kMXP; // TODO: Update this

  // Auto Commands Text File Path
  public final String[] UsbPaths = { "u//", "v//" };
  public final String InternalPath = env.get("HOME");
  public final String AutoFileName = "autoCommands.txt";

  // Error Retry Limit
  public final int LoggerFileCreateLim = 10;
  public final int AutoReadLim = 10;

  // Storage Limit Switch
  public final int LimitSwitchChannel = 69420; // TODO: Update this
}
