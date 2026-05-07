package org.firstinspires.ftc.teamcode.subsystems.Scoring;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Intake {
    CRServo intake;
    public static enum intakeState {in, out, idle};
    intakeState state = intakeState.idle;

    public Intake(HardwareMap hwMap) {
        intake = hwMap.get(CRServo.class, Constants.intake);
        intake.setDirection(CRServo.Direction.REVERSE);
    }

    public void update() {
        switch (state) {
            case in:
                intake.setPower(Constants.intakeIn);
                break;

            case out:
                intake.setPower(Constants.intakeOut);
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
}
