// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.tankDrive;

public class targetingCommand extends Command {
  /** Creates a new targetingCommand. */
  LimeLight lime_light;
  tankDrive tank_drive;
  public targetingCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(lime_light, tank_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    new DriveCommand(tank_drive, LimeLight.getX(), 0);
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
