package model.player;

import java.util.List;
import java.util.Optional;

public interface PlayersCollection {

    /**
     *
     * @return the player with the best score
     */
    Player getBestPlayer();

    /**
     *
     * @param name the name of the player to be returned
     * @return the player with that name
     */
    Player getPlayer(String name);

    List<Player> getPlayers();

    void modifyPlayerBestScore(String name, int score);

    void addPlayer(Player player);

    void addPlayer(String name);

    Optional<Player> getCurrentPlayer();

    void setCurrentPlayer(String name);

    boolean containsPlayer(String name);

    void update();

    List<Player> getPlayersInOrder();
}
