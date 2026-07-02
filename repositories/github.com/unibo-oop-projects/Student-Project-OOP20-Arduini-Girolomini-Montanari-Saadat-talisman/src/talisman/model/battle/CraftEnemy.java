package talisman.model.battle;

/**
 * Implementation of craft enemy's informations in battle.
 * 
 * @author Alice Girolomini
 *
 */
public class CraftEnemy implements EnemyModel {
    private final int craft;
    private final String name;

    /**
     * Initializes the enemie's craft.
     * 
     * @param craft - enemie's craft
     * @param name - enemie's name
     */
    public CraftEnemy(final int craft, final String name) {
        this.craft = craft;
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStrength() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCraft() {
        return this.craft;
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
