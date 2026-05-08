package org.firstinspires.ftc.teamcode;

public class Utilities {
    public static double applyDeadzone(double value) {
        double deadzoneVal = Constants.deadzone;
        if (Math.abs(value) < deadzoneVal) return 0.0;
        else return value;
    }
}
