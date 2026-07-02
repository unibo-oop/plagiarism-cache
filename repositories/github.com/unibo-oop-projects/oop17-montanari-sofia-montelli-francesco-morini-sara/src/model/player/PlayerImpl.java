package model.player;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.EqualsAndHashCode;

/**
 * This class implement the interface Player.
 */

@EqualsAndHashCode(exclude = {"timeLastAccess"})
public final class PlayerImpl implements Player, Serializable {
    /**
     * Auto-generated serial version ID.
     */
    private static final long serialVersionUID = 4239871336184795731L;
    /**
     * name of the player.
     */
    private final String name;
    /**
     * date of the creation of the player.
     */
    private final LocalDate dateCreation;
    /**
     * date of last use of the player.
     */
    private LocalDateTime timeLastAccess;
    /**
     * field for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(PlayerImpl.class.getName());

    /**
     * @param name New player's nickname.
     */
    public PlayerImpl(final String name) {
        LOGGER.trace("Create a new player {}", name);
        this.name = name;
        dateCreation = LocalDate.now();
        timeLastAccess = LocalDateTime.now();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getDateCreation() {
        return dateCreation;
    }

    @Override
    public LocalDateTime getTimeLastAccess() {
        return timeLastAccess;
    }

    @Override
    public void setTimeLastAccess() {
        LOGGER.trace("Change timeLastaAccess in class Player");
        timeLastAccess = LocalDateTime.now();
    }

}
