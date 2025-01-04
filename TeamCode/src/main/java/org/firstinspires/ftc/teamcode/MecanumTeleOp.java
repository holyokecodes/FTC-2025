package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.DriveTrain;
import org.firstinspires.ftc.teamcode.modules.Elbow;
import org.firstinspires.ftc.teamcode.modules.Flipper;
import org.firstinspires.ftc.teamcode.modules.Lift;
//import org.firstinspires.ftc.teamcode.modules.Wrist;
import org.firstinspires.ftc.teamcode.modules.Chopsticks;
//import org.firstinspires.ftc.teamcode.modules.Intake;

// docked Wrist 0.1 Arm 0
// above bucket Wrist 0.0 Arm 520
// above ground Wrist 0.7 Arm 1700
// on ground Wrist 0.7 Arm 1800


@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        GamepadEx controller1 = new GamepadEx(gamepad1);
        GamepadEx controller2 = new GamepadEx(gamepad2);

        DriveTrain driveTrain = new DriveTrain(hardwareMap, controller1);
        Lift lift = new Lift(hardwareMap, controller2);
        Elbow elbow = new Elbow(hardwareMap, controller1, telemetry);
        Flipper flipper = new Flipper(hardwareMap, controller2);
        Chopsticks chopsticks = new Chopsticks(hardwareMap,controller1);

        waitForStart();

        driveTrain.init();
        lift.init();
        elbow.init();
        flipper.init();
        chopsticks.init();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            driveTrain.run();
            lift.run();
            elbow.run();
            flipper.run();
            chopsticks.run();

            telemetry.update();

        }
    }
}