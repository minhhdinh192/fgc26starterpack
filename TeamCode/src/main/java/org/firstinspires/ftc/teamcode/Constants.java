package org.firstinspires.ftc.teamcode;

public class Constants {
    //Drivetrain
    public static final String frontLeftDrive = "frontLeftDrive";
    public static final String frontRightDrive = "frontRightDrive";
    public static final String rearLeftDrive = "rearLeftDrive";
    public static final String rearRightDrive = "rearRightDrive";
    public static final String leftDrive = "leftDrive";
    public static final String rightDrive = "rightDrive";
    public static final double maxVelocity = 1.0;
    public static final double multiplier = 0.8;


    //Intake
    public static final String intake = "intake";
    public static final double intakeIn = 1.0;
    public static final double intakeOut = -1.0;
    public static final double intakeIdle = 0;

    //Outtake
    public static final String rightOuttake = "rightOuttake";
    public static final String leftOuttake = "leftOuttake";
    public static final double outtakeP = 20.0;
    public static final double outtakeI = 0.0;
    public static final double outtakeD = 0.00001;
    public static final double outtakeF = 20.0;
    public static final int outtakePower = 1800;
    public static final int outtakeTolerance = 40;

    //Adjustable hood
    public static final String hood = "hood";
    public static double startPos = 0.25;
    public static double hoodTolerance = 0.015;
    public static double mulA = 0.00002;
    public static double mulB = 0.001;
    public static double mulC = 0.15;

    //Vision
    public static final String vision = "Webcam 1";

    //Misc
    public static final double deadzone = 0.05;
}
