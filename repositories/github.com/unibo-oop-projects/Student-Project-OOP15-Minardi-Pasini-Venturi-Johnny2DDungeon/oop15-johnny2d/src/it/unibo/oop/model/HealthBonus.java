package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BONUS;

/**
 * {@link Collectable} item that heals the {@link MainCharacter} of a set value.
 *
 */
public class HealthBonus extends AbstractEntity implements Collectable {

    private static final int HEAL_VALUE = 1;

    /**
     * Creates the {@link HealthBonus} in a defined starting position
     * 
     * @param startingX
     *            Starting X position
     * @param startingY
     *            Starting Y position
     */
    public HealthBonus(final double startingX, final double startingY) {
        super(startingX, startingY);
    }

    /**
     * Gets the {@link HealthBonus} Height
     * @return the height
     */
    protected int getEntityHeight() {
        return BONUS.getHeight();
    }

    /**
     * gets the {@link HealthBonus} width
     * @return the width
     */
    protected int getEntityWidth() {
        return BONUS.getWidth();
    }

    /**
     * The {@link MainCharacter} passed collects the {@link HealthBonus} and
     * gets healed.
     * @param bonusCollector The character who collected the bonus
     */
    public void collect(final MainCharacter bonusCollector) {
        bonusCollector.getHealth().increaseHealth(HEAL_VALUE);
    }

}
