package model.player;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import model.data_access.DataAccessManager;
import model.data_access.FileDataAccessManagerImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implements the interface {@link PlayersManipulationDB}.
 */
public final class PlayersManipulationDBImpl implements PlayersManipulationDB {
    /**
     * filed for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(PlayersManipulationDBImpl.class.getName());
    /**
     * data base for players.
     */
    private final DataAccessManager<Player> dataBase;
    /**
     * Class builder.
     * @param filename the name of the file to be created for persistence 
     */
    public PlayersManipulationDBImpl(final String filename) {
        dataBase = new FileDataAccessManagerImpl<>(filename);
    }

    @Override
    public List<String> getPlayerNameList() {
        return getPlayerSet().stream()
                             .map(Player::getName)
                             .collect(Collectors.toList());
    }

    @Override
    public List<String> getPlayerNameOrderedList() {
        return getPlayerSet().stream()
                             .sorted((player1, player2) -> player2.getTimeLastAccess().compareTo(player1.getTimeLastAccess()))
                             .map(Player::getName)
                             .collect(Collectors.toList());
    }

    @Override
    public Set<Player> getPlayerSet() {
        return dataBase.getSet();
    }

    @Override
    public Player getPlayer(final String name) {
        return dataBase.getSet().stream()
                                .filter(p -> p.getName().equals(name))
                                .collect(Collectors.toList()).get(0);
    }

    @Override
    public void selectedPlayer(final Player player) {
        LOGGER.trace("Chose the player {} in order to play (change the time of last access)", player.getName());
        player.setTimeLastAccess();
        dataBase.updateElement(player);
    }

    @Override
    public void newPlayer(final String name) throws IllegalArgumentException {
        if (getPlayerNameList().contains(name) && !"Anonymous".equals(name)) {
            LOGGER.error("Illegal Argument");
            throw new IllegalArgumentException();
        } else {
            LOGGER.trace("Add the new player {}", name);
            dataBase.saveNewElement(new PlayerImpl(name));
        }
    }
}
