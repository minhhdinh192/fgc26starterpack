package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Utilities;

public class Drivetrain {
    DcMotor leftDrive, rightDrive;

    public Drivetrain(HardwareMap hwMap) {
        leftDrive = hwMap.get(DcMotor.class, Constants.leftDrive);
        rightDrive = hwMap.get(DcMotor.class, Constants.rightDrive);

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    double leftPower, rightPower, max = Constants.maxVelocity;
    double rawMax;
    public void drive(double forward, double rotate) {
        forward = Utilities.applyDeadzone(forward);
        rotate = Utilities.applyDeadzone(rotate);

        leftPower  = forward + rotate;
        rightPower = forward - rotate;

        rawMax = Math.max(Math.abs(rightPower), Math.abs(leftPower));
        if (rawMax > max)
        {
            leftPower /= rawMax;
            rightPower /= rawMax;
        }

        setDrivePower(leftPower, rightPower);
    }

    public void setDrivePower(double leftPower, double rightPower) {
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
    }
}
