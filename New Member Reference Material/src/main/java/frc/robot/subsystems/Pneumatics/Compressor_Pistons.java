// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Compressor_Pistons extends SubsystemBase {
  /** Configuration and example code for compressors and pistons */

  private final DoubleSolenoid piston;

  static Compressor compressor;

  public Compressor_Pistons() {

    compressor = new Compressor(PneumaticsModuleType.REVPH);
    /* instantiates compressor
     * PneumaticsModuleType is always REVPH, never CTREPCM
     */

    piston = new DoubleSolenoid(PneumaticsModuleType.REVPH, 3, 5);
    /* instantiates piston
     * PneumaticsModuleType is always REVPH, never CTREPCM
     * forward and reverse channel are based on the piston
     * configuring the compressor itself is not needed
     */

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runCompressor() {
    compressor.enableAnalog(60, 120);
    /* runs compressor
     * min should always be 60
     * max should be 120, could be higher if there are a lot of pistons
     */
  }

  public void togglePiston() {
    piston.toggle();
    // toggles piston state from open to closed or vice versa
  }

  public void openPiston() {
    piston.set(Value.kForward);
    /* extends piston
     * opening or closing depends on mechanism
     */
  }

  public void closePiston() {
    piston.set(Value.kReverse);
    /* retracts piston
     * opening or closing depends on mechanism
     */
  }
}
