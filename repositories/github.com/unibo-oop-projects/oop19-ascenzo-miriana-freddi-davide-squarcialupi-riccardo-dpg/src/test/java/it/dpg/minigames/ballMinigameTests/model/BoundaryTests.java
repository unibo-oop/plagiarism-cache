package it.dpg.minigames.ballMinigameTests.model;

import it.dpg.minigames.ballgame.model.Boundary;
import it.dpg.minigames.ballgame.model.CollisionType;
import it.dpg.minigames.ballgame.model.HorizontalBoundary;
import it.dpg.minigames.ballgame.model.VerticalBoundary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoundaryTests {
    @Test
    public void testExeptions() {
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(90, 110, 2, CollisionType.BOUNCE));
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(200, 1, 56, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(200, 1, 56, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(-1, 45, 3, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(4, -78, 56, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(5, 10, -10, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new VerticalBoundary(90, 110, 2, CollisionType.BOUNCE));
        assertThrows(IllegalArgumentException.class, () -> new VerticalBoundary(200, 1, 56, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new VerticalBoundary(200, 1, 56, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new VerticalBoundary(-1, 45, 3, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new VerticalBoundary(4, -78, 56, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new VerticalBoundary(5, 10, -10, CollisionType.GOAL));
    }

    @Test
    public void testBoundaries() {
        Boundary b1 = new HorizontalBoundary(40, 60, 50, CollisionType.BOUNCE);
        assertTrue(b1.isBallColliding(38, 52, 8));
        assertTrue(b1.isBallColliding(45, 49, 5));
        assertFalse(b1.isBallColliding(51, 30, 5));
        assertFalse(b1.isBallColliding(30, 20, 15));
        assertTrue(b1.isBallColliding(45, 51, 5));
        assertFalse(b1.isBallColliding(51, 70, 5));
        assertTrue(b1.isBallColliding(62, 48, 5));
        assertFalse(b1.isBallColliding(70, 80, 15));
        Boundary b2 = new VerticalBoundary(50, 40, 60, CollisionType.BOUNCE);
        assertTrue(b2.isBallColliding(53, 47, 5));
        assertTrue(b2.isBallColliding(48, 62, 8));
        assertTrue(b2.isBallColliding(49, 55, 7));
        assertFalse(b2.isBallColliding(20, 50, 10));
        assertFalse(b2.isBallColliding(80, 50, 10));
        assertFalse(b2.isBallColliding(80, 30, 15));
    }
}
