// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;



public class Robot extends TimedRobot {

WPI_VictorSPX frontleft = new WPI_VictorSPX(31);
WPI_VictorSPX frontRight = new WPI_VictorSPX(5);
WPI_VictorSPX rearLeft = new WPI_VictorSPX(3);
WPI_VictorSPX rearRight = new WPI_VictorSPX(7);
WPI_VictorSPX midRight = new WPI_VictorSPX(6);
WPI_VictorSPX midLeft = new WPI_VictorSPX(1);

WPI_VictorSPX intake = new WPI_VictorSPX(4);
WPI_VictorSPX feeder = new WPI_VictorSPX(2);

CANSparkMax shooter = new CANSparkMax(11, MotorType.kBrushless) ;


MotorControllerGroup leftMotorControllerGroup = new MotorControllerGroup(frontleft, midLeft, rearLeft) ;
MotorControllerGroup rightMotorControllerGroup = new MotorControllerGroup(frontRight, midRight, rearRight) ;


Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
Solenoid solenoid_forward = new Solenoid(PneumaticsModuleType.CTREPCM, 7);
Solenoid solenoid_back = new Solenoid(PneumaticsModuleType.CTREPCM, 4);

RelativeEncoder relativeEncoder = shooterSparkMax.getEncoder();


DifferentialDrive drive = new DifferentialDrive(leftMotorControllerGroup, rightMotorControllerGroup);

Joystick joystick = new Joystick(0);
Joystick stick = new Joystick(1);


  @Override
  public void robotInit() {
    compressor.enableDigital();   
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}
  
  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    drive.arcadeDrive(- joystick.getRawAxis(1)*0.60, joystick.getRawAxis(4)*0.60);

    if(stick.getRawButton(1)){
      intake.set(1);
    }

    else if (stick.getRawButtonReleased(1)){
      intake.set(0);
    }

    if(stick.getRawButton(2)){
      intake.set(-1); 
    }
    
    else if(stick.getRawButtonReleased(2)){
      intake.set(0);
    }
    
    if(stick.getRawButton(3)){
      solenoid_forward.set(true);
      solenoid_back.set(false); 
    }
    else if(stick.getRawButton(4)){
      solenoid_forward.set(false);
      solenoid_back.set(true);
    }

     if(joystick.getRawButton(1)){
      shooter.set(1);
    }

    else if(joystick.getRawButtonReleased(1)){
      shooter.set(0);
    }

    if(joystick.getRawButton(2)){
      feeder.set(1);
    }

    else if(joystick.getRawButtonReleased(2)){
      feeder.set(0);
    }

    if(joystick.getRawButton(3)){
      feeder.set(-1);
    }

    else if(joystick.getRawButtonReleased(3)){
      feeder.set(0);
    }














  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
