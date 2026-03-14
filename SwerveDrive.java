package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveDrive {
    private final int drive_FL_id = 7;
    private final int drive_FR_id = 9;
    private final int drive_BL_id = 6;
    private final int drive_BR_id = 8;

    private final int turn_FL_id = 3;
    private final int turn_FR_id = 5;
    private final int turn_BL_id = 2;
    private final int turn_BR_id = 4;

    private final SwerveModule module_FL = new SwerveModule(drive_FL_id, turn_FL_id);
    private final SwerveModule module_FR = new SwerveModule(drive_FR_id, turn_FR_id);
    private final SwerveModule module_BL = new SwerveModule(drive_BL_id, turn_BL_id);
    private final SwerveModule module_BR = new SwerveModule(drive_BR_id, turn_BR_id);


    private final Translation2d loc_FL = new Translation2d(0.381, 0.381);
    private final Translation2d loc_FR = new Translation2d(0.381, -0.381);
    private final Translation2d loc_BL = new Translation2d(-0.381, 0.381);
    private final Translation2d loc_BR = new Translation2d(-0.381, -0.381);

    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(loc_FL, loc_FR, loc_BL, loc_BR);

    private final ChassisSpeeds speeds = new ChassisSpeeds();

    private SwerveModuleState[] moduleStates;

    public SwerveDrive() {

    }

    public void drive(double x, double y, double rot, double time) {
        moduleStates = kinematics.toSwerveModuleStates( speeds.discretize(x, y, rot, time) );

        module_FL.setState(moduleStates[0]);
        module_FR.setState(moduleStates[1]);
        module_BL.setState(moduleStates[2]);
        module_BR.setState(moduleStates[3]);

    }
}
