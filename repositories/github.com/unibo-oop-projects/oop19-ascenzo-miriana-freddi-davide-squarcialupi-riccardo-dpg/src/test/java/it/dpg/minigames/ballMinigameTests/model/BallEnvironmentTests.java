package it.dpg.minigames.ballMinigameTests.model;

import it.dpg.minigames.ballgame.model.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BallEnvironmentTests {

    @Test
    public void envTest1() {
        Boundary b = new VerticalBoundary(60, 0, 100, CollisionType.RESET);
        BallEnvironment env = new BallEnvironmentImpl(50, 50, 5, Set.of(b), 15);
        env.nextFrame(false, false, false, true);
        int i;
        for(i = 0; i < 30 ; i++) {
            System.out.println(env.getX() + " " + env.getY());
            env.nextFrame(false, false, false, true);
            if(env.getX() == 50 && env.getY() == 50) {
                break;
            }
        }
        assertTrue(i < 30);
    }

    @Test
    public void envTest2() {
        Boundary b = new VerticalBoundary(60, 0, 100, CollisionType.RESET);
        BallEnvironment env = new BallEnvironmentImpl(50, 50, 5, Set.of(b), 15);
        env.nextFrame(true, true, false, false);
        env.nextFrame(false, false, true, true);
        assertTrue(env.getX() == 50 && env.getY() == 50);
    }

    @Test
    public void envTest3() {
        Boundary b = new VerticalBoundary(70, 0, 100, CollisionType.BOUNCE);
        BallEnvironment env = new BallEnvironmentImpl(50, 50, 5, Set.of(b), 15);
        for(int i = 0; i < 100; i++) {
            System.out.println(env.getX() + " " + env.getY());
            env.nextFrame(false, false, false, true);
            assertTrue(env.getX() > 50 && env.getX() < 70 && env.getY() == 50);
        }
    }

    @Test
    public void envTest4() {
        Boundary b = new HorizontalBoundary(50, 85, 75, CollisionType.BOUNCE);
        BallEnvironment env = new BallEnvironmentImpl(50, 50, 5, Set.of(b), 15);
        double prevX;
        for(int i = 0; i < 100; i++) {
            prevX = env.getX();
            System.out.println(env.getX() + " " + env.getY());
            env.nextFrame(true, false, false, true);
            assertTrue(prevX <= env.getX());
            assertTrue(env.getX() > 50 && env.getY() > 50);
        }
    }

    @Test
    public void envTest5() {
        Boundary b = new HorizontalBoundary(50, 85, 75, CollisionType.GOAL);
        BallEnvironment env = new BallEnvironmentImpl(50, 50, 5, Set.of(b), 15);
        int i;
        for(i = 0; i < 100; i++) {
            System.out.println(env.getX() + " " + env.getY());
            env.nextFrame(true, false, false, true);
            if(env.goalReached()) {
                break;
            }
        }
        assertTrue(i < 100);
    }

    @Test
    public void ballTest() {
        Ball ball = new Ball(3, 50, 50, 10, 5, 50);
        double deltaT = 1;
        ball.calculateNextPosition(false, false, false, true, 1);
        ball.calculateNextPosition(false, false, false, true, 1);
        assertEquals(80, ball.getCenterX());
        assertEquals(50, ball.getCenterY());
        ball.setxSpeed(0);
        ball.calculateNextPosition(false, true, true, false, 1);
        assertEquals(70, ball.getCenterX());
        assertEquals(40, ball.getCenterY());
        ball.reset();
        assertEquals(50, ball.getCenterX());
        assertEquals(50, ball.getCenterY());
        double yPos1 = ball.getCenterY();
        for(int i = 0; i < 30; i++) {
            ball.calculateNextPosition(true, false, false, false, 1d / 15d);
        }
        double yPos2 = ball.getCenterY();
        for(int i = 0; i < 30; i++) {
            ball.calculateNextPosition(false, false, false, false, 1d / 15d);
        }
        double yPos3 = ball.getCenterY();
        assertTrue(yPos2 - yPos1 > yPos3 - yPos2);
    }
}
