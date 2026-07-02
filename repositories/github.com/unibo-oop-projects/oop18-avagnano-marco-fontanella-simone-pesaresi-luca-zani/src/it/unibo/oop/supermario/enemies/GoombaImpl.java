package it.unibo.oop.supermario.enemies;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

import it.unibo.oop.supermario.character.Mario;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.Hud;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * This class build the model of Goomba.
 */
public class GoombaImpl extends Enemy implements Goomba {
    /** Manages Goomba' sounds. */
    private final AssetManager assetManager;

    /** Keep in memory the score. */
    private final Hud hud;

    /** Check if Goomba dying smashed. */
    private boolean smashed;

    /**
     * Constructor for Goomba.
     *
     * @param screen the PlayScreen of the game
     * @param x the x-axis
     * @param y the y-axis
     */
    public GoombaImpl(final PlayScreen screen, final float x, final float y) {
        super(screen, x, y);
        this.hud = getPS().getHud();
        b2body.setActive(false);
        assetManager = GameManager.instance.getAssetManager();
    }

    @Override
    public final void defBody() {
        createBody(Enemies.GOOMBA);
    }

    @Override
    public final void update(final float dt) {
        if (b2body.getPosition().x < getPS().getMarioPosition().x + (GameManager.DISTANCE_CAM / GameManager.PPM)) {
            b2body.setActive(true);
        }
        if (getPS().getMarioController().isBlocked()) {
            setVelocity(0);
            stopEnemies();
        }
        if (isToDestroy() && !isDestroyed()) {
            setDestroyed();
            for (final Fixture fixture : b2body.getFixtureList()) {
                final Filter filter = fixture.getFilterData();
                filter.maskBits = GameManager.NOTHING_BIT;
                fixture.setFilterData(filter);
            }
            if (!smashed) {
                b2body.setLinearVelocity(new Vector2(0, b2body.getLinearVelocity().y));
                b2body.applyLinearImpulse(new Vector2(0.0f, b2body.getMass() * (2f - b2body.getLinearVelocity().y)),
                        b2body.getWorldCenter(), true);
            }
            assetManager.get("stomp.wav", Sound.class).play();
        } else if (isDestroyed() && smashed) {
            b2body.setActive(false);
        } else if (!isDestroyed()) {
            b2body.setLinearVelocity(getVelocity());
        }
    }

    @Override
    public final void hitOnHead(final Mario mario) {
        hud.addScore(KILLED_SCORE);
        queueDestroy();
        smashed = true;
    }

    @Override
    public final void hitByEnemy(final Enemy enemy) {
        if (enemy instanceof KoopaImpl && ((KoopaImpl) enemy).getCurrentState() == KoopaImpl.State.MOVING_SHELL) {
            hud.addScore(KILLED_SCORE);
            queueDestroy();
        } else {
            reverseVelocity(true, false);
        }
    }


    @Override
    public final void hitByFire() {
        hud.addScore(FIRED_SCORE);
        queueDestroy();
    }

    /**
     * Get the state where Goomba is smashed.
     * 
     * @return smashed
     */
    public boolean isSmashed() {
        return this.smashed;
    }
}
