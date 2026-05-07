package org.firstinspires.ftc.teamcode.subsystems.Extension;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class Lift {

    private final DcMotorEx leftLift;
    private final DcMotorEx rightLift;

    public static final int home = 0;
    public static final int low = 400;
    public static final int mid  = 800;
    public static final int high = 1600;

    private int target = home;

    private double kP = 0.0035;
    private double kD = 0.00015;

    private double holdPower = 0.05;

    private double syncKp = 0.001;

    private int lastError = 0;

    private static final double MANUAL_DEADBAND = 0.08;

    public Lift(HardwareMap hardwareMap) {

        leftLift = hardwareMap.get(DcMotorEx.class, "leftLift");
        rightLift = hardwareMap.get(DcMotorEx.class, "rightLift");

        rightLift.setDirection(DcMotorSimple.Direction.REVERSE);

        setupMotor(leftLift);
        setupMotor(rightLift);
    }

    private void setupMotor(DcMotorEx motor) {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void update() {

        int current = getPosition();
        int error = target - current;
        int derivative = error - lastError;

        double basePower =
                (error * kP) +
                        (derivative * kD);

        if (target > 20) {
            basePower += holdPower;
        }

        int leftPos = leftLift.getCurrentPosition();
        int rightPos = rightLift.getCurrentPosition();

        int syncError = leftPos - rightPos;
        double syncCorrection = syncError * syncKp;

        double leftPower = basePower - syncCorrection;
        double rightPower = basePower + syncCorrection;

        leftPower = Range.clip(leftPower, -1.0, 1.0);
        rightPower = Range.clip(rightPower, -1.0, 1.0);

        leftLift.setPower(leftPower);
        rightLift.setPower(rightPower);

        lastError = error;
    }

    public void manual(double input) {

        if (Math.abs(input) < MANUAL_DEADBAND) {
            return;
        }

        leftLift.setPower(input);
        rightLift.setPower(input);

        target = getPosition();
    }

    public void goHome() {
        target = home;
    }

    public void goLow() {
        target = low;
    }

    public void goMid() {
        target = mid;
    }

    public void goHigh() {
        target = high;
    }

    public void setTarget(int ticks) {
        target = ticks;
    }

    public int getTarget() {
        return target;
    }

    public int getPosition() {
        return (leftLift.getCurrentPosition()
                + rightLift.getCurrentPosition()) / 2;
    }

    public boolean isAtTarget(int tolerance) {
        return Math.abs(target - getPosition()) <= tolerance;
    }

    public void resetEncoder() {
        leftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        target = home;
    }

    public void stop() {
        leftLift.setPower(0);
        rightLift.setPower(0);
    }

    public void setKP(double kP) {
        this.kP = kP;
    }

    public void setKD(double kD) {
        this.kD = kD;
    }

    public void setHoldPower(double holdPower) {
        this.holdPower = holdPower;
    }

    public void setSyncKp(double syncKp) {
        this.syncKp = syncKp;
    }
}