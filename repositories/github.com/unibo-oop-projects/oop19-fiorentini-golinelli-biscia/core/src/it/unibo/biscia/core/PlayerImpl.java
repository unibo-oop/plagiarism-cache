package it.unibo.biscia.core;

import it.unibo.biscia.core.Player.PlayerManaged;

import java.util.Objects;
import java.util.Optional;

class PlayerImpl implements PlayerManaged {
    private final String name;
    private int lives = Player.INITIAL_LIVES;
    private int points;
    private Optional<Entity.EntityManaged.Movable.Directable> directable = Optional.empty();

    /**
     * simple constructor by name.
     */
    PlayerImpl(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public int getLives() {
        return this.lives;
    }

    @Override
    public Entity getEntity() {
        if (this.directable.isEmpty()) {
            return null;
        }
        return (Entity) this.directable.get();
    }

    @Override
    public void dead() {
        if (this.directable.isEmpty()) {
            throw new IllegalStateException();
        }
        this.lives--;
        this.directable = Optional.empty();
        this.points += Player.POINTS_FOR_DEAD;
    }

    @Override
    public void addPoints(final int points) {
        if (this.directable.isEmpty()) {
            throw new IllegalStateException();
        }
        if (this.lives <= 0) {
            throw new IllegalStateException();
        }
        this.points += points;
    }

    @Override
    public void setDirectable(final Entity.EntityManaged.Movable.Directable directable) {
        if (this.lives <= 0) {
            throw new IllegalStateException();
        }
        if (Objects.isNull(directable)) {
            this.directable = Optional.empty();
        } else {
            if (this.directable.isPresent()) {
                throw new IllegalStateException();
            }
            this.directable = Optional.of(directable);

        }
    }

    @Override
    public Entity.EntityManaged.Movable.Directable getDirectable() {
        if (this.directable.isEmpty()) {
            return null;
        }
        return this.directable.get();
    }

    @Override
    public String toString() {
        return "Player name=" + name + ", lives=" + this.lives + ", points=" + this.points;
    }

    @Override
    public void removeDirectable() {
        this.directable = Optional.empty();
    }

}
