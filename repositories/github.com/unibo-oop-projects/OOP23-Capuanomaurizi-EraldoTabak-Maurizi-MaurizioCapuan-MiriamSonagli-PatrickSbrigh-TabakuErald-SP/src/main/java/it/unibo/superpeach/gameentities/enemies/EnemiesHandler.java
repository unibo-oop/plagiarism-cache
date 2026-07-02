package it.unibo.superpeach.gameentities.enemies;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Enemies Handler class used for enemies management.
 * 
 * @author Eraldo Tabaku
 */
public class EnemiesHandler {

    private final List<Enemy> enemies;

    /**
     * EnemiesHandler costructor method that creates a new array of enemies.
     */
    public EnemiesHandler() {
        enemies = new ArrayList<>();
    }

    /**
     * this method calls the tick() method of all the enemies.
     */
    public void tickEnemies() {
        final Set<Enemy> enemiesToRemove = new HashSet<>();
        for (final Enemy enemy : enemies) {
            enemy.tick();
            if (!enemy.isAlive()) {
                enemiesToRemove.add(enemy);
            }
        }

        for (final Enemy enemy : enemiesToRemove) {
            enemies.remove(enemy);

        }
    }

    /**
     * method used to call the render method of
     * all the enemies generated.
     * 
     * @param g for the graphics.
     */
    public void renderEnemies(final Graphics g) {
        for (final Enemy enemy : enemies) {
            enemy.render(g);
        }
    }

    /**
     * @param enemy enemy to add to the enemies handler.
     */
    public void addEnemy(final Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * @param enemy to remove an enemy from the enemies handler.
     */
    public void removeEnemy(final Enemy enemy) {
        this.enemies.remove(enemy);
    }

    /**
     * @return a list of the enemies the are currently alive.
     */
    public List<Enemy> getEnemies() {
        return List.copyOf(enemies);
    }

}
