package it.tbt.model.entities.npc.impl;

import it.tbt.model.entities.KillableEntity;
import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.npc.api.HealerNPC;
import it.tbt.model.party.IParty;
import it.tbt.model.world.api.KillObserver;

/**
 * The {@code HealerNPCImpl} class is an implementation of the {@link HealerNPC} interface.
 * It extends the {@link AbstractNPCImpl} class and represents an NPC that can heal allies.
 */
public class HealerNPCImpl extends AbstractNPCImpl implements HealerNPC, KillableEntity {

    private final int healAmount;
    private KillObserver killObserver;

    /**
     * Constructs a new instance of the HealerNPCImpl class with the specified name, position, dimensions, and heal amount.
     *
     * @param name       the name of the healer NPC
     * @param x          the X coordinate of the healer NPC's position
     * @param y          the Y coordinate of the healer NPC's position
     * @param height     the height of the healer NPC
     * @param width      the width of the healer NPC
     * @param healAmount the amount of healing provided by the NPC
     * @throws IllegalArgumentException if name is null or empty, or if healAmount is negative
     */
    public HealerNPCImpl(final String name, final int x, final int y, final int height, final int width, final int healAmount) {
        super(name, x, y, height, width);
        if (healAmount < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative");
        }
        this.healAmount = healAmount;
        this.killObserver = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealAmount() {
        return healAmount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInteraction(final SpatialEntity interactable) {
        if (interactable instanceof IParty) {
            for (final Ally ally : ((IParty) interactable).getMembers()) {
                if (ally.getHealth() + this.getHealAmount() <= ally.getMaxHealth()) {
                    ally.setHealth(ally.getHealth() + this.getHealAmount());
                } else {
                    ally.setHealth(ally.getMaxHealth());
                }
            }
        }
        this.killObserver.onKill(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKillObserver(final KillObserver killObserver) {
        this.killObserver = killObserver;
    }
}
