package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Utilities;

public class Glider {
    DcMotor leftGlider, rightGlider;
    public Glider(HardwareMap hwMap) {
        leftGlider = hwMap.get(DcMotor.class, Constants.leftGlider);
        rightGlider = hwMap.get(DcMotor.class, Constants.rightGlider);
        leftGlider.setDirection(DcMotor.Direction.REVERSE);
        leftGlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightGlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void glide(double glidePower) {
        glidePower = Utilities.applyDeadzone(glidePower);
        leftGlider.setPower(glidePower);
        rightGlider.setPower(glidePower);
    }
}
