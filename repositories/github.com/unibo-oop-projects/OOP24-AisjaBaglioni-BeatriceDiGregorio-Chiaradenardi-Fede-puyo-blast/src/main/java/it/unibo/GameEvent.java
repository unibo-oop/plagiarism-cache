
package it.unibo;

import java.util.EventObject;

import it.unibo.controller.LevelManager;

/**
 * The GameEvent class represents an event in the game,
 * such as restarting a level.
 * It extends {@link java.util.EventObject}. This serves as the
 * base for defining custom event classes that can carry additional event data
 * and be used in an event-driven system.
 * 
 * The GameEvent constructor can optionally carry a {@link LevelManager.LevelConfig} that provides
 * configuration details for a level.
 * This event is typically used to notify game components when a
 * significant action occurs, such as transitioning
 * between the game and the menu.
 */
public class GameEvent extends EventObject {
    public LevelManager.LevelConfig levelConfig;

    public GameEvent(Object source) {
        super(source);
    }

    public GameEvent(Object source, LevelManager.LevelConfig levelConfig) {
        super(source);
        this.levelConfig = levelConfig;
    }
}
