package org.firstinspires.ftc.teamcode.subsystems;

import java.util.*;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.arcrobotics.ftclib.controller.PIDFController;


public class delivery {

    private LinearOpMode myOpMode = null;

    private DcMotorEx liftRight;
    private DcMotorEx liftLeft;

    private PIDController controller;

    public static double p = 0.05, i = 0, d = 0;
    public static double f = 0;

    public static int target = 0;


    public delivery(LinearOpMode opMode) {
        myOpMode = opMode;

        liftRight = myOpMode.hardwareMap.get(DcMotorEx.class,"liftRight");
        liftLeft = myOpMode.hardwareMap.get(DcMotorEx.class,"liftLeft");


        liftRight.setDirection(DcMotorSimple.Direction.FORWARD); //this might not be needed; or the left slide should be the one being reversed
        liftLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        liftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }




    public void extend(double liftSpeed) {

        liftRight.setPower(Math.abs(liftSpeed));
        liftLeft.setPower(Math.abs(liftSpeed));

    }

    public void retract(double slideSpeed) {

        liftRight.setPower(-Math.abs(slideSpeed) / 1.5);
        liftLeft.setPower(-Math.abs(slideSpeed) / 1.5);

    }


    public void stall() {

        liftRight.setPower(0.1);
        liftLeft.setPower(0.1);

    }






    public void moveToStage(int stage, ElapsedTime runtime) {


        controller = new PIDController(p,i,d);
        int liftPosition = liftRight.getCurrentPosition();

        double pid = controller.calculate(liftPosition, target);
        double ff = 0.1;

        int Position[] = {0, 30, 60, 90};
        int Stage[] = {1, 2, 3, 4};

        int stageIndex = Byte.MAX_VALUE;

        for (int i = 0; i < Stage.length; i++) {

            if (Stage[i] == stage) {
                stageIndex = i;
                break;
            }
        }

        if (stageIndex != Byte.MAX_VALUE) {

             target = Position[stageIndex];
             double power = pid + ff;

             liftRight.setPower(power);
             liftLeft.setPower(power);
             
            }
        }
    }






