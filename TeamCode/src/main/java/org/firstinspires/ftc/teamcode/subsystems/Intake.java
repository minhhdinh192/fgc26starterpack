package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Intake {
    DcMotor intake;
    enum intakeState {in, out, idle, pullForLaunch};
    intakeState state = intakeState.idle;

    public Intake(HardwareMap hwMap) {
        intake = hwMap.get(DcMotor.class, Constants.intake);
        intake.setDirection(DcMotor.Direction.REVERSE);
    }

    public void update() {
        switch (state) {
            case in:
                intake.setPower(Constants.intakeIn);
                break;
            case out:
                intake.setPower(Constants.intakeOut);
                break;
            case pullForLaunch:
                intake.setPower(Constants.pullForLaunch);
                break;
            case idle:
                intake.setPower(Constants.intakeIdle);
                break;
        }
    }

    public void in() {
        state = intakeState.in;
    }

    public void out() {
        state = intakeState.out;
    }

    public void idle() {
        state = intakeState.idle;
    }

    public void pullForLaunch() {
        state = intakeState.pullForLaunch;
    }
}
