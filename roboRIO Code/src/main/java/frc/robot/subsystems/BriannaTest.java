/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;

/**
 * Add your docs here.
 */
public class BriannaTest extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //Note: Quadrature encoders get both direction and speed

  static Encoder myEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  //SETTING PARAMETERS
    Encoder sampleEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X); // using a 4x sampling, which is the most accurate.
   sampleEncoder.setMaxPeriod(.1); // The max period where the device is considered moving. Is the time between pulses
   //(Set max) used to determine state of getStopped() method and effect the output of getPeriod() and getRate().
   sampleEncoder.setMinRate(10); // Sets min rate before device is stopped
   //(set min) compensates for scale factor and distance per pulse, should be entered in engineering units.
   sampleEncoder.setDistancePerPulse(5); // Sets scale factor between pulses and distance.
   //(set distance) ONLY BE SET EXCLUSIVELY BASED ON ENCODER'S Pulses per Revolution
   sampleEncoder.setReverseDirection(true); // Sets the direction enocder counts, used to flip direction if the encod
   sampleEncoder.setSamplesToAverage(7); // Sets number of samples to average. Averaging may be desired to account for mechanical imperfections (unevenly spaced reflectors)
   //(set samples to average) Valid values are 1 - 127 samples.
   
   //STARTING, STOPPING AND RESETTING ENCODERS
   Encoder sampleEncoder2 = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
   sampleEncoder2.reset(); // Encoder begins to count as soon it's created. To reset, call reset()
  
   //GETTING ENCODER VALUES
   Encoder sampleEncoder3 = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
   int count = sampleEncoder3.get(); // The current count
   double distance = sampleEncoder.getRaw(); // The count w/o compensation for decoding scale factor
   double distance2 = sampleEncoder.getDistance(); // Current distance reading from counter. Is multiplied by Distance Per Count scale factor
   double period = sampleEncoder.getPeriod(); // The current period of counter in seconds. If counter is stopped, value may be returned as 0.
   //RECOMMENDED TO USE RATE INSTEAD
   double rate = sampleEncoder.getRate(); // The current rate of the counter in units/secs. Calculated by DistancePerPulse/period.
   //If stopped, value may return as NaN (not a number)
   boolean direction = sampleEncoder.getDirection(); // The direction of the last value changed (true for Up, false for Down)
   boolean stopped = sampleEncoder.getStopped(); // If the counter is currently stopped
  
   
  } 

  //STARTING, STOPPING AND RESETTING ENCODERS
  Encoder sampleEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
  //sampleEncoder.reset(); // Encoder begins to count as soon it's created. To reset, call reset()
}
