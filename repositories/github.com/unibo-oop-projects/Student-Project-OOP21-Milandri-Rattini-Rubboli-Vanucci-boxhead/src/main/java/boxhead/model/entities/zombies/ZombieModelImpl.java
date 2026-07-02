package boxhead.model.entities.zombies;

import java.util.HashSet;
import java.util.Set;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import boxhead.model.entities.Player;
import boxhead.model.score.Score;

/**
 * Implementation of {@link ZombieModel}.
 *
 */
public class ZombieModelImpl implements ZombieModel {

    /**
     * Spawn timing
     */
    private static final int SPAWN_INTERVAL = 2500;

    private final Set<Zombie> zombies;
    private final Spawn spawnHandler;
    private final ZombieAI ai;
    private int zombiesToSpawn;
    private double lastSpawn;
    private Set<Zombie> zombiesToKill;
    private int killedZombies;
    private Player player;
    private Score score;

    /**
     * Instantiates a {@link ZombieModelImpl}.
     */
    public ZombieModelImpl() {
        this.zombies = new HashSet<>();
        this.spawnHandler = new SpawnImpl();
        this.ai = new ZombieAIImpl();
        this.zombiesToKill = new HashSet<>();
        this.killedZombies = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.updateSpawns();
        this.ai.determineNextMovement(zombies, player);
        this.updateKills();
        if (this.score != null) {
        	this.score.update();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWalls(final Set<BoundingBox> walls) {
        this.ai.setWalls(walls);
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
    public final void setZombiesToSpawn(final int zombiesToSpawn) {
        this.zombiesToSpawn = zombiesToSpawn;
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
        zombie.takeDamage(damage);
        if (!zombie.isAlive()) {
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
     * Zombie spawn checker
     */
    private void updateSpawns() {
        if (this.zombiesToSpawn > 0 && System.currentTimeMillis() - lastSpawn > SPAWN_INTERVAL) {
            this.spawnZombie();
        }
    }

    /**
     * Spawns a new zombie
     */
    private void spawnZombie() {
        this.zombies.add(spawnHandler.getZombie(this.spawnHandler.getSpawnPoint()));
        this.lastSpawn = System.currentTimeMillis();
        this.zombiesToSpawn = this.zombiesToSpawn - 1;
    }

    /**
     * Kill and remove from the set a zombie
     * @param zombie to remove
     */
    private void killZombie(final Zombie zombie) {
        this.zombiesToKill.add(zombie);
        this.score.addKill();
        
    }

    /**
     * Updates alive zombies set removing dead zombies, also update the counter
     */
    private void updateKills() {
        this.killedZombies += this.zombiesToKill.size();
        this.zombies.removeAll(zombiesToKill); 
        this.zombiesToKill.clear();
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
	public void linkScore(Score score) {
		this.score = score;
		
	}

}
