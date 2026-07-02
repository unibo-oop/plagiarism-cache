package it.unibo.oop.supermario.character;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import it.unibo.oop.supermario.enemies.Enemy;
import it.unibo.oop.supermario.enemies.KoopaImpl;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.Hud;
import it.unibo.oop.supermario.screens.PlayScreen;
import it.unibo.oop.supermario.utils.State;

/**
 * This class implements Mario interface.
 */
public class MarioImpl extends RigidBody implements Mario {

    private State currentState;
    private final State previousState;

    private boolean die;
    private boolean shrink;
    private boolean growUp;
    private boolean crouch;
    private boolean fire;

    private boolean firingMario;
    private boolean growningUp;
    private boolean getDead;
    private boolean runningRight;
    private boolean firing;

    private static final float FIRE_INTERVAL = 0.3f;
    private static final float FIRE_TIME = 0.1f;
    private final AssetManager assetManager;
    private final Hud hud;
    private float fireTimer;

    /**
     * Constructor for Mario.
     *
     * @param screen the Playscreen of the game
     * @param x the x-axis
     * @param y the y-axis
     */
    public MarioImpl(final PlayScreen screen, final float x, final float y) {
        super(screen, x, y);
        die = false;
        shrink = false;
        growUp = false;
        crouch = false;
        growningUp = false;
        firingMario = false;
        fire = false;
        firing = false;
        runningRight = true;
        currentState = State.STANDING;
        previousState = State.STANDING;
        assetManager = GameManager.instance.getAssetManager();
        hud = screen.getHud();
        fireTimer = 0f;
    }

    @Override
    public final Body getBody() {
        return this.b2body;
    }
    @Override
    protected final void defBody() {
        final BodyDef def = new BodyDef();
        def.position.set(32 / GameManager.PPM, 32 / GameManager.PPM);
        def.type = BodyDef.BodyType.DynamicBody;
        b2body = getWorld().createBody(def);
        final FixtureDef fdef = new FixtureDef();
        final CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS);

        fdef.shape = shape;
        fdef.filter.categoryBits = GameManager.MARIO_BIT;
        fdef.filter.maskBits = GameManager.BRICK_BIT 
                | GameManager.COIN_BIT 
                | GameManager.ENEMY_BIT 
                | GameManager.ENEMY_HEAD_BIT 
                | GameManager.GROUND_BIT 
                | GameManager.PIPE_BIT 
                | GameManager.ITEM_BIT
                | GameManager.FLAG_BIT;

        b2body.createFixture(fdef).setUserData(this);

        // Mario's feet
        final EdgeShape edgeShape = new EdgeShape();
        fdef.filter.categoryBits = GameManager.FEET_BIT;
        edgeShape.set(new Vector2(-RADIUS, -RADIUS), new Vector2(RADIUS, -RADIUS));
        fdef.shape = edgeShape;
        b2body.createFixture(fdef).setUserData(this);

        // Mario's head
        edgeShape.set(new Vector2(-RADIUS / 6, RADIUS), new Vector2(RADIUS / 6, RADIUS));
        fdef.shape = edgeShape;
        fdef.filter.categoryBits = GameManager.MARIO_HEAD_BIT;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);
    }

    private void defSmallMario() {
        //create fixture for small Mario
        final Vector2 currentPosition = b2body.getPosition();
        final Vector2 currentVelocity = b2body.getLinearVelocity();
        getWorld().destroyBody(b2body);

        final BodyDef def = new BodyDef();
        def.position.set(currentPosition);
        def.type = BodyDef.BodyType.DynamicBody;
        def.linearVelocity.set(currentVelocity);
        b2body = getWorld().createBody(def);

        final FixtureDef fdef = new FixtureDef();
        final CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS);

        fdef.shape = shape;
        fdef.filter.categoryBits = GameManager.MARIO_BIT;
        fdef.filter.maskBits = GameManager.BRICK_BIT | GameManager.COIN_BIT | GameManager.ENEMY_BIT 
                | GameManager.ENEMY_HEAD_BIT | GameManager.GROUND_BIT | GameManager.PIPE_BIT 
                | GameManager.ITEM_BIT | GameManager.POWER_UP | GameManager.FLAG_BIT;

        b2body.createFixture(fdef).setUserData(this);

        // Mario's feet
        final EdgeShape edgeShape = new EdgeShape();
        fdef.filter.categoryBits = GameManager.FEET_BIT;
        edgeShape.set(new Vector2(-RADIUS, -RADIUS), new Vector2(RADIUS, -RADIUS));
        fdef.shape = edgeShape;
        b2body.createFixture(fdef).setUserData(this);

        // Mario's head
        edgeShape.set(new Vector2(-RADIUS / 6, RADIUS), new Vector2(RADIUS / 6, RADIUS));
        fdef.shape = edgeShape;
        fdef.filter.categoryBits = GameManager.MARIO_HEAD_BIT;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);
    }

    private void defBigMario() {
        //create fixture for big Mario
        final Vector2 currentPosition = b2body.getPosition();
        final Vector2 currentVelocity = b2body.getLinearVelocity();
        getWorld().destroyBody(b2body);

        final BodyDef def = new BodyDef();
        def.position.set(currentPosition);
        def.type = BodyDef.BodyType.DynamicBody;
        def.linearVelocity.set(currentVelocity);
        b2body = getWorld().createBody(def);

        //Mario's body
        final FixtureDef fdef = new FixtureDef();
        final CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS);
        shape.setPosition(new Vector2(0, 0));
        fdef.shape = shape;
        fdef.filter.categoryBits = GameManager.MARIO_BIT;
        fdef.filter.maskBits = GameManager.BRICK_BIT | GameManager.COIN_BIT | GameManager.ENEMY_BIT 
                | GameManager.ENEMY_HEAD_BIT | GameManager.GROUND_BIT | GameManager.PIPE_BIT 
                | GameManager.ITEM_BIT | GameManager.POWER_UP | GameManager.FLAG_BIT;
        b2body.createFixture(fdef).setUserData(this);

        shape.setPosition(new Vector2(0, RADIUS * 2));
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        // Mario's feet
        final EdgeShape edgeShape = new EdgeShape();
        fdef.filter.categoryBits = GameManager.FEET_BIT;
        edgeShape.set(new Vector2(-RADIUS , -RADIUS), new Vector2(RADIUS, -RADIUS));
        fdef.shape = edgeShape;
        b2body.createFixture(fdef).setUserData(this);

        //Mario's head
        final EdgeShape head = new EdgeShape();
        head.set(new Vector2(-RADIUS / 6, RADIUS * 3), new Vector2(RADIUS / 6, RADIUS * 3));
        fdef.filter.categoryBits = GameManager.MARIO_HEAD_BIT;
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);

    }

    @Override
    public final void onFire() {
        assetManager.get("audio/mario/powerup.wav", Sound.class).play();
        fire = true;
    }

    @Override
    public final void jump() {
        //we use a pulse that immediately changes position 
        //1 parameter : x,y change of coordinates in the world
        //2 parameter : point of application in the object
        //3 parameter : boolean wake (When Box2D determines that a body has come to rest, the body enters a sleep state which has very little CPU overhead. If a body is awake and collides with a sleeping body, then the sleeping body wakes up. Bodies will also wake up if a joint or contact attached to them is destroyed.)
        if (this.currentState != State.JUMPING && this.currentState != State.CROUCHING && this.currentState != State.FALLING && this.currentState != State.GROWING && this.currentState != State.SHRINKING) {
            assetManager.get("audio/mario/jump_small.wav", Sound.class).play();
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
        }
    }

    @Override
    public final State getState() {
        return this.currentState;
    }

    private void setState() {
        if (die || hud.getTime() <= 0) {
            if (!getDead) {
                GameManager.music.dispose();
                assetManager.get("audio/mario/mariodie.wav", Sound.class).play();
                getDead = true;
                // do not collide with anything anymore
                for (final Fixture fixture : b2body.getFixtureList()) {
                    final Filter filter = fixture.getFilterData();
                    filter.maskBits = GameManager.NOTHING_BIT;
                    fixture.setFilterData(filter);
                }
                currentState = State.DYING;
                b2body.setLinearVelocity(new Vector2(0, b2body.getLinearVelocity().y));
                if (b2body.getPosition().y < 0) {
                    b2body.applyLinearImpulse(new Vector2(0.0f, b2body.getMass() * (8f - b2body.getLinearVelocity().y)),
                            b2body.getWorldCenter(), true);
                } else {
                    b2body.applyLinearImpulse(new Vector2(0.0f, b2body.getMass() * (2f - b2body.getLinearVelocity().y)),
                            b2body.getWorldCenter(), true);
                }
            }
        } else if (shrink) {
            currentState = State.SHRINKING;
            growningUp = false;
            firingMario = false;
            // temporarily not collide with enemies
            for (final Fixture fixture : b2body.getFixtureList()) {
                final Filter filter = fixture.getFilterData();
                filter.maskBits = GameManager.GROUND_BIT | GameManager.ITEM_BIT | GameManager.PIPE_BIT;
                fixture.setFilterData(filter);
            }
        } else if (growUp) { 
            growningUp = true;
            defBigMario();
            this.currentState = State.GROWING;
        } else if (fire) {
            firingMario = true;
            growningUp = true;
            fire = false;
            this.currentState = State.FIRE;
        } else if (crouch) {
            this.currentState = State.CROUCHING;
        } else if (b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING) && !getDead) {
            this.currentState = State.JUMPING;
        } else if (b2body.getLinearVelocity().y < 0 && !getDead) {
            //if negative in Y-Axis mario is falling
            this.currentState = State.FALLING;
        } else if (b2body.getLinearVelocity().x != 0) {
            //if mario is positive or negative in the X axis he is running
            this.currentState = State.RUNNING;
        } else {
            //if none of these return then he must be standing
            this.currentState = State.STANDING;
        }
    }

    @Override
    public final void shrinking() {
        shrink = false;
        defSmallMario();
    }

    @Override
    public final void update(final float dt) {
        fireTimer += dt;
        //limit Mario movement
        if (b2body.getPosition().x <= -0.01f) {
            b2body.setTransform(b2body.getPosition().x + 0.01f, b2body.getPosition().y, 0);
            b2body.setLinearVelocity(new Vector2(0, 0));
        } else if (b2body.getPosition().x > 37f) {
            b2body.setTransform(b2body.getPosition().x - FIRE_TIME, b2body.getPosition().y, 0);
            b2body.setLinearVelocity(0, b2body.getLinearVelocity().y);
        }
        if (fireTimer > FIRE_TIME) {
            firing = false;
        }
        if (b2body.getPosition().y< -2.0f || hud.getTime() <= 0){
            die = true;
        }
        setState();
    }

    @Override
    public final void grow() {
        assetManager.get("audio/mario/powerup.wav", Sound.class).play();
        growUp = true;
    }

    @Override
    public final void setGrowUp(final boolean growUp) {
        this.growUp = growUp;
    }

    @Override
    public final boolean isGrownUp() {
        return growningUp;
    }

    @Override
    public final boolean isFireMario() {
        return firingMario;
    }

    @Override
    public final  boolean isDead() {
        return getDead;
    }

    @Override
    public final void getCrouch() {
        if (!crouch) {
            crouch = growningUp;
            if (crouch) {
                defSmallMario();
            }
        }
    }

    @Override
    public final void standUp() {
        if (crouch) {
            defBigMario();
        }
        crouch = false;
    }

    @Override
    public final boolean isRunningRight() {
        return this.runningRight;
    }

    @Override
    public final void move(final float direction) {
        if (direction > 0) {
            runningRight  = true;
            if (b2body.getLinearVelocity().x <= 2 && !crouch) {
                b2body.applyLinearImpulse(new Vector2(direction, 0), b2body.getWorldCenter(), true);
            }
        } else {
            runningRight  = false;
            if (b2body.getLinearVelocity().x >= -2 && !crouch) {
                b2body.applyLinearImpulse(new Vector2(direction, 0), b2body.getWorldCenter(), true);
            }
        }
    }

    @Override
    public final void shoot() {
        if (firingMario && fireTimer > FIRE_INTERVAL && currentState != State.CROUCHING) {
            final float x = isRunningRight() ? FIRE_TIME : -FIRE_TIME;
            final float y = FIRE_TIME;
            firing = true;
            fireTimer = 0;
            assetManager.get("audio/mario/fireball.ogg", Sound.class).play();
            getPS().addFireball(b2body.getPosition().x + x, b2body.getPosition().y + y, runningRight);
        }
    }

    @Override
    public final void hit(final Enemy enemy) {
        if (enemy instanceof KoopaImpl && ((KoopaImpl) enemy).getCurrentState() == KoopaImpl.State.STANDING_SHELL) {
            ((KoopaImpl) enemy).kick(enemy.b2body.getPosition().x > b2body.getPosition().x ? KoopaImpl.KICK_RIGHT : KoopaImpl.KICK_LEFT);
        } else {
            // temporarily invincible when shrinking
            if (shrink) {
                return;
            }
            if (!growningUp) {
                die = true;
            } else {
                assetManager.get("audio/mario/powerdown.wav", Sound.class).play();
                shrink = true;
            }
        }
    }

    @Override
    public final Vector2 getPosition() {
        return this.b2body.getPosition();
    }

    @Override
    public final boolean isFiring() {
        return this.firing;
    }
}

