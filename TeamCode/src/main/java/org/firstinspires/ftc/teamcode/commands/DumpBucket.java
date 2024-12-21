package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Bucket;

public class DumpBucket extends CommandBase {
    private final Bucket m_bucketSubsystem;

    public DumpBucket(Bucket subsystem) {
        m_bucketSubsystem = subsystem;
        addRequirements(m_bucketSubsystem);
    }

    @Override
    public void initialize() {
        m_bucketSubsystem.dump();
    }

    @Override
    public boolean isFinished() {
        return m_bucketSubsystem.isDumped();
    }
}
