package utilities.test;

import java.util.ArrayList;
import java.util.List;

import model.players.Player;

/**
 * Test class for the settings, DO NOT USE in final version.
 * Andrea Serafini.
 *
 */
public class TemporaryGameSettings {

    private static final int LIMIT = 29;
    private static final int STEPS = 6;

    private static TemporaryGameSettings settings;

    private int boardLimit;
    private List<Player> matchPlayers;
    private Integer minotaurusSteps;
    private boolean jumpEnable;
    private boolean edgeJumping;
    private int maze;
    private Integer currentPlayer;
    private boolean minotaurusEdgeJumping;
    private int charactersForWin;

    private boolean musicOn;

    /**
     * Constructor.
     */
    public TemporaryGameSettings() {

        this.boardLimit = LIMIT;
        this.matchPlayers = new ArrayList<>();
        this.edgeJumping = true;
        this.jumpEnable = false;
        this.currentPlayer = -1;
        this.musicOn = true;
        this.charactersForWin = 2;
        this.minotaurusEdgeJumping = false;
        this.minotaurusSteps = STEPS;
        this.edgeJumping = false;
    }

    /**
     * 
     * @param player the player
     */
    public void addNewPlayer(final Player player) {
        this.matchPlayers.add(player);
    }

    /**
     * 
     * @return the limit
     */
    public int getBoardLimit() {
        return this.boardLimit;
    }

    /**
     * 
     * @return character for win
     */
    public int getCharactersForWin() {
        return this.charactersForWin;
    }

    /**
     * 
     * @return match palyers
     */
    public List<Player> getMatchPlayers() {
        return this.matchPlayers;
    }

    /**
     * 
     * @return the maze
     */
    public int getMaze() {
        return this.maze;
    }

    /**
     * 
     * @return number of steps
     */
    public Integer getMinotaurusSteps() {
        return this.minotaurusSteps;
    }

    /**
     * 
     * @return the next player
     */
    public Player getNextPlayer() {
        this.currentPlayer++;

        if (this.currentPlayer == this.matchPlayers.size()) {
            this.currentPlayer = 0;
        }
        return this.matchPlayers.get(this.currentPlayer);
    }

    /**
     * 
     * @return true if edge jumping
     */
    public boolean isEdgeJumping() {
        return this.edgeJumping;
    }

    /**
     * 
     * @return true if minotaurus edge jumping
     */
    public boolean isMinotaurusHedgeJumping() {
        return this.minotaurusEdgeJumping;
    }

    /**
     * @return the musicOn
     */
    public boolean isMusicOn() {
        return this.musicOn;
    }

    /**
     * 
     * @return if jumping is enabled
     */
    public boolean jumpEnabled() {
        return this.jumpEnable;
    }

    /**
     * 
     */
    public void resetTurn() {
        this.matchPlayers.stream().forEach(e -> e.resetTurn());
    }

    /**
     * 
     * @param boardLimit the limit
     */
    public void setBoardLimit(final int boardLimit) {
        this.boardLimit = boardLimit;
    }

    /**
     * 
     * @param value the number of hero for the win
     */
    public void setCharactersForWin(final Integer value) {
        this.charactersForWin = value;
    }

    /**
     * 
     * @param isEdgeJumping true if hedge jumping game
     */
    public void setEdgeJumping(final boolean isEdgeJumping) {
        this.edgeJumping = isEdgeJumping;
    }

    /**
     * 
     * @param jumpEnable true if hedge jumping possible in this turn
     */
    public void setJumpEnable(final boolean jumpEnable) {
        this.jumpEnable = jumpEnable;
    }

    /**
     * 
     * @param matchPlayers the list of the player for the game
     */
    public void setMatchPlayers(final List<Player> matchPlayers) {
        this.matchPlayers = matchPlayers;
    }

    /**
     * 
     * @param maze the number of the selected maze
     */
    public void setMaze(final int maze) {
        this.maze = maze;
    }

    /**
     * 
     * @param isMinotaurusHedgeJumpingMode true if minotaurus is hedge jumping
     */
    public void setMinotaurusHedgeJumpingMode(final boolean isMinotaurusHedgeJumpingMode) {
        this.minotaurusEdgeJumping = isMinotaurusHedgeJumpingMode;
    }

    /**
     * 
     * @param minotaurusSteps number of steps for the minotaurs
     */
    public void setMinotaurusSteps(final Integer minotaurusSteps) {
        this.minotaurusSteps = minotaurusSteps;
    }

    /**
     * @param musicOn
     *            the musicOn to set
     */
    public void setMusicOn(final boolean musicOn) {
        this.musicOn = musicOn;
    }

    /**
     * 
     * @return the settings
     */
    public static synchronized TemporaryGameSettings getLog() {
        if (settings == null) {
            settings = new TemporaryGameSettings();
        }
        return settings;
    }

}
