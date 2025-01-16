package org.firstinspires.ftc.teamcode.modules;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Elbow {
    private final HardwareMap hardwareMap;
    private final GamepadEx gamepadEx;

    private Motor armMotor;

    private final Telemetry telemetry;

    private ButtonReader leftBumper;
    private ButtonReader rightBumper;

    private int armMotorTargetPosition = 0;

    public Elbow(HardwareMap hardwareMap, GamepadEx gamepadEx, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
        this.telemetry = telemetry;
    }

    public void init() {
        // Initialization code here
        armMotor = new Motor(hardwareMap, "armMotor");

        armMotor.setRunMode(Motor.RunMode.PositionControl);
        armMotor.setPositionCoefficient(0.05);
        armMotor.setPositionTolerance(3);
        armMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        armMotor.resetEncoder();

        leftBumper = new ButtonReader(gamepadEx, GamepadKeys.Button.LEFT_BUMPER);
        rightBumper = new ButtonReader(gamepadEx, GamepadKeys.Button.RIGHT_BUMPER);
    }

    public void run() {
        // Run code here
        leftBumper.readValue();
        rightBumper.readValue();

        if(leftBumper.isDown()) {
            armMotorTargetPosition += 15;
        }

        if(rightBumper.isDown()) {
            armMotorTargetPosition -=  15;
        }

        if(armMotorTargetPosition < 0) {
            armMotorTargetPosition = 0;
        }

        if(armMotorTargetPosition > 1960) {
            armMotorTargetPosition = 1960;
        }


        // WHY IS IT RUNNING AT FULL SPEED?
        armMotor.set(0.05);
        armMotor.setTargetPosition(armMotorTargetPosition);

        telemetry.addData("arm position", armMotor.getCurrentPosition());
        telemetry.addData("arm target position", armMotorTargetPosition);
    }
}
