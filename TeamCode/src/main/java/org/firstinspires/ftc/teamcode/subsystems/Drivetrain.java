package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Utilities;

public class Drivetrain {
    DcMotor frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive;

    public Drivetrain(HardwareMap hwMap) {
        frontLeftDrive = hwMap.get(DcMotor.class, Constants.frontLeftDrive);
        rearLeftDrive = hwMap.get(DcMotor.class, Constants.rearLeftDrive);
        frontRightDrive = hwMap.get(DcMotor.class, Constants.frontRightDrive);
        rearRightDrive = hwMap.get(DcMotor.class, Constants.rearRightDrive);

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        rearLeftDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    double leftPower, rightPower, max = Constants.maxVelocity;
    double rawMax;
    double multiplier = Constants.multiplier;
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
        frontLeftDrive.setPower(leftPower * multiplier);
        rearLeftDrive.setPower(leftPower * multiplier);
        frontRightDrive.setPower(rightPower * multiplier);
        rearRightDrive.setPower(rightPower * multiplier);
    }
}
