package it.unibo.oop.supermario.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * This class implements Mario Controller interface.
 */
public class MarioControllerImpl implements MarioController {
    private final Mario mario;
    private final MarioView marioView;
    private boolean blockMario;
    private static final float VELOCITY = 0.1f;

    /**
     * Constructor for Mario Controller.
     *
     * @param mario     Mario's modelS
     * @param marioView Mario's view
     */
    public MarioControllerImpl(final Mario mario, final MarioView marioView) {
        this.mario = mario;
        this.marioView = marioView;
        this.marioView.setPlayer(this.mario);
        blockMario = false;
    }

    @Override
    public final Body getBody() {
        return this.mario.getBody();
    }

    /**
     * Check if Mario is dead or not.
     * 
     * @return Dead state
     */
    public boolean isDead() {
        return this.mario.isDead() && marioView.getStateTimer() > 3f;
    }

    /**
     * Mario position.
     * 
     * @return Vector2 : x,y coordinate
     */
    public Vector2 getMarioPosition() {
        return this.mario.getPosition();
    }

    private void handleInput() {
        // cheat key
        if (!mario.isDead() && !blockMario) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
                mario.grow();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
                mario.grow();
                mario.onFire();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                mario.jump();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                mario.move(VELOCITY);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                mario.move(-VELOCITY);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                mario.shoot();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                mario.getCrouch();
            } else {
                mario.standUp();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
                mario.shoot();
            }
        }
    }

    @Override
    public final void setBlockMario() {
        blockMario = true;
    }

    /**
     * Mario is stopped at the end of the level.
     * 
     * @return Mario is blocked state
     */
    public boolean isBlocked() {
        return this.blockMario;
    }

    @Override
    public final void update(final float dt) {
        handleInput();
        mario.update(dt);
        marioView.update(dt);
    }

    @Override
    public final void draw(final Batch batch) {
        marioView.draw(batch);
    }
}
