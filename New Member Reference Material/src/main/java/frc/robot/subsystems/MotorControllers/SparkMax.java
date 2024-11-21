// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.MotorControllers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SparkMax extends SubsystemBase {
  /** Configuration and example code for SparkMax motor controllers */

  private final CANSparkMax sparkMax;

    public SparkMax() {
      sparkMax = new CANSparkMax(7, MotorType.kBrushless);
      /* instantiates motor controller
       * deviceID is specific to the motor controller
       * motorType is always MotorType.kBrushless b/c we use brushless motors
       */
  
      sparkMax.restoreFactoryDefaults();
      // resets config to defaults
  
      sparkMax.setIdleMode(IdleMode.kBrake);
      // sets motor to brake when not doing anything, always do this
  
      sparkMax.setInverted(true);
      /* sets the motor controller to be inverted or not
       * use case depends on where the motor is being used
       */
  
      sparkMax.burnFlash();
      /* stores the config onto the motor controller itself
       * always do this when you're done configuring
       */
    }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void run() {
    sparkMax.set(1);
    /* sets the motor speed
     * range is -1 to 1 with 2 decimals precision
     * negative is backwards, positive is forwards
     * 0 is stationary
     */
  }
}
