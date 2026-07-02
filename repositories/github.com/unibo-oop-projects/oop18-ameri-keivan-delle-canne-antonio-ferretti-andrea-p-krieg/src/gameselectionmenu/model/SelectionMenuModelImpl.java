package gameselectionmenu.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import model.gamerules.GameRules;
import model.player.Player;
import model.player.PlayerImpl;
import model.races.Race;

/**
 * SelectionMenuModelImpl is a class that implements the interface
 * SelectionMenuModel. This class represents the model part of the player
 * selection menu. It verifies that there are no players with the same race
 * and/or the same name.
 */
public class SelectionMenuModelImpl implements SelectionMenuModel {

    private static final int MINIMUM_PLAYERS = 2;
    private static final int MAXIMUM_PLAYERS = 4;

    private int numberOfPlayers;
    private List<Player> players;
    private final List<Race> races;
    private final GameRules selectedGameMode;

    /**
     * Constructor.
     * 
     * @param numberOfPlayers is the number of player to show in default.
     * @param gameMode the selected game mode for the game to be started
     * 
     */
    public SelectionMenuModelImpl(final int numberOfPlayers, final GameRules gameMode) {
        this.numberOfPlayers = numberOfPlayers;
        players = new LinkedList<>();
        this.races = getRace();
        this.selectedGameMode = gameMode;
    }

    private List<Race> getRace() {
        final List<Race> racesList = new LinkedList<>();
        try (ScanResult scanResult = new ClassGraph().enableAllInfo().whitelistPackages("model.races").scan()) {
            final ClassInfoList widgetClasses = scanResult.getClassesImplementing("model.races.Race");
            final List<String> widgetClassNames = widgetClasses.getNames();
            widgetClassNames.remove("model.races.RaceImpl");

            widgetClassNames.forEach(i -> {
                try {
                    final Class<?> clazz = Class.forName(i);
                    final Object object = clazz.newInstance();
                    racesList.add((Race) object);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
        return Collections.unmodifiableList(racesList);
    }

    /** {@inheritDoc} **/
    @Override
    public void setCurrentPlayers(final int currentPlayers) {
        if (this.numberOfPlayers > currentPlayers) {
            this.players = players.stream().filter(p -> p.getId() <= currentPlayers).collect(Collectors.toList());
        }
        this.numberOfPlayers = currentPlayers;
    }

    /** {@inheritDoc} **/
    @Override
    public int getPlayerNumber() {
        return this.numberOfPlayers;
    }

    /** {@inheritDoc} **/
    @Override
    public int getMinimumPlayers() {
        return MINIMUM_PLAYERS;
    }

    /** {@inheritDoc} **/
    @Override
    public int getMaximumPlayers() {
        return MAXIMUM_PLAYERS;
    }

    /** {@inheritDoc} **/
    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    /** {@inheritDoc} **/
    @Override
    public List<String> getRaceNameList() {
        return Collections.unmodifiableList(races.stream().map(r -> r.getRaceName()).collect(Collectors.toList()));
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canStart() {
        return this.players.size() == numberOfPlayers;
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canAddThePlayer(final int id, final String name, final String raceName) {
        if (players.stream().map(p -> p.getName()).anyMatch(n -> n.equals(name))
                || players.stream().map(p -> p.getRace().getRaceName()).anyMatch(n -> n.equals(raceName))) {
            return false;
        }
        this.players.add(new PlayerImpl(name, id + 1,
                races.stream().filter(r -> r.getRaceName().equals(raceName)).findFirst().get(),
                selectedGameMode.generateObjective()));
        return true;
    }

    /** {@inheritDoc} **/
    @Override
    public GameRules getSelectedGameMode() {
        return this.selectedGameMode;
    }

}
