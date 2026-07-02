package it.unibo.oop.crossline.game.bullet;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import it.unibo.oop.crossline.game.actor.Actor;

/**
 * The class manages all the logic behind a bullet.
 */
public class BulletImpl implements Bullet {

    private final Body body;
    private final Actor owner;
    private final float damage;
    private final float speed;
    private final Vector2 direction;
    private boolean queuedForDeletion;

    /**
     * Initialize the bullet.
     * @param damage the bullet damage
     * @param direction the bullet direction
     * @param owner the bullet owner
     * @param position the bullet position
     * @param size the bullet size
     * @param speed the bullet speed
     */
    public BulletImpl(final float damage, final Vector2 direction, final Actor owner, final Vector2 position, final float size, final float speed) {
        this.owner = owner;
        this.damage = damage;
        this.speed = speed;
        this.direction = direction.nor();
        final BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyType.DynamicBody;
        body = owner.getBody().getWorld().createBody(bodyDef);
        body.setUserData(this);
        body.setGravityScale(0);
        final CircleShape circle = new CircleShape();
        circle.setRadius(size);
        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        body.createFixture(fixtureDef);
        circle.dispose();
    }

    @Override
    public final void update() {
        body.setLinearVelocity(direction.cpy().scl(speed));
    }

    @Override
    public final Body getBody() {
        return body;
    }

    @Override
    public final float getDamage() {
        return damage;
    }

    @Override
    public final Vector2 getDirection() {
        return direction;
    }

    @Override
    public final boolean isQueuedForDestruction() {
        return queuedForDeletion;
    }

    @Override
    public final void queueForDestruction() {
        queuedForDeletion = true;
    }

    @Override
    public final Actor getOwner() {
        return owner;
    }

    @Override
    public final float getSpeed() {
        return speed;
    }

	/**
	 * HashCode method.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + Float.floatToIntBits(damage);
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		final int magic1 = 1231;
		final int magic2 = 1237;
		result = prime * result + (queuedForDeletion ? magic1  : magic2);
		result = prime * result + Float.floatToIntBits(speed);
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
		final BulletImpl other = (BulletImpl) obj;
		if (body != null) {
			if (!body.equals(other.body)) {
				return false;
			}
		} else {
			if (other.body != null) {
				return false;
			}
		}
		if (Float.floatToIntBits(damage) != Float.floatToIntBits(other.damage)) {
			return false;
		}
		if (direction == null) {
			if (other.direction != null) {
				return false;
			}
		} else if (!direction.equals(other.direction)) {
			return false;
		}
		if (owner == null) {
			if (other.owner != null) {
				return false;
			}
		} else if (!owner.equals(other.owner)) {
			return false;
		}
		if (queuedForDeletion != other.queuedForDeletion) {
			return false;
		}
		return Float.floatToIntBits(speed) == Float.floatToIntBits(other.speed);
	}

	/**
	 * ToString method. 
	 */
	@Override
	public String toString() {
		return "BulletImpl [body=" + body + ", owner=" + owner + ", damage=" + damage + ", speed=" + speed
				+ ", direction=" + direction + ", queuedForDeletion=" + queuedForDeletion + "]";
	}

}
