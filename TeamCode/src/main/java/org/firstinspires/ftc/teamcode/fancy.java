package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;



import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.subsystems.drive;
import org.firstinspires.ftc.teamcode.subsystems.delivery;
import org.firstinspires.ftc.teamcode.subsystems.collection;

@TeleOp

public class fancy extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    public void runOpMode() throws InterruptedException {

        drive jihyo = new drive(this);
        collection momo = new collection(this);
        delivery tzuyu = new delivery(this);


        ColorSensor clawSensor = hardwareMap.colorSensor.get("colorSensor1");
        ColorSensor guideSensor = hardwareMap.colorSensor.get("colorSensor2");

        int i = 0;

        waitForStart();
        if (isStopRequested()) return;

        while(opModeIsActive()){

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            double pFrontRight  =   (y + x + rx) / denominator;
            double pBackRight   =   (y - x + rx) / denominator;
            double pFrontLeft   =   (y - x - rx) / denominator;
            double pBackLeft    =   (y + x - rx) / denominator;

            double fallSpeed    =  0.8;
            double liftSpeed    =  0.8;


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

            else if (gamepad2.cross){
                momo.closeClaw();
            }

            if (gamepad2.dpad_down){
                tzuyu.retract(fallSpeed);
            }

            else if(gamepad2.dpad_up){
                tzuyu.extend(liftSpeed);
            }

            else{
                tzuyu.stall();
            }

            if (runtime.seconds() > 79 && runtime.seconds() < 91) {
                momo.flash("red","blue", runtime);
            }

            else {
                if (((DistanceSensor) clawSensor).getDistance(DistanceUnit.MM) < 35 ^
                        ((DistanceSensor) guideSensor).getDistance(DistanceUnit.MM) < 35) {

                    momo.setColor("yellow");
                    if (i == 0){

                        gamepad1.rumble(500);
                        gamepad2.rumble(500);
                        ++i;

                    }
                }

                else if (((DistanceSensor) clawSensor).getDistance(DistanceUnit.MM) < 35 &&
                            ((DistanceSensor) guideSensor).getDistance(DistanceUnit.MM) < 35){

                    momo.setColor("green");
                    if(i==1){

                        gamepad1.rumble(500);
                        gamepad2.rumble(500);
                        ++i;

                    }
                }

                else {

                    momo.setColor("red");
                    i = 0;

                }
            }
        }
    }
}