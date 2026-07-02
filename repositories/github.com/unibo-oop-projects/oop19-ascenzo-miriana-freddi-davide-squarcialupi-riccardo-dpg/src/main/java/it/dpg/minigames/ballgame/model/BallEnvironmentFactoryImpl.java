package it.dpg.minigames.ballgame.model;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashSet;
import java.util.Set;

public class BallEnvironmentFactoryImpl implements BallEnvironmentFactory {

    private final int expectedFPS;

    BallEnvironmentFactoryImpl(int expectedFPS) {
        this.expectedFPS = expectedFPS;
    }

    @Override
    public BallEnvironment createEnvironment(BallMinigameLevel level) {
        Set<Boundary> temp = new HashSet<>();
        if (level.equals(BallMinigameLevel.LEVEL1)) {
            temp.add(new HorizontalBoundary(10, 25, 5, CollisionType.BOUNCE));
            temp.add(new HorizontalBoundary(10, 50, 40, CollisionType.BOUNCE));
            temp.add(new HorizontalBoundary(30, 62.5, 20, CollisionType.BOUNCE));
            temp.add(new HorizontalBoundary(50, 62.5, 35, CollisionType.BOUNCE));
            temp.add(new HorizontalBoundary(30, 77.5, 60, CollisionType.BOUNCE));
            temp.add(new HorizontalBoundary(47.5, 95, 75, CollisionType.BOUNCE));

            temp.add(new VerticalBoundary(10, 5, 40, CollisionType.BOUNCE));
            temp.add(new VerticalBoundary(25, 5, 20, CollisionType.BOUNCE));
            temp.add(new VerticalBoundary(50, 35, 40, CollisionType.BOUNCE));
            temp.add(new VerticalBoundary(95, 60, 75, CollisionType.BOUNCE));

            temp.add(new HorizontalBoundary(25, 30, 23, CollisionType.RESET));
            temp.add(new HorizontalBoundary(25, 30, 37, CollisionType.RESET));
            temp.add(new HorizontalBoundary(62.5, 80, 20, CollisionType.RESET));
            temp.add(new HorizontalBoundary(62.5, 65, 35, CollisionType.RESET));
            temp.add(new HorizontalBoundary(80, 95, 35, CollisionType.RESET));
            temp.add(new HorizontalBoundary(65, 80, 50, CollisionType.RESET));
            temp.add(new HorizontalBoundary(45, 47.5, 75, CollisionType.RESET));

            temp.add(new VerticalBoundary(25, 20, 23, CollisionType.RESET));
            temp.add(new VerticalBoundary(25, 37, 40, CollisionType.RESET));
            temp.add(new VerticalBoundary(30, 37, 40, CollisionType.RESET));
            temp.add(new VerticalBoundary(30, 20, 23, CollisionType.RESET));
            temp.add(new VerticalBoundary(80, 20, 35, CollisionType.RESET));
            temp.add(new VerticalBoundary(65, 35, 50, CollisionType.RESET));
            temp.add(new VerticalBoundary(95, 35, 60, CollisionType.RESET));
            temp.add(new VerticalBoundary(80, 50, 60, CollisionType.RESET));
            temp.add(new VerticalBoundary(30, 60, 100, CollisionType.RESET));
            temp.add(new VerticalBoundary(45, 75, 100, CollisionType.RESET));

            temp.add(new HorizontalBoundary(30, 45, 100, CollisionType.GOAL));

            return new BallEnvironmentImpl(17.5, 12.5, 3, temp, expectedFPS);
        }
        throw new NotImplementedException("level not implemented");
    }
}
