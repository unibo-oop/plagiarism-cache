package it.tbt.model.entities.npc.api;

import it.tbt.model.entities.characters.Ally;

/**
 * The {@code AllyNPC} interface represents an ally non-player character (NPC).
 * It extends the {@link NPC} interface and provides access to the associated ally character.
 */
public interface AllyNPC extends NPC {

    /**
     * Gets the ally character associated with the ally NPC.
     *
     * @return the ally character
     */
    Ally getAlly();
}
