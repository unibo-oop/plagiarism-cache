package model.ai.behavior;

import java.util.List;

import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;

/**
 * Interface defining the behavior of an AI-controlled NPC (non-player character),
 * specifically for interaction between a survivor target and zombie NPCs.
 *
 * @param <E> the type of Survivor (target)
 * @param <B> the type of Zombie (NPC controlled by AI)
 */
public interface AINPCBehavior<E extends Survivor, B extends Zombie> {
    /**
     * Updates the AI behavior of the NPC zombie with respect to a survivor target
     * and a list of other NPC zombies.
     *
     * @param target the survivor the NPC should consider (e.g., to chase or avoid)
     * @param npc the NPC zombie to update
     * @param npcs the list of other NPC zombies for group or environment context
     */
    void updateAINPC(E target, B npc, List<B> npcs);
}

