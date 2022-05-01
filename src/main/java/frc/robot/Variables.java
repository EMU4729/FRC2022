// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;

/**
 * Variables - use this class to define and set globally-accessed
 * settings/options/variables.
 */
public final class Variables {
  private static Optional<Variables> instance = Optional.empty();

  private Variables() {
  }

  public static Variables getInstance() {
    if (!instance.isPresent()) {
      instance = Optional.of(new Variables());
    }
    return instance.get();
  }

  /** Bool to invert robot steering direction */
  public boolean invertSteering = false;
  /** Bool to invert robot drive direction flipping the apparent front of the robot */
  public boolean invertDriveDirection = false;

  /** Multiplier for robot max speed in teleop */
  public double teleopSpeedMultiplier = 1;
  /** Multiplier for robot max speed in auto */
  public double autoSpeedMultiplier = 1;

  /** Drive Input Curve Exponent */
  public double inputCurveExponent = 3;
}
