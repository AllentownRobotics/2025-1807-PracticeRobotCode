// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ModuleConstants;

public class SwerveModule extends SubsystemBase {

    CANSparkMax turningMotor;
    CANSparkFlex drivingMotor;

    AbsoluteEncoder turningEncoder;
    RelativeEncoder drivingEncoder;

    SparkPIDController turningPIDController;
    SparkPIDController drivingPIDController;

    double chassisAngularOffset = 0;
    SwerveModuleState desiredState = new SwerveModuleState(0.0, new Rotation2d());

  /** Creates a new SwerveModule. */
  public SwerveModule(int drivingCANID, int turningCANID, double chassisAngularOffset) {
    
    drivingMotor = new CANSparkFlex(drivingCANID, MotorType.kBrushless);
    turningMotor = new CANSparkMax(turningCANID, MotorType.kBrushless);

    drivingMotor.restoreFactoryDefaults();
    turningMotor.restoreFactoryDefaults();

    drivingEncoder = drivingMotor.getEncoder();
    turningEncoder = turningMotor.getAbsoluteEncoder(Type.kDutyCycle);

    drivingPIDController = drivingMotor.getPIDController();
    turningPIDController = turningMotor.getPIDController();
    drivingPIDController.setFeedbackDevice(drivingEncoder);

    drivingEncoder.setPositionConversionFactor(ModuleConstants.DRIVE_ENCODER_POS_FACTOR);
    drivingEncoder.setVelocityConversionFactor(ModuleConstants.DRIVE_ENCODER_VELOCITY_FACTOR);
    turningEncoder.setPositionConversionFactor(ModuleConstants.TURN_ENCODER_POS_FACTOR);
    turningEncoder.setVelocityConversionFactor(ModuleConstants.TURN_ENCODER_VELOCITY_FACTOR);

    drivingPIDController.setP(ModuleConstants.DRIVE_P);
    drivingPIDController.setI(ModuleConstants.DRIVE_I);
    drivingPIDController.setD(ModuleConstants.DRIVE_D);
    drivingPIDController.setFF(ModuleConstants.DRIVE_FF);
    drivingPIDController.setOutputRange(ModuleConstants.DRIVE_MIN_OUTPUT, ModuleConstants.DRIVE_MAX_OUTPUT);

    // Set the PID gains for the turning motor. Note these are example gains, and you
    // may need to tune them for your own robot!
    turningPIDController.setP(ModuleConstants.TURN_P);
    turningPIDController.setI(ModuleConstants.TURN_I);
    turningPIDController.setD(ModuleConstants.TURN_D);

    drivingMotor.setIdleMode(ModuleConstants.DRIVE_MOTOR_IDLE_MODE);
    turningMotor.setIdleMode(ModuleConstants.TURN_MOTOR_IDLE_MODE);
    drivingMotor.setSmartCurrentLimit(ModuleConstants.DRIVE_MOTOR_CURRENT_LIMIT);
    turningMotor.setSmartCurrentLimit(ModuleConstants.TURN_MOTOR_CURRENT_LIMIT);

    // Save the SPARK MAX configurations. If a SPARK MAX browns out during
    // operation, it will maintain the above configurations.
    drivingMotor.burnFlash();
    turningMotor.burnFlash();

    this.chassisAngularOffset = chassisAngularOffset;
    desiredState.angle = new Rotation2d(turningEncoder.getPosition());
    drivingEncoder.setPosition(0);
  }

  public void setDesiredState(SwerveModuleState desired) {
    desired.angle = desired.angle.plus(Rotation2d.fromRadians(chassisAngularOffset));
    desiredState = desired;
    desiredState = SwerveModuleState.optimize(desired, Rotation2d.fromRadians(turningEncoder.getPosition()));
    drivingPIDController.setReference(desiredState.speedMetersPerSecond, ControlType.kVelocity);
    turningPIDController.setReference(desiredState.angle.getRadians(), ControlType.kPosition);
  }

  public double getDesiredSpeed() {
    return desiredState.speedMetersPerSecond;
  }

  public double getActualSpeed() {
    return drivingEncoder.getVelocity();
  }

  public SwerveModulePosition getModulePosition() {
    return new SwerveModulePosition(drivingEncoder.getPosition(), Rotation2d.fromRadians(turningEncoder.getPosition() - chassisAngularOffset));
  }

  public SwerveModuleState getState() {
    return new SwerveModuleState(drivingEncoder.getVelocity(), Rotation2d.fromRadians(turningEncoder.getPosition()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
