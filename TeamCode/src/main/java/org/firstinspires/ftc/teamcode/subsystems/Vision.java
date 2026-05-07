package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class Vision {
    AprilTagProcessor aprilTag;
    VisionPortal visionPortal;

    public Vision(HardwareMap hwMap) {
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(hwMap.get(WebcamName.class, Constants.vision), aprilTag);
    }

    public boolean hasTarget() {
        return !aprilTag.getDetections().isEmpty();
    }

    public double getDistance(){
        if(!hasTarget()) return -1;
        AprilTagDetection tag = aprilTag.getDetections().get(0);
        return tag.ftcPose.range;
    }

    public double getYaw(){
        if(!hasTarget()) return 0;
        AprilTagDetection tag = aprilTag.getDetections().get(0);
        return tag.ftcPose.yaw;
    }
}
