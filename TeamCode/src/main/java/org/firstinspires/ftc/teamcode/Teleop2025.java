package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Teleop2025")
public class Teleop2025 extends LinearOpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor armMotor;

    private double power = 1.0;


    @Override
    public void runOpMode() {

        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");

        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setTargetPosition(armMotor.getCurrentPosition());



        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            double leftPower = gamepad1.left_stick_y * power;
            double rightPower = -gamepad1.right_stick_y * power;

            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);

            if(gamepad1.a) {
                armMotor.setTargetPosition(armMotor.getTargetPosition() + 5);
            }
            if(gamepad1.b) {
                armMotor.setTargetPosition(armMotor.getTargetPosition() - 5);
            }

            ((DcMotorEx) armMotor).setVelocity(2100);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("Left Power", leftPower);
            telemetry.addData("Right Power", rightPower);
            telemetry.addData("Target Position", armMotor.getTargetPosition());
            telemetry.addData("Arm Position", armMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}