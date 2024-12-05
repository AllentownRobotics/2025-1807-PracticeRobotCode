// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Sensors;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AbsEncoder extends SubsystemBase {
  /** Configruation and example code for absolute encoders */

  AbsoluteEncoder absoluteEncoder;
  CANSparkMax sampleMotor;
  // a hypothetical motor needed for the absolute encoder to work

  public AbsEncoder() {
    sampleMotor = new CANSparkMax(0, MotorType.kBrushless);
    // instantiates motor, same as whatever normal motor controller

    absoluteEncoder = sampleMotor.getAbsoluteEncoder();
    // gets absolute encoder
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getPosition() {
    return absoluteEncoder.getPosition();
    // gets position in the form of a double
  }

  public double getVelocity() {
    return absoluteEncoder.getVelocity();
    // gets velocity in the form of a double
  }
}
