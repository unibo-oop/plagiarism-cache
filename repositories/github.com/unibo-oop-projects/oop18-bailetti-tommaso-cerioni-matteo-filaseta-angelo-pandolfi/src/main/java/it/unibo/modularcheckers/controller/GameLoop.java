package it.unibo.modularcheckers.controller;

import java.util.Optional;

import it.unibo.modularcheckers.model.Coordinate;

/**
 * The game repeats itself over and over. GameLoop can be seen as a consecutive
 * sequence of actions that every player performs each turn. It's the Controller
 * of the MVC pattern.
 */
public interface GameLoop {

    /**
     * Start the loop of the relative Game.
     */
    void startLoop();

    /**
     * When the player decide to surrender.
     */
    void surrender();

    /**
     * Select the Piece to move.
     * 
     * @param pieceSelected the piece chosen on the view. Empty if none is selected.
     */
    void selectPiece(Optional<Coordinate> pieceSelected);

    /**
     * Make the step chosen for the selected piece.
     * 
     * @param whereToMove the coordinate where to move.
     */
    void makeStepChosen(Coordinate whereToMove);
}
