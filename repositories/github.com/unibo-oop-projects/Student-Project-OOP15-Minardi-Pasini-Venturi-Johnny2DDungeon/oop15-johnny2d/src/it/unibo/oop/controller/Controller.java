package it.unibo.oop.controller;

import java.io.IOException;

import it.unibo.oop.model.Score;

/**
 * Interface for Controller entity in MVC model.
 */
public interface Controller {

    /**
     * Method which "starts" the object implementing this interface for the
     * first time.
     */
    void start();

    /**
     * Method which "plays" some functionalities of the object implementing this
     * interface.
     */
    void play();

    /**
     * Puts best {@link Score} to file.
     * 
     * @throws IOException
     *             if some errors occurs in writing.
     */
    void putStatToFile() throws IOException;
}