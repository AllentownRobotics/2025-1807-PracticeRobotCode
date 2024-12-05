// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Sensors;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pigeon extends SubsystemBase {
  /** Configuration and example code for Pigeon sensors */

  final private Pigeon2 pigeon;

  public Pigeon() {
    
    pigeon = new Pigeon2(Constants.pigeonID);
    /* instantiates sensor
     * deviceID is based on the sensor itself
     */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getRoll() {
    return pigeon.getRoll().getValueAsDouble();
  }

  public double getPitch() {
    return pigeon.getPitch().getValueAsDouble();
  }

  public double getYaw() {
    return pigeon.getYaw().getValueAsDouble();
  }

  public double getAngle() {
    return pigeon.getAngle();
  }
}
