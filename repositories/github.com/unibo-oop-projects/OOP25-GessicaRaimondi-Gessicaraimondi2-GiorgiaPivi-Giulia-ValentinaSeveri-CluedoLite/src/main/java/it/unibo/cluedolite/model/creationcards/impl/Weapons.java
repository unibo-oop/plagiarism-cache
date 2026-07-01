package it.unibo.cluedolite.model.creationcards.impl;

/**
 * Represents a weapon card in the CluedoLite game.
 * Extends {@link AbstractCard} and returns {@link CardType#WEAPON} as its type.
 */
public final class Weapons extends AbstractCard { 

    /**
     * Constructs a Weapons card with the given name.
     * 
     * @param name the name of the weapon
     */
    public Weapons(final String name) {
        super(name);
    }

    @Override
    public CardType getType() {
        return CardType.WEAPON;
    }
}
