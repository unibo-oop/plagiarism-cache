package it.unibo.arkanoid.controller;

import java.util.Iterator;
import java.util.function.Supplier;

import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.model.LevelBuilder;

/**
 * This class represent an Iterator of Level.
 *
 */
public class IteratorLevel implements Iterator<Level> {

    private int currentIndex;
    private final int maxIndex;
    private final LevelGenerator levelGenerator;
    private final Supplier<LevelBuilder> supplierBuilder;

    /**
     * Constructor for an Iterator of Level.
     * 
     * @param levelGenerator
     *            The LevelGenerator to generate Levels.
     * @param supplierBuilder
     *            A Supplier of LevelBuilder to build Levels.
     * @param numberOfLevel
     *            The number of level to iterate.
     */
    public IteratorLevel(final LevelGenerator levelGenerator, final Supplier<LevelBuilder> supplierBuilder,
            final int numberOfLevel) {
        this.levelGenerator = levelGenerator;
        this.maxIndex = numberOfLevel;
        this.supplierBuilder = supplierBuilder;
    }

    /**
     * True if has another level to iterate, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return currentIndex < maxIndex;
    }

    /**
     * Return the next level.
     */
    @Override
    public Level next() {
        return this.levelGenerator.generateLevel(++currentIndex, supplierBuilder.get());
    }
}
