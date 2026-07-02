package powpaw.player.controller.api;

import java.time.Duration;
import java.util.List;

import powpaw.player.model.api.Player;
import powpaw.player.view.api.KeyObservable;

/**
 * This interface is responsible for keeping track of a list of players,
 * handling their inputs through a KeyObservable object, updating their states
 * and checking for the end of the game.
 * 
 * @author Alessia Carfì
 */
public interface PlayerObservable {

    /**
     * Returns the list of players currently being observed.
     * 
     * @return a List of Player objects.
     */
    List<Player> getPlayers();

    /**
     * Returns the KeyObservable object being used by the players.
     * 
     * @return a KeyObservable object.
     */
    KeyObservable getKeyObservable();

    /**
     * Returns the AttackController object being used by the players.
     * 
     * @return an AttackController object.
     */
    AttackController getAttackController();

    /**
     * Updates the players states and checks for the end of the game, stopping the
     * game loop and showing the game over screen if necessary.
     * 
     * @param deltaTime the time passed since the last update.
     */
    void update(Duration deltaTime);

}
