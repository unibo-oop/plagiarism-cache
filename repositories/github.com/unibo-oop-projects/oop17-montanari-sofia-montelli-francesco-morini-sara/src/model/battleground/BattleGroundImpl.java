package model.battleground;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.basic_component.Cell;
import model.basic_component.CellImpl;
import model.basic_component.StaticPoint2D;
import model.basic_component.StaticPoint2DImpl;
import model.navy.Navy;

/**
 * Basic implementation of a {@link BattleGround}.
 */
public final class BattleGroundImpl implements BattleGround {
    /**
     * the navy of the battle ground.
     */
    private final Navy navy;
    /**
     * the list of the cell hit.
     */
    private final List<Cell> shootHistory = new ArrayList<>();
    /**
     * the size of the grid of the battle ground.
     */
    private final int gridSize;
    /**
     * RNG for random shot.
     */
    private final Random random = new Random();
    /**
     * 
     * @param navy the navy positioned by the player 
     * @param gridSize the size of the grid
     */
    public BattleGroundImpl(final Navy navy, final int gridSize) {
        this.navy = navy;
        this.gridSize = gridSize;
    }

    @Override
    public void shoot(final int coordX, final int coordY) throws IllegalArgumentException {
        shoot(new StaticPoint2DImpl(coordX, coordY));
    }

    @Override
    public void shoot(final StaticPoint2D coordinate) throws IllegalArgumentException {
        if (canShot(coordinate.getX(), coordinate.getY())) {
            if (navy.interact(coordinate)) {
                shootHistory.add(new CellImpl(coordinate, Cell.Status.OCCUPIED_AND_TARGETED));
            } else {
                shootHistory.add(new CellImpl(coordinate, Cell.Status.TARGETED));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * 
     * @param coordX first coordinate
     * @param coordY second coordinate
     * @return true if is possible to shoot the point specified 
     */
    private boolean canShot(final int coordX, final int coordY) {
        return !(coordX < 0 || coordX >= gridSize || coordY < 0 || coordY >= gridSize || shootPresent(coordX, coordY));
    }

    private boolean shootPresent(final int coordX, final int coordY) {
        return shootHistory.contains(new CellImpl(new StaticPoint2DImpl(coordX, coordY)));
    }

    private boolean pointOfNavy(final int coordX, final int coordY) {
        return navy.getShips()
                   .stream()
                   .map(s -> s.getCellSet()
                              .stream()
                              .anyMatch(c -> c.getCoordinate()
                                              .equals(new StaticPoint2DImpl(coordX, coordY))))
                   .anyMatch(p -> p.equals(true));
    }

    @Override
    public boolean randomShoot() {
        final int coordX = random.nextInt(gridSize);
        final int coordY = random.nextInt(gridSize);
        if (shootHistory.size() == gridSize * gridSize) {
            return false;
        } else if (canShot(coordX, coordY)) {
            shoot(coordX, coordY);
            return true;
        } else {
            randomShoot();
        }
        return false;
    }

    @Override
    public List<Cell> getShootsHistory() {
        return Collections.unmodifiableList(shootHistory);
    }

    @Override
    public Cell.Status getStatus(final int coordX, final int coordY) throws IllegalArgumentException {
        if (coordX < 0 || coordX > gridSize || coordY < 0 || coordY > gridSize) {
            throw new IllegalArgumentException();
        } else if (shootPresent(coordX, coordY)) {
            return shootHistory.stream()
                               .filter(c -> c.getCoordinate().equals(new StaticPoint2DImpl(coordX, coordY)))
                               .findFirst()
                               .get()
                               .getStatus();
        } else if (pointOfNavy(coordX, coordY)) {
            return navy.getShips()
                       .stream()
                       .flatMap(s -> s.getCellSet().stream())
                       .filter(c -> c.getCoordinate().equals(new StaticPoint2DImpl(coordX, coordY)))
                       .findFirst()
                       .get()
                       .getStatus();
        } else {
            return Cell.Status.FREE;
        }
    }

    @Override
    public Cell.Status getStatus(final StaticPoint2D coordinate) throws IllegalArgumentException {
        return getStatus(coordinate.getX(), coordinate.getY());
    }

    @Override
    public Navy getNavy() {
        return navy.getNavyCopy();
    }
}
