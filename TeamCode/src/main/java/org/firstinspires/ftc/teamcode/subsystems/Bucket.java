package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bucket extends SubsystemBase {
    private final Servo bucketRotation;

    public Bucket(final HardwareMap hMap, final String name) {
        bucketRotation = hMap.get(Servo.class, name);
    }

    public void dump() {
        bucketRotation.setPosition(1);
    }

    public void collect() {
        bucketRotation.setPosition(0);
    }

    public boolean isDumped() {
        return bucketRotation.getPosition() == 1;
    }

    public boolean isCollected() {
        return bucketRotation.getPosition() == 0;
    }
}
