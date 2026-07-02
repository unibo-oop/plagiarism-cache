package it.tbt.model.command.explore;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.command.api.Command;
import it.tbt.model.party.IParty;

/**
 * Command for moving the IParty.
 */
public final class CommandMove implements Command {
    private final IParty party;
    private final int xVar;
    private final int yVar;

    /**
     * @param party the party on which this command is mapped.
     * @param xVar the x value to be moved.
     * @param yVar the y value to be moved.
     */

    @SuppressFBWarnings(value = "EI2",
            justification = "The Command pattern encapsulates the objects on which perform the operations.")
    public CommandMove(final IParty party, final int xVar, final int yVar) {
        this.party = party;
        this.xVar = xVar;
        this.yVar = yVar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.party.move(this.xVar, this.yVar);
    }
}
