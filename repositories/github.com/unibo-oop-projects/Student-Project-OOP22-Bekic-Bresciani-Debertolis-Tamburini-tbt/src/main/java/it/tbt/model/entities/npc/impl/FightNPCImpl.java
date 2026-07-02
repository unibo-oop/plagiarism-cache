package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.KillableEntity;
import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.npc.api.FightNPC;
import it.tbt.model.fight.api.FightModel;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;
import it.tbt.model.world.api.KillObserver;

/**
 * The {@code FightNPCImpl} class is an implementation of the {@link FightNPC} interface.
 * It extends the {@link AbstractNPCImpl} class and represents an NPC that triggers fights.
 */
public class FightNPCImpl extends AbstractNPCImpl implements FightNPC, StateTrigger, KillableEntity {

    private final FightModel fightModel;
    private KillObserver killObserver;
    private StateObserver stateObserver;

    /**
     * Constructs a new instance of the FightNPCImpl class with the specified name, position, dimensions, and fight model.
     *
     * @param name       the name of the fight NPC
     * @param x          the X coordinate of the fight NPC's position
     * @param y          the Y coordinate of the fight NPC's position
     * @param height     the height of the fight NPC
     * @param width      the width of the fight NPC
     * @param fightModel the fight model associated with the NPC
     */
    public FightNPCImpl(final String name, final int x, final int y, final int height,
                        final int width, final FightModel fightModel) {
        super(name, x, y, height, width);
        this.fightModel = fightModel;
        this.killObserver = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInteraction(final SpatialEntity interactable) {
        if (!(interactable instanceof IParty)) {
            throw new IllegalArgumentException("Interactable must be an instance of IParty");
        }
        if (stateObserver == null) {
            throw new IllegalStateException("StateObserver not set"); // throw IllegalStateException if stateObserver is not set
        }
        if (killObserver == null) {
            throw new IllegalStateException("KillObserver not set"); // throw IllegalStateException if killObserver is not set
        }
        fightModel.initializeParty((IParty) interactable);
        this.stateObserver.onFight(fightModel);
        this.killObserver.onKill(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FightModel getFightModel() {
        return fightModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKillObserver(final KillObserver killObserver) {
        this.killObserver = killObserver;
    }
}
