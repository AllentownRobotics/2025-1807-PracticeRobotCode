// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.MotorControllers;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CANSparkMax extends SubsystemBase {
  /** Configuration and example code for SparkMax motor controllers */

  private final SparkMax sparkMax;
  private final SparkMaxConfig config;

    public CANSparkMax() {
      sparkMax = new SparkMax(Constants.sparkMaxMotorID, MotorType.kBrushless);
      /* instantiates motor controller
       * deviceID is specific to the motor controller
       * motorType is always MotorType.kBrushless b/c we use brushless motors
       */
      
      config = new SparkMaxConfig();
      // instantiates configuration

       config
      .inverted(true)
      // configures if the motor is inverted
      // depends on use case of motor
      .idleMode(IdleMode.kBrake);
      // sets brake mode of motor (always use brake)

      sparkMax.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
      // loads configuration onto motor controller itself
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
