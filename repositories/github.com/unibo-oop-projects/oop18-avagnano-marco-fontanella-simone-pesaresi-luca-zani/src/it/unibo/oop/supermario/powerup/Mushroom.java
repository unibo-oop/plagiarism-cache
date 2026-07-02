package it.unibo.oop.supermario.powerup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import it.unibo.oop.supermario.character.Mario;
import it.unibo.oop.supermario.character.RigidBody;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.Hud;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * Class defines mushroom object.
 */
public class Mushroom extends RigidBody implements Item {
    private static final int MASHROOM_POINTS = 1000;
    private static final float SPEED = 0.7f;
    private final Hud hud;
    private final Vector2 velocity;

    /**
     * Mushroom constructor.
     * 
     * @param playScreen playscreen object
     * @param x          x object's coordinate
     * @param y          y object's
     */
    public Mushroom(final PlayScreen playScreen, final float x, final float y) {
        super(playScreen, x, y);
        velocity = new Vector2(SPEED, 0);
        hud = playScreen.getHud();
    }

    @Override
    public final void onCollide(final Mario mario) {
        queueDestroy();
        mario.grow();
        hud.addScore(MASHROOM_POINTS);
    }

    @Override
    protected final void defBody() {
        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getPosition().x, getPosition().y);

        b2body = getWorld().createBody(bodyDef);
        b2body.setActive(false);

        final FixtureDef fdef = new FixtureDef();
        final CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS);
        fdef.filter.categoryBits = GameManager.ITEM_BIT;
        fdef.filter.maskBits = GameManager.MARIO_BIT | GameManager.PIPE_BIT | GameManager.GROUND_BIT
                | GameManager.COIN_BIT | GameManager.BRICK_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    /**
     * Reverse the the directions.
     * 
     * @param x the horizontal direction.
     * @param y the vertical direction.
     */
    public void reverseVelocity(final boolean x, final boolean y) {
        if (x) {
            velocity.x = -velocity.x;
        }
        if (y) {
            velocity.y = -velocity.y;
        }
    }

    /**
     * Update mushroom's velocity.
     * 
     * @param dt delta time
     */
    public void update(final float dt) {
        velocity.y = b2body.getLinearVelocity().y;
        b2body.setLinearVelocity(velocity);
    }
}
