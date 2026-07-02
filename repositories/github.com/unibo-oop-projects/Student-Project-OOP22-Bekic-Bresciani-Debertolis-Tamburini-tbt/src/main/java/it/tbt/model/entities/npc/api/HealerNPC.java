package it.tbt.model.entities.npc.api;

/**
 * The {@code HealerNPC} interface represents a non-player character (NPC) that can heal entities.
 * It extends the {@link NPC} interface and provides a method to retrieve the heal amount.
 */
public interface HealerNPC extends NPC {

    /**
     * Gets the amount of healing provided by the NPC.
     *
     * @return the heal amount
     */
    int getHealAmount();
}
