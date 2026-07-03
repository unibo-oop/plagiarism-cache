package it.unibo.roguekong.model.entity;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;

/**
 * Power Ups interface:
 * 1. Applies effect on player
 * 2. Removes effect
 * 3. Returns PowerUp name
 * 4. Returns PowerUp Description
 */
public interface PowerUp {
    public void applyEffect(PlayerImpl player);
    public static void removeEffect(PlayerImpl player){};

    String getName();
    String getDescription();
}
