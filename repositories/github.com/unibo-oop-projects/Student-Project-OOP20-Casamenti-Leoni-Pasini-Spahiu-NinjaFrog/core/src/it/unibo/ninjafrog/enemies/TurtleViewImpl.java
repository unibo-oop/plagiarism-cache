package it.unibo.ninjafrog.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.PlayScreen;

public class TurtleViewImpl extends Sprite implements EnemyView {
    private static final double ANIMATION_DEAD_TIME = 0.5;
    private static final String ASSET = "ninjaAndEnemies";
    private static final int X_COORDINATE_FOR_DEATH_FRAME = 440;
    private static final int FULL_SPIKES_AND_NO_SPIKES_DURATION = 2;
    private static final int SPIKES_IN_AND_SPIKES_OUT_DURATION = 1;
    private static final int NUMBER_OF_THE_LAST_FRAME = 7;
    private static final int NUMBER_OF_FRAMES = 8;
    private static final int X_DISTANCE_FROM_FRAMES = 44;
    private static final int Y_COORDINATE_IN_THE_PNG = 98;
    private static final int HEIGHT_IN_THE_PNG = 27;
    private static final int WIDTH_IN_THE_PNG = 44;
    private static final float FRAME_DURATION = 0.1f;
    private static final int BOUNDS_HEIGHT = 17;
    private static final int HALF = 2;
    private static final int BOUNDS_WIDTH = 25;
    private TurtleState currentState;
    private TurtleState previousState;
    private final EnemyController controller;
    private final PlayScreen screen;
    private final TextureRegion spikes;
    private final TextureRegion noSpikes;
    private final Animation<TextureRegion> spikesInAnimation;
    private final Animation<TextureRegion> spikesOutAnimation;
    private float time;

    /**
     * public constructor of the TurtleView.
     * 
     * @param screen     the playscreen
     * @param x          the X coordinate of the turtle
     * @param y          the Y coordinate of the turtle
     * @param controller the EnemyController
     */

    public TurtleViewImpl(final PlayScreen screen, final float x, final float y, final EnemyControllerImpl controller) {
        this.controller = controller;
        this.screen = screen;
        setPosition(x, y);
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion(ASSET), i * X_DISTANCE_FROM_FRAMES,
                    Y_COORDINATE_IN_THE_PNG, WIDTH_IN_THE_PNG, HEIGHT_IN_THE_PNG));
        }
        spikesInAnimation = new Animation<>(FRAME_DURATION, frames);
        frames.clear();
        frames = new Array<>();
        for (int i = NUMBER_OF_THE_LAST_FRAME; i >= 0; i--) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion(ASSET), i * X_DISTANCE_FROM_FRAMES,
                    Y_COORDINATE_IN_THE_PNG, WIDTH_IN_THE_PNG, HEIGHT_IN_THE_PNG));
        }
        spikesOutAnimation = new Animation<>(FRAME_DURATION, frames);
        spikes = new TextureRegion(screen.getAtlas().findRegion(ASSET),
                NUMBER_OF_THE_LAST_FRAME * X_DISTANCE_FROM_FRAMES, Y_COORDINATE_IN_THE_PNG, WIDTH_IN_THE_PNG,
                HEIGHT_IN_THE_PNG);
        noSpikes = new TextureRegion(screen.getAtlas().findRegion(ASSET), 0, Y_COORDINATE_IN_THE_PNG, WIDTH_IN_THE_PNG,
                HEIGHT_IN_THE_PNG);
        setBounds(getX(), getY(), BOUNDS_WIDTH / GameConst.PPM, BOUNDS_HEIGHT / GameConst.PPM);
        this.currentState = TurtleState.NO_SPIKES;
    }

    @Override
    public final void update(final Body body, final float dt) {
        // TODO Auto-generated method stub
        setPosition(body.getPosition().x - getWidth() / HALF, body.getPosition().y - getHeight() / HALF);
        setRegion(getFrame(dt));
    }

    @Override
    public final void draw(final SpriteBatch batch) {
        if (!controller.isDestroyed(this) || controller.getStateTime(this) < ANIMATION_DEAD_TIME) {
            super.draw(batch);
        }
    }

    private TextureRegion getFrame(final float dt) {
        currentState = getState();
        TextureRegion region;
        switch (currentState) {
        case SPIKES_IN:
            region = this.spikesInAnimation.getKeyFrame(time);
            break;
        case SPIKES_OUT:
            region = this.spikesOutAnimation.getKeyFrame(time);
            break;
        case NO_SPIKES:
            region = this.noSpikes;
            break;
        case SPIKES:
        default:
            region = this.spikes;
            break;
        }
        time = currentState.equals(previousState) ? time + dt : 0;
        previousState = currentState;
        return region;
    }

    private TurtleState getState() {
        if (this.currentState == TurtleState.NO_SPIKES || this.currentState == TurtleState.SPIKES) {
            if (this.time > FULL_SPIKES_AND_NO_SPIKES_DURATION) {
                if (this.currentState == TurtleState.NO_SPIKES) {
                    this.time = 0;
                    return TurtleState.SPIKES_IN;
                } else {
                    this.time = 0;
                    return TurtleState.SPIKES_OUT;
                }
            } else {
                return this.currentState;
            }
        } else {
            if (this.time > SPIKES_IN_AND_SPIKES_OUT_DURATION) {
                if (this.currentState == TurtleState.SPIKES_IN) {
                    this.time = 0;
                    return TurtleState.SPIKES;
                } else {
                    this.time = 0;
                    return TurtleState.NO_SPIKES;
                }
            } else {
                return this.currentState;
            }
        }
    }

    public final void setDeathRegion() {
        setRegion(new TextureRegion(screen.getAtlas().findRegion(ASSET), X_COORDINATE_FOR_DEATH_FRAME,
                Y_COORDINATE_IN_THE_PNG, WIDTH_IN_THE_PNG, HEIGHT_IN_THE_PNG));
    }

    @Override
    public final boolean isKillable() {
        return this.currentState == TurtleState.SPIKES || this.currentState == TurtleState.SPIKES_IN;
    }

}
