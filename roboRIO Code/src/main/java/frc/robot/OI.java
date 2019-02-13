package frc.robot;

import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public Joystick leftStick = new Joystick(0);
  public Joystick rightStick = new Joystick(1);

  public JoystickButton hatchButton = new JoystickButton(leftStick, 1);

  public XboxController mainController = new XboxController(0);

  public DigitalInput 
  digitalLS1 = new DigitalInput(5),
  digitalLS2 = new DigitalInput(6),
  digitalLS3 = new DigitalInput(7),
  digitalLS4 = new DigitalInput(8),
  digitalLS5 = new DigitalInput(9);




  // public JoystickButton 
  // button1 = new JoystickButton(leftStick, 6),
  // button2 = new JoystickButton(leftStick, 7),
  // button3 = new JoystickButton(leftStick, 8),
  // button4 = new JoystickButton(leftStick, 9),
  // button5 = new JoystickButton(leftStick, 10),
  // button6 = new JoystickButton(leftStick, 11);

  public OI(){
    while(true){
      button1.whenPressed(new LiftToSpecificHeight(1));
      button2.whenPressed(new LiftToSpecificHeight(2));
      button3.whenPressed(new LiftToSpecificHeight(3));
      button4.whenPressed(new LiftToSpecificHeight(4));
      button5.whenPressed(new LiftToSpecificHeight(5));
      button6.whenPressed(new LiftToSpecificHeight(6));
    }
  }
}
