//package com.spartronics4915.frc2019;
package lib;

import java.nio.file.Paths;

import lib.math.Translation2d;
import lib.util.ConstantsBase;

/**
 * A list of constants used by the rest of the robot code. 
 * File separates hardware configuration constants from software
 * constants.  The software constants include various timeout periods
 * as well as physics constants and those determined through calibrations.
 * 
 * Constant names should include the subsystem or field element (etc)
 * to which they refer.
 */
public class Constants //extends ConstantsBase
{
    public static final boolean kUseTestbedConstants = false; // XXX: We should use the ConstantsBase reflection -> file instead of this
    /* HARDWARE CONFIGURATION CONSTANTS ----------------------------------------------------- */
    // CAN Bus
    // there are at least three families of ids on the CAN bus defined by
    // these device classes:
    //      PDP - power distribution panel (only one of these)
    //      SRX - CANTalon Ids
    //      PCM - Pressure Control Module Ids (often 0 or 1)
    
    // Talon SRX Channels --------------
    //      (Note that if multiple talons are dedicated to a mechanism, any sensors
    //      are attached to the master)
    public static final int kLeftDriveMasterId = 1;
    public static final int kLeftDriveSlaveId = 2;
    public static final int kRightDriveMasterId = 3;
    public static final int kRightDriveSlaveId = 4;
    public static final int kDriveIMUTalonId = kRightDriveSlaveId; // must be a slave

    public static final int kNumTalons = 5; // total talon count on robot (not testbed)
    
    public static final int kNumPDPs = 1; // doesn't always show up in CANProbe
    public static final int kNumPCMs = 1; // Pressure control module (pneumatics)
    public static final int kNumCANDevices = kNumTalons + kNumPCMs; // don't count PDP
    
    // Pressure Control Module (PCM) Channels

    // PWM (Servo) Pins
    
    // Relay Pins
    public static final int kLEDVisionLampId = 0;
    public static final int kLEDDriverLEDId = 1;
    
    // DIO Pins
    
    // Analog In Pins

    // Software configuration constants
    public static final int kLooperDtMS = 5; //
    public static final double kLooperDt = kLooperDtMS / 1000.0;
    
    // Vision
    public static final int kAndroidAppTcpPort = 8254;

    /* ROBOT PHYSICAL CONSTANTS ----------------------------------------------------------- */

    // Wheels
    public static final double kDriveWheelDiameterInches = 6;
    public static final double kTrackWidthInches = 23.75;
    public static final double kTrackScrubFactor = 0.624;

    // Chassis Geometry -- Currently unused
    public static final double kCenterToFrontBumperDistance = 17.839285714;
    // public static final double kCenterToIntakeDistance = 20.9675;
    public static final double kCenterToRearBumperDistance = kCenterToFrontBumperDistance;
    public static final double kCenterToSideBumperDistance = 15.375;

    /* LIDAR CONSTANTS -------------------------------------------------------------------- */
    public static final int kLidarScanSize = 400;
    public static final int kLidarNumScansToStore = 10;

    // XXX: This constant is not true for everyone.
    
    // public static final String kLidarPath = "/home/darwin/Programming/Robotics/LIDAR/254-sdk/sdk/output/Linux/Release";
    public static final String kLidarPath = "C:/Users/alexa/Desktop/VSProjects/LidarDEV/src/main/java/lidar/chezy_lidar";
    public static final double kLidarRestartTime = 2.5;

    public static final String kLidarLogDir = Paths.get(System.getProperty("user.home"), "/lidarlogs/").toString();
    public static final int kNumLidarLogsToKeep = 10;
    public static final double kLidarICPTranslationEpsilon = 0.01; // convergence threshold for tx,ty
    public static final double kLidarICPAngleEpsilon = 0.01;       // convergence threshold for theta

    // Pose of the LIDAR frame w.r.t. the robot frame
    public static final double kLidarXOffset = -3.3211;
    public static final double kLidarYOffset = 0.0;
    public static final double kLidarYawAngleDegrees = 0.0;

    /* CONTROL LOOP GAINS ---------------------------------------------------------------- */

    // PID gains for drive velocity loop (HIGH GEAR)
    // Units: setpoint, error, and output are in inches per second.
    public static final double kDriveVelocityKp = 5;
    public static final double kDriveVelocityKi = 0.01;
    public static final double kDriveVelocityKd = 100;
    public static final double kDriveVelocityKf = 0.5;
    public static final int kDriveVelocityIZone = 0;
    public static final double kDriveVelocityMaxIAccum = 0; // 0 means n/a
    public static final double kDriveVelocityRampRate = .05; // 240V/s -> 12V in .05s
    public static final double kDriveHighGearNominalOutput = 0.5;
    public static final double kDriveHighGearMaxSetpoint = 17 * 12; // 17 fps in ips

    // PID gains for drive position loop (LOW GEAR)
    // Units: setpoint, error, and output are in inches per second.
    public static final double kDrivePositionKp = 2;
    public static final double kDrivePositionKi = 0;
    public static final double kDrivePositionKd = 300.0;
    public static final double kDrivePositionKf = 0;
    public static final int kDrivePositionIZone = 0;
    public static final int kDrivePositionMaxIAccum = 0; // 0 means n/a
    public static final double kDrivePositionRampRate = .05; // 240V/s -> 12V in .05s
    public static final double kDriveLowGearNominalOutput = 0.5; // pct
    public static final double kDriveLowGearMaxVelocity = 3*12*60 / (Math.PI * kDriveWheelDiameterInches); 
        // max velocity is 3 fps, represented in RPM
    public static final double kDriveLowGearMaxAccel = 15*12*60 / (Math.PI * kDriveWheelDiameterInches); 
        // max accel is 15 fps/s, represented in RPM/s

    public static final double kDriveVoltageCompensationRampRate = 0.0;
    
    // Drive ------------------------------------------------------
    // Encoder: https://cdn.usdigital.com/assets/datasheets/E4P_datasheet.pdf?k=636523267170919858
    // modelr: E4P-360-250-N-S-D-D (western digital)
    //              ^ EncoderCodesPerRev (later multiplied by 4 for quad)
    //                  ^ .25" shaft mount 
    //                     ^ "No Index"
    //                        ^ "Single Output" (not Differential)
    //                          ^ ^ Default cover, Default base
    public static final int kEncoderCodesPerRev = 360;

    // Turret
    public static final int kTurretMotorId = 10; // TODO: Figure out the correct motor ID
    // We're using the CTRE Mag encoders: https://content.vexrobotics.com/vexpro/pdf/Magnetic-Encoder-User's-Guide-01282016.pdf
    public static final int kTurretEncoderCodesPerRev = 13653; // 4096 Quadrature CPR * (10 / 3) Belt reduction
    public static final Translation2d kTurretTargetFieldPosition = new Translation2d(0, 0);
    public static final class TurretPIDConstants {
        public static final double kP = 1.0, kI = 0.0, kD = 0.0, kF = 0.0;
        public static final double kRampRate = 0.5 /* 0.5 seconds */;
        public static final int kIZone = 0;
    }; // TODO: Tune these
    public static final Translation2d kTurretRobotCenterOffset = new Translation2d(0, 0); // TODO: Set offset


    // Path following constants -----------------------------------
    public static final double kMinLookAhead = 12.0; // inches
    public static final double kMaxLookAhead = 24.0; // inches
    public static final double kDeltaLookAhead = kMaxLookAhead - kMinLookAhead;

    public static final double kMinLookAheadSpeed = 9.0; // inches per second
    public static final double kMaxLookAheadSpeed = 120.0; // inches per second
    public static final double kDeltaLookAheadSpeed = kMaxLookAheadSpeed - kMinLookAheadSpeed;
    
    public static final double kInertiaSteeringGain = 0.0; 
        // angular velocity command is multiplied by this gain * our speed in ips
    public static final double kSegmentCompletionTolerance = 0.05; // inches
    public static final double kPathFollowingMaxAccel = 120.0; // inches per second^2
    public static final double kPathFollowingMaxVel = 120.0; // inches per second
    public static final double kPathFollowingProfileKp = 4;
    public static final double kPathFollowingProfileKi = 0.08;
    public static final double kPathFollowingProfileKv = 0;
    public static final double kPathFollowingProfileKffv = 0.8;
    public static final double kPathFollowingProfileKffa = 0;
    public static final double kPathFollowingGoalPosTolerance = 0.75;
    public static final double kPathFollowingGoalVelTolerance = 12.0;
    public static final double kPathStopSteeringDistance = 9.0;

    // Goal tracker constants
    public static final double kMaxGoalTrackAge = 1.0;
    public static final double kMaxTrackerDistance = 18.0;
    public static final double kCameraFrameRate = 30.0;
    public static final double kTrackReportComparatorStablityWeight = 1.0;
    public static final double kTrackReportComparatorAgeWeight = 1.0;
    public static final double kShooterOptimalRangeCeiling = 80.0; // unused
    public static final double kShooterOptimalRangeFloor = 70.0; // unused

    // Pose of the camera frame w.r.t. the robot frame, used by RobotState, but
    // not relied upon for POWERUP.
    public static final double kCameraXOffset = -3.3211;
    public static final double kCameraYOffset = 0.0;
    public static final double kCameraZOffset = 20.9;
    public static final double kCameraPitchAngleDegrees = 29.56; // Measured on 4/26
    public static final double kCameraYawAngleDegrees = 0.0;
    public static final double kCameraDeadband = 0.0;
    public static final String kVisionTableName = "Vision"; // name in nettab below root
    public static final String kVisionTargetAngleName = "ax"; // "clock", "ay" also available
    
    // Field constants (inches)
    public static final double kFieldWidth = 648;
    public static final double kFieldHeight = 324;
    public static final Translation2d kFieldDimensionTranslation = new Translation2d(kFieldWidth, kFieldHeight);
    
    /**
     * Make an {@link Solenoid} instance for the single-number ID of the
     * solenoid
     * 
     * @param solenoidId
     *        One of the kXyzSolenoidId constants
     */

    public String getFileLocation()
    {
        return "~/constants.txt";
    }
}
