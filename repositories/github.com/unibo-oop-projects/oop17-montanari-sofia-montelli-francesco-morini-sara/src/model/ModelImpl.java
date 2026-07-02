package model;

import java.util.List;
import java.util.Optional;

import model.battleground.BattleGround;
import model.battleground.BattleGroundImpl;
import model.game_data.GameDataImpl;
import model.game_data.GameData;
import model.match.MatchImpl;
import model.match.Match;
import model.navy.Navy;
import model.navy.NavyBuilder;
import model.navy.NavyBuilderImpl;
import model.player.Player;
import model.player.PlayerImpl;
import model.player.PlayersManipulationDB;
import model.player.PlayersManipulationDBImpl;
import model.statistic.StatisticMatchManager;
import model.statistic.StatisticMatchManagerImpl;

/**
 * Singleton implementation of {@link Model}.
 */
public final class ModelImpl implements Model {

    /**
     * Instance of the {@link PlayersManipulationDB} player database.
     */
    private PlayersManipulationDB db;
    /**
     * Singleton instance of the model.
     */
    private static final ModelImpl INSTANCE = new ModelImpl();
    /**
     * {@link GameData} of player one, used while building match.
     */
    private final GameData gameDataOne = new GameDataImpl();
    /**
     * {@link GameData} of player two, used while building match.
     */
    private final GameData gameDataTwo = new GameDataImpl();
    /**
     * The {@link Match} among two player.
     */
    private Optional<Match> match = Optional.empty();
    /**
     * The specification for the {@link NavyBuilder}.
     */
    private Optional<List<Integer>> navyBuilderSpecification = Optional.empty();
    /**
     * The size of the grid.
     */
    private Optional<Integer> gridSide = Optional.empty();
    /**
     * Instance of the {@link StatisticMatchManager}.
     */
    private Optional<StatisticMatchManager> statManager = Optional.empty();

    private String playerFile;

    private String statisticFile;
    /**
     * Checks a condition then throws a {@link RuntimeException}.
     * @param condition the condition to be checked.
     * @param exception the {@link RuntimeException} that need to be thrown.
     */
    private void checkAndThrow(final boolean condition, final RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }

    private ModelImpl() { }

    /**
     * @return the one and only instance of the model.
     */
    public static ModelImpl getModel() {
        return INSTANCE;
    }

    @Override
    public List<String> getPlayerNameListOrdered() {
        return db.getPlayerNameOrderedList();
    }

    @Override
    public void addNewPlayer(final String playerName) { 
        db.newPlayer(playerName);
    }

    @Override
    public Player getPlayer(final String name) {
        return name.equals("Anonymous") ? new PlayerImpl("Anonymous") : db.getPlayer(name);
    }

    @Override
    public NavyBuilder getNavyBuilder() throws IllegalStateException {
        checkAndThrow(!navyBuilderSpecification.isPresent(), new IllegalStateException());
        checkAndThrow(!gridSide.isPresent(), new IllegalStateException());
        return new NavyBuilderImpl(navyBuilderSpecification.get(), gridSide.get());
    }

    @Override
    public void setNavy(final Navy navy) throws IllegalStateException, IllegalArgumentException {
        checkAndThrow(!navyBuilderSpecification.isPresent(), new IllegalStateException());
        checkAndThrow(!gridSide.isPresent(), new IllegalStateException());
        checkAndThrow(!navy.getNavySpecification().equals(navyBuilderSpecification.get()), new IllegalArgumentException());
        final BattleGround battleGround = new BattleGroundImpl(navy, gridSide.get());
        if (!gameDataOne.getBattleGround().isPresent()) {
            gameDataOne.setBattleGround(battleGround);
        } else if (!gameDataTwo.getBattleGround().isPresent()) {
            gameDataTwo.setBattleGround(battleGround);
        } else {
            throw new IllegalStateException();
        }
        createMatch();
    }

    @Override
    public boolean canPlay() {
       return match.isPresent();
    }

    @Override
    public void setPlayerData(final Player player, final String password) throws IllegalStateException {
        if (!gameDataOne.getPlayer().isPresent()) {
            gameDataOne.setPlayer(player);
            gameDataOne.setPassword(password);
            db.selectedPlayer(player);
        } else if (!gameDataTwo.getPlayer().isPresent()) {
            gameDataTwo.setPlayer(player);
            gameDataTwo.setPassword(password);
            db.selectedPlayer(player);
        } else {
            throw new IllegalStateException();
        }
        createMatch();
    }

    private void createMatch() {
        if (gameDataOne.getPlayer().isPresent() && gameDataOne.getPassword().isPresent() && gameDataOne.getBattleGround().isPresent()
            &&
            gameDataTwo.getPlayer().isPresent() && gameDataTwo.getPassword().isPresent() && gameDataTwo.getBattleGround().isPresent()) {
            match = Optional.of(new MatchImpl(gameDataOne, gameDataTwo, gridSide.get()));
            statManager = Optional.of(new StatisticMatchManagerImpl(match.get()));
        }
    }
    @Override
    public List<Integer> getSpecification() throws IllegalStateException {
        checkAndThrow(!navyBuilderSpecification.isPresent(), new IllegalStateException());
        return navyBuilderSpecification.get();
    }
    @Override
    public void setSpecification(final List<Integer> specification, final int gridSize) throws IllegalArgumentException {
        new NavyBuilderImpl(specification, gridSize);
        this.navyBuilderSpecification = Optional.of(specification);
        this.gridSide = Optional.of(gridSize);

    }

    @Override
    public int getGridSize() throws IllegalStateException {
        checkAndThrow(!gridSide.isPresent(), new IllegalStateException());
        return gridSide.get();
    }

    @Override
    public Match getMatch() {
        return match.get();
    }

    @Override
    public void changeTurn() {
        if (!match.get().isEnded()) {
            match.get().changeTurn();
        }
    }

    @Override
    public GameData getGameData1() throws IllegalStateException {
       return match.isPresent() ? match.get().getGameDataP1() : gameDataOne;
    }

    @Override
    public GameData getGameData2() throws IllegalStateException {
        return match.isPresent() ? match.get().getGameDataP2() : gameDataTwo;
    }

    @Override
    public StatisticMatchManager getStatManager() {
        return statManager.get();
    }

    @Override
    public String getPlayerPath() {
        return playerFile;
    }

    @Override
    public String getStatisticsPath() {
        return statisticFile;
    }

    @Override
    public void setPaths(final String playerFile, final String statisticsFile) {
       this.playerFile = playerFile;
       this.statisticFile = statisticsFile;
       db = new PlayersManipulationDBImpl(playerFile);
    }
}
