package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class collection {

    private LinearOpMode myOpMode = null;

    private Servo clawRight;
    private Servo clawLeft;
    private Servo lusp;

    public collection(LinearOpMode opMode) {

        myOpMode = opMode;

        lusp = myOpMode.hardwareMap.get(Servo.class, "lusp");
        clawRight = myOpMode.hardwareMap.get(Servo.class, "clawRight");
        clawLeft = myOpMode.hardwareMap.get(Servo.class, "clawLeft");

        clawLeft.setDirection(Servo.Direction.REVERSE);

        clawRight.setPosition(0);
        clawLeft.setPosition(0);


    }


    public void openClaw() {

        clawRight.setPosition(0.5);
        clawLeft.setPosition(0.5);

    }

    public void closeClaw() {
        clawRight.setPosition(0.5);
        clawLeft.setPosition(0.5);
    }

    public void setColor(String Color) {

        String color[] = {"green", "red", "rainbow"};
        double colorID[] = {0.71, 0.61, -0.99};

        int colorIndex = Byte.MAX_VALUE;

        for (int i = 0; i < color.length; i++) {

            if (color[i].equals(Color)) {
                colorIndex = i;
                break;
            }
        }

        if (colorIndex != Byte.MAX_VALUE) {

            lusp.setPosition(colorID[colorIndex]);

        }
    }

    public void flash(String color1, String color2, ElapsedTime runTime) {

       if((int)runTime.seconds() %2 == 0){
           setColor(color1);

        }else{

           setColor(color2);
       }

    }

    public void clawTest(){

        if(myOpMode.gamepad2.square) {

            clawRight.setPosition(clawRight.getPosition() + 0.01);
            clawLeft.setPosition(clawLeft.getPosition() + 0.01);

            myOpMode.telemetry.addData("<CR:", clawRight.getPosition());
            myOpMode.telemetry.addData("<CL:", clawLeft.getPosition());

            myOpMode.telemetry.update();
        }

        else if (myOpMode.gamepad2.circle){

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





