package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.npc.api.DialogueNPC;
import it.tbt.model.party.IParty;
import javafx.util.Pair;

/**
 * The {@code DialogueNPCImpl} class is an implementation of the {@link DialogueNPC} interface.
 * It extends the {@link AbstractNPCImpl} class and represents an NPC with dialogue.
 */
public class DialogueNPCImpl extends AbstractNPCImpl implements DialogueNPC {

    private final String dialogue;

    /**
     * Constructs a new instance of the DialogueNPCImpl class with the specified name, position, dimensions, and dialogue.
     *
     * @param name     the name of the dialogue NPC
     * @param x        the X coordinate of the dialogue NPC's position
     * @param y        the Y coordinate of the dialogue NPC's position
     * @param height   the height of the dialogue NPC
     * @param width    the width of the dialogue NPC
     * @param dialogue the dialogue associated with the NPC
     * @throws IllegalArgumentException if name or dialogue is null or empty, or if height or width is negative
     */
    public DialogueNPCImpl(final String name, final int x, final int y, final int height,
                           final int width, final String dialogue) {
        super(name, x, y, height, width);
        if (dialogue == null || dialogue.isEmpty()) {
            throw new IllegalArgumentException("Dialogue cannot be null or empty");
        }
        this.dialogue = dialogue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDialogue() {
        return this.dialogue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInteraction(final SpatialEntity interactable) {
        if (interactable instanceof IParty) {
            ((IParty) interactable).setDialogue(new Pair<>(this.getName(), this.getDialogue()));
        }
    }
}
