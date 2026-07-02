package it.unibo.oop.supermario.powerup;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import it.unibo.oop.supermario.character.Mario;
import it.unibo.oop.supermario.character.RigidBody;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.Hud;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * Class defines flower object.
 */
public class Flower extends RigidBody implements Item {
    private static final int FLOWER_POINTS = 2000;
    private final Hud hud;
    /**
     * Flower constructor.
     * 
     * @param playScreen playscreen object
     * @param x          x object's coordinate
     * @param y          y object's
     */
    public Flower(final PlayScreen playScreen, final float x, final float y) {
        super(playScreen, x, y);
        hud = playScreen.getHud();
    }

    @Override
    public final void onCollide(final Mario mario) {
        queueDestroy();
        if (!mario.isGrownUp()) {
            mario.grow();
        }
        mario.onFire();
        hud.addScore(FLOWER_POINTS);
    }

    @Override
    protected final void defBody() {
        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
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
}
