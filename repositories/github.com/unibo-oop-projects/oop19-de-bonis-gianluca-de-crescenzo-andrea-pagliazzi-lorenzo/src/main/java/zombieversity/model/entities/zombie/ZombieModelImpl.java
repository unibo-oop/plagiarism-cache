package zombieversity.model.entities.zombie;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import zombieversity.model.entities.Player;

/**
 * Implementation of {@link ZombieModel}.
 *
 */
public class ZombieModelImpl implements ZombieModel {

    /**
     * Time to wait to spawn next zombie.
     */
    private static final int SPAWN_INTERVAL = 2000;

    private final Set<Zombie> zombies;
    private final Spawn spawnHandler;
    private final ZombieAI ai;
    private Tiers zombieTier;
    private int zombiesToSpawn;
    private double lastSpawn;
    private Player player;
    private Set<Zombie> zombiesToKill;
    private int killedZombies;

    /**
     * Instantiates a {@link ZombieModelImpl}.
     */
    public ZombieModelImpl() {
        this.zombies = new HashSet<>();
        this.spawnHandler = new SpawnImpl();
        this.ai = new ZombieAIImpl();
        this.zombieTier = Tiers.WEAK;
        this.zombiesToKill = new HashSet<>();
        this.killedZombies = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.updateSpawns();
        this.ai.computeNextMovement(zombies, player);
        this.updateKills();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObstacles(final Set<BoundingBox> obstacles) {
        this.ai.setObstacles(obstacles);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void setSpawnPoints(final Set<Point2D> spawnPoints) {
        this.spawnHandler.setSpawnPoints(spawnPoints);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayer(final Player player) {
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setZombiesToSpawn(final int zombiesToSpawn) {
        this.zombiesToSpawn = zombiesToSpawn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setZombieTier(final Tiers zombieTier) {
        this.zombieTier = zombieTier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Zombie> getZombies() {
        return new HashSet<>(this.zombies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getZombiesCount() {
        return this.zombiesToSpawn + this.zombies.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void hitZombie(final Zombie zombie, final int damage) {
        zombie.getLifeManager().decreaseHP(damage);
        if (!zombie.getLifeManager().isAlive()) {
            this.killZombie(zombie);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getKilledZombies() {
        return this.killedZombies;
    }

    /**
     * Checks if a new zombie has to be spawned.
     */
    private void updateSpawns() {
        if (this.zombiesToSpawn > 0 && System.currentTimeMillis() - lastSpawn > SPAWN_INTERVAL) {
            this.spawnZombie();
        }
    }

    /**
     * Spawns a new zombie.
     */
    private void spawnZombie() {
        this.zombies.add(spawnHandler.getZombieFromTier(this.spawnHandler.getSpawnPoint(), this.zombieTier));
        this.lastSpawn = System.currentTimeMillis();
        this.zombiesToSpawn = this.zombiesToSpawn - 1;
    }

    /**
     * Kills a zombie, removing it from the Set.
     * @param zombie to remove.
     */
    private void killZombie(final Zombie zombie) {
        this.zombiesToKill.add(zombie);
    }

    /**
     * Updates kills counter and removes dead zombies from alive zombies set.
     */
    private void updateKills() {
        this.killedZombies += this.zombiesToKill.size();
        this.zombies.removeAll(zombiesToKill);
        this.zombiesToKill.clear();
    }

}
