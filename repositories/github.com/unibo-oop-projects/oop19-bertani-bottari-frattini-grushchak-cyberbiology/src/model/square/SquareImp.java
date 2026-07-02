package model.square;

import java.util.Optional;

import model.entity.Entity;

public class SquareImp implements Square {

    /**
     * the occupant of each square
     */
    private Optional<Entity> entity;
    public SquareImp(final Optional<Entity> entity) {
        this.entity = entity;
    }

    @Override
    public final Optional<Entity> getEntity() {
        return this.entity;
    }

    @Override
    public final void setEntity(final Optional<Entity> entity) {
        this.entity = entity;
    }
}
