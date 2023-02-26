package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveOdometry;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import com.arcrobotics.ftclib.geometry.Translation2d;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class odo extends LinearOpMode {


    static final double TRACKWIDTH = 13.7;
    static final double TICKS_TO_INCHES  = 32.38239418;

    Translation2d frontLeftLocation =
            new Translation2d(0.381, 0.381);

    Translation2d frontRightLocation =
            new Translation2d(0.381, -0.381);

    Translation2d backLeftLocation =
            new Translation2d(-0.381, 0.381);

    Translation2d backRightLocation =
            new Translation2d(-0.381, -0.381);


    MecanumDriveKinematics kinematics = new MecanumDriveKinematics
            (
                    frontLeftLocation, frontRightLocation,
                    backLeftLocation, backRightLocation
            );

    private Motor frontRight;
    private Motor backRight;
    private Motor frontLeft;
    private Motor backLeft;



    public void runOpMode(){


        frontRight = new Motor(hardwareMap, "frontRight", Motor.GoBILDA.RPM_435);
        backRight = new Motor(hardwareMap, "backRight", Motor.GoBILDA.RPM_435);
        frontLeft = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        backLeft = new Motor(hardwareMap, "backLeft", Motor.GoBILDA.RPM_435);


        MecanumDrive mecanum = new MecanumDrive(frontLeft, frontRight,
                backLeft, backRight);


    }
}
