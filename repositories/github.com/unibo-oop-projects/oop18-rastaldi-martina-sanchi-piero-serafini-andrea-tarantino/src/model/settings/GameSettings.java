package model.settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.players.Player;

/**
 * This class is used to share the settings and the rules for the game between every other class.
 * Andrea Serafini.
 *
 */
public class GameSettings implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final int STANDARD_MAZE = 2;
    private static final int STANDARD_LIMIT = 29;
    private static final int STANDARD_HERO_FOR_WIN = 2;
    private static final int STANDARD_MINOTARUS_STEPS = 8;
    private static final String SAVED_GAME_FILE_NAME = "savedGame.dat";
    private static final String SAVED_SETTINGS_FILE_NAME = "savedSettings.dat";
    private static final String SETTINGS_FILE_NAME = "startingSettings.dat";
    private static final String RANKING_FILE_NAME = "scores.dat";

    private int maze;
    private int heroForWin;
    private List<Player> matchPlayers;
    private int minotaurusSteps;
    private boolean hedgeJumping;
    private boolean jumpEnabled;
    private boolean minotaurusHedgeJumping;
    private boolean musicOn;
    private int currentPlayer;
    private int boardLimit;

    /**
     * Constructor.
     */
    public GameSettings() {
        this.currentPlayer = -1;
        this.matchPlayers = new ArrayList<>();
    }

    /**
     * @param player
     *            add a new player to the match
     */
    public void addNewPlayer(final Player player) {
        this.matchPlayers.add(player);
    }

    /**
     * Decrement the actual player, to be used before saving settings.
     */
    public void decrementTurn() {
        this.currentPlayer--;
    }

    /**
     * @return the board limit
     */
    public int getBoardLimit() {
        return this.boardLimit;
    }

    /**
     * @return the number of hero needed to win
     */
    public int getHeroForWin() {
        return this.heroForWin;
    }

    /**
     * @return the match players
     */
    public List<Player> getMatchPlayers() {
        return this.matchPlayers;
    }

    /**
     * @return the maze
     */
    public int getMaze() {
        return this.maze;
    }

    /**
     * @return the number of steps for the minotaurus
     */
    public int getMinotaurusSteps() {
        return this.minotaurusSteps;
    }

    /**
     *
     * @return the next player in the order decided for the game
     */
    public Player getNextPlayer() {
        this.currentPlayer++;

        if (this.currentPlayer == this.matchPlayers.size()) {
            this.currentPlayer = 0;
        }
        return this.matchPlayers.get(this.currentPlayer);
    }

    /**
     * Initialize standard values.
     */
    public void initialize() {
        this.boardLimit = STANDARD_LIMIT;
        this.maze = STANDARD_MAZE;
        this.heroForWin = STANDARD_HERO_FOR_WIN;
        this.minotaurusSteps = STANDARD_MINOTARUS_STEPS;
        this.hedgeJumping = false;
        this.jumpEnabled = false;
        this.minotaurusHedgeJumping = false;
        this.musicOn = true;
    }

    /**
     * @return true if hedge jumping is possible for the match
     */
    public boolean isHedgeJumping() {
        return this.hedgeJumping;
    }

    /**
     * @return true if the jump is enabled for the current turn
     */
    public boolean isJumpEnabled() {
        return this.jumpEnabled;
    }

    /**
     * @return true if hedge jumping is possible for the minotaurus in this match
     */
    public boolean isMinotaurusHedgeJumping() {
        return this.minotaurusHedgeJumping;
    }

    /**
     * @return true if the music is on
     */
    public boolean isMusicOn() {
        return this.musicOn;
    }

    /**
     * Resets to zero all the players turns.
     */
    public void resetTurn() {
        this.matchPlayers.stream().forEach(e -> e.resetTurn());
    }

    /**
     * @param boardLimit
     *            the boardLimit to set
     */
    public void setBoardLimit(final int boardLimit) {
        this.boardLimit = boardLimit;
    }

    /**
     * @param hedgeJumping
     *            the hedgeJumping to set
     */
    public void setHedgeJumping(final boolean hedgeJumping) {
        this.hedgeJumping = hedgeJumping;
    }

    /**
     * @param heroForWin
     *            the heroForWin to set
     */
    public void setHeroForWin(final int heroForWin) {
        this.heroForWin = heroForWin;
    }

    /**
     * @param jumpEnabled
     *            the jumpEnabled to set
     */
    public void setJumpEnabled(final boolean jumpEnabled) {
        this.jumpEnabled = jumpEnabled;
    }

    /**
     * @param matchPlayers
     *            the matchPlayers to set
     */
    public void setMatchPlayers(final List<Player> matchPlayers) {
        this.matchPlayers = matchPlayers;
    }

    /**
     * @param maze
     *            the maze to set
     */
    public void setMaze(final int maze) {
        this.maze = maze;
    }

    /**
     * @param minotaurusHedgeJumping
     *            the minotaurusHedgeJumping to set
     */
    public void setMinotaurusHedgeJumping(final boolean minotaurusHedgeJumping) {
        this.minotaurusHedgeJumping = minotaurusHedgeJumping;
    }

    /**
     * @param minotaurusSteps
     *            the minotaurusSteps to set
     */
    public void setMinotaurusSteps(final int minotaurusSteps) {
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
     * @return a string that summarizes the settings
     */
    public String string() {

        String settingsString = "";

        settingsString += "\nBoard limit:\t" + this.boardLimit;
        settingsString += "\nMaze:\t" + this.maze;
        settingsString += "\nHero For Win:\t" + this.heroForWin;
        settingsString += "\nMino Steps:\t" + this.minotaurusSteps;
        settingsString += "\nHedge Jumping:\t" + this.hedgeJumping;
        settingsString += "\nMino Hedge Jumping:\t" + this.minotaurusHedgeJumping;
        settingsString += "\nMusic On:\t" + this.musicOn;

        return settingsString;
    }

    /**
     *
     * @return the standard name of the ranking file
     */
    public static String getRankingFileName() {
        return RANKING_FILE_NAME;
    }

    /**
     *
     * @return the standard name of the saved game file
     */
    public static String getSavedGameFileName() {
        return SAVED_GAME_FILE_NAME;
    }

    /**
     *
     * @return the standard name of the saved settings file
     */
    public static String getSavedSettingsFileName() {
        return SAVED_SETTINGS_FILE_NAME;
    }

    /**
     *
     * @return the standard name of the starting settings file
     */
    public static String getSettingsFileName() {
        return SETTINGS_FILE_NAME;
    }
}
