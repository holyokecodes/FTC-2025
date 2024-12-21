package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

public class DriveCommand extends RunCommand {
    public DriveCommand(DriveSubsystem driveSubsystem) {
        super(driveSubsystem::drive, driveSubsystem);
    }
}