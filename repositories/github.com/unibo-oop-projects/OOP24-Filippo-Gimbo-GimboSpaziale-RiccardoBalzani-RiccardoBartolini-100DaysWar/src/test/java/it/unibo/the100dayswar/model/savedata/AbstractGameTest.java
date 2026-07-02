package it.unibo.the100dayswar.model.savedata;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;
import it.unibo.the100dayswar.model.gamedata.api.GameData;
import it.unibo.the100dayswar.model.gamedata.impl.GameDataImpl;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.impl.GameTurnManagerImpl;

/**
 * Class to be extended by test classes that need to set up a game environment.
 */
public class AbstractGameTest {

    /**
     * The map dimension used for test setup.
     */
    private static final int MAP_DIMENSION = 10;

    /**
     * The game map manager.
     */
    private MapManager mockGameMapManager;

    /**
     * The bot player.
     */
    private BotPlayer mockBotPlayer;

    /**
     * The human player.
     */
    private HumanPlayer mockHumanPlayer;

    /**
     * The game turn manager.
     */
    private GameTurnManager mockGameTurnManager;

    /**
     * Set up the game environment.
     */
    @BeforeEach
    void setUp() {
        this.mockGameMapManager = new MapManagerImpl(new GameMapBuilderImpl(MAP_DIMENSION, MAP_DIMENSION));
        this.mockBotPlayer = new SimpleBot(mockGameMapManager);
        this.mockHumanPlayer = new HumanPlayerImpl("Mock human player", mockGameMapManager.getPlayerSpawn());
        this.mockGameTurnManager = new GameTurnManagerImpl(List.of(mockBotPlayer, mockHumanPlayer));
    }

    /**
     * Returns the game map manager.
     * 
     * @return the current mock MapManager
     */
    public MapManager getMockGameMapManager() {
        return new MapManagerImpl(mockGameMapManager);
    }

    /**
     * Returns the bot player.
     * 
     * @return the current mock BotPlayer
     */
    public BotPlayer getMockBotPlayer() {
        return new SimpleBot(mockBotPlayer);
    }

    /**
     * Returns the human player.
     * 
     * @return the current mock HumanPlayer
     */
    public HumanPlayer getMockHumanPlayer() {
        return new HumanPlayerImpl(mockHumanPlayer);
    }

    /**
     * Returns the game turn manager.
     * 
     * @return the current mock GameTurnManager
     */
    public GameTurnManager getMockGameTurnManager() {
        return getMockGameData().getGameTurnManager();
    }

    /**
     * Returns the game data.
     * 
     * @return the current mock GameDataImpl
     */
    public GameData getMockGameData() {
        return new GameDataImpl(mockHumanPlayer, mockBotPlayer, mockGameMapManager, mockGameTurnManager);
    }
}
