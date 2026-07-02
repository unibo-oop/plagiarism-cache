package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.model.character.Character;
import it.dpg.minigames.MinigameType;

/**
 * interface for handle behaviours of a specific entity, human or cpu
 * depending on the implementation.
 */
public interface PlayerController {

    /**
     * @return the character controller by the controller
     */
    Character getCharacter();

    /**
     * handles the throw of a dice for the player (human or cpu)
     *
     * @return the dice rolled
     */
    int throwDice();

    /**
     * handles the choice of direction for the player (human or cpu)
     */
    void chooseDirection();

    /**
     * handles the phase of the turn in witch a player (human or cpu) has to play a minigame
     *
     * @param type the minigame that has to be played
     */
    void playMinigame(MinigameType type);
}
