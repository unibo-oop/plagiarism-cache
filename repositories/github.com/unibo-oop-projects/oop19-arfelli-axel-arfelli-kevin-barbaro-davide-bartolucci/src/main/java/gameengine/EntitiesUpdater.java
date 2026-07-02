package gameengine;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.StopWatch;

import entity.Actor;
import entity.Entity;
import entity.EntitySpawner;
import entity.Projectile;
import entity.UUIDProjectile;
import gameengine.GameLogger.OutputLevel;

/**
 * Its Runnble Class that update the entities.
 */
public class EntitiesUpdater implements ControlledRunnable {
    private final GameLogger logger;
    private Object lock = new Object();

    private final List<Actor> actors = new LinkedList<>();
    private final List<Projectile> projectiles = new LinkedList<>();

    private int tick;


    private final AtomicBoolean alive = new AtomicBoolean(false);
    private final AtomicBoolean paused = new AtomicBoolean(false);


    public EntitiesUpdater() {
        this(10);
    }

    public EntitiesUpdater(final int tick) {
        this(new GameLogger() {

            @Override
            public void logLine(final String line, final OutputLevel level) {
                System.out.println(String.format("%tD %tT %s", new Date(), new Date(), level.format(line)));
            }
        }, tick);
    }

    /**
     * @param logger
     * @param tick
     */
    public EntitiesUpdater(final GameLogger logger, final int tick) {
        this.logger = logger;
        this.tick = tick;
    }

    public EntitiesUpdater(final GameLogger logger, final int tick, final Object lock) {
        this.logger = logger;
        this.tick = tick;
        this.lock = lock;
    }

    private void checkCollisions() {
        synchronized (lock) {
            this.projectiles.parallelStream()
            .filter(x->x.isAlive())
            .forEach(x -> {
                    final Optional<Actor> dead = this.actors.stream()
                    .filter(Entity::isAlive)
                    .filter(y -> x.getFather().getFaction() != y.getFaction())
                    .filter(y -> x.getBody().checkCollision(y.getBody().getCollisionBox()))
                    .findFirst();
                    if (dead.isPresent()) {
                        dead.get().addToLife(-x.getDamage());
                        x.hit();
                    }
                });
        }
    }

    private void cleanAll() {
        synchronized (this) {
            actors.clear();
            projectiles.clear();
        }
    }

    private synchronized Set<Actor> cleanDeadActors() {
        synchronized (actors) {
            final Set<Actor> set = this.actors.parallelStream()
            .filter(x -> !x.isAlive())
            .collect(Collectors.toSet());
            actors.removeAll(set);
            return set;
        }
    }

    private synchronized Set<Projectile> cleanDeadProjectiles() {
        synchronized (projectiles) {
            final Set<Projectile> set = this.projectiles.stream()
            .filter(x -> !x.isAlive())
            .collect(Collectors.toSet());
            projectiles.removeAll(set);
            return set;
        }
    }

    private synchronized long updateProjetiles() {
        Set<Projectile> set;
        synchronized (projectiles) {
            set = Set.copyOf(projectiles);
        }
        return set.parallelStream()
                .filter(Entity::isAlive)
                .peek(Entity::update)
                .count();

        
    }

    private synchronized long updateActors() {
        Set<Actor> set;
        synchronized (actors) {
            set = Set.copyOf(actors);
        }
        return set.parallelStream()
            .filter(Entity::isAlive)
            .peek(Entity::update)
            .count();
    }

    public final EntitySpawner getSpawner() {
        return new EntitySpawner() {

            @Override
            public void spwanProjectile(final Projectile p) {
                synchronized (projectiles) {
                	projectiles.add(p);
				}            	
            }

            @Override
            public void spwanActor(final Actor a) {
            	synchronized (actors) {
            		actors.add(a);
				}                
            }

            @Override
            public Set<Actor> getDespawnedActors() {
            	synchronized (actors) {
            		final Set<Actor> set = cleanDeadActors();
                    logger.logLine(String.format("Getting %d Dead Actors", set.size()), OutputLevel.DEBUG);
                    return set;
				}                
            }

            @Override
            public Set<Projectile> getDespawnedProjectiles() {
            	synchronized (projectiles) {
            		final Set<Projectile> set = cleanDeadProjectiles();
                    logger.logLine(String.format("Getting %d Dead Projectiles", set.size()), OutputLevel.DEBUG);
                    return set;
				}                
            }
        };
    }

    @Override
    public final void run() {
        alive.set(true);
        while (alive.get()) {
            if (paused.get()) {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    if (isPaused()) {
                        logger.logLine("Thread-Entities have been wakened!", OutputLevel.DEBUG);
                    } else {
                        logger.logLine("Thread-Entities have been wakened too early!", OutputLevel.DEBUG);
                    }
                }
            } else {
                final StopWatch w = new StopWatch();
                w.start();
                logger.logLine("Start Updating Entities!", OutputLevel.DEBUG);
                final long updatedprojectiles = updateProjetiles();
                checkCollisions();
                final long updatedactors = updateActors();
                logger.logLine(String.format("Updated: %dA %dP", updatedactors, updatedprojectiles), OutputLevel.DEBUG);

                w.split();
                while (w.getSplitTime() < (1000 / tick)) {
                    try {
                        Thread.sleep(1000 / tick - w.getSplitTime());
                    } catch (InterruptedException e) {
                    }
                    w.split();
                }
                logger.logLine(String.format("Cicle time was %d!", w.getSplitTime()), OutputLevel.DEBUG);
                w.stop();
            }
        }
        stop();
    }

    @Override
    public final void stop() {
        if (this.alive.get()) {
            this.alive.set(false);
            this.cleanAll();
        }
       
    }

    /**
     * Check if the thread is started.
     * @return A bolean representing the state
     */
    public final boolean isStarted() {
        return this.alive.get();
    }

    @Override
    public final boolean isPaused() {
        return this.alive.get() && this.paused.get();
    }

    @Override
    public final void pause() {
        this.paused.set(true);
    }

    @Override
    public final void resume() {
        synchronized (this) {
            if (this.isPaused()) {
                this.paused.set(false);
                notifyAll();
            }
        }
    }

    @Override
    public final boolean isEnded() {
        return !isStarted();
    }
}
