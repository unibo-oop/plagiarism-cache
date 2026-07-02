package it.unibo.oop.supermario.enemies;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

import it.unibo.oop.supermario.character.Mario;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.Hud;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * This class implements model of Koopa character.
 */
public class KoopaImpl extends Enemy implements Koopa {
    /** Manages Goomba' sounds. */
    private final AssetManager assetManager;

    /** value for dx direction. */
    public static final int KICK_LEFT = -2;

    /** value for sx direction. */
    public static final int KICK_RIGHT = 2;

    /** Keep in memory the score. */
    private final Hud hud;

    /** Keeps the previous state of the character. */
    private State previousState;

    /** Keeps the current state of the character. */
    private State currentState;

    /** possible states assumed by Koopa. */
    public enum State { WALKING, MOVING_SHELL, STANDING_SHELL, DYING, STOPPED }

    /**
     * Constructor for Koopa.
     *
     * @param screen the Playscreen of the game
     * @param x the x-axis
     * @param y the y-axis
     */
    public KoopaImpl(final PlayScreen screen, final float x, final float y) {
        super(screen, x, y);
        this.hud = getPS().getHud();
        b2body.setActive(false);
        this.currentState = State.WALKING; 
        this.previousState = State.WALKING; 
        assetManager = GameManager.instance.getAssetManager();
    }

    @Override
    public final void defBody() {
        createBody(Enemies.KOOPA);
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
            b2body.setLinearVelocity(new Vector2(0, b2body.getLinearVelocity().y));
            b2body.applyLinearImpulse(new Vector2(0.0f, b2body.getMass() * (2f - b2body.getLinearVelocity().y)),
                    b2body.getWorldCenter(), true);
            assetManager.get("stomp.wav", Sound.class).play();
        } else if (!isDestroyed()) {
            b2body.setLinearVelocity(getVelocity());
        }
    }

    /**
     * Round the input to decimal number.
     * 
     * @param n the float input number
     * @return rounded number to int.
     */
    private int roundToDecimal(final float n) {
        return Math.round(n * 100) / 10;
    }

    @Override
    public final void hitOnHead(final Mario mario) {
        if (currentState == State.STANDING_SHELL) {
            if (roundToDecimal(mario.getBody().getPosition().x) > roundToDecimal(b2body.getPosition().x)) {
                setVelocity(KICK_LEFT);
                currentState = State.MOVING_SHELL;
            } else if (roundToDecimal(mario.getBody().getPosition().x) < roundToDecimal(b2body.getPosition().x)) {
                setVelocity(KICK_RIGHT);
                currentState = State.MOVING_SHELL;
            }
            assetManager.get("stomp.wav", Sound.class).play();
        } else {
            currentState = State.STANDING_SHELL;
            setVelocity(0);
        }
    }

    @Override
    public final void hitByEnemy(final Enemy enemy) {
        if ((enemy instanceof KoopaImpl && ((KoopaImpl) enemy).currentState == KoopaImpl.State.MOVING_SHELL)
                || (enemy instanceof KoopaImpl && ((KoopaImpl) enemy).currentState == KoopaImpl.State.MOVING_SHELL 
                && currentState == State.MOVING_SHELL)) {
            hud.addScore(KILLED_SCORE);
            queueDestroy();
            currentState = State.DYING;
        } else if (currentState != State.MOVING_SHELL) {
            reverseVelocity(true, false);
        }
    }

    @Override
    public final void hitByFire() {
        queueDestroy();
        hud.addScore(FIRED_SCORE);
        currentState = State.DYING;
    }

    @Override
    public final void kick(final int direction) {
        setVelocity(direction);
        currentState = State.MOVING_SHELL;
    }

    @Override
    public final State getCurrentState() {
        return this.currentState;
    }

    @Override
    public final State getPreviousState() {
        return this.previousState;
    }

    @Override
    public final void setCurrentState(final State currentState) {
        this.currentState = currentState;
    }

    @Override
    public final void setPreviousState(final State previousState) {
        this.previousState = previousState;
    }
}
