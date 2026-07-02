package ballblast.model.physics;

import java.util.List;

import org.locationtech.jts.geom.Geometry;

import com.google.common.collect.ImmutableList;

/**
 * The manager to check the {@link Collision}s during a game session.
 */
public class SimpleCollisionManager implements CollisionManager {

    private ImmutableList<Collidable> collidables;

    /**
     * The costructor to initialize the list of collidable objects.
     */
    public SimpleCollisionManager() {
        this.collidables = ImmutableList.of();
    }

    @Override
    public final void checkLoop() {

        for (int i = 0; i < this.collidables.size(); i++) {
            for (int j = i + 1; j < this.collidables.size(); j++) {
                final Collidable c1 = this.collidables.get(i);
                final Collidable c2 = this.collidables.get(j);
                checkCollision(c1, c2);
            }
        }
    }

    private void checkCollision(final Collidable c1, final Collidable c2) {

        final CollisionTag t1 = c1.getCollisionTag();
        final CollisionTag t2 = c2.getCollisionTag();

        if (t1.canCollideWith(t2)) {
            final Geometry s1 = c1.getCollisionBox();
            final Geometry s2 = c2.getCollisionBox();
            if (s1.intersects(s2)) {
                c1.notifyCollision(new Collision(c1, c2));
                c2.notifyCollision(new Collision(c2, c1));
            }
        }
    }

    @Override
    public final void addCollidable(final Collidable coll) {
        if (this.collidables.isEmpty()) {
            this.collidables = ImmutableList.of(coll);
        } else {
            this.collidables = ImmutableList.<Collidable>builder().addAll(this.collidables).add(coll).build();
        }
    }

    @Override
    public final void removeCollidable(final Collidable coll) {
        if (this.collidables.isEmpty()) {
            throw new UnsupportedOperationException("Cannot remove from an empty list");
        } else {
            this.collidables = this.collidables.stream().filter(c -> !c.equals(coll))
                    .collect(ImmutableList.toImmutableList());
        }
    }

    @Override
    public final List<Collidable> getCollidables() {
        return ImmutableList.copyOf(this.collidables);
    }

}
