package model.spawner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import model.entities.Enemy;
import model.entities.Spaceship;

/**
 * 
 * The spawner for enemies.
 * 
 */
public class StoryModeSpawner implements Spawner {

    private static final int INITIAL_SPAWN_TIME = 2000;

    private int spawnDelay;
    private int enemySpawned;
    private int timeElapsed;

    private Optional<State> state;

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public StoryModeSpawner(final Spaceship spaceship) {
        this.spawnDelay = INITIAL_SPAWN_TIME;
        this.enemySpawned = 0;
        this.timeElapsed = 0;
        this.state = Optional.of(new InitialState(spaceship));
    }

    /**
     * 
     * @param elapsed
     *            time elapsed from the previous update.
     */
    @Override
    public void update(final int elapsed) {
        this.timeElapsed += elapsed;
    }

    /**
     * 
     * @return true if an enemy can be spawned.
     */
    @Override
    public boolean canSpawn() {
        return this.timeElapsed >= this.spawnDelay && this.state.isPresent();
    }

    /**
     * 
     * @param delay
     *            new spawn delay.
     */
    @Override
    public void setSpawnDelay(final int delay) {
        this.spawnDelay = delay;
    }

    /**
     * 
     * @return the number of enemy spawned.
     */
    @Override
    public int getEnemySpawned() {
        return this.enemySpawned;
    }

    /**
     * 
     * @return the actual state of the spawner.
     */
    @Override
    public Optional<State> getState() {
        return state;
    }

    /**
     * 
     * @param state
     *            to set.
     */
    @Override
    public void setState(final Optional<State> state) {
        this.state = state;
    }

    /**
     * 
     * @return a collection of enemies that have just spawned.
     */
    @Override
    public Collection<Enemy> spawn() {
        this.timeElapsed = 0;
        final Collection<Enemy> enemies;

        if (this.state.isPresent()) {
            enemies = this.state.get().spawn();

            this.setSpawnDelay(this.state.get().getSpawnDelay());
            this.enemySpawned += enemies.size();

            if (this.state.get().isStateEnded()) {
                this.state = this.state.get().getNextState();
            }
        } else {
            enemies = new ArrayList<>();
        }

        return enemies;
    }

    /**
     * 
     * @return true if all enemies are already spawned.
     */
    @Override
    public boolean isSpawnFinished() {
        return !this.state.isPresent();
    }
}
