package it.unibo.arkanoid.controller;

import java.io.IOException;

/**
 * 
 * Interface for saving and loading of score data.
 *
 */
public interface ScoreLoader {

    /**
     * @param name
     *            Player's name
     * @param score
     *            Player's score
     * @throws IOException
     *             problems in loading file
     * 
     */
    void saveScore(String name, int score) throws IOException;

    /**
     * @return the score list.
     * @throws IOException
     *             problems in loading file
     * @throws ClassNotFoundException
     *             problems in type casting
     */
    ScoreList loadScore() throws IOException;

}
