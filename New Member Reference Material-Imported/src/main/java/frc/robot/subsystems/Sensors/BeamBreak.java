// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Sensors;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BeamBreak extends SubsystemBase {
  /** Configuration and example code for beam break sensors */

  DigitalInput beamBreak;

  public BeamBreak() {
    beamBreak = new DigitalInput(Constants.beamBreakID);
    /* instantiates sensor
     * channel depends on sensor itself
     */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void getState() {
    beamBreak.get();
    // gets the beam break sensor's state
  }
}
