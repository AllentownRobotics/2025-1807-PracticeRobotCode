// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.MotorControllers;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SparkFlex extends SubsystemBase {
  /** Configuration and example code for SparkFlex motor controllers */

  private final CANSparkFlex sparkFlex;

  public SparkFlex() {
    sparkFlex = new CANSparkFlex(7, MotorType.kBrushless);
    /* instantiates motor controller
     * deviceID is specific to the motor controller
     * motorType is always MotorType.kBrushless b/c we use brushless motors
     */

    sparkFlex.restoreFactoryDefaults();
    // resets config to defaults

    sparkFlex.setIdleMode(IdleMode.kBrake);
    // sets motor to brake when not doing anything, always do this

    sparkFlex.setInverted(true);
    /* sets the motor controller to be inverted or not
     * use case depends on where the motor is being used
     */

    sparkFlex.burnFlash();
    /* stores the config onto the motor controller itself
     * always do this when you're done configuring
     */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void run() {
    sparkFlex.set(1);
    /* sets the motor speed
     * range is -1 to 1 with 2 decimals precision
     * negative is backwards, positive is forwards
     * 0 is stationary
     */
  }
}
