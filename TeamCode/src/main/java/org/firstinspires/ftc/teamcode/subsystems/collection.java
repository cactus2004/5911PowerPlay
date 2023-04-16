package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class collection {

    private LinearOpMode myOpMode = null;

    public collection(LinearOpMode opMode) {

        myOpMode = opMode;


        clawRight = myOpMode.hardwareMap.get(Servo.class, "clawRight");
        clawLeft = myOpMode.hardwareMap.get(Servo.class, "clawLeft");

        clawLeft.setDirection(Servo.Direction.REVERSE);

        clawRight.setPosition(0);
        clawLeft.setPosition(0);

    }

    private Servo clawRight;
    private Servo clawLeft;



    public void openClaw(){

        clawRight.setPosition(0);
        clawLeft.setPosition(0);

    }

    public void closeClaw(){
        clawRight.setPosition(1);
        clawLeft.setPosition(1);
    }

    public void clawTest(){

        if(myOpMode.gamepad2.cross) {

            clawRight.setPosition(clawRight.getPosition() + 0.01);
            clawLeft.setPosition(clawLeft.getPosition() + 0.01);

            myOpMode.telemetry.addData("<CR:", clawRight.getPosition());
            myOpMode.telemetry.addData("<CL:", clawLeft.getPosition());

            myOpMode.telemetry.update();
        }

        else if (myOpMode.gamepad2.triangle){

            clawRight.setPosition(clawRight.getPosition() - 0.01);
            clawLeft.setPosition(clawLeft.getPosition() - 0.01);

            myOpMode.telemetry.addData("<CR:", clawRight.getPosition());
            myOpMode.telemetry.addData("<CL:", clawLeft.getPosition());

            myOpMode.telemetry.update();
        }

        else{
            myOpMode.telemetry.addData("<CR:", clawRight.getPosition());
            myOpMode.telemetry.addData("<CL:", clawLeft.getPosition());

            myOpMode.telemetry.update();
        }
    }

}