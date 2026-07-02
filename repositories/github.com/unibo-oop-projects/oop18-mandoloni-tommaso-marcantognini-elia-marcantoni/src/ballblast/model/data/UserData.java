package ballblast.model.data;

/**
 * The class that represents the information of a User.
 */
public class UserData {

    private String name;
    private int globalScore;
    private int destroyedBalls;
    private int matchesPlayed;
    private double gameTime;
    private int spawnedBullets;
    private int framesPerSecond;
    private String keySetting;

    /**
     * Empty constructor to make this object serializable into xml file.
     */
//    public UserData() { 
//    }

    /**
     * Updates the user fields with the game session data.
     * 
     * @param gameData the {@link GameData} of the game session.
     */
    public void addGameData(final GameData gameData) {
        this.setMatchesPlayed(this.getMatchesPlayed() + 1);
        this.setGlobalScore(this.getGlobalScore() + gameData.getScore());
        this.setDestroyedBalls(this.getDestroyedBalls() + gameData.getDestroyedBalls());
        this.setGameTime(this.getGameTime() + gameData.getTime());
        this.setSpawnedBullets(this.getSpawnedBullets() + gameData.getSpawnedBullets());
    }

    /**
     * Getter for the user name.
     * 
     * @return the user name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for global score of the user.
     * 
     * @return the score.
     */
    public int getGlobalScore() {
        return globalScore;
    }

    /**
     * Getter for the destryed balls.
     * 
     * @return the destroyed balls.
     */
    public int getDestroyedBalls() {
        return destroyedBalls;
    }

    /**
     * Getter for the matches played.
     * 
     * @return the played matches.
     */
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    /**
     * Getter for the game time.
     * 
     * @return the game time.
     */
    public double getGameTime() {
        return gameTime;
    }

    /**
     * Getter for the spawned bullet.
     * 
     * @return the spawned bullets.
     */
    public int getSpawnedBullets() {
        return spawnedBullets;
    }

    /**
     * Setter for the user name.
     * 
     * @param name the name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Setter for the user global score.
     * 
     * @param globalScore the score.
     */
    public void setGlobalScore(final int globalScore) {
        this.globalScore = globalScore;
    }

    /**
     * Setter for the number of destroyed ball.
     * 
     * @param destroyedBalls the killed balls.
     */
    public void setDestroyedBalls(final int destroyedBalls) {
        this.destroyedBalls = destroyedBalls;
    }

    /**
     * Setter for the matches played by the user.
     * 
     * @param matchesPlayed the number of played matches.
     */
    public void setMatchesPlayed(final int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    /**
     * Setter for the game time.
     * 
     * @param gameTime the total game time.
     */
    public void setGameTime(final double gameTime) {
        this.gameTime = gameTime;
    }

    /**
     * Setter for the spawned bullets.
     * 
     * @param spawnedBullets the number of bullets spawned.
     */
    public void setSpawnedBullets(final int spawnedBullets) {
        this.spawnedBullets = spawnedBullets;
    }

    /**
     * Getter for the FPS to use in the game loop for this {@link UserData}.
     * 
     * @return the FPS setting value.
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    /**
     * Setter for the FPS variable.
     * 
     * @param frames the value of FPS to set.
     */
    public void setFramesPerSecond(final int frames) {
        this.framesPerSecond = frames;
    }

    /**
     * Getter for the key code to play the game.
     * 
     * @return the key set of the current user.
     */
    public String getKeySetting() {
        return keySetting;
    }

    /**
     * Setter for the key code of the user.
     * 
     * @param keySetting the key code to set.
     */
    public void setKeySetting(final String keySetting) {
        this.keySetting = keySetting;
    }

}
