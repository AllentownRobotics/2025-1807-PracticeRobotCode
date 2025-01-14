// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Sensors;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class UltrasonicSensor extends SubsystemBase {
  /** Configuration and example code for ultrasonic sensors */

  private final Ultrasonic ultrasonic;

  public UltrasonicSensor() {
    
    ultrasonic = new Ultrasonic(Constants.ultrasonicPingChannel, Constants.ultrasonicEchoChannel);
    /* instantiates ultrasonic sensor
     * ping channel and echo channel depends on the sensor itself
     */
    ultrasonic.setEnabled(true);
    // enables sensor in case it isn't already
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getDistance() {
    ultrasonic.getRangeMM();
    return ultrasonic.getRangeInches();
    /* gets distance to closest object
     * often used with if statements
     * getRangeMM and getRangeInches do the same thing, different units
     */
  }
}
