package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.AbsoluteEncoder;

import edu.wpi.first.math.kinematics.SwerveModuleState;
import com.revrobotics.spark.SparkClosedLoopController;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;

public class SwerveModule {
    private final SparkFlex drive_motor;
    private final SparkMax turn_motor;
    private final RelativeEncoder drive_encoder;
    private final AbsoluteEncoder turn_encoder;

    private final SparkClosedLoopController drive_control;
    private final SparkClosedLoopController turn_control;
    
    SwerveModule(int drive_id, int turn_id) {
        drive_motor = new SparkFlex(drive_id, MotorType.kBrushless);
        turn_motor = new SparkMax(turn_id, MotorType.kBrushless);

        drive_encoder = drive_motor.getEncoder();
        turn_encoder = turn_motor.getAbsoluteEncoder();

        drive_control = drive_motor.getClosedLoopController();
        turn_control = turn_motor.getClosedLoopController();

        drive_motor.configure(Config.MAXSwerveModule.drivingConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        turn_motor.configure(Config.MAXSwerveModule.turningConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        drive_encoder.setPosition(0);
    }

    /* Set module turn angle and drive speed */
    public void setState(SwerveModuleState state) {
        System.out.println(state.speedMetersPerSecond);
        //drive_motor.set(state.speedMetersPerSecond);
        drive_control.setSetpoint(state.speedMetersPerSecond, ControlType.kVelocity);
        turn_control.setSetpoint(state.angle.getRadians(), ControlType.kPosition);
    }
}
