package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Utilities;

public class Glider {
    DcMotor glider;
    public Glider(HardwareMap hwMap) {
        glider = hwMap.get(DcMotor.class, Constants.glider);
        glider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void glide(double glidePower) {
        glidePower = Utilities.applyDeadzone(glidePower);
        glider.setPower(glidePower);
    }
}
