// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class tankDrive extends SubsystemBase {
  /** Creates a new tankDrive. */
  SparkMax fL;
  SparkMax fR;
  SparkMax bL;
  SparkMax bR;
  SparkMaxConfig fLConfig;
  SparkMaxConfig fRConfig;
  SparkMaxConfig bLConfig;
  SparkMaxConfig bRConfig;
  DifferentialDrive tank_Drive;
  public tankDrive() {
    fL = new SparkMax(Constants.DriveConstants.fLdriveID, MotorType.kBrushless);
    fR = new SparkMax(Constants.DriveConstants.fRdriveID, MotorType.kBrushless);
    bR = new SparkMax(Constants.DriveConstants.bLdriveID, MotorType.kBrushless);
    bL = new SparkMax(Constants.DriveConstants.bRdriveID, MotorType.kBrushless);

    fLConfig = new SparkMaxConfig();
    fL.configure(fLConfig, null, null);

    fRConfig = new SparkMaxConfig();
    fR.configure(fRConfig, null, null);

    bLConfig = new SparkMaxConfig();
    bL.configure(bLConfig, null, null);

    bRConfig = new SparkMaxConfig();
    bR.configure(bRConfig, null, null);

    bLConfig.follow(Constants.DriveConstants.fLdriveID);
    bRConfig.follow(Constants.DriveConstants.fRdriveID);
    fLConfig.inverted(true);

    tank_Drive = new DifferentialDrive(fL, fR);
  }

  public DifferentialDrive getDifferentialDrive(){
    return tank_Drive;
  }
    
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
