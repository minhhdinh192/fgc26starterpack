package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Extension.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Scoring.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Scoring.Outtake;

@TeleOp(name = "Main TeleOp", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {
    Drivetrain drivetrain;
    Intake intake;
    Outtake outtake;

    @Override
    public void runOpMode() {
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double forward = -gamepad1.left_stick_y;
            double rotate = gamepad1.right_stick_x;

            drivetrain.drive(forward, rotate);

            if (gamepad1.y) intake.in();
            else if (gamepad1.a) intake.out();
            else intake.idle();
            intake.update();
        }
    }
}
