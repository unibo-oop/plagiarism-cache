package bubbleshooter.model.game;

/**
 * Represents a class that collects game information.
 */
public class GameData {

    private double gameTime;
    private int destroyedBubbles;
    private int wrongShoots;
    private int score;

    /**
     * Updates the game time.
     * 
     * @param elapsed the time elapsed every {@link GameLoop} cycle.
     */
    public final void updateGameTime(final double elapsed) {
        this.gameTime += elapsed;
    }

    /**
     * Adds one destroyed bubble.
     */
    public final void addDestroyedBubble() {
        this.destroyedBubbles += 1;
    }

    /**
     * Adds one wrong shoot.
     */
    public final void addWrongShoot() {
        this.wrongShoots += 1;
    }

    /**
     * Update the game score.
     * 
     * @param score the score of the player.
     */
    public final void updateScore(final int score) {
        this.score = score;
    }

    /**
     * Gets the game time.
     * 
     * @return the game time.
     */
    public final int getGameTime() {
        return (int) gameTime;
    }

    /**
     * Gets the number of destroyed bubbles in the game.
     * 
     * @return the number of destroyed bubbles.
     */
    public final int getDestroyedBubbles() {
        return destroyedBubbles;
    }

    /**
     * Gets number of wrong shots after the generation of the last row of
     * {@link GridBubble}s.
     * 
     * @return the wrong shoots.
     */
    public final int getWrongShoots() {
        return wrongShoots;
    }

    /**
     * Resets the number of wrong shots. it is called after the generation of a new
     * row of {@link GridBubble}s.
     */
    public final void clearWrongShoots() {
        this.wrongShoots = 0;
    }

    /**
     * Gets the score.
     * 
     * @return the score.
     */
    public final int getScore() {
        return score;
    }
}
