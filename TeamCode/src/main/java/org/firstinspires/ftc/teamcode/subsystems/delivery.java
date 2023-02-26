package org.firstinspires.ftc.teamcode.subsystems;


import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class delivery {

    private LinearOpMode myOpMode = null;

    public delivery(LinearOpMode opMode) {
        myOpMode = opMode;

        double p = 0.2;
        double i = 0.0;
        double d = 0.005;

        liftRight = new Motor(myOpMode.hardwareMap, "liftRight", Motor.GoBILDA.RPM_435);
        liftLeft = new Motor(myOpMode.hardwareMap, "liftLeft", Motor.GoBILDA.RPM_435);

        liftRight.setInverted(false); //this might not be needed; or the left slide should be the one being reversed
        liftLeft.setInverted(true);

        liftLeft.setRunMode(Motor.RunMode.RawPower);
        liftRight.setRunMode(Motor.RunMode.RawPower);

        liftRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        liftLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        controller = new PIDController(p, i, d);
        reset();

    }

    private Motor liftRight;
    private Motor liftLeft;

    private PIDController controller;

    public void extend(double liftSpeed) {

        liftRight.set(Math.abs(liftSpeed));
        liftLeft.set(Math.abs(liftSpeed));

    }

    public void retract(double slideSpeed) {

        liftRight.set(-Math.abs(slideSpeed));
        liftLeft.set(-Math.abs(slideSpeed));

    }


    public void stall() {

        liftRight.set(0.1);
        liftLeft.set(0.1);

    }

    public void moveToStage(String stage) {

        int slidePosition = liftRight.getCurrentPosition();

        int Position[] = {-75, 1000, 1750, 2500};
        String Stage[] = {"GROUND", "LOW", "MID", "HIGH"};

        int stageIndex = Byte.MAX_VALUE;
        int res = 0;


        for (int i = 0; i < Stage.length; i++) {

            if (Stage[i].equals(stage)) {
                stageIndex = i;
                break;
            }
        }

        if (stageIndex != Byte.MAX_VALUE) {

            res = Position[stageIndex];
            double pid = controller.calculate(slidePosition, res);

            int error = res - slidePosition;

            double power = pid + 0.1;

            if (Math.abs(error) > 100) {

                liftRight.set(power);
                liftLeft.set(power);

            } else {

                stall();

            }

            myOpMode.telemetry.addData("error", error);
            myOpMode.telemetry.addData("target", res);
            myOpMode.telemetry.addData("slidePosition", slidePosition);
            myOpMode.telemetry.update();

        }
    }

    public void reset(){

        liftRight.resetEncoder();

    }
}