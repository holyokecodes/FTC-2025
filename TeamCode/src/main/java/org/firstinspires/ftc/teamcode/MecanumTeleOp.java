package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.modules.DriveTrain;
import org.firstinspires.ftc.teamcode.modules.Elbow;
import org.firstinspires.ftc.teamcode.modules.Flipper;
import org.firstinspires.ftc.teamcode.modules.Lift;

enum Direction {
    Still,
    Intake,
    Outtake,
}

@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        GamepadEx controller1 = new GamepadEx(gamepad1);
        GamepadEx controller2 = new GamepadEx(gamepad2);

        DriveTrain driveTrain = new DriveTrain(hardwareMap, controller1);
        Lift lift = new Lift(hardwareMap, controller2);
        Elbow elbow = new Elbow(hardwareMap, controller1);
        Flipper flipper = new Flipper(hardwareMap, controller2);

        Direction intakeStatus = Direction.Still;
        CRServo intakeServo;


        // Init Servos
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");


        ButtonReader aButton = new ButtonReader(controller1, GamepadKeys.Button.A);
        ButtonReader bButton = new ButtonReader(controller1, GamepadKeys.Button.B);

        ButtonReader xButton = new ButtonReader(controller1, GamepadKeys.Button.X);
        ButtonReader leftBumper = new ButtonReader(controller1, GamepadKeys.Button.LEFT_BUMPER);
        ButtonReader rightBumper = new ButtonReader(controller1, GamepadKeys.Button.RIGHT_BUMPER);


        waitForStart();

        driveTrain.init();
        lift.init();
        elbow.init();
        flipper.init();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            driveTrain.run();
            lift.run();
            elbow.run();
            flipper.run();

            aButton.readValue();
            bButton.readValue();
            xButton.readValue();

            rightBumper.readValue();
            leftBumper.readValue();


            // Y toggle slow mode
            // arm triggers up and down
            // intake A and B
            // X is toggle bucket
            // linear arm go up and down bumpers
            // dpad is shortcuts




            if (aButton.wasJustReleased()) {
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

//            if (armMotor.getCurrentPosition() < 600) {
//                wristServo.setPosition(60);
//            } else if (armMotor.getCurrentPosition() > 600 && armMotor.getCurrentPosition() < 1200) {
//                wristServo.setPosition(0);
//            } else if (armMotor.getCurrentPosition() > 1200) {
//                wristServo.setPosition(180);
//            }

            telemetry.update();

        }
    }
}