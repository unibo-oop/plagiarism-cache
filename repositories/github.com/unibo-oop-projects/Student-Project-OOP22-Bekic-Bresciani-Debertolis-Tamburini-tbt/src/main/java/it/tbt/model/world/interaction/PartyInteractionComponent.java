package it.tbt.model.world.interaction;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.world.collision.CollisionDetector;
import it.tbt.model.world.collision.CollisionDetectorImpl;
import it.tbt.model.party.IParty;

/**
 * Interaction component implementation for the IParty objects.
 */

public class PartyInteractionComponent implements InteractionComponent {

    private final IParty party;
    private final CollisionDetector collisionDetector = new CollisionDetectorImpl();

    /**
     * @param party the party object onto which this component is attached to.
     */
    @SuppressFBWarnings (
            value = { "EI2" },
            justification = "The Component needs to access the exact instance of the Party the game is using."
    )
    public PartyInteractionComponent(final IParty party) {
        this.party = party;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interactLogic() {
        party.getCurrentRoom().getEntities().
                stream().
                filter(l -> l instanceof Interactable).
                filter(l -> collisionDetector.checkCollision(l, this.party)).
                findFirst().
                ifPresent(l -> ((Interactable) l).onInteraction(this.party));

    }
}
