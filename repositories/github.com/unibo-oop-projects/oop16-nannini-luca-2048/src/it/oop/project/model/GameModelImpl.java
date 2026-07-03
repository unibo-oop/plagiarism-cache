package it.oop.project.model;

import it.oop.project.util.Direction;
import it.oop.project.util.RandomUtilities;
import it.oop.project.util.SquareMatrix;
import it.oop.project.util.SquareMatrixImpl;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;

/**
 * {@inheritDoc}
 *
 */
public class GameModelImpl implements GameModel, Serializable {

    private static final long serialVersionUID = 6487478491488692125L;
    private static final int MINIMUM_SIZE = 4;
    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System
            .getProperty("file.separator");
    private static final String SAVEGAME_PATH = HOME + SEPARATOR + ".2048"
            + SEPARATOR + "savegame.obj";
    private static final int MULTIPLIER = 2;
    private final static Map<Integer, Double> SPAWN_PROBABILITY = new HashMap<>();
    static {
        SPAWN_PROBABILITY.put(2, 0.9);
        SPAWN_PROBABILITY.put(4, 0.1);
    }
    private final static int WIN_VALUE = 2048;
    private SquareMatrix board;
    private int size;
    private int score;
    private int bestScore;
    private boolean won;
    private boolean wonDisplayed;
    private List<Point> spawnCoordinates;
    private List<Point> fusionCoordinates;
    private boolean volumeOn;

    /**
     * Constructs a new game model with specified board size.
     * 
     * @param size
     *            board size.
     * @throws IllegalArgumentException
     *             if size < 4
     */
    public GameModelImpl(final int size) throws IllegalArgumentException {
        if (size < MINIMUM_SIZE) {
            throw new IllegalArgumentException();
        } else {
            this.size = size;
            if (!loadSaveGame()) {
                this.bestScore = 0;
                this.volumeOn = true;
                this.reset();
            }
            this.initCommonFields();
        }
    }

    private GameModelImpl(final SquareMatrix matrix)
            throws IllegalArgumentException {
        if (matrix.getSize() < MINIMUM_SIZE) {
            throw new IllegalArgumentException();
        } else {
            this.board = matrix;
            this.size = this.board.getSize();
            this.volumeOn = true;
            this.initFields();
            this.initCommonFields();
        }
    }

    private boolean loadSaveGame() {
        try {
            if (this.readSavegame()) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean readSavegame() throws ClassNotFoundException {
        try {
            ObjectInputStream inStream = new ObjectInputStream(
                    new FileInputStream(SAVEGAME_PATH));
            try {
                GameModelImpl savegame = (GameModelImpl) inStream.readObject();
                this.board = savegame.board;
                this.score = savegame.score;
                this.bestScore = savegame.bestScore;
                this.won = savegame.won;
                this.wonDisplayed = savegame.wonDisplayed;
                this.spawnCoordinates = savegame.spawnCoordinates;
                this.volumeOn = savegame.volumeOn;
                inStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.board = new SquareMatrixImpl(this.size);
        this.initFields();
        this.initGame();
    }

    private void initCommonFields() {
        this.fusionCoordinates = new ArrayList<>();
        this.spawnCoordinates = new ArrayList<>();
    }

    private void initFields() {
        this.score = 0;
        this.won = false;
        this.wonDisplayed = false;
    }

    private void initGame() {
        final int numberOfRandoms = this.size / 2;
        this.spawnCoordinates = new ArrayList<>();
        for (int i = 0; i < numberOfRandoms; i++) {
            this.spawnRandom();
        }
    }

    private void spawnRandom() {
        Point coordinates;
        do {
            coordinates = RandomUtilities.getRandomCoordinates(this.size - 1);
        } while (!this.board.isEmpty(coordinates));
        int randomNumber = RandomUtilities
                .getRandAmongValues(SPAWN_PROBABILITY);
        this.board.write(coordinates, Optional.of(randomNumber));
        this.spawnCoordinates.add(coordinates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SquareMatrix getBoard() {
        return this.board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBestScore() {
        return this.bestScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> getSpawnCoordinates() {
        return new ArrayList<>(this.spawnCoordinates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> getFusionCoordinates() {
        return new ArrayList<>(this.fusionCoordinates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVolumeOn() {
        return this.volumeOn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWonDisplayed() {
        this.wonDisplayed = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVolume() {
        this.volumeOn = !this.volumeOn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shift(final Direction direction) {
        switch (direction) {
        case UP:
            this.board.rotate90Degrees(Direction.LEFT);
            shiftLeft();
            this.board.rotate90Degrees(Direction.RIGHT);
            this.fusionCoordinates = SquareMatrixImpl.rotateCoordinatesList(
                    Direction.RIGHT, this.fusionCoordinates, this.size);
            this.spawnCoordinates = SquareMatrixImpl.rotateCoordinatesList(
                    Direction.RIGHT, this.spawnCoordinates, this.size);
            break;
        case DOWN:
            this.board.rotate90Degrees(Direction.RIGHT);
            shiftLeft();
            this.board.rotate90Degrees(Direction.LEFT);
            this.fusionCoordinates = SquareMatrixImpl.rotateCoordinatesList(
                    Direction.LEFT, this.fusionCoordinates, this.size);
            this.spawnCoordinates = SquareMatrixImpl.rotateCoordinatesList(
                    Direction.LEFT, this.spawnCoordinates, this.size);
            break;
        case RIGHT:
            this.board.rotate180Degrees();
            shiftLeft();
            this.board.rotate180Degrees();
            this.fusionCoordinates = SquareMatrixImpl.rotateCoordinatesList(
                    Direction.LEFT, this.fusionCoordinates, this.size);
            this.fusionCoordinates = SquareMatrixImpl.rotateCoordinatesList(
                    Direction.LEFT, this.fusionCoordinates, this.size);
            this.spawnCoordinates = SquareMatrixImpl.rotateCoordinatesList(
                    Direction.LEFT, this.spawnCoordinates, this.size);
            this.spawnCoordinates = SquareMatrixImpl.rotateCoordinatesList(
                    Direction.LEFT, this.spawnCoordinates, this.size);
            break;
        case LEFT:
            shiftLeft();
            break;
        default:
            break;
        }
    }

    private void shiftLeft() {
        SquareMatrixImpl newMatrix = new SquareMatrixImpl(this.size);
        this.fusionCoordinates = new ArrayList<>();
        for (int row = 0; row < this.board.getSize(); row++) {
            if (!this.board.isEmptyRow(row)) {
                List<Optional<Object>> rowValues = removeEmptyCells(
                        this.board.getRow(row));
                // If there is only one number there are no possibilities to
                // fuse tiles
                if (rowValues.size() > 1) {
                    // For each value: if same than following sum and remove one
                    for (int col = 0; col < rowValues.size() - 1; col++) {
                        if (rowValues.get(col).equals(rowValues.get(col + 1))) {
                            int newValue = (Integer) rowValues.get(col).get()
                                    * MULTIPLIER;
                            if ((!wonDisplayed) && (newValue == WIN_VALUE)) {
                                this.won = true;
                            }
                            this.score += newValue;
                            rowValues.set(col, Optional.of(newValue));
                            rowValues.remove(col + 1);
                            this.fusionCoordinates.add(new Point(row, col));
                        }
                    }
                }
                newMatrix.writeRow(row, fill(rowValues));
            }
        }
        // If nothing happened no need to spawn new number or check best score
        if (!this.board.equals(newMatrix)) {
            this.board = newMatrix;
            this.spawnCoordinates = new ArrayList<>();
            if (this.score > this.bestScore) {
                this.bestScore = this.score;
            }
            this.spawnRandom();
        } else {
            this.spawnCoordinates.removeAll(this.spawnCoordinates);
        }
    }

    private List<Optional<Object>> removeEmptyCells(
            final List<Optional<Object>> row) {
        List<Optional<Object>> resultRow = new ArrayList<>();
        for (Optional<Object> el : row) {
            if (el.isPresent()) {
                resultRow.add(el);
            }
        }
        return resultRow;
    }

    private List<Optional<Object>> fill(final List<Optional<Object>> row) {
        List<Optional<Object>> result = row;
        for (int i = result.size() - 1; i < this.size; i++) {
            result.add(Optional.absent());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasWon() {
        return this.won && !this.wonDisplayed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean noMovesAvailable() {
        for (Direction d : Direction.values()) {
            GameModelImpl m;
            try {
                m = new GameModelImpl(this.board.clone());
                m.shift(d);
                if (!this.board.equals(m.getBoard())) {
                    return false;
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeSavegame() {
        try {
            File file = new File(SAVEGAME_PATH);
            file.getParentFile().mkdirs();
            ObjectOutputStream outStream = new ObjectOutputStream(
                    new FileOutputStream(SAVEGAME_PATH));
            outStream.writeObject(this);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
