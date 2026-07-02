package model.intelligence;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import model.enums.Orientation;
import model.enums.ShipType;
import model.match.CellsFilledException;
import model.match.PlaygroundBattle;
import model.match.PlaygroundBattleImpl;
import model.match.Ship;
import model.util.Pair;

/**
 * Represents a specific implementation of {@link IntelligenceComputation}.
 * 
 */
public class BasicIntelligenceComputation implements IntelligenceComputation {

    private static final int POSSIBLE_ORIENTATIONS = 2;

    private final int maxRows;
    private final int maxCols;

    public BasicIntelligenceComputation(final int maxRows, final int maxCols) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
    }

    private Pair<Integer, Integer> getRandomInitPosition() {
        Optional<Random> rand = Optional.of(new Random());
        return new Pair<Integer, Integer>(rand.get().nextInt(this.maxRows - (this.maxRows / 2) + 1), rand.get().nextInt(this.maxCols - (this.maxCols / 2) + 1));
    }

    private Orientation setRandomOrientation() {
        Optional<Random> rand = Optional.of(new Random());
        final int num = rand.get().nextInt(BasicIntelligenceComputation.POSSIBLE_ORIENTATIONS);
        return num == 0 ? Orientation.VERTICAL : Orientation.HORIZONTAL;
    }

    private boolean checkCollision(final PlaygroundBattle shipsGrid, final ShipType type) {
        try {
            final Ship shipToPos = new Ship(type);
            shipToPos.setOrientation(this.setRandomOrientation());
            shipsGrid.positionShip(shipToPos, this.getRandomInitPosition());
            return true;
        } catch (CellsFilledException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public final PlaygroundBattle initShips() {
        PlaygroundBattle shipsGrid = new PlaygroundBattleImpl(this.maxRows, this.maxCols);

        for (final ShipType ship : ShipType.values()) {
            boolean setShip = this.checkCollision(shipsGrid, ship);
            if (!setShip) {
                boolean flag = true;
                while (flag) {
                    if (this.checkCollision(shipsGrid, ship)) {
                        flag = false;
                    }
                }
            }
        }
        return shipsGrid;
    }

    private Pair<Integer, Integer> getRandomPosition() {
        Optional<Random> rand = Optional.of(new Random());
        return new Pair<Integer, Integer>(rand.get().nextInt(this.maxRows), rand.get().nextInt(this.maxCols));
    }

    @Override
    public final Pair<Integer, Integer> setNextToHit(final List<Pair<Integer, Integer>> attackGrid) {
        Pair<Integer, Integer> nextPos = this.getRandomPosition();

        if (attackGrid.contains(nextPos)) {
            boolean flag = true;
            while (flag) {
                nextPos = this.getRandomPosition();
                if (!attackGrid.contains(nextPos)) {
                    flag = false;
                }
            }
        }
        return nextPos;
    }

}
