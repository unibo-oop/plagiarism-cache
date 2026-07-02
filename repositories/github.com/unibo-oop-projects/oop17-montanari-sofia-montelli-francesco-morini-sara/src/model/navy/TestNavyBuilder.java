package model.navy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.basic_component.StaticPoint2D;
import model.basic_component.StaticPoint2DImpl;
import model.ship.BuilderShip;
import model.ship.BuilderShipImpl;
import model.ship.Ship;

/**
 * test for the {@link NavyBuilder}.
 */
public class TestNavyBuilder {
    /**
     * A {@link NavyBuilder} that need only one ship to build.
     */
    private NavyBuilder navyBuilderOneShip;

    /**
     * A {@link NavyBuilder} that need only one ship to build.
     */
    private NavyBuilder navyBuilderTwoShip;

    /**
     * Used to check if the result of the {@link NavyBuilder} 
     *          are coherent with a grid.
     */
    private GridManage gridManageOneShip;

    /**
     * A {@link Ship} that can be place.
     */
    private Ship firstShip;

    /**
     * Another {@link Ship}.
     **/
    private Ship secondShip;

    /**
     * Initialization for the {@link NavyBuilder}.
     */
    @Before
    public void init() {
        navyBuilderOneShip = new NavyBuilderImpl(Arrays.asList(1, 0, 0, 0), 10);
        navyBuilderTwoShip = new NavyBuilderImpl(Arrays.asList(1, 1, 0, 0), 10);
        gridManageOneShip = new GridManageImpl(10, Arrays.asList(1, 0, 0, 0));
        final BuilderShip buildFirst = new BuilderShipImpl();
        buildFirst.setFirstCoord(new StaticPoint2DImpl(0, 0));
        firstShip = buildFirst.build();
        final BuilderShip buildSecond = new BuilderShipImpl();
        buildSecond.setFirstCoord(new StaticPoint2DImpl(0, 0));
        buildSecond.setSecondCoord(new StaticPoint2DImpl(0, 3));
        secondShip = buildSecond.build();
    }

    /**
     * Test the building of a navy with just one ship.
     */
    @Test
    public void testBuildWithOneShip() {
        navyBuilderOneShip.addShip(firstShip);
        final Set<Ship> ships = new HashSet<>();
        ships.add(firstShip);
        assertTrue("test that is possible to build", navyBuilderOneShip.canBuild());
        assertEquals("test if the building was successful", navyBuilderOneShip.buildNavy(), new NavyImpl(ships));
    }

    /**
     * expected {@link IllegalArgumentException} if a {@link Ship} is added when not needed.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testFailAdd() {
        navyBuilderOneShip.addShip(firstShip);
        navyBuilderOneShip.addShip(firstShip);
    }

    /**
     * expected {@link IllegalStateException} if a {@link Ship} is added after the build.
     */
    @Test (expected = IllegalStateException.class)
    public void testAddAfterBuild() {
        navyBuilderOneShip.addShip(firstShip);
        navyBuilderOneShip.buildNavy();
        navyBuilderOneShip.addShip(firstShip);
    }

    /**
     * expected {@link IllegalArgumentException} if the {@link Ship} is not required.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddUslessShip() {
        //adding a ship of size two to a navy that require none of them
        navyBuilderOneShip.addShip(secondShip);
    }

    /**
     * Plain test on a {@link Ship} removal.
     */
    @Test
    public void testRemove() {
        navyBuilderOneShip.addShip(firstShip);
        assertTrue("test that is possible to build after add a ship", navyBuilderOneShip.canBuild());
        navyBuilderOneShip.removeShip(firstShip);
        assertFalse("test that isn't possible to build after remove the only ship", navyBuilderOneShip.canBuild());
    }

    /**
     * Expected {@link IllegalStateException} if a remove is performed after the build.
     */
    @Test (expected = IllegalStateException.class)
    public void removeAfterBuild() {
        navyBuilderOneShip.addShip(firstShip);
        navyBuilderOneShip.buildNavy();
        navyBuilderOneShip.removeShip(firstShip);
    }

    /**
     * Test on the initialization of the {@link NavyBuilder}.
     */
    @Test
    public void testInit() {
        assertEquals("check that the internal state of the grid and the external one match", navyBuilderOneShip.getAvailablePositions(), gridManageOneShip.getSetFreeCell());
        assertFalse("test that isn't possible to build", navyBuilderOneShip.canBuild());
    }

    /**
     * the second coordinate of a size 1 {@link Ship} is empty.
     */
    @Test
    public void possibleSecondCellSize1() {
        final Set<StaticPoint2D> points = new HashSet<>();
        points.add(new StaticPoint2DImpl(0, 0));
        assertEquals("verify the correct available position of the second coordinate", navyBuilderOneShip.getAvailablePositionsSecondCord(new StaticPoint2DImpl(0, 0), 1), points);
    }

    /**
     * plain test on the possible second coord.
     */
    @Test
    public void possibleSecondCoord() {
        navyBuilderTwoShip.addShip(firstShip);
        final Set<StaticPoint2DImpl> possibleSecondCord = new HashSet<>();
        possibleSecondCord.add(new StaticPoint2DImpl(3, 0));
        possibleSecondCord.add(new StaticPoint2DImpl(2, 1));
        assertEquals("verify the correct available position of the second coordinate", navyBuilderTwoShip.getAvailablePositionsSecondCord(new StaticPoint2DImpl(2, 0)), possibleSecondCord);
    }


    /**
     * Plain test of the available cell after {@link Ship} add.
     */
    @Test
    public void possibleCordsAfterAdd() {
        navyBuilderOneShip.addShip(firstShip);
        gridManageOneShip.placeShip(firstShip);
        assertEquals("check that the internal state of the grid and the external one match", navyBuilderOneShip.getAvailablePositions(), gridManageOneShip.getSetFreeCell());
    }
}
