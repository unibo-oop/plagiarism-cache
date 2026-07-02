package model;

import java.util.List;
import java.util.Optional;
import model.intelligence.BasicArtificialIntelligence;
import model.intelligence.BasicIntelligenceComputation;
import model.players.ArtificialPlayer;
import model.enums.PlayerNumber;
import model.match.PlaygroundBattle;
import model.gamemode.GameMode;
import model.gamemode.WinCondition;
import model.gamemode.WinConditionImpl;
import model.match.players.CurrentPlayer;
import model.match.players.CurrentPlayerImpl;
import model.match.players.MatchInfo;
import model.players.Player;
import model.players.PlayerManager;
import model.players.PlayerOperation;
import model.util.Pair;

/**
 * Implementation of Model interface.
 */
public final class ModelImpl implements Model {

    /**
     * Defines the max number of playground rows.
     */
    public static final int MAX_ROWS = 10;

    /**
     * Defines the max number of playground columns.
     */
    public static final int MAX_COLS = 10;

    /**
     * Defines the AI basic name.
     */
    public static final String BASIC_AI_NAME = "BasicAI";

    /**
     * Defines the AI basic password.
     */
    public static final String BASIC_AI_PASS = "basic";

    private final ArtificialPlayer playerAI;
    private final WinCondition winCondition = new WinConditionImpl();
    private final CurrentPlayer currentPlayer = new CurrentPlayerImpl();
    private Optional<MatchInfo> matchInfo = Optional.empty(); 

    /**
     * concrete implementation of Model interface. 
     */
    public ModelImpl() {
        this.playerAI = new ArtificialPlayer(BASIC_AI_NAME, BASIC_AI_PASS, 
                new BasicArtificialIntelligence(new BasicIntelligenceComputation(MAX_ROWS, MAX_COLS)));
    }

    @Override
    public PlayerManager setPlayerManager(final Optional<List<Player>> players) {
        return new PlayerOperation(players);
    }

    @Override
    public ArtificialPlayer getArtificialPlayer() {
        return this.playerAI;
    }

    @Override
    public PlaygroundBattle startBasicAI() {
        return this.playerAI.getArtificialIntelligence().initShipsOnGrid();
    }

    @Override
    public Pair<Integer, Integer> getNextHitPointBasicAI() {
        return this.playerAI.getArtificialIntelligence().setNextHitPoint();
    }

    /**
     * @return the current player
     */
    @Override
    public Optional<PlayerNumber> getCurrentPlayer() {
        return currentPlayer.getCurrentPlayer();
    }

    /**
     * @param playerNumber - the new current player
     */
    @Override
    public void setCurrentPlayer(final PlayerNumber playerNumber) {
        currentPlayer.setCurrentPlayer(playerNumber);
    }

    @Override
    public void setGameMode(final GameMode gameMode) {
        winCondition.setGameMode(gameMode);
    }

    @Override
    public Boolean isMatchOver(final int hits, final int opponentRemainingShips) {
        return winCondition.isMatchOver(hits, opponentRemainingShips);
    }

    @Override
    public void nextPlayer() {
        currentPlayer.nextPlayer();
    }

    @Override
    public Optional<MatchInfo> getMatchInfo() {
        return matchInfo;
    }

    @Override
    public void setMatchInfo(final MatchInfo info) {
       matchInfo = Optional.ofNullable(info); 
    }

}
