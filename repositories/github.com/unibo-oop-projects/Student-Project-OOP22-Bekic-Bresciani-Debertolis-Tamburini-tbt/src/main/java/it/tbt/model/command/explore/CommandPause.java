package it.tbt.model.command.explore;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.command.api.Command;
import it.tbt.model.statechange.PauseTrigger;

/**
 * Command for triggering a Pause GameState.
 */
public class CommandPause implements Command {
    private final PauseTrigger pauseTrigger;

    /**
     * @param pauseTrigger the pause trigger which will be used to trigger the Pause GameState.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "The Command pattern encapsulates the objects on which perform the operations.")
    public CommandPause(final PauseTrigger pauseTrigger) {
        this.pauseTrigger = pauseTrigger;
    }

    /**
     * Triggers the {@link it.tbt.model.GameState#PAUSE}.
     */
    @Override
    public void execute() {
        pauseTrigger.triggerPause();
    }

}
