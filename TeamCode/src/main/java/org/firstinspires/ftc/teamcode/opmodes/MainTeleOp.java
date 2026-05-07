package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Hood;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

@TeleOp(name = "Main TeleOp", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {
    Drivetrain drivetrain;
    Intake intake;
    Outtake outtake;
    Vision vision;
    Hood hood;

    @Override
    public void runOpMode() {
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        outtake = new Outtake(hardwareMap);
        vision = new Vision(hardwareMap);
        hood = new Hood(hardwareMap);

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

            double dis = vision.getDistance();
            double pos = hood.adjustHood(dis);
            hood.setPos(pos);
        }
    }
}
