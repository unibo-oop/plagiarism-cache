package talisman.model.battle;

/**
 * Implementation of strength enemy's informations in battle.
 * 
 * @author Alice Girolomini
 *
 */
public class StrengthEnemy implements EnemyModel {
    private final int strength;
    private final String name;

    /**
     * Initializes the enemie's strength.
     * 
     * @param strength - enemie's strength
     * @param name - the enemie's name
     */
    public StrengthEnemy(final int strength, final String name) {
        this.strength = strength;
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStrength() {
        return this.strength;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCraft() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFate() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStrength(final int points) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCraft(final int points) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFate(final int coins) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealth() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGold() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealth(final int points) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGold(final int coins) {

    }

}
