// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ColorSensor;

public class ColorSensorCommand extends Command {
  ColorSensor colorSensor;
  /** Creates a new ColorSensorCommand. */
  public ColorSensorCommand(ColorSensor colorSensor) {
    this.colorSensor = colorSensor;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(colorSensor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    colorSensor.sendColorToElastic();
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
