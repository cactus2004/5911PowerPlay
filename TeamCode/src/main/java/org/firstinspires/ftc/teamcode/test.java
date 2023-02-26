package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import org.firstinspires.ftc.teamcode.subsystems.drive;
import org.firstinspires.ftc.teamcode.subsystems.delivery;
import org.firstinspires.ftc.teamcode.subsystems.collection;

@TeleOp

public class test extends LinearOpMode {

    drive jihyo = new drive(this);
    collection momo = new collection(this);
    delivery joy = new delivery(this);

    private ColorSensor colorSensor;

    double y = -gamepad1.left_stick_y;
    double x = gamepad1.left_stick_x * 1.1;
    double rx = gamepad1.right_stick_x;

    double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

    double pFrontRight  =   (y + x + rx) / denominator;
    double pBackRight   =   (y - x + rx) / denominator;
    double pFrontLeft   =   (y - x - rx) / denominator;
    double pBackLeft    =   (y + x - rx) / denominator;

    double liftSpeed    =   0.8;
    double fallSpeed    =   0.8;

    public void runOpMode() throws InterruptedException {



        colorSensor = hardwareMap.colorSensor.get("colorSensor");

        waitForStart();
        if (isStopRequested()) return;
        while(opModeIsActive()){


            if(gamepad1.left_bumper) {
                jihyo.drive(pFrontRight - 0.5, pBackRight + 0.5,
                        pFrontLeft + 0.5, pBackLeft - 0.5);
            }

            else if (gamepad1.right_bumper){
                jihyo.drive(pFrontRight + 0.5, pBackRight - 0.5,
                        pFrontLeft - 0.5, pBackLeft + 0.5);
            }

            else{
                jihyo.drive(pFrontRight, pBackRight ,pFrontLeft , pBackLeft);
            }


            if(gamepad2.triangle){
                momo.openClaw();
            }

            else if (gamepad2.circle){
                momo.closeClaw();
            }





        }
    }
}
