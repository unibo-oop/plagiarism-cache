package talisman.model.character;

public interface CharacterModel {

    /**
     * Gets the amount of health points
     *
     * @return the value
     */
    int getHealth();
    /**
     * Gets the number of strength points.
     *
     * @return the value
     */
    int getStrength();

    /**
     * Gets the number of craft points.
     *
     * @return the value
     */
    int getCraft();

    /**
     * Gets the number of fate coins.
     *
     * @return the value
     */
    int getFate();

    /**
     * Gets the amount of gold
     *
     * @return the value
     */
    int getGold();

    /**
     * Sets the amount of health
     *
     * @param points the value to set
     */
    void setHealth(final int points);

    /**
     * Sets strength points value.
     *
     @param points - the value to be set
     */
    void setStrength(int points);

    /**
     * Sets craft points value.
     *
     @param points - the value to be set
     */
    void setCraft(int points);

    /**
     * Sets fate points value.
     *
     @param coins - the value to be set
     */
    void setFate(int coins);

    /**
     * Sets the amount of gold
     *
     * @param coins the value to set
     */
    void setGold(final int coins);
}
