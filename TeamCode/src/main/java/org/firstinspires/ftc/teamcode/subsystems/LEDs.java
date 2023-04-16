package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class LEDs {

    private LinearOpMode myOpMode = null;

    public LEDs(LinearOpMode opMode) {

        myOpMode = opMode;
        lusp = myOpMode.hardwareMap.get(Servo.class, "lusp");

    }


    private Servo lusp;


    public void setColor(String Color) {

        String color[] = {"green", "red", "rainbow", "blue", "purple", "dark blue", "pink", "white", "yellow"};
        double colorID[] = {0.71, 0.67, 0.22, 0.65, 0.75, 0.73, 0.66, 0.77, 0.69};

        int colorIndex = Byte.MAX_VALUE;
        double res = 0;

        for (int i = 0; i < color.length; i++) {

            if (color[i].equals(Color)) {
                colorIndex = i;
                break;
            }
        }

        if(colorIndex != Byte.MAX_VALUE){

            res = colorID[colorIndex];
            lusp.setPosition(res);

        }
    }

    public void flash(String color1, String color2, ElapsedTime runTime) {

        if((int)runTime.seconds() %2 != 0){
            setColor(color1);

        }else{

            setColor(color2);
        }

    }

}