package it.unibo.arkanoid.controller;

import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.model.LevelBuilder;

/**
 * 
 * Interface used to generate new levels.
 *
 */
public interface LevelGenerator {

    /**
     * 
     * @param index
     *            index of level
     * @param levelBuilder
     *            level Object builder
     * @return new level
     */
    Level generateLevel(int index, LevelBuilder levelBuilder);
}
