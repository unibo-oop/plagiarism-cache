package it.unibo.ninjafrog.frog;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

import it.unibo.ninjafrog.screens.PlayScreen;

public class FrogControllerImpl implements FrogController {
    private static final float VEL = 0.1f;

    private boolean pause;
    private final PlayScreen screen;
    private final FrogModel frog;
    private final FrogView frogView;

    /**
     * public constructor of the frog controller.
     * 
     * @param screen the playscreen.
     */
    public FrogControllerImpl(final PlayScreen screen) {
        this.screen = screen;
        this.frog = new FrogModelImpl(screen);
        this.frogView = new FrogViewImpl(this, screen);
        this.pause = false;
    }

    @Override
    public final Body getBody() {
        return this.frog.getBody();
    }

    @Override
    public final void update(final float dt) {
        frog.update(dt);
        frogView.update(dt);
    }

    @Override
    public final void draw(final Batch batch) {
        frogView.draw(batch);
    }

    @Override
    public final void handleInput() {
        if (!this.pause) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                frog.jump();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                frog.move(VEL);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                frog.move(-VEL);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                this.pause = !this.pause;
            }
        } else {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                this.screen.setMenuScreen();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                this.pause = !this.pause;
            }
        }
    }

    @Override
    public final FrogModel getModel() {
        return this.frog;
    }

    @Override
    public final boolean isDoubleJumpActive() {
        return this.frog.isDoubleJumpActive();
    }

    @Override
    public final FrogState getState() {
        return this.frog.getState();
    }

    @Override
    public final boolean isRunningRight() {
        return this.frogView.isRunningRight();
    }

    @Override
    public final boolean isPaused() {
        return this.pause;
    }

    @Override
    public final FrogState getPrevState() {
        return this.frog.getPrevState();
    }

    @Override
    public final void setPrevState(final FrogState prevState) {
        this.frog.setPrevState(prevState);
    }

    @Override
    public final void setDoubleJumping(final boolean b) {
        this.frog.setDoubleJumping(b);
    }

}
