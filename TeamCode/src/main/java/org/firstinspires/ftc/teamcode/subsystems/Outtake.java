package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Outtake {
    DcMotorEx rightOuttake, leftOuttake;
    double outtakePower = Constants.outtakePower;
    double outtakeIdle = Constants.outtakeIdle;

    public Outtake(HardwareMap hwMap) {
        rightOuttake = hwMap.get(DcMotorEx.class, Constants.rightOuttake);
        leftOuttake = hwMap.get(DcMotorEx.class, Constants.leftOuttake);
        leftOuttake.setDirection(DcMotorEx.Direction.REVERSE);
        rightOuttake.setVelocityPIDFCoefficients(Constants.outtakeP, Constants.outtakeI, Constants.outtakeD, Constants.outtakeF);
        leftOuttake.setVelocityPIDFCoefficients(Constants.outtakeP, Constants.outtakeI, Constants.outtakeD, Constants.outtakeF);
        rightOuttake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftOuttake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void initiatePower() {
        leftOuttake.setVelocity(outtakePower);
        rightOuttake.setVelocity(outtakePower);
    }

    public void idle() {
        leftOuttake.setVelocity(outtakeIdle);
        rightOuttake.setVelocity(outtakeIdle);
    }

    public boolean isReadyToShoot() {
        double currentPower = Math.max(leftOuttake.getVelocity(), rightOuttake.getVelocity());
        double readyToShootPower = Constants.outtakePower - Constants.outtakeTolerance;
        return currentPower >= readyToShootPower;
    }
}
