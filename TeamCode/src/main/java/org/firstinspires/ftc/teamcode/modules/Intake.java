package org.firstinspires.ftc.teamcode.modules;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

enum Direction {
    Still,
    Intake,
    Outtake,
}

public class Intake
{
    private final HardwareMap hardwareMap;
    private final GamepadEx gamepadEx;

    Direction intakeStatus = Direction.Still;
    CRServo intakeServo;

    ButtonReader aButton;
    ButtonReader bButton;



    public Intake(HardwareMap hardwareMap, GamepadEx gamepadEx) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
    }

    public void init(){
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");

        aButton = new ButtonReader(this.gamepadEx, GamepadKeys.Button.A);
        bButton = new ButtonReader(this.gamepadEx, GamepadKeys.Button.B);

    }

    public void run() {

        if (this.aButton.wasJustReleased()) {
            if(intakeStatus == Direction.Intake) {
                intakeStatus = Direction.Still;
            } else {
                intakeStatus = Direction.Intake;
            }
        }

        if (bButton.wasJustReleased()) {
            if(intakeStatus == Direction.Outtake) {
                intakeStatus = Direction.Still;
            } else {
                intakeStatus = Direction.Outtake;
            }
        }

        switch (intakeStatus) {
            case Intake:
                intakeServo.setPower(-1);
                break;
            case Outtake:
                intakeServo.setPower(1);
                break;
            default:
                intakeServo.setPower(0);
        }
    }

}
