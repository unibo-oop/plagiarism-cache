package it.tbt.model.entities.npc.api;

/**
 * The {@code DialogueNPC} interface represents a non-player character (NPC) that provides dialogue.
 * It extends the {@link NPC} interface and provides a method to retrieve the dialogue.
 */
public interface DialogueNPC extends NPC {

    /**
     * Gets the dialogue provided by the NPC.
     *
     * @return the dialogue string
     */
    String getDialogue();
}
