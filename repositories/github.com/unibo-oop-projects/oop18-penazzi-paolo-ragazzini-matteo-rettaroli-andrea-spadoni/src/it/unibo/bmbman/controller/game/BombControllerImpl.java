package it.unibo.bmbman.controller.game;

import java.awt.Rectangle;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import it.unibo.bmbman.controller.SoundsController;
import it.unibo.bmbman.model.collision.CollisionImpl;
import it.unibo.bmbman.model.entities.BombImpl;
import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.utilities.BombState;
import it.unibo.bmbman.model.utilities.Pair;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.entities.BombView;
/**
 *  Implementation of {@link BombController}.
 */
public class BombControllerImpl implements BombController {
    private final List<Pair<BombImpl, BombView>> amountBombs;
    /**
     * Create BombControllerImpl.
     */
    public BombControllerImpl() {
        super();
        this.amountBombs = new CopyOnWriteArrayList<>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<BombImpl, BombView>> getBombsToRemove() {
        return this.amountBombs.stream().filter(b -> b.getX().remove())
                .collect(Collectors.toList());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<BombImpl> getBombsInExplosion() {
        return this.amountBombs.stream()
                .filter(b -> b.getX().getState() == BombState.IN_EXPLOSION)
                .map(b -> b.getX())
                .collect(Collectors.toList());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<BombView> getBombView() {
        return this.amountBombs.stream().map(b -> b.getY()).collect(Collectors.toSet());
    }
    /**
     * {@inheritDoc} 
     */
    @Override
    public Optional<BombImpl> plantBomb(final HeroImpl hero) {
        if (hero.getBombsNumber() - this.amountBombs.size() >= 1) {
            final Position pos = new Position(Position.getCenteredPosition(hero.getPosition()));
            final BombImpl b = new BombImpl(pos, hero.getBombRange());
            b.startTimer();
            this.amountBombs.add(new Pair<BombImpl, BombView>(b, new BombView(pos)));
            SoundsController.getPlaceBombSound().ifPresent(s -> s.play());
            return Optional.of(b);
        }
        return Optional.empty();
    }
    /**
     * {@inheritDoc} 
     */
    @Override
    public void update() {
        this.amountBombs.forEach(b -> b.getX().update());
        this.amountBombs.forEach(b -> {
            b.getY().setBombState(b.getX().getState());
            if (b.getX().getState() == BombState.IN_EXPLOSION) {
                    SoundsController.getExplosionSound().ifPresent(s -> s.play());
            }
        });
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBomb() {
        this.amountBombs.removeAll(getBombsToRemove());
    }
    /**
     * {@inheritDoc} 
     */
    @Override
    public void collision(final Set<Entity> entities) {
        this.getBombsInExplosion().forEach(b -> {
            entities.forEach(e -> {
                if (checkCollision(e, b.getExplosion().get().getX()) || checkCollision(e, b.getExplosion().get().getY())) {
                    this.notifyCollision(e, b);
                }
            });
        });
        this.getBombsInExplosion().forEach(b -> b.setBombExploded());
    }
    private boolean checkCollision(final Entity receiver, final Rectangle collider) {
        return receiver.getCollisionComponent().getHitbox().intersects(collider);
    }
    private void notifyCollision(final Entity receiver, final BombImpl b) {
        receiver.onCollision(new CollisionImpl(b, receiver.getPosition()));
    }
}
