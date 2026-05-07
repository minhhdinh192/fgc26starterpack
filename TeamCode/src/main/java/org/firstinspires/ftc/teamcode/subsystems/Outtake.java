package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Outtake {
    DcMotorEx rightOuttake, leftOuttake;

    public Outtake(HardwareMap hwMap) {
        rightOuttake = hwMap.get(DcMotorEx.class, Constants.rightOuttake);
        leftOuttake = hwMap.get(DcMotorEx.class, Constants.leftOuttake);
        leftOuttake.setDirection(DcMotorEx.Direction.REVERSE);
        rightOuttake.setVelocityPIDFCoefficients(Constants.outtakeP, Constants.outtakeI, Constants.outtakeD, Constants.outtakeF);
        leftOuttake.setVelocityPIDFCoefficients(Constants.outtakeP, Constants.outtakeI, Constants.outtakeD, Constants.outtakeF);
        rightOuttake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftOuttake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void launch(double power) {
        leftOuttake.setVelocity(power);
        rightOuttake.setVelocity(power);
    }
}
