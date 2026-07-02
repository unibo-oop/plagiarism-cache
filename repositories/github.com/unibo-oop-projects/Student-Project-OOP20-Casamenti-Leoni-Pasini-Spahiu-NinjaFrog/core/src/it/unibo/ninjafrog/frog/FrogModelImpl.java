package it.unibo.ninjafrog.frog;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.PlayScreen;

public class FrogModelImpl implements FrogModel {
    private static final float WORLD_TIME_STEP = 1f / 60f;
    private static final float ACC_DEF = 0.25f;
    private static final int X_VELOCITY = 2;
    private static final float VEL_MAX = 2.5f;
    private static final int RADIUS = 7;
    private static final int X_INIT_POS = 240;
    private static final int Y_INIT_POS = 100;
    private static final int HEAD = 2;

    private Integer life = 1;
    private boolean isDoubleJumpActive;
    private boolean isDoubleJumping;
    private final PlayScreen screen;
    private FrogState currentState;
    private FrogState prevState;
    private Body body;
    private final World world;
    private float accumulator;
    private float delta;

    /**
     * public constructor of the frog model.
     * 
     * @param screen the playscreen.
     */
    public FrogModelImpl(final PlayScreen screen) {
        this.screen = screen;
        this.world = screen.getWorld();
        this.prevState = FrogState.STANDING;
        this.currentState = FrogState.STANDING;
        this.isDoubleJumpActive = false;
        this.isDoubleJumping = false;
        defineFrog();
    }

    @Override
    public final Body getBody() {
        return this.body;
    }

    @Override
    public final Vector2 getPos() {
        return body.getPosition();
    }

    @Override
    public final FrogState getState() {
        if ((body.getLinearVelocity().y > 0 || body.getLinearVelocity().y < 0 && prevState == FrogState.DOUBLEJUMPING)
                && this.isDoubleJumping) {
            this.currentState = FrogState.DOUBLEJUMPING;
            return this.currentState;
        } else if ((body.getLinearVelocity().y > 0 || body.getLinearVelocity().y < 0 && prevState == FrogState.JUMPING)
                && !this.isDoubleJumping) {
            this.currentState = FrogState.JUMPING;
            return this.currentState;
        } else if (body.getLinearVelocity().y < 0) {
            this.currentState = FrogState.FALLING;
            return this.currentState;
        } else if (body.getLinearVelocity().x != 0) {
            this.currentState = FrogState.RUNNING;
            return this.currentState;
        } else {
            this.currentState = FrogState.STANDING;
            return this.currentState;
        }
    }

    @Override
    public final void jump() {
        if (this.currentState == FrogState.DOUBLEJUMPING) {
            body.applyLinearImpulse(new Vector2(0, 0), body.getWorldCenter(), true);

        } else if (this.currentState != FrogState.JUMPING && this.currentState != FrogState.FALLING) {
            body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
        } else if (isDoubleJumpActive() && !isDoubleJumping) {
            isDoubleJumping = true;
            if (body.getLinearVelocity().y <= 0) {
                if (this.currentState != FrogState.FALLING && this.prevState != FrogState.FALLING) {
                    body.applyLinearImpulse(new Vector2(0, -body.getLinearVelocity().y + 4f), body.getWorldCenter(),
                            true);
                }
            } else {
                body.applyLinearImpulse(new Vector2(0, 4f - body.getLinearVelocity().y), body.getWorldCenter(), true);
            }
        }
    }

    @Override
    public final void move(final float direction) {
        this.accumulator += Math.min(this.delta, ACC_DEF);
        if (this.accumulator >= WORLD_TIME_STEP) {
            this.accumulator -= WORLD_TIME_STEP;
            if (direction > 0) {
                if (body.getLinearVelocity().x <= X_VELOCITY) {
                    body.applyLinearImpulse(new Vector2(direction, 0), body.getWorldCenter(), true);
                }
            } else {
                if (body.getLinearVelocity().x >= -X_VELOCITY) {
                    body.applyLinearImpulse(new Vector2(direction, 0), body.getWorldCenter(), true);
                }
            }
        }
    }

    @Override
    public final boolean isDoubleJumpActive() {
        return this.isDoubleJumpActive;
    }

    @Override
    public final void setDoubleJump(final boolean isDoubleJump) {
        this.isDoubleJumpActive = isDoubleJump;
    }

    @Override
    public final void addLife() {
        this.life++;
    }

    @Override
    public final void removeLife() {
        this.life -= 1;
        if (this.life == 0) {
            this.screen.setGameOverScreen();
        }
    }

    @Override
    public final void defineFrog() {
        final BodyDef bdef = new BodyDef();
        bdef.position.set(X_INIT_POS / GameConst.PPM, Y_INIT_POS / GameConst.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        this.body = world.createBody(bdef);

        final FixtureDef fdef = new FixtureDef();
        final CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS / GameConst.PPM);

        fdef.filter.categoryBits = GameConst.NINJA;
        fdef.filter.maskBits = GameConst.GROUND | GameConst.FRUITBOX | GameConst.BRICK | GameConst.RINO
                | GameConst.RINO_HEAD | GameConst.TURTLE | GameConst.TURTLE_HEAD | GameConst.GROUND_OBJECT
                | GameConst.FRUIT | GameConst.FINISH;
        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
        /*
         * define frog head
         */
        final EdgeShape head = new EdgeShape();
        head.set(new Vector2(-HEAD / GameConst.PPM, RADIUS / GameConst.PPM),
                new Vector2(HEAD / GameConst.PPM, RADIUS / GameConst.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        fdef.filter.categoryBits = GameConst.NINJA_HEAD;
        body.createFixture(fdef).setUserData(this);
    }

    @Override
    public final void update(final float dt) {
        this.delta = dt;
        if (this.body.getLinearVelocity().y < -VEL_MAX) {
            body.setLinearVelocity(body.getLinearVelocity().x, -VEL_MAX);
        }
        if (body.getPosition().y < 0) {
            if (this.life > 0) {
                this.screen.removeLife();
                this.world.destroyBody(this.body);
                this.defineFrog();
            } else {
                this.screen.setGameOverScreen();
            }
        }

    }

    @Override
    public final FrogState getPrevState() {
        return this.prevState;
    }

    @Override
    public final void setPrevState(final FrogState prevState) {
        this.prevState = prevState;
    }

    @Override
    public final void setDoubleJumping(final boolean b) {
        this.isDoubleJumping = b;
    }
}
