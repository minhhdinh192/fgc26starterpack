package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@TeleOp(name = "Main TeleOp", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {
    Drivetrain drivetrain;
    Intake intake;
    Outtake outtake;

    @Override
    public void runOpMode() {
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        outtake = new Outtake(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double forward = -gamepad1.left_stick_y;
            double rotate = gamepad1.right_stick_x;

            drivetrain.drive(forward, rotate);

            if (gamepad1.y) intake.in();
            else if (gamepad1.a) intake.out();
            else intake.idle();

            if (gamepad1.x) outtake.initiatePower();
            else if (gamepad1.b) outtake.idle();
            if (outtake.isReadyToShoot() && gamepad1.right_bumper) intake.pullForLaunch();

            intake.update();
        }
    }
}
