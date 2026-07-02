package startmenu.model;

import java.util.List;

import model.gamerules.GameRules;

/**
 * The model that handles the action on relative communications from the
 * controller.
 */
public interface ModelController {

    /**
     * @return the possible GameRules to be chosen
     */
    List<GameRules> getGameMode();

    /**
     * @param index the index of the game mode in the list
     */
    void setGameMode(int index);

    /**
     * @return the game mode chosen
     */
    GameRules getChosenGameMode();
}
