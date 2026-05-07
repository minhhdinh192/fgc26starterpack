package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

import java.util.*;

public class Hood {
    Servo hood;
    double hoodPos = Constants.startPos;
    double a = Constants.mulA; double b = Constants.mulB; double c = Constants.mulC;

    public Hood(HardwareMap hwMap) {
        hood = hwMap.get(Servo.class, Constants.hood);
        update();
    }

    public double adjustHood(double distance){
        double adjustPos = a * (Math.pow(distance, 2)) + b * distance + c;
        return adjustPos;
    }

    public void setPos(double pos){
        hoodPos = pos;
    }

    public void update(){
        hood.setPosition(hoodPos);
    }
}
