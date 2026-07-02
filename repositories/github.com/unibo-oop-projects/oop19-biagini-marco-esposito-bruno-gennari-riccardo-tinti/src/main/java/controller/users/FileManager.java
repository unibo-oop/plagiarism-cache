package controller.users;

import java.util.List;
import java.util.Optional;

import model.players.Player;

/**
 * Interface that manage the registration and saving or removal of a {@link Player}.
 * 
 */
public interface FileManager {

    /**
     * Method that saves players on the disc.
     * 
     * @param player
     *          the specific player to save.
     */
    void saveUser(Player player);

    /**
     * Method that loads all players saved on the disc.
     * 
     * @return
     *      An optional player list.
     */
    Optional<List<Player>> loadUsers();

    /**
     * Method that removes players from the disc.
     * 
     * @param player
     *          the specific player to remove.
     */
    void removeUser(Player player);

}
