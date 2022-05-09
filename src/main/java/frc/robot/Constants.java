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
  /** Port Number for left master drive */
  public final int DRIVE_MOTOR_PORT_LM = 4;
  /** Port Number for right master drive */
  public final int DRIVE_MOTOR_PORT_RM = 1;
  /** Port Number for left slave drive */
  public final int DRIVE_MOTOR_PORT_LS = 5;
  /** Port Number for right slave drive */
  public final int DRIVE_MOTOR_PORT_RS = 2;
  /** Port Number for left drive encoder A channel */
  public final int DRIVE_ENCODER_PORT_LA = 69420; // UNUSED
  /** Port Number for left drive encoder B channel */
  public final int DRIVE_ENCODER_PORT_LB = 69420; // UNUSED
  /** Port Number for right drive encoder A channel */
  public final int DRIVE_ENCODER_PORT_RA = 69420; // UNUSED
  /** Port Number for right drive encoder B channel */
  public final int DRIVE_ENCODER_PORT_RB = 69420; // UNUSED

  /** Proportional constant for driving straight durin auto */
  public double AUTO_STRAIGHT_KP = 0.234;

  // Other Motors
  /** Port Number for intake motor */
  public final int INTAKE_MOTOR_PORT = 6; // WORKING
  /** Port Number for storage conveyer motor */
  public final int STORAGE_CONVEYER_MOTOR_PORT = 7; // WORKING
  /** Port Number for storage ballstop motor */
  public final int STORAGE_BALLSTOP_MOTOR_PORT = 69420; // UNUSED
  /** Port Number for storage shooter motor */
  public final int STORAGE_SHOOTER_MOTOR_PORT = 8; // WORKING
  /** Port Number for climber motor */
  public final int CLIMBER_MOTOR_PORT = 0; // WORKING

  // Controller
  /** Port Number for xbox controller input device */
  public final int DEVICE_PORT_XBOX_CONTROLLER = 0; // WORKING

  // Storage Sensors
  /** Port Number for upper color sensor in storage */
  public final I2C.Port STORAGE_SENSOR_COLOR_TOP = I2C.Port.kMXP; // UNUSED
  /** Port Number for lower color sensor in storage */
  public final I2C.Port BOTTOM_COLOR_SENSOR_PORT = I2C.Port.kMXP; // UNUSED
  /** Port Number for ball entry sensor in storage */
  public final int LIMIT_SWITCH_CHANNEL = 69420; // UNUSED

  // File Paths
  /** file path header for files on usb storage */
  public final String[] PATH_USB = { "u//", "v//" };
  /** file path header for files on internal storage */
  public final String PATH_INTERNAL = env.get("HOME");
  /** file name for default auto */
  public final String PATH_AUTO_FILE_NAME = "autoCommands.txt";

  // Error Retry Limit
  /** limit for repeated attempts to create log file on USB storage */
  public final int REPEAT_LIMIT_LOGGER_CREATION = 10;
  /** limit for repeated attempts to read auto from internal storage */
  public final int REPEAT_LIMIT_AUTO_READ = 10;
}
