package it.unibo.monopoli.model.table;

/**
 * This class represents an implementation of {@link NeutralArea}'s {@link Box}
 * of Monopoly. This one is for the italian version and it allows to hide steal
 * money.
 *
 */
public class ItalianNeutralArea implements Box {

    private final String name;
    private final int id;
    private int dirtyMoney;

    /**
     * Constructs an implementation of {@link ItalianNeutralArea} that needs a
     * name and an ID. This one is for the Italian version of the game.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     */
    public ItalianNeutralArea(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }

    /**
     * Returns the steal money that where hide there.
     * 
     * @return the hidden money
     */
    public int getDirtyMoney() {
        return this.dirtyMoney;
    }

    /**
     * Sets the hidden money.
     * 
     * @param dirtyMoney
     *            the hidden money
     */
    public void setDirtyMoney(final int dirtyMoney) {
        this.dirtyMoney = dirtyMoney;
    }

    /**
     * Resets the money. Some one has stole them.
     */
    public void resetMoney() {
        this.dirtyMoney = 0;
    }

}
