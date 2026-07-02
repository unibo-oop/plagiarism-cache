package model.ai.behavior;

import model.ai.collision.DefaultCollisionResolver;
import model.ai.movement.DefaultMovementStrategy;
import model.ai.separation.DefaultSeparationBehavior;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;

/**
 * Factory class for creating default AI NPC behavior instances.
 */
public class FactoryAINPCBehavior {

    /**
     * Creates a default AI behavior instance for zombie NPCs.
     * This behavior combines default movement, separation, and collision
     * strategies.
     *
     * @return a new instance of AINPCBehavior for Zombies targeting Survivors
     */
    public AINPCBehavior<Survivor, Zombie> createBaseNPCBehavior() {
        return new AIZombieBehavior<>(new DefaultMovementStrategy<>(),
                new DefaultSeparationBehavior<>(),
                new DefaultCollisionResolver<>());
    }

}
