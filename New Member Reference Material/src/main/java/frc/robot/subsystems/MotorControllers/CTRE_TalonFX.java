// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.MotorControllers;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CTRE_TalonFX extends SubsystemBase {
  /** Configuration and example code for TalonFX motor controllers */

  private final TalonFX talonFX;
  
  public CTRE_TalonFX() {

    talonFX = new TalonFX(7);
    /* intantiates motor controller
     * deviceID is specific to the motor controller 
    */

    talonFX.setNeutralMode(NeutralModeValue.Brake);
    // sets motor to brake when not doing anything, always do this
    talonFX.setInverted(true);
    /* sets if motor should run forwards or backwards
     * use case depends on where the motor is being used
    */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void run() {
    talonFX.set(1);
    /* sets motor speed
     * value ranges from -1 to 1 to 2 decimals
     * -1 is full speed backwards, 1 is full speed forwards, 0 is still 
     */
  }
}
