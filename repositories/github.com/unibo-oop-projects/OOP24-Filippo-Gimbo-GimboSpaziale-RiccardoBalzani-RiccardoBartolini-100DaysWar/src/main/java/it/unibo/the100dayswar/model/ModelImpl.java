package it.unibo.the100dayswar.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.gamedata.api.GameData;
import it.unibo.the100dayswar.model.gamedata.impl.GameDataImpl;
import it.unibo.the100dayswar.model.loaddata.api.GameLoader;
import it.unibo.the100dayswar.model.loaddata.impl.GameLoaderImpl;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.GameMapImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.savedata.api.GameSaver;
import it.unibo.the100dayswar.model.savedata.impl.GameSaverImpl;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.statistic.api.GameStatistics;
import it.unibo.the100dayswar.model.statistic.impl.GameStatisticsImpl;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.unit.api.Unit;
import it.unibo.the100dayswar.model.unit.api.UnitFactory;
import it.unibo.the100dayswar.model.unit.impl.UnitFactoryImpl;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.impl.GameTurnManagerImpl;

/**
 * Class that implements the Model interface.
 */
public class ModelImpl implements Model {
    private static final int DEFAULT_MAP_SIZE = 10;
    private static final int MAX_USERNAME_LENGTH = 15;
    private static final int BOT_PLAYER = 0;
    private static final int HUMAN_PLAYER = 1;
    private static final int MAX_DAY = 100;
    private static final Logger LOGGER = Logger.getLogger(ModelImpl.class.getName());

    private final GameTurnManager turnManager;
    private final MapManager mapManager;
    private final List<Player> players;
    private final UnitFactory factory = new UnitFactoryImpl();
    private final GameStatistics gameStatistics;

    /** 
     * Constructor of the model from scratch.
     * 
     * @param namePlayer the username of the player
     */
    public ModelImpl(final String namePlayer) {
        this.mapManager = new MapManagerImpl(new GameMapBuilderImpl(DEFAULT_MAP_SIZE, DEFAULT_MAP_SIZE));
        this.players = List.of(new SimpleBot(mapManager), new HumanPlayerImpl(namePlayer, mapManager.getPlayerSpawn()));
        this.turnManager = new GameTurnManagerImpl(players);
        this.mapManager.attach(turnManager);
        this.gameStatistics = new GameStatisticsImpl(players, mapManager);
        gameStatistics.updateAllStatistics();
        this.turnManager.startTimer();
    }

    /**
     * Constructor of the model from a pre-saved match.
     * 
     * @param path the path of the file containing the saves
     * @throws IllegalStateException if the data aren't laoded correctly
     */
    public ModelImpl(final Optional<String> path) {
        final GameLoader loader = path.isPresent() ? new GameLoaderImpl(path.get()) : new GameLoaderImpl();
        final Optional<GameData> data = loader.loadGame();
        if (data.isEmpty()) {
            Logger.getLogger(ModelImpl.class.getName()).warning("The data are not loaded correctly");
        }
        this.mapManager = new MapManagerImpl(data.get().getMapManager());
        this.turnManager = data.get().getGameTurnManager();
        this.players = List.of(new SimpleBot(data.get().getBotData()), data.get().getHumanData());
        this.gameStatistics = new GameStatisticsImpl(players, mapManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyTower(final TowerType type, final Cell position) {
        final Tower tower = factory.createTower(players.get(HUMAN_PLAYER), type, position);
        getHumanPlayer().buyUnit(tower);
        updateMap(tower, List.of(mapManager));
        tower.attach(mapManager);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void buySoldier() {
        final Soldier soldier = factory.createSoldier(getHumanPlayer());
        getHumanPlayer().buyUnit(soldier);
        updateMap(soldier, List.of(mapManager));
        soldier.attach(mapManager);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void addPlayer(final String username) {
        if (username.length() > MAX_USERNAME_LENGTH) {
            throw new IllegalArgumentException("The username is too long");
        }
        if (players.size() == 1) {
            players.add(new HumanPlayerImpl(
                username,
                mapManager.getPlayerSpawn()
            ));
        } else {
            throw new IllegalStateException("Error: the bot player is not added yet or maximum number of players reached");
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public HumanPlayer getHumanPlayer() {
        if (players.size() > HUMAN_PLAYER && players.get(HUMAN_PLAYER) instanceof HumanPlayer) {
            return (HumanPlayer) players.get(HUMAN_PLAYER);
        } else {
            Logger.getLogger(ModelImpl.class.getName()).info("The human player has not been added yet");
            return null;
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public BotPlayer getBotPlayer() {
        if (!players.isEmpty() && players.get(BOT_PLAYER) instanceof BotPlayer) {
            return (BotPlayer) players.get(BOT_PLAYER);
        } else {
            Logger.getLogger(ModelImpl.class.getName()).info("The bot player has not been added yet");
            return null;
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return turnManager.getCurrentPlayer();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return turnManager.getDay() >= MAX_DAY || players.stream().anyMatch(player ->
            players.stream()
                .filter(p -> !p.equals(player))
                .map(Player::getSpawnPoint)
                .anyMatch(spawn -> player.getSoldiers().stream()
                                            .map(Soldier::getPosition)
                                            .anyMatch(pos -> pos.equals(spawn)))
        );
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public Player getWinner() {
        if (!isOver()) {
            Logger.getLogger(ModelImpl.class.getName()).info("The game is not over yet");
        }
        return players.stream()
            .filter(player -> players.stream()
                .filter(opponent -> !opponent.equals(player))
                .anyMatch(opponent -> player.getSoldiers().stream()
                    .map(Soldier::getPosition)
                    .anyMatch(pos -> pos.equals(opponent.getSpawnPoint()))))
            .findFirst()
            .or(() -> mapManager.getPlayersCells().entrySet().stream()
                .max((entry1, entry2) -> Integer.compare(entry1.getValue().size(), entry2.getValue().size()))
                .map(Map.Entry::getKey))
            .orElseThrow(() -> new IllegalStateException("No winner."));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveGame(final String path) {
        try {
            final GameData data = new GameDataImpl(
                getHumanPlayer(), 
                getBotPlayer(), 
                mapManager,
                turnManager
            );
            final GameSaver gameSaver = new GameSaverImpl(data, path);

            gameSaver.saveGame();
        } catch (IOException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgradeUnit(final Unit unit) {
        getHumanPlayer().upgradeUnit(unit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void skipTurn() {
        turnManager.switchTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseGame() {
        turnManager.stopTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resumeGame() {
        turnManager.startTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMapWidth() {
        return mapManager.getMapDimension().getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMapHeight() {
        return mapManager.getMapDimension().getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap getMap() {
        return new GameMapImpl(
            (int) mapManager.getMapDimension().getWidth(), 
            (int) mapManager.getMapDimension().getHeight(),
            MapManager.createMapFromStream((int) getMapWidth(),
            (int) getMapHeight(), mapManager.getMapAsAStream())
            );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopTimer() {
        turnManager.stopTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Cell> getMapStream() {
        return mapManager.getMapAsAStream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean moveSoldier(final Pair<Unit, Cell> source) {
        if (source.getFirst() instanceof Soldier) {
            try {
                mapManager.update(source);
            } catch (IllegalStateException e) {
                return false;
            }
            return true;
        }
        LOGGER.log(Level.WARNING, "The unit is not a soldier");
        return false;
    }

    /**
     * Method that updates the observer after the creation of a unit.
     * 
     * @param unit the unit created
     * @param observers the observer to update
     */
    private void updateMap(final Unit unit, final List<Observer<Pair<Unit, Cell>>> observers) {
        observers.forEach(o -> o.update(new Pair<>(unit, unit.getPosition())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGameDay() {
        return turnManager.getDay();
    }
}
