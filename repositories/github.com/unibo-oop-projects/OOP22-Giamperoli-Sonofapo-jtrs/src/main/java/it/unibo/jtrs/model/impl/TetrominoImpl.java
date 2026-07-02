package it.unibo.jtrs.model.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.jtrs.model.api.Tetromino;
import it.unibo.jtrs.utils.Pair;

/**
 * Tetromino implementation.
 */
public class TetrominoImpl implements Tetromino {

    private final String color;
    private final Pair<Double, Double> center;
    private int xPosition;
    private int yPosition;
    private Set<Pair<Integer, Integer>> compontents;

    /**
     * Constructor.
     *
     * @param compontents the initial set of coordinates
     * @param x initial horizontal position
     * @param y initial vertical position
     * @param color the Tetromino's color
     */
    public TetrominoImpl(final Set<Pair<Integer, Integer>> compontents, final int x,
        final int y, final String color) {

        this.compontents = new HashSet<>(compontents);
        this.color = color;
        this.xPosition = x;
        this.yPosition = y;
        this.center = this.center();
    }

    /**
     * Evaluates the Tetromino's center of gravity with floating point precision.
     *
     * @return the center of gravity
     */
    private Pair<Double, Double> center() {
        final var c = IntStream.concat(this.compontents.stream().mapToInt(Pair::getX),
            this.compontents.stream().mapToInt(Pair::getY)).max().getAsInt() / 2.0;
        return new Pair<>(c, c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotate() {
        this.compontents = this.compontents.stream()
            .map(c -> new Pair<>(
                (int) (c.getY() - center.getY() + center.getX()),
                (int) (center.getX() - c.getX() + center.getY())))
            .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void translate(final int x, final int y) {
        this.xPosition = this.xPosition + x;
        this.yPosition = this.yPosition + y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Pair<Integer, Integer>> getComponents() {
        return this.compontents.stream()
            .map(c -> new Pair<>(c.getX() + this.xPosition, c.getY() + this.yPosition))
            .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Tetromino> delete(final int position) {
        if (this.compontents.stream().anyMatch(c -> c.getX() + this.xPosition == position)) {
            return this.compontents.stream()
                .filter(c -> c.getX() + this.xPosition != position)
                .map(c -> new TetrominoImpl(Set.of(c), this.xPosition, this.yPosition, this.color))
                .collect(Collectors.toCollection(HashSet::new));
        }
        return new HashSet<>(Set.of(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColor() {
        return this.color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tetromino copy() {
        return new TetrominoImpl(Set.copyOf(compontents), this.xPosition, this.yPosition, this.color);
    }

}
