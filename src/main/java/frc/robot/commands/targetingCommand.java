// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.tankDrive;

public class targetingCommand extends Command {
  /** Creates a new targetingCommand. */
  LimeLight lime_light;
  tankDrive tank_drive;
  PIDController controller;
  public targetingCommand(LimeLight lime_light, tankDrive tank_drive) {
    this.lime_light = lime_light;
    this.tank_drive = tank_drive;
    controller = new PIDController(.02, 0, 0);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(lime_light, tank_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tank_drive.getDifferentialDrive().arcadeDrive(0, -controller.calculate(lime_light.getX()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
