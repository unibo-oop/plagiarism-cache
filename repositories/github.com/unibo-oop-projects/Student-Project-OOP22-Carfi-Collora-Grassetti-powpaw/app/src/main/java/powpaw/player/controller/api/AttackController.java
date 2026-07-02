package powpaw.player.controller.api;

import java.util.List;
import java.util.Optional;

import powpaw.player.model.api.Player;

/**
 * Interface for AttackControllerImpl that control if a player hit another
 * player and their respective deaths.
 * 
 * @author Giacomo Grassetti
 */

public interface AttackController {

    /**
     * Player setters for the class.
     * 
     * @param players A list of Player objects that contains players.
     */
    void setPlayers(List<Player> players);

    /**
     * Method that checks if either player has gone out of
     * the screen and returns an Optional containing the player who is still on
     * the screen (player alive). If both players are still on the screen, it
     * returns an empty Optional.
     * 
     * @return Optional<Player> if a player is out of screen, Optional.empty()
     *         otherwise.
     */
    Optional<Player> checkDeath();

    /**
     * Method that checks if a player has hit the other player. It takes in a player
     * object as a parameter and
     * checks if that player's hitbox collides with the other player's hitbox. If a
     * hit is detected, it sets the attacking player's state to `PlayerState.ATTACK`
     * and
     * decrements the durability of their weapon (if they have one). It then calls
     * the receiveAttack method on the other player, passing in the direction of the
     * attack and the
     * attacking player's attack stat.
     * 
     * @param player the other player.
     */
    void checkHit(Player player);

}
