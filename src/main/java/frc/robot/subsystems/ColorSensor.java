// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ColorSensor extends SubsystemBase {
  ColorSensorV3 colorSensor;
  Color sensorColor;
  /** Creates a new ColorSensor. */
  public ColorSensor() {
    colorSensor = new ColorSensorV3(Port.kOnboard);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void sendColorToElastic() {
    sensorColor = colorSensor.getColor();
    SmartDashboard.putString("Color Sensor Color", sensorColor.toHexString());
  }
}
