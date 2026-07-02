package zombieversity.model.region;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point2D;
import zombieversity.model.entities.Entity;
import zombieversity.model.entities.passive.PassiveEntity;

public class RegionImpl implements Region {

    private final Point2D lowLimit;
    private final Point2D upLimit;

    private Set<PassiveEntity> obstacles;
    private final Set<Entity> entities;

    public RegionImpl(final Point2D lLimit, final Point2D uLimit) {
        this.lowLimit = lLimit;
        this.upLimit = uLimit;
        this.entities = new HashSet<>();
        this.loadObstacles(new HashSet<>());
    }
    @Override
    public final void addObstacle(final PassiveEntity o) {
        this.obstacles.add(o);
    }
    @Override
    public final void loadObstacles(final Set<PassiveEntity> obstacles) {
        this.obstacles = obstacles;
    }

    @Override
    public final void addEntity(final Entity e) {
        this.entities.add(e);
    }

    @Override
    public final Set<Entity> outOfRegion() {
        final Set<Entity> result = new HashSet<>();
        this.entities.forEach(e -> {
            if (!isInside(e.getPosition())) {
                this.entities.remove(e);
               result.add(e);
            }
        });
        return result;
    }

    @Override
    public final void computeCollision() {
////        int numOfe = entities.size();
////        for( int i = 0; i < numOfe; i++) {
////            Entity e = entities.get(i);
////            for(int j = i + 1; j < numOfe; j++) {
////                Entity e2 = entities.get(j);
////                if( e.getBBox().intersects(e2.getBBox()))
////                    //e.computeCollision(CollisionType.of(e2.getType()));
////                this.obstacles.forEach(o -> {
////                    if(e.getBBox().intersects(o.getBBox()))
////                        //e.computeCollision(CollisionType.WITHOBSTACLE));
////                        ;
////                });
////            }
//        }
        this.entities.forEach(e -> {

        });
    }
    @Override
    public final boolean isInside(final Point2D p) {
        return p.subtract(lowLimit) != Point2D.ZERO && upLimit.getX() >= p.getX() && upLimit.getY() >= p.getY();
    }
    @Override
    public final String toString() {
        return this.entities.toString() + this.obstacles.toString();
    }

}
