package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class collection {


    public collection(HardwareMap hardwareMap) {



        clawRight = hardwareMap.get(Servo.class, "clawRight");
        clawLeft = hardwareMap.get(Servo.class, "clawLeft");

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



}