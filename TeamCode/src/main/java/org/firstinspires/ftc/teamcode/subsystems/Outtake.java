package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Outtake {
    DcMotorEx rightOuttake, leftOuttake;
    double defaultPower = Constants.outtakePower;

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

    public double powerCalculator(double dis, double pos) {
        double power = dis * pos + 1200;
        //nháp chứ đéo như này đâu ra đề tính lại sau
        return power;
    }

    public void launchDefaultPow() {
        leftOuttake.setVelocity(defaultPower);
        rightOuttake.setVelocity(defaultPower);
    }
}
