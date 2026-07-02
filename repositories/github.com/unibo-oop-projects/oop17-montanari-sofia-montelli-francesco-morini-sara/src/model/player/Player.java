package model.player;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This interface represents a player.
 */
public interface Player {
    /**
     * @return the player's name.
     */
    String getName();
    /**
     * @return the creation date of the player.
     */
    LocalDate getDateCreation();
    /**
     * @return the date of last player access
     *         if the player has never been used, returns the current time.
     */
    LocalDateTime getTimeLastAccess();
    /**
     * This method set the date of last player access.
     */
    void setTimeLastAccess();
}
