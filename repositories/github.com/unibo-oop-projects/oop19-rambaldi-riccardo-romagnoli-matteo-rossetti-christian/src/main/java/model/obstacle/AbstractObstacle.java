package model.obstacle;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

/**
 * The implementation of {@link Obstacle} class. 
 */

public abstract class AbstractObstacle implements Obstacle {

    protected Body physicalBody;
    protected boolean hit = false;
    protected Pair<Double, Double> position;
    protected ObstacleBehavior color;
    protected List<Double> measures;

    private static final double RESTITUTION = 0.6;

    public final ObstacleBehavior getBehavior() {
        return this.color;
    }

    public final Pair<Double, Double> getPosition() {
        return this.position;
    }

    public final Body getPhysicalBody() {
        return this.physicalBody;
    }

    public final boolean hit() {
        if (!this.physicalBody.getContacts(false).isEmpty()) {
            this.hit = true;
        }
        return this.hit;
    }

    public final List<Double> getMeasures() {
        return this.measures;
    }

    public final void setObstacleBehavior(final ObstacleBehavior color) {
        this.color = color;
    }

    /**
     * Initializes the {@link Body} with default parameter and the given position.
     * @param fix the {@link BodyFixture} to add to the {@link Body}.
     * @param position to translate the {@link Body}.
     */
    protected final void setBody(final BodyFixture fix, final Pair<Double, Double> position) {
        this.physicalBody = new Body();
        this.measures = new LinkedList<>();
        this.color = ObstacleBehavior.BLU;
        this.physicalBody.addFixture(fix);
        this.position = Pair.of(position.getLeft(), position.getRight());
        this.physicalBody.translate(new Vector2(this.position.getLeft(), this.position.getRight()));
        this.physicalBody.setMass(MassType.INFINITE);
        this.physicalBody.getFixture(0).setRestitution(RESTITUTION);
    }
}
