// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.servohub.ServoHub.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ModuleConstants;

public class SwerveModule extends SubsystemBase {

  SparkFlex driveMotor;
  SparkMax turnMotor;

  SparkFlexConfig driveConfig;
  SparkMaxConfig turnConfig;

  RelativeEncoder driveEncoder;
  AbsoluteEncoder turnEncoder;

  SparkClosedLoopController drivePIDController;
  SparkClosedLoopController turnPIDController;

  double chassisAngularOffset = 0;
  SwerveModuleState desiredState = new SwerveModuleState(0.0, new Rotation2d());

  /** Creates a new SwerveModule. */
  public SwerveModule(int driveMotorID, int turnMotorID, double chassisAngularOffset) {

    driveMotor = new SparkFlex(driveMotorID, MotorType.kBrushless);
    turnMotor = new SparkMax(turnMotorID, MotorType.kBrushless);

    driveConfig = new SparkFlexConfig();
    turnConfig = new SparkMaxConfig();

    turnConfig.inverted(true);
    turnConfig.idleMode(IdleMode.kBrake);
    turnConfig.smartCurrentLimit(ModuleConstants.TURN_MOTOR_CURRENT_LIMIT);
    turnConfig.encoder.positionConversionFactor(ModuleConstants.TURN_ENCODER_POS_FACTOR);
    turnConfig.encoder.velocityConversionFactor(ModuleConstants.TURN_ENCODER_VELOCITY_FACTOR);
    turnConfig.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder);
    turnConfig.closedLoop.positionWrappingEnabled(true);
    turnConfig.closedLoop.positionWrappingMinInput(ModuleConstants.TURN_ENCODER_POS_MIN_INPUT);
    turnConfig.closedLoop.positionWrappingMaxInput(ModuleConstants.TURN_ENCODER_POS_MAX_INPUT);
    turnConfig.closedLoop.pid(ModuleConstants.TURN_P, ModuleConstants.TURN_I, ModuleConstants.TURN_D);
    turnConfig.closedLoop.outputRange(ModuleConstants.TURN_MIN_OUTPUT, ModuleConstants.TURN_MAX_OUTPUT);

    driveConfig.idleMode(IdleMode.kBrake);
    driveConfig.smartCurrentLimit(ModuleConstants.DRIVE_MOTOR_CURRENT_LIMIT);
    driveConfig.encoder.positionConversionFactor(ModuleConstants.DRIVE_ENCODER_POS_FACTOR);
    driveConfig.encoder.velocityConversionFactor(ModuleConstants.DRIVE_ENCODER_VELOCITY_FACTOR);
    driveConfig.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);
    driveConfig.closedLoop.pid(ModuleConstants.DRIVE_P, ModuleConstants.DRIVE_I, ModuleConstants.DRIVE_D);
    driveConfig.closedLoop.outputRange(ModuleConstants.DRIVE_MIN_OUTPUT, ModuleConstants.DRIVE_MAX_OUTPUT);

    driveMotor.configure(driveConfig, null, PersistMode.kPersistParameters);
    turnMotor.configure(driveConfig, null, PersistMode.kPersistParameters);

    driveEncoder = driveMotor.getEncoder();
    turnEncoder = turnMotor.getAbsoluteEncoder();

    drivePIDController = driveMotor.getClosedLoopController();
    turnPIDController = turnMotor.getClosedLoopController();

    this.chassisAngularOffset = chassisAngularOffset;
    desiredState.angle = new Rotation2d(0);
    driveEncoder.setPosition(0);
    setDesiredState(desiredState);
  }

 public void setDesiredState(SwerveModuleState desired) {
    desired.angle = desired.angle.plus(Rotation2d.fromRadians(chassisAngularOffset));
    desiredState = desired;
    desiredState.optimize(Rotation2d.fromRadians(turnEncoder.getPosition()));
    drivePIDController.setReference(desiredState.speedMetersPerSecond, ControlType.kVelocity);
    turnPIDController.setReference(desiredState.angle.getRadians(), ControlType.kPosition);
  }

  public double getDesiredSpeed() {
    return desiredState.speedMetersPerSecond;
  }

  public double getActualSpeed() {
    return driveEncoder.getVelocity();
  }

  public SwerveModulePosition getModulePosition() {
    return new SwerveModulePosition(driveEncoder.getPosition(), Rotation2d.fromRadians(turnEncoder.getPosition() - chassisAngularOffset));
  }

  public SwerveModuleState getState() {
    return new SwerveModuleState(driveEncoder.getVelocity(), Rotation2d.fromRadians(turnEncoder.getPosition() - chassisAngularOffset));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
