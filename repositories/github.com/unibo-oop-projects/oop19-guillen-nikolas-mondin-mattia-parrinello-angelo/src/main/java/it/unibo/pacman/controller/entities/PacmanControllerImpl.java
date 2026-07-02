package it.unibo.pacman.controller.entities;

import java.util.Optional;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import it.unibo.pacman.model.entities.Entity;
import it.unibo.pacman.model.entities.Mortal;
import it.unibo.pacman.model.leaderboard.PlayerScore;
import it.unibo.pacman.model.utilities.Direction;
import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.model.utilities.Status;
import it.unibo.pacman.view.entities.MovableView;
/**
 * An implementation of {@link PacmanController}.
 */
public class PacmanControllerImpl extends MovableControllerDecorator implements PacmanController {
private final Mortal pacman;
private final Set<EntityController> walls;
private final Set<EntityController> pills; 
private final Set<EntityController> powerPills;
private final Set<GhostController> ghosts;
private final PlayerScore score;
private Optional<Direction> preferred = Optional.empty();
private Timer timer = new Timer();
private static final int DELAY = 8;
    /**
     * Construct an implementation of {@link PacmanController}.
     * @param pacman the game object that represent pacman
     * @param view its view
     * @param entities pills, walls and other static game objects
     * @param movables ghosts and other movable game objects
     * @param score the class that manage the score points in game
     */
    public PacmanControllerImpl(final Mortal pacman, final MovableView view,
                           final Set<EntityController> entities, final Set<? extends MovableController> movables,
                           final PlayerScore score) {
        super(new SimpleMovableController(pacman, view));
        this.pacman = pacman;
        this.walls = entities.stream()
                             .filter(c -> c.getType().equals(EntityType.WALL))
                             .collect(Collectors.toSet());
        this.pills = entities.stream()
                             .filter(c -> c.getType().equals(EntityType.PILL))
                             .collect(Collectors.toSet());
        this.powerPills = entities.stream()
                          .filter(c -> c.getType().equals(EntityType.POWERPILL))
                          .collect(Collectors.toSet());
        this.ghosts = movables.stream().filter(c -> c.getType().equals(EntityType.BLINKY)
                                                    || c.getType().equals(EntityType.CLYDE)
                                                    || c.getType().equals(EntityType.INKY)
                                                    || c.getType().equals(EntityType.PINKY))
                                       .map(c -> (GhostController) c)
                                       .collect(Collectors.toSet());
        this.score = score;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        if (preferred.isPresent()) {
            final Direction dir = pacman.getDirection();
            pacman.setDirection(preferred.get());
            if (!this.wallCheck()) {
               super.move();
           } else {
               pacman.setDirection(dir);
               if (!this.wallCheck()) {
                  super.move();
               }
           }
        } else {
            if (!this.wallCheck()) {
               super.move();
           }
        }
    }
    private boolean wallCheck() {
        return walls.stream()
                    .anyMatch(w -> w.getCollision(pacman.getBoundsAt(pacman.nextPosition())).isPresent());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void eatPill() {
        this.eat(pills);
        this.removeAll(pills);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void eatPowerPill() {
        if (powerPills.stream()
                      .anyMatch(p -> p.getCollision(pacman.getBounds()).isPresent())) {
            ghosts.stream().forEach(g -> g.fear());
            this.timer.cancel();
            this.timer = new Timer();
            pacman.setStatus(Status.CHASING);
            timer.schedule(new SetChased(), DELAY * 1000);
        }
        this.eat(powerPills);
        this.removeAll(powerPills);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void eatGhost() {
        if (pacman.getStatus().equals(Status.CHASING)) {
            this.eat(ghosts);
            ghosts.stream()
                  .filter(g -> g.getCollision(pacman.getBounds()).isPresent())
                  .forEach(g -> g.respawn());
        } else {
            if (ghosts.stream()
                      .anyMatch(g -> g.getCollision(pacman.getBounds()).isPresent())) {
                pacman.decreaseLives();
                ghosts.stream().forEach(g -> g.respawn());
                if (this.pacman.getLives() <= 0) {
                    this.remove();
                }
                this.respawn();
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPreferredDirection(final Direction dir) {
        preferred = Optional.of(dir);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void unsetPreferredDirection() {
        preferred = Optional.empty();
    }
    private boolean eat(final Set<? extends EntityController> toEat) {
        final Set<Entity> set = toEat.stream()
                               .map(p -> p.getCollision(pacman.getBounds()))
                               .filter(o -> o.isPresent())
                               .map(o -> o.get())
                               .collect(Collectors.toSet());
         if (!set.isEmpty()) {
             score.updateScore(set);
             return true;
         }
        return false;
    }

    private void removeAll(final Set<? extends EntityController> toRemove) {
        toRemove.removeAll(toRemove.stream()
                                   .filter(p -> p.getCollision(pacman.getBounds()).isPresent())
                                   .peek(p -> p.remove())
                                   .collect(Collectors.toSet()));
    }

    private class SetChased extends TimerTask {
        @Override
        public void run() {
            pacman.setStatus(Status.CHASED);
        }
    }

}
