// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.tankDrive;

public class DriveCommand extends Command {
  /** Creates a new DriveCommand. */
  XboxController XboxController;
  tankDrive tank_drive;
  double x_dir;
  double speed;
  public DriveCommand(tankDrive tank_drive, double x_dir, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.tank_drive = tank_drive;
    this.x_dir = x_dir;
    this.speed = speed;
    addRequirements(tank_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tank_drive.getDifferentialDrive().arcadeDrive(speed, x_dir);
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
