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

  // Motor Options
  public boolean invertSteering = false;
  public boolean invertDriveDirection = false;
}
