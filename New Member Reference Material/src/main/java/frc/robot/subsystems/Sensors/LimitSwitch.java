// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimitSwitch extends SubsystemBase {
  /** Configuration and example code for limit switches */

  private final DigitalInput limitSwitch;

  public LimitSwitch() {
    
    limitSwitch = new DigitalInput(7);
    /* instantiates sensor
     * channel is dependent on the sensor itself
     */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean getState() {
    return limitSwitch.get();
    // gets the limit switch's state
  }
}
