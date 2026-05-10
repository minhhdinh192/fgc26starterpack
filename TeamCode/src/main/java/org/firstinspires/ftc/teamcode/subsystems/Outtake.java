package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Outtake {
    DcMotorEx rightOuttake, leftOuttake;
    double outtakePower = Constants.outtakePower;
    double outtakeIdle = Constants.outtakeIdle;
    enum outtakeState {on, off};
    outtakeState state = outtakeState.off;

    public Outtake(HardwareMap hwMap) {
        rightOuttake = hwMap.get(DcMotorEx.class, Constants.rightOuttake);
        leftOuttake = hwMap.get(DcMotorEx.class, Constants.leftOuttake);

        rightOuttake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftOuttake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        leftOuttake.setDirection(DcMotorEx.Direction.REVERSE);

        rightOuttake.setVelocityPIDFCoefficients(Constants.outtakeP, Constants.outtakeI, Constants.outtakeD, Constants.outtakeF);
        leftOuttake.setVelocityPIDFCoefficients(Constants.outtakeP, Constants.outtakeI, Constants.outtakeD, Constants.outtakeF);

        rightOuttake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftOuttake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void update() {
        switch (state) {
            case on:
                rightOuttake.setVelocity(outtakePower);
                leftOuttake.setVelocity(outtakePower);
                break;
            case off:
                leftOuttake.setVelocity(outtakeIdle);
                rightOuttake.setVelocity(outtakeIdle);
        }
    }

    public void on() {
        state = outtakeState.on;
    }

    public void off() {
        state = outtakeState.off;
    }

    public boolean isReadyToShoot() {
        double currentPower = Math.max(leftOuttake.getVelocity(), rightOuttake.getVelocity());
        double readyToShootPower = Constants.outtakePower - Constants.outtakeTolerance;
        return currentPower >= readyToShootPower;
    }
}
