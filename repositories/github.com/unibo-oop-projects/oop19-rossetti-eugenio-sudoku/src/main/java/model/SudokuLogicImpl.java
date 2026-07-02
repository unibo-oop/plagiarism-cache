package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javafx.util.Pair;
import com.google.common.base.Optional;

public class SudokuLogicImpl implements SudokuLogic, Serializable {

    private static final long serialVersionUID = -304596429699396494L;
    private static final int EMPTY_VALUE = 0;
    private final int size;
    private final int squareSize;
    private final int[][] solution;
    private final Map<Pair<Integer, Integer>, Optional<Integer>> initialGrid;
    private final Map<Pair<Integer, Integer>, Pair<Optional<Integer>, Boolean>> sudokuGrid;

    /**
     * Setup the Sudoku.
     * @param initialGrid Initial grid
     * @param solution Solution of Sudoku
     * @param size Size of Sudoku
     * @param squareSize SquareSize of Sudoku
     */
    public SudokuLogicImpl(final int[] initialGrid, final int[] solution, final int size, final int squareSize) {
        this.sudokuGrid = new TreeMap<>(new PairComparator());
        this.initialGrid = new TreeMap<>(new PairComparator());
        this.solution = new int[size][size];
        this.size = size;
        this.squareSize = squareSize;
        int c = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.initialGrid.put(new Pair<>(i, j), initialGrid[c] == EMPTY_VALUE ? Optional.absent() : Optional.of(initialGrid[c]));
                this.sudokuGrid.put(new Pair<>(i, j), initialGrid[c] == EMPTY_VALUE ? new Pair<>(Optional.absent(), false) : new Pair<>(Optional.of(initialGrid[c]), true));
                this.solution[i][j] = solution[c];
                c++;
            }
        }
    }

    @Override
    public final void hit(final int x, final int y, final int value) {
        final Pair<Integer, Integer> p = new Pair<>(x, y);
        if (!this.initialGrid.get(p).isPresent()) {
            this.sudokuGrid.put(p, new Pair<>(Optional.of(value), this.checkRow(x, value) && this.checkColumn(y, value) && this.checkSquare(x, y, value)));
        }

    }

    @Override
    public final void remove(final int x, final int y) {
        if (!this.initialGrid.get(new Pair<>(x, y)).isPresent()) {
            this.sudokuGrid.put(new Pair<>(x, y), new Pair<>(Optional.absent(), false));
        }
    }

    @Override
    public final boolean isDone() {
        return sudokuGrid.entrySet()
                         .stream()
                         .map(e -> e.getValue().getValue())
                         .allMatch(b -> b.equals(true));
    }

    @Override
    public final List<Pair<Optional<Integer>, Boolean>> getValues() {
        return sudokuGrid.entrySet()
                         .stream()
                         .map(e -> e.getValue())
                         .collect(Collectors.toList());
    }

    @Override
    public final String[] getInitialGrid() {
        return this.initialGrid.entrySet().stream().map(i -> i.getValue().or(EMPTY_VALUE)).map(String::valueOf).toArray(String[]::new);
    }

    @Override
    public final int getSize() {
        return this.size;
    }

    @Override
    public final int getSquareSize() {
        return this.squareSize;
    }

    /**
     * Check if element is unique in row.
     * @param x Row of the element
     * @param value Value of the element
     * @return if value is unique in row
     */
    private boolean checkRow(final int x, final int value) {
        return !this.sudokuGrid.entrySet()
                         .stream()
                         .filter(r -> r.getKey().getKey().equals(x))
                         .filter(m -> m.getValue().getKey().isPresent())
                         .anyMatch(m -> m.getValue().getKey().get().equals(value));
    }

    /**
     * Check if element is unique in column.
     * @param y Column of the element
     * @param value Value of the element
     * @return if value is unique in column
     */
    private boolean checkColumn(final int y, final int value) {
        return !this.sudokuGrid.entrySet()
                         .stream()
                         .filter(c -> c.getKey().getValue().equals(y))
                         .filter(m -> m.getValue().getKey().isPresent())
                         .anyMatch(m -> m.getValue().getKey().get().equals(value));
    }

    /**
     * Check if element is unique in square.
     * @param x Row of the element
     * @param y Column of the element
     * @param value Value of the element
     * @return if value is unique in square
     */
    private boolean checkSquare(final int x, final int y, final int value) {
        final int subX = x - x % squareSize;
        final int subY = y - y % squareSize;
        return !this.sudokuGrid.entrySet()
                         .stream()
                         .filter(r -> r.getKey().getKey() >= subX && r.getKey().getKey() < (subX + this.squareSize))
                         .filter(c -> c.getKey().getValue() >= subY && c.getKey().getValue() < (subY + this.squareSize))
                         .filter(m -> m.getValue().getKey().isPresent())
                         .anyMatch(m -> m.getValue().getKey().get() == value);
    }

    /**
     * Custom comparator for TreeMap.
     */
    private static class PairComparator implements Comparator<Pair<Integer, Integer>>, Serializable {

            private static final long serialVersionUID = -1134203414355055727L;

            @Override 
            public int compare(final Pair<Integer, Integer> p1, final Pair<Integer, Integer> p2) {
                return p1.getKey().equals(p2.getKey()) ? p1.getValue().compareTo(p2.getValue()) : p1.getKey().compareTo(p2.getKey());
            }
    };
}


