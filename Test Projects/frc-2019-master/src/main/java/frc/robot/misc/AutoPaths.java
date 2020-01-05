package frc.robot.misc;

import frc.robot.RobotMap;
import frc.robot.commands.FollowTrajectory;
import robot.pathfinder.core.RobotSpecs;
import robot.pathfinder.core.TrajectoryParams;
import robot.pathfinder.core.Waypoint;
import robot.pathfinder.core.path.PathType;
import robot.pathfinder.core.trajectory.TankDriveTrajectory;
import robot.pathfinder.core.trajectory.TrajectoryGenerator;

/**
 * This class holds all the trajectories used in auto.
 * As trajectory generation can take a while, it is not practical to do these in real-time.
 * Therefore some already known trajectories are generated by this class when the robot
 * starts up.
 */
public final class AutoPaths {

    public static TankDriveTrajectory debug;
    public static TankDriveTrajectory approachCargoShipFrontLevelOneL;
    public static TankDriveTrajectory approachCargoShipFrontLevelOneR;
    public static TankDriveTrajectory approachCargoShipFrontLevelOneSideL;
    public static TankDriveTrajectory approachCargoShipFrontLevelOneSideR;
    public static TankDriveTrajectory approachCargoShipSideLevelOneL;
    public static TankDriveTrajectory approachCargoShipSideLevelOneR;
    public static TankDriveTrajectory approachCargoShipSideForVisionLevelOneL;
    public static TankDriveTrajectory approachCargoShipSideForVisionLevelOneR;
    public static TankDriveTrajectory driveOffHabLevelTwo;
    
    public static void generateAll() {
        RobotSpecs specs = FollowTrajectory.getSpecs();

        TrajectoryParams params = new TrajectoryParams();
        params.waypoints = new Waypoint[] {
            new Waypoint(0, 0, Math.PI / 2),
            new Waypoint(RobotMap.FieldDimensions.CARGOSHIP_FRONT_OFFSET, 
                    RobotMap.FieldDimensions.HAB_LVL1_TO_CARGO_SHIP - RobotMap.RobotDimensions.LENGTH, Math.PI / 2),
        };
        params.alpha = 150;
        params.isTank = true;
        params.pathType = PathType.QUINTIC_HERMITE;
        params.segmentCount = 200;
        approachCargoShipFrontLevelOneR = new TankDriveTrajectory(specs, params);
        approachCargoShipFrontLevelOneL = approachCargoShipFrontLevelOneR.mirrorLeftRight();

        params.waypoints = new Waypoint[] {
            new Waypoint(0, 0, Math.PI / 2),
            new Waypoint(-RobotMap.FieldDimensions.CARGOSHIP_FRONT_OFFSET_SIDE, 
                    RobotMap.FieldDimensions.HAB_LVL1_TO_CARGO_SHIP - RobotMap.RobotDimensions.LENGTH, Math.PI / 2),
        };
        params.alpha = 150;
        approachCargoShipFrontLevelOneSideR = new TankDriveTrajectory(specs, params);
        approachCargoShipFrontLevelOneSideL = approachCargoShipFrontLevelOneSideR.mirrorLeftRight();

        params.waypoints = new Waypoint[] {
            new Waypoint(0, 0, Math.PI / 2),
            new Waypoint(RobotMap.FieldDimensions.HAB_LVL1_EDGE_TO_CARGO_SHIP_SIDE - RobotMap.RobotDimensions.LENGTH,
                    RobotMap.FieldDimensions.HAB_LVL1_TO_CARGO_SHIP_SIDE - RobotMap.RobotDimensions.LENGTH / 2, 0.0),
        };
        params.alpha = 200;
        approachCargoShipSideLevelOneL = new TankDriveTrajectory(specs, params);
        approachCargoShipSideLevelOneR = approachCargoShipSideLevelOneL.mirrorLeftRight();

        params.waypoints = new Waypoint[] {
            new Waypoint(0, 0, Math.PI / 2),
            new Waypoint(RobotMap.FieldDimensions.HAB_LVL1_EDGE_TO_CARGO_SHIP_SIDE - RobotMap.RobotDimensions.LENGTH
                    - 12, RobotMap.FieldDimensions.HAB_LVL1_TO_CARGO_SHIP_SIDE - RobotMap.RobotDimensions.LENGTH / 2, Math.PI / 6),
        };
        approachCargoShipSideForVisionLevelOneL = new TankDriveTrajectory(specs, params);
        approachCargoShipSideForVisionLevelOneR = approachCargoShipSideForVisionLevelOneL.mirrorLeftRight();

        driveOffHabLevelTwo = TrajectoryGenerator.generateStraightTank(specs, RobotMap.FieldDimensions.HAB_LVL2_LENGTH);
        
        params.waypoints = new Waypoint[] {
            new Waypoint(0, 0, Math.PI / 2),
            new Waypoint(-60, 84, Math.PI / 2),
        };
        params.alpha = 100;
        debug = new TankDriveTrajectory(specs, params);
    }
}