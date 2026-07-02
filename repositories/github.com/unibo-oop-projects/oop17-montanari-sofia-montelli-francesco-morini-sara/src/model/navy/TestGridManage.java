package model.navy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import model.basic_component.StaticPoint2D;
import model.basic_component.StaticPoint2DImpl;
import model.ship.BuilderShip;
import model.ship.BuilderShipImpl;
import model.ship.Ship;

/**
 * Test for the grid  manage.
 *
 */
public class TestGridManage {
    /**
     * grid where is the navy.
     */
    private GridManage grid;
    /**
     * list of the the static point inserted.
     */
    private List<StaticPoint2D> list;
    /**
     * A {@link Ship} of size 1 used across some tests.
     */
    private Ship size1Ship;
    /**
     * The composition of a classic formation.
     */
    private List<Integer> composition;
    /**
     * initialization.
     */
    @Before
    public void init() {
        composition = new ArrayList<>();
        composition.add(4);
        composition.add(3);
        composition.add(2);
        composition.add(1);
        grid = new GridManageImpl(10, composition);
        list = new ArrayList<>();
        final BuilderShip build = new BuilderShipImpl();
        build.setFirstCoord(new StaticPoint2DImpl(0, 0));
        size1Ship = build.build();
    }
    /**
     * Fill the grid with a complete set of points.
     */
    private void fillGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                list.add(new StaticPoint2DImpl(i, j));
            }
        }
    }
    /**
     * testing for a wrong Grid.
     */
    @Test (expected = IllegalArgumentException.class)
    public void wrongGrid() {
        final GridManage wrongGrid = new GridManageImpl(3, composition);
        wrongGrid.reset();
    }
    /**
     * test for the correct getting free cells.
     */
    @Test
    public void testGetFreeCells() {
        fillGrid();
        assertEquals("Test Free Cells", grid.getSetFreeCell(), list.stream().collect(Collectors.toSet()));
    }
    /**
     * test for the correct second coordinates.
     */
    @Test
    public void testSecondCoord() {
        list.add(new StaticPoint2DImpl(0, 0));
        assertEquals("Test Possible second coord", grid.getPossiblePositionSecondCoord(new StaticPoint2DImpl(0, 0), 1),
                        list.stream().collect(Collectors.toSet()));
    }
    /**
     * test for the correct second coordinates.
     */
    @Test
    public void testSecondCoord2() {
        list.add(new StaticPoint2DImpl(0, 0));
        list.add(new StaticPoint2DImpl(1, 0));
        list.add(new StaticPoint2DImpl(2, 0));
        list.add(new StaticPoint2DImpl(3, 0));
        list.add(new StaticPoint2DImpl(0, 1));
        list.add(new StaticPoint2DImpl(0, 2));
        list.add(new StaticPoint2DImpl(0, 3));
        assertEquals("Test Possible second coord", grid.getPossiblePositionSecondCoord(new StaticPoint2DImpl(0, 0)),
                        list.stream().collect(Collectors.toSet()));
    } 
    /**
     * test for the correct ship placement.
     */
    @Test
    public void testShipPlacement() {
        fillGrid();
        grid.placeShip(size1Ship);
        list.removeAll(grid.getUnavailableCell());
        assertEquals("Test ship placement", grid.getSetFreeCell().stream().collect(Collectors.toSet()), list.stream().collect(Collectors.toSet()));
        assertNotEquals(grid.getRemaining(), grid.getInitial());
    }
    /**
     * test for the remotion of a ship.
     */
    @Test
    public void testRemoveShip() {
        fillGrid();
        grid.placeShip(size1Ship);
        grid.removeShip(size1Ship);
        list.removeAll(grid.getUnavailableCell());
        assertEquals("Test ship placement", grid.getSetFreeCell().stream().collect(Collectors.toSet()), list.stream().collect(Collectors.toSet()));
    }
    /**
     * test for the reset of all set coordinates.
     */
    @Test
    public void testReset() {
        final BuilderShip build = new BuilderShipImpl();
        build.setFirstCoord(new StaticPoint2DImpl(0, 0));
        build.setSecondCoord(new StaticPoint2DImpl(0, 1));
        grid.placeShip(build.build());
        assertNotEquals(grid.getRemaining(), grid.getInitial());
        grid.reset();
        assertEquals("Test method reset the remaninig and initial composition", grid.getRemaining(), grid.getInitial());
    }
}
