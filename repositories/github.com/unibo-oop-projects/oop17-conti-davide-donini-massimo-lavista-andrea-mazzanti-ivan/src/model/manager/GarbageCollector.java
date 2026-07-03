package model.manager;

import java.util.List;

import model.entities.Bullet;
import model.entities.Enemy;
import model.entities.powerup.PowerUp;

/**
 * Represents the class that delete reference to unnecessary object, reducing computing and memory.
 */
public interface GarbageCollector {

    /**
     * 
     * Delete entity that are out of the game map.
     * 
     * @param enemies
     *            game's enemies
     * @param powerUps
     *            game's power ups
     * @param bullets
     *            game's bullets
     */
    void deleteUnnecessaryEntities(List<Enemy> enemies, List<PowerUp> powerUps, List<Bullet> bullets);

}
