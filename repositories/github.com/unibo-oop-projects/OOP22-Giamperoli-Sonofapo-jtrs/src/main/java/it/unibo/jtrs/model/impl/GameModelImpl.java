package it.unibo.jtrs.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.jtrs.model.api.GameModel;
import it.unibo.jtrs.model.api.Tetromino;
import it.unibo.jtrs.utils.Pair;

/**
 * GameModel implementation.
 */
public class GameModelImpl implements GameModel {

    /**
     * The number of grid's rows.
     */
    public static final int GRID_ROWS = 20;

    /**
     * The number of grid's columns.
     */
    public static final int GRID_COLS = 10;

    private List<Tetromino> pieces;
    private final Set<Integer> deletedLines;

    /**
     * Constructor.
     */
    public GameModelImpl() {
        this.pieces = new ArrayList<>();
        this.deletedLines = new HashSet<>();
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public List<Tetromino> getPieces() {
        return this.pieces.stream()
            .map(Tetromino::copy)
            .collect(Collectors.toList());
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public boolean nextPiece(final Tetromino next) {
        this.pieces.add(next);
        if (this.collide(next.getComponents())) {
            this.pieces.remove(this.pieces.size() - 1);
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteRows() {
        this.deletedLines.addAll(this.getCompletedRows());
        this.removeRows();
        return this.deletedLines.size();
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public boolean advance(final Interaction i) {
        final Predicate<Set<Pair<Integer, Integer>>> predicate = c -> this.checkAvailablePosition(c);
        final Consumer<Tetromino> consumer = switch (i) {
            case ROTATE -> Tetromino::rotate;
            case DOWN -> t -> t.translate(1, 0);
            case LEFT -> t -> t.translate(0, -1);
            case RIGHT -> t -> t.translate(0, 1);
        };
        return this.action(consumer, predicate);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public Set<Integer> getDeletedLines() {
        final var res = Set.copyOf(this.deletedLines);
        this.deletedLines.clear();
        return res;
    }

    private Tetromino getCurrentPiece() {
        return this.pieces.get(this.pieces.size() - 1);
    }

    private Stream<Pair<Integer, Integer>> getStreamComponents(final int end) {
        return this.pieces.subList(0, end).stream().flatMap(p -> p.getComponents().stream());
    }

    private boolean action(final Consumer<Tetromino> function, final Predicate<Set<Pair<Integer, Integer>>> predicate) {
        final var tmp = this.getCurrentPiece().copy();
        function.accept(tmp);
        if (predicate.test(tmp.getComponents())) {
            function.accept(this.getCurrentPiece());
            return true;
        }
        return false;
    }

    private boolean checkAvailablePosition(final Set<Pair<Integer, Integer>> coords) {
        return this.inBound(coords) && !this.collide(coords);
    }

    private boolean inBound(final Set<Pair<Integer, Integer>> coords) {
        return this.getYStats(coords).getMin() >= 0
            && this.getYStats(coords).getMax() < GRID_COLS
            && this.getXStats(coords).getMax() < GRID_ROWS;
    }

    private IntSummaryStatistics getXStats(final Set<Pair<Integer, Integer>> coords) {
        return coords.stream()
            .mapToInt(Pair::getX)
            .summaryStatistics();
    }

    private IntSummaryStatistics getYStats(final Set<Pair<Integer, Integer>> coords) {
        return coords.stream()
            .mapToInt(Pair::getY)
            .summaryStatistics();
    }

    private boolean collide(final Set<Pair<Integer, Integer>> coords) {
        return this.getStreamComponents(this.pieces.size() - 1)
            .anyMatch(c -> coords.contains(c));
    }

    private Set<Integer> getCompletedRows() {
        return IntStream.range(0, GRID_ROWS)
            .map(i -> this.getStreamComponents(this.pieces.size())
                .filter(c -> c.getX() == i)
                .count() == GRID_COLS ? i : -1)
                .filter(e -> e != -1).boxed().collect(Collectors.toSet());
    }

    private void removeRows() {
        this.deletedLines.stream().sorted().forEach(l -> {
            this.pieces = this.pieces.stream()
                .flatMap(p -> p.delete(l).stream())
                .collect(Collectors.toCollection(ArrayList::new));
            this.pack(l);
        });
    }

    private void pack(final int line) {
        this.pieces.stream()
            .filter(p -> p.getComponents().stream()
                .anyMatch(c -> c.getX() < line))
            .forEach(p -> p.translate(1, 0));
    }

}
