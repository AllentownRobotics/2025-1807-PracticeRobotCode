// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {

    public static final int kDriverControllerPort = 0;

  }
  
  public static final int talonMotorID = 0;

  public static final int sparkFlexMotorID = 1;

  public static final int sparkMaxMotorID = 2;

  public static final int pistonForwardChannel = 3;

  public static final int pistonReverseChannel = 4;

  public static final int encoderMotorID = 5;

  public static final int beamBreakID = 6;

  public static final int limitSwitchID = 7;

  public static final int pigeonID = 8;

  public static final int ultrasonicPingChannel = 9;

  public static final int ultrasonicEchoChannel = 10;
}
