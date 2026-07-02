package model.battleground;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.basic_component.Cell;
import model.basic_component.StaticPoint2D;
import model.basic_component.StaticPoint2DImpl;
import model.navy.Navy;
import model.navy.NavyBuilder;
import model.navy.NavyBuilderImpl;
import model.ship.BuilderShip;
import model.ship.BuilderShipImpl;
/**
 * test class BattleGround.
 */
public class TestBattleGround {
    /**
     * Initialisation of the {@link BattleGround} needed.
     */
    private transient BattleGround battleGround;
    /**
     * method for the initialisation of the battle ground. 
     */
    @Before
    public void init() {
        final List<Integer> list = Arrays.asList(4, 3, 2, 1);
        final NavyBuilder navyb = new NavyBuilderImpl(list, 10);

        final BuilderShip ship11 = new BuilderShipImpl();
        ship11.setFirstCoord(new StaticPoint2DImpl(0, 0));
        navyb.addShip(ship11.build());

        final BuilderShip ship12 = new BuilderShipImpl();
        ship12.setFirstCoord(new StaticPoint2DImpl(0, 2));
        navyb.addShip(ship12.build());

        final BuilderShip ship13 = new BuilderShipImpl();
        ship13.setFirstCoord(new StaticPoint2DImpl(0, 4));
        navyb.addShip(ship13.build());

        final BuilderShip ship14 = new BuilderShipImpl();
        ship14.setFirstCoord(new StaticPoint2DImpl(0, 2 * 3));
        navyb.addShip(ship14.build());

        final BuilderShip ship21 = new BuilderShipImpl();
        ship21.setFirstCoord(new StaticPoint2DImpl(0, 8));
        ship21.setSecondCoord(new StaticPoint2DImpl(0, 3 * 3));
        navyb.addShip(ship21.build());

        final BuilderShip ship22 = new BuilderShipImpl();
        ship22.setFirstCoord(new StaticPoint2DImpl(2, 0));
        ship22.setSecondCoord(new StaticPoint2DImpl(2, 1));
        navyb.addShip(ship22.build());

        final BuilderShip ship23 = new BuilderShipImpl();
        ship23.setFirstCoord(new StaticPoint2DImpl(2, 3));
        ship23.setSecondCoord(new StaticPoint2DImpl(2, 4));
        navyb.addShip(ship23.build());

        final BuilderShip ship31 = new BuilderShipImpl();
        ship31.setFirstCoord(new StaticPoint2DImpl(2, 2 * 3));
        ship31.setSecondCoord(new StaticPoint2DImpl(2, 8));
        navyb.addShip(ship31.build());

        final BuilderShip ship32 = new BuilderShipImpl();
        ship32.setFirstCoord(new StaticPoint2DImpl(4, 0));
        ship32.setSecondCoord(new StaticPoint2DImpl(4, 2));
        navyb.addShip(ship32.build());

        final BuilderShip ship41 = new BuilderShipImpl();
        ship41.setFirstCoord(new StaticPoint2DImpl(4, 4));
        ship41.setSecondCoord(new StaticPoint2DImpl(4, 2 * 3 + 1));
        navyb.addShip(ship41.build());

        final Navy navy = navyb.buildNavy();
        battleGround = new BattleGroundImpl(navy, 10);
    }
    /**
     * Simulated shoots.
     */
    @Test
    public void shotsTest() {
        final StaticPoint2D shotedPoint = new StaticPoint2DImpl(0, 2);

        battleGround.shoot(shotedPoint);
        assertTrue("virification of the correct shot history", battleGround.getShootsHistory().stream().allMatch(cell -> cell.getStatus().equals(Cell.Status.OCCUPIED_AND_TARGETED)));
    }
    /**
     * Test the missed target.
     */
    @Test
    public void missedShot() {
        final StaticPoint2D shotedPoint = new StaticPoint2DImpl(9, 9);

        battleGround.shoot(shotedPoint);
        assertTrue("verification of a wrong shot", battleGround.getShootsHistory().stream().allMatch(cell -> cell.getStatus().equals(Cell.Status.TARGETED)));
    }
    /**
     * This test tries to shoot outside the grid's bound.
     */
    @Test (expected = IllegalArgumentException.class)
    public void errorPoint() {
        final StaticPoint2D shotedPoint = new StaticPoint2DImpl(0, 10);
        battleGround.shoot(shotedPoint);
    }
}
