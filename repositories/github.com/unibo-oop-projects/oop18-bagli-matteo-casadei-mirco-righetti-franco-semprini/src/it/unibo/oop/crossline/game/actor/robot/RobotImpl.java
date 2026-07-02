package it.unibo.oop.crossline.game.actor.robot;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicReference;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import it.unibo.oop.crossline.game.attributes.Physical;
import it.unibo.oop.crossline.game.weapon.Weapon;
/**
 * The class of a RobotImpl.
 */
public class RobotImpl extends Observable implements Robot {

    private static final float MOVE_SPEED = 1;
    private static final float CIRCLE_RADIUS = 0.3f;

    private static AtomicReference<FixtureDef> atomicFixture = new AtomicReference<>();
    private static AtomicReference<CircleShape> atomicShape = new AtomicReference<>();

    private final float attackRange;
    private final Body body;
    private float health;
    private boolean queuedForDestruction;
    private final Physical target;
    private final Integer team;
    private final Weapon weapon;

    /**
     * Constructor of RobotImpl.
     * @param attackRange the attackRange
     * @param health the health
     * @param position the position
     * @param target the target
     * @param team the team
     * @param weapon the weapon
     * @param world the world
     */
    public RobotImpl(final float attackRange, final float health, final Vector2 position, final Physical target,
            final int team, final Weapon weapon, final World world) {
        super();
        this.attackRange = attackRange;
        this.health = health;
        this.target = target;
        this.team = team;
        this.weapon = weapon;

        final BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyType.KinematicBody;
        body = world.createBody(bodyDef);
        body.setUserData(this);
        body.createFixture(getFixture());
        body.getTransform().setPosition(position);
    }

    @Override
    public final void update() {
        final Vector2 position = getPosition().cpy();
        final Vector2 targetPosition = target.getPosition().cpy();
        final Vector2 deltaPosition = targetPosition.cpy().sub(position);
        final float angle = deltaPosition.angle() * MathUtils.degreesToRadians;
        body.setTransform(position, angle);
        body.setLinearVelocity(deltaPosition.nor().scl(MOVE_SPEED));
        weapon.setDirection(deltaPosition.nor());
        if (weapon.canShoot() && position.dst(targetPosition) <= attackRange) {
            weapon.shoot();
        }
    }

    @Override
    public final Body getBody() {
        return body;
    }

    @Override
    public final boolean isQueuedForDestruction() {
        return queuedForDestruction;
    }

    @Override
    public final void queueForDestruction() {
        queuedForDestruction = true;
        setChanged();
        notifyObservers();
    }

    private static FixtureDef getFixture() {
        FixtureDef fixtureDef = atomicFixture.get();
        if (fixtureDef == null) {
            fixtureDef = new FixtureDef();
            fixtureDef.shape = getShape();
            atomicFixture.compareAndSet(null, fixtureDef);
        }
        return fixtureDef;
    }

    @Override
    public final float getHealth() {
        return health;
    }

    @Override
    public final void applyDamage(final float damage) {
        health = Math.max(health - damage, 0);
        if (health == 0) {
            queueForDestruction();
        }
    }

    @Override
    public final void addObserver(final Observer observer) {
        super.addObserver(observer);
    }

    private static CircleShape getShape() {
        CircleShape circleShape = atomicShape.get();
        if (circleShape == null) {
            circleShape = new CircleShape();
            circleShape.setRadius(CIRCLE_RADIUS);
            atomicShape.compareAndSet(null, circleShape);
        }
        return circleShape;
    }

    @Override
    public final Physical getTarget() {
        return target;
    }

    @Override
    public final int getTeam() {
        return team;
    }

    @Override
    public final Weapon getWeapon() {
        return weapon;
    }

    /**
     * HashCode method.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(attackRange);
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + Float.floatToIntBits(health);
        final int magic1 = 1231;
        final int magic2 = 1237;
        result = prime * result + (queuedForDestruction ? magic1 : magic2);
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
        return result;
    }

    /**
     * Equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RobotImpl other = (RobotImpl) obj;
        if (Float.floatToIntBits(attackRange) != Float.floatToIntBits(other.attackRange)) {
            return false;
        }
        if (body == null) {
            if (other.body != null) {
                return false;
            }
        } else if (!body.equals(other.body)) {
            return false;
        }
        if (Float.floatToIntBits(health) != Float.floatToIntBits(other.health)) {
            return false;
        }
        if (queuedForDestruction != other.queuedForDestruction) {
            return false;
        }
        if (target == null) {
            if (other.target != null) {
                return false;
            }
        } else if (!target.equals(other.target)) {
            return false;
        }
        if (team == null) {
            if (other.team != null) {
                return false;
            }
        } else if (!team.equals(other.team)) {
            return false;
        }
        if (weapon == null) {
            if (other.weapon != null) {
                return false;
            }
        } else if (!weapon.equals(other.weapon)) {
            return false;
        }
        return true;
    }

    /**
     * ToString method.
     */
    @Override
    public String toString() {
        return "RobotImpl [attackRange=" + attackRange + ", body=" + body + ", health=" + health
                + ", queuedForDestruction=" + queuedForDestruction + ", target=" + target + ", team=" + team
                + ", weapon=" + weapon + "]";
    }

}
