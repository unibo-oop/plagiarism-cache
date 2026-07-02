package it.tbt.model.entities.npc.api;

import it.tbt.model.fight.api.FightModel;

/**
 * The {@code FightNPC} interface represents a non-player character (NPC) that participates in fights.
 * It extends the {@link NPC} interface and provides a method to retrieve the associated fight model.
 */
public interface FightNPC extends NPC {

    /**
     * Gets the fight model associated with the NPC.
     *
     * @return the fight model
     */
    FightModel getFightModel();
}
