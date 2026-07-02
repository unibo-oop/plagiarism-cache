package it.unibo.oop.crossline.game.actor;

import it.unibo.oop.crossline.game.attributes.Damageable;
import it.unibo.oop.crossline.game.attributes.Destructible;
import it.unibo.oop.crossline.game.attributes.Updatable;

/**
 * An interface that represents an actor. This should be used by objects that that need to represent an "alive" entity.
 */
public interface Actor extends Damageable, Destructible, Updatable {

    /**
     * Get the current actor team.
     * @return the team
     */
    int getTeam();

}
