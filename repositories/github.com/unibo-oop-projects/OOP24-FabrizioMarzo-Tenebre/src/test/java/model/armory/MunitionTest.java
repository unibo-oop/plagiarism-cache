package model.armory;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.armory.munition.FactoryMunition;
import model.armory.munition.Munition;
import model.armory.munition.Parabellum;
import model.bounding_box.BoundingBox;

public class MunitionTest {
    private FactoryMunition factory;
    private final Pair<Double, Double> initialPos = Pair.of(100.0, 200.0);

    @BeforeEach
    void setUp() {
        factory = new FactoryMunition();
    }

    @Test
    void testCreateParabellumNotNull() {
        Munition m = factory.createParabellum(initialPos);
        assertNotNull(m, "Parabellum should not be null");
    }

    @Test
    void testParabellumInitialProperties() {
        Parabellum m = (Parabellum) factory.createParabellum(initialPos);
        assertEquals(400, m.getDamage(), "Damage should be 400");
        assertEquals(3, m.getWidth(), "Width should be 3");
        assertEquals(initialPos, m.getCurrentPos(), "Initial position should match");
    }

    @Test
    void testParabellumBoundingBoxNotNull() {
        Munition m = factory.createParabellum(initialPos);
        BoundingBox bbox = m.getBBox();
        assertNotNull(bbox, "Bounding box should not be null");
    }

    @Test
    void testSetShootUpdatesState() {
        Parabellum m = (Parabellum) factory.createParabellum(initialPos);
        Pair<Double, Double> direction = Pair.of(1.0, 0.0);
        Pair<Double, Double> shootPos = Pair.of(150.0, 250.0);
        m.setShoot(direction, shootPos);

        assertTrue(m.isShoot(), "Munition should be marked as shot");
        assertEquals(shootPos, m.getCurrentPos(), "Munition position should be updated to shoot position");
    }

    @Test
    void testMoveShootChangesPosition() {
        Parabellum m = (Parabellum) factory.createParabellum(initialPos);
        Pair<Double, Double> dir = Pair.of(1.0, 0.0);
        m.setShoot(dir, initialPos);

        Pair<Double, Double> before = m.getCurrentPos();
        m.moveShoot(100); // 100 ms
        Pair<Double, Double> after = m.getCurrentPos();

        assertNotEquals(before, after, "Position should change after moveShoot()");
        assertTrue(after.getLeft() > before.getLeft(), "X position should have increased");
    }

    @Test
    void testMoveShootWithoutSetShootThrows() {
        Parabellum m = (Parabellum) factory.createParabellum(initialPos);
        assertThrows(IllegalStateException.class, () -> m.moveShoot(100),
                "Should throw if direction is not initialized");
    }
}
