package it.tbt.model.entities.npc.api;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.world.interaction.Interactable;

/**
 * The {@code NPC} interface represents a non-player character (NPC) in the game world.
 * It extends the {@link SpatialEntity} interface and the {@link Interactable} interface.
 */
public interface NPC extends SpatialEntity, Interactable {

    /**
     * Retrieves the name of the NPC.
     *
     * @return the name of the NPC
     */
    @Override
    String getName();
}
