package controller.playercontroller;

import java.util.Optional;

import controller.command.Action;
import model.character.Character;

/**
 * Modeling interface for a PlayerController: defines the way the Controller can interact with Player, human or cpu.
 */
public interface PlayerController {

    /**
     * Enumeration representing possible Status of the player.
     */
    enum Status {
        WON, LOST,  PLAYING, OPPONENT_QUIT;

        public String toString() {
            return name().toLowerCase().replaceAll("_", " ");
        }
    }

    /**
     * Getter method.
     * @throws IllegalStateException 
     *                  in case the Player has not selected yet
     * @return the Character selected by the Player
     */
    Character getSelected();

    /**
     * Getter method.
     * @return the player status.
     */
    Status getStatus();

    /**
     * Communicate the Player to start its turn.
     */
    void play();

    /**
     * Allows to ask a question to the Player.
     * @throws InterruptedException 
     *                  in case of interruption (for instance: opponent's quit)
     * @param action 
     *                  the Action by which obtaining the question
     * @return a boolean representing the answer
     */
    boolean askPlayer(Action action) throws InterruptedException;

    /**
     * Allows to register an answer.
     * @throws InterruptedException 
     *                  in case of interruption (for instance: opponent's quit).
     * @param action 
     *                  the Action by which obtain the question Player asked for.
     * @param answer 
     *                  opponent's answer.
     */
    void registerAnswer(Action action, boolean answer) throws InterruptedException;

    /**
     * Communicate the player that game is ended.
     * @param status 
     *                  this player final status
     * @param opponentCharacter 
     *                  the opponent's Character if present
     */
    void endGame(Status status, Optional<Character> opponentCharacter);

}
