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

    private ButtonReader dPadLeft;
    private ButtonReader dPadUp;
    private ButtonReader dPadRight;

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

        dPadLeft = new ButtonReader(gamepadEx, GamepadKeys.Button.DPAD_LEFT);
        dPadRight = new ButtonReader(gamepadEx, GamepadKeys.Button.DPAD_RIGHT);
        dPadUp = new ButtonReader(gamepadEx, GamepadKeys.Button.DPAD_UP);
    }

    public void run() {
        // Run code here

        if(dPadLeft.isDown()) {
            armMotorTargetPosition = 0;
        }

        if(dPadUp.isDown()) {
            armMotorTargetPosition = 832;
        }

        if(dPadRight.isDown()) {
            armMotorTargetPosition = 1960;
        }

        // WHY IS IT RUNNING AT FULL SPEED?
        armMotor.set(0.05);
        armMotor.setTargetPosition(armMotorTargetPosition);

        telemetry.addData("arm position", armMotor.getCurrentPosition());
        telemetry.addData("arm target position", armMotorTargetPosition);
    }
}
