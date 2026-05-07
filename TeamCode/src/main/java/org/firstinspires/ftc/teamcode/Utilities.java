package org.firstinspires.ftc.teamcode;

public class Utilities {
    public static double applyDeadzone(double value) {
        return Math.abs(value) < Constants.deadzone ? 0.0 : value;
    }
}
