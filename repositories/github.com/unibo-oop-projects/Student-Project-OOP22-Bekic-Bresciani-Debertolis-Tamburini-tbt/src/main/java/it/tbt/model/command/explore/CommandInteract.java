package it.tbt.model.command.explore;

import it.tbt.model.command.api.Command;
import it.tbt.model.world.interaction.InteractionTrigger;

/**
 * Command for the interaction for any object who wants to interact with the environment.
 */
public final class CommandInteract implements Command {
    private final InteractionTrigger interactionTrigger;

    /**
     * @param interactionTrigger
     */
    public CommandInteract(final InteractionTrigger interactionTrigger) {
        this.interactionTrigger = interactionTrigger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.interactionTrigger.triggerInteraction();
    }
}
