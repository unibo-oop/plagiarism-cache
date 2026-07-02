package model.objects;

import java.util.List;

import model.gamerules.GameRules;
import model.player.Player;

public interface GameController {
    /**
     * starts a new game, build(), to place in the controller, now initialisez the
     * map, in the future it will happen in the constructor.
     */
    void startGame();

    /**
     * if not called default.
     * 
     * @param gamePlayers
     */
    void setPlayers(List<Player> gamePlayers);

    /**
     * if not called default.
     * 
     * @param gameRules
     */
    void setRules(GameRules gameRules);
}
