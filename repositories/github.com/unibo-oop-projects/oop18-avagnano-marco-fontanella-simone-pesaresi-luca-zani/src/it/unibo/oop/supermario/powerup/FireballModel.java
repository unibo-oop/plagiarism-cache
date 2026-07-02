package it.unibo.oop.supermario.powerup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import it.unibo.oop.supermario.character.RigidBody;
import it.unibo.oop.supermario.enemies.Enemy;
import it.unibo.oop.supermario.enemies.GoombaImpl;
import it.unibo.oop.supermario.enemies.KoopaImpl;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * This class represents physics and data of fireball.
 */
public class FireballModel extends RigidBody {
    private final boolean movingRight;
    private boolean hit;
    private float stateTime;
    private float prevX;

    /**
     * Constructor of Fireball's Model.
     * 
     * @param playScreen the screen where the fireball is displayed.
     * @param x axys 
     * @param y axys
     * @param movingRight check if direction of Mario is right or left
     */
    public FireballModel(final PlayScreen playScreen, final float x, final float y, final boolean movingRight) {
        super(playScreen, x, y);
        this.movingRight = movingRight;
        prevX = b2body.getPosition().x;
        stateTime = 0;
        hit = false;
    }

    @Override
    protected final void defBody() {
        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getPosition().x, getPosition().y);

        b2body = getWorld().createBody(bodyDef);

        final CircleShape shape = new CircleShape();
        shape.setRadius(4 / GameManager.PPM);

        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GameManager.POWER_UP;
        fixtureDef.restitution = 0.1f;
        b2body.createFixture(fixtureDef).setUserData(this);

        final EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(new Vector2(-4 / GameManager.PPM, -4 / GameManager.PPM), new Vector2(4 / GameManager.PPM, -4 / GameManager.PPM));

        fixtureDef.shape = edgeShape;
        b2body.createFixture(fixtureDef).setUserData(this);

        shape.dispose();
        edgeShape.dispose();
    }

    /**
     * Update Fireball's model every frame.
     *
     * @param delta delta time of the frame
     */
    public final void update(final float delta) {
        if (isDestroyed()) {
            return;
        }
        if (stateTime > 0.01f && isToDestroy()) {
            destroyBody();
            b2body = null;
            return;
        }

        if ((movingRight && prevX > b2body.getPosition().x) || (!movingRight && prevX < b2body.getPosition().x)) {
            hit = true;
        }

        stateTime += delta;

        if (!hit) {
            final float speed = 2.8f;
            if (movingRight) {
                b2body.applyLinearImpulse(new Vector2((speed - b2body.getLinearVelocity().x) * b2body.getMass(), 0), b2body.getWorldCenter(), true);
            } else {
                b2body.applyLinearImpulse(new Vector2((-speed - b2body.getLinearVelocity().x) * b2body.getMass(), 0), b2body.getWorldCenter(), true);
            }
        } else {
            if (!isToDestroy()) {
                stateTime = 0;
                queueDestroy();
            }
        }

        prevX = b2body.getPosition().x;

        // if the fireball leaves the screen, queueDestroy
        if (Math.abs(b2body.getPosition().x - getPS().getCamera().position.x) > GameManager.V_WIDTH / 2) {
            queueDestroy();
        }
    }

    /**
     * Handle the collision between fireball and other entity.
     * 
     * @param enemy reference if fireball collide with enemy
     */
    public void onCollide(final Enemy enemy) {
        if (enemy instanceof GoombaImpl || enemy instanceof KoopaImpl) {
            enemy.hitByFire();
            hit = true;
        } else {
            b2body.applyLinearImpulse(new Vector2(0, b2body.getMass() * (2.0f - b2body.getLinearVelocity().y)), b2body.getWorldCenter(), true);
        }
    }

    /**
     * Check if fireball hit something.
     * 
     * @return hit State
     */
    public boolean isHit() {
        return this.hit;
    }
}
