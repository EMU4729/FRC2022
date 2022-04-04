package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Navigation Subsystem.
 * Handles all autonomous navigation.
 */
public class NavigationSub extends SubsystemBase {
  private final Constants constants = Constants.getInstance();
  private final ADIS16470_IMU imu = new ADIS16470_IMU();
  private final Encoder leftEncoder = new Encoder(constants.DRIVE_ENCODER_PORT_LA, constants.DRIVE_ENCODER_PORT_LB);
  private final Encoder rightEncoder = new Encoder(constants.DRIVE_ENCODER_PORT_RA, constants.DRIVE_ENCODER_PORT_RB);
  private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
      Rotation2d.fromDegrees(imu.getAngle()));

  @Override
  public void periodic() {
    odometry.update(Rotation2d.fromDegrees(imu.getAngle()), leftEncoder.getDistance(), rightEncoder.getDistance());
  }

  public void setOdometry(Pose2d newPose) {
    odometry.resetPosition(newPose, Rotation2d.fromDegrees(imu.getAngle()));
  }

  public void reset() {
    imu.reset();
    odometry.resetPosition(new Pose2d(), new Rotation2d());
  }
}
