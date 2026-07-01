package it.unibo.cluedolite.model.accuseandsuspect.impl;

import it.unibo.cluedolite.model.accuseandsuspect.api.InterfaceSuspicion;
import it.unibo.cluedolite.model.creationcards.impl.AbstractCard;

/**
 * Represents a suspicion made by a player in the CluedoLite game.
 * Encapsulates the character, weapon, and room suspected to be involved in the crime.
 */
public final class Suspicion implements InterfaceSuspicion {
    private final AbstractCard suspectCharacter;
    private final AbstractCard suspectWeapon;
    private final AbstractCard suspectRoom;

    /**
     * Constructs a Suspicion with the given character, weapon and room.
     * 
     * @param suspectCharacter the suspected character card
     * @param suspectWeapon    the suspected weapon card
     * @param suspectRoom      the room card where the suspicion is made
     */
    public Suspicion(final AbstractCard suspectCharacter, final AbstractCard suspectWeapon, final AbstractCard suspectRoom) {
        this.suspectCharacter = suspectCharacter;
        this.suspectWeapon = suspectWeapon;
        this.suspectRoom = suspectRoom;
    }

    @Override
    public AbstractCard getCharacter() { 
        return suspectCharacter; 
    }

    @Override
    public AbstractCard getWeapon() { 
        return suspectWeapon; 
    }

    @Override
    public AbstractCard getRoom() { 
        return suspectRoom; 
    }

    @Override
    public String toString() {
        return "Suspect: " + suspectCharacter.getName() 
             + " with " + suspectWeapon.getName() 
             + " in the " + suspectRoom.getName();
    }
}
