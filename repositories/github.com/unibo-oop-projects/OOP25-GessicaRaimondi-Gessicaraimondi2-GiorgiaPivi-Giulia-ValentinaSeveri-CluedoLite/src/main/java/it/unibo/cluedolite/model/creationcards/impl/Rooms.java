package it.unibo.cluedolite.model.creationcards.impl;

/**
 * Represents a room card in the CluedoLite game.
 * Extends {@link AbstractCard} and returns {@link CardType#ROOM} as its type.
 */
public final class Rooms extends AbstractCard { 

    /**
     * Constructs a Rooms card with the given name.
     * 
     * @param name the name of the room
     */
    public Rooms(final String name) {
        super(name);
    }

    @Override
    public CardType getType() {
        return CardType.ROOM;
    }

}
