package it.unibo.oop.supermario.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.utils.CharacterAnimation;
import it.unibo.oop.supermario.utils.State;

/**
 * This class implements Mario View interface.
 */
public class MarioViewImpl extends CharacterAnimation implements MarioView {
    private Body b2body;

    private State currentState;
    private State previousState;

    private final TextureRegion standingSmall;
    private final Animation runningSmall;
    private final TextureRegion jumpingSmall;
    private final TextureRegion dying;

    private final TextureRegion standingBig;
    private final Animation runningBig;
    private final TextureRegion jumpingBig;
    private final TextureRegion crouchingBig;

    private final TextureRegion standingFireMario;
    private final Animation runningFireMario;
    private final TextureRegion jumpingFireMario;
    private final TextureRegion crouchingFireMario;

    private final Animation shrinking;
    private final Animation growing;

    private Mario mario;
    private boolean runningRight;

    private final Animation firingAnimation;

    private static final float ANIMATION_DURATION = 0.1f;
    private static final int JUMP_INDEX = 5;
    private static final int CROUCH_INDEX = 6;
    private static final int GROW_INDEX = 15;
    private static final String FIRE = "Fire";
    private static final String SMALL = "_small";
    private static final String BIG = "_big"; 

    /**
     * Constructor for Mario View.
     */
    public MarioViewImpl() {
        final String playerSkin = GameManager.instance.getSkin();
        final Array<TextureRegion> frames = new Array<>();
        //get small Mario animation frames and add them to Animation
        jumpingSmall = new TextureRegion(atlas.findRegion(playerSkin + SMALL), 16 * JUMP_INDEX, 0, 16, 16);
        standingSmall = new TextureRegion(atlas.findRegion(playerSkin + SMALL), 0, 0, 16, 16);
        runningSmall = setAnimation(1, 4, playerSkin + SMALL, 16, 16);
        dying = new TextureRegion(atlas.findRegion(playerSkin + SMALL), 16 * CROUCH_INDEX, 0, 16, 16);

        //get big Mario animation frames and add them to Animation
        jumpingBig = new TextureRegion(atlas.findRegion(playerSkin + BIG), 16 * JUMP_INDEX, 0, 16, 32);
        standingBig = new TextureRegion(atlas.findRegion(playerSkin + BIG), 0, 0, 16, 32);
        crouchingBig = new TextureRegion(atlas.findRegion(playerSkin + BIG), 16 * CROUCH_INDEX, 0, 16, 32);
        runningBig = setAnimation(1, 4, playerSkin + "_big", 16, 32);

        // shrinking animation
        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(atlas.findRegion(playerSkin + BIG), 0, 0, 16, 32));
            frames.add(new TextureRegion(atlas.findRegion(playerSkin + BIG), 16 * GROW_INDEX, 0, 16, 32));
        }
        shrinking = new Animation(ANIMATION_DURATION, frames);
        frames.clear();

        // growing up animation
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(atlas.findRegion(playerSkin + BIG), 16 * GROW_INDEX, 0, 16, 32));
            frames.add(new TextureRegion(atlas.findRegion(playerSkin + BIG), 0, 0, 16, 32));
        }
        growing = new Animation(ANIMATION_DURATION, frames);
        frames.clear();

        //get FireMario animation frames and add them to Animation
        standingFireMario = new TextureRegion(atlas.findRegion(FIRE + playerSkin), 0, 0, 16, 32);
        jumpingFireMario = new TextureRegion(atlas.findRegion(FIRE + playerSkin), 16 * JUMP_INDEX, 0, 16, 32);
        crouchingFireMario = new TextureRegion(atlas.findRegion(FIRE + playerSkin), 16 * CROUCH_INDEX, 0, 16, 32);
        runningFireMario = setAnimation(1, 4, FIRE + playerSkin, 16, 32);

        // firing animation
        firingAnimation = setAnimation(16, 19, FIRE + playerSkin, 16, 32);

        setBounds(getX(), getY(), 16 / GameManager.PPM, 16 / GameManager.PPM);
    }

    private TextureRegion setSprite(final float dt) {
        TextureRegion region = new TextureRegion();
        region = getFrame(dt);
        if (!runningRight && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if (runningRight && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        return region;
    }

    @Override
    public final void update(final float dt) {
        runningRight = mario.isRunningRight();
        //update our sprite to correspond with the position of our Box2D body
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - radius);
        setRegion(setSprite(dt));
    }

    @Override
    public final void draw(final Batch batch) {
        super.draw(batch);
    }

    private TextureRegion getFrame(final float dt) {
        previousState = currentState;
        //get marios current state. like jumping, running, standing...
        currentState = mario.getState();
        stateTimer = previousState == currentState ? stateTimer + dt : 0;
        TextureRegion region = new TextureRegion();
        switch (currentState) {
        case DYING:
            region = dying;
            setSize(16 / GameManager.PPM, 16 / GameManager.PPM);
            break;
        case SHRINKING:
            region = shrinking.getKeyFrame(stateTimer, false);
            if (shrinking.isAnimationFinished(stateTimer)) {
                setBounds(b2body.getPosition().x, b2body.getPosition().y, 16 / GameManager.PPM, 16 / GameManager.PPM);
                mario.shrinking();
            }
            break;
        case CROUCHING:
            if (mario.isFireMario()) {
                region = crouchingFireMario;
            } else if (mario.isGrownUp()) {
                region = crouchingBig;
            }
            break;
        case FIRE:
            region = standingFireMario;
            break;
        case GROWING:
            setBounds(getX(), getY(), 16 / GameManager.PPM, 32 / GameManager.PPM);
            region = growing.getKeyFrame(stateTimer, false);
            if (growing.isAnimationFinished(stateTimer)) {
                mario.setGrowUp(false);
            }
            break;
        case JUMPING:
            if (mario.isFireMario()) {
                region = jumpingFireMario;
            } else {
                region = mario.isGrownUp() ? jumpingBig : jumpingSmall;
            }
            break;
        case RUNNING:
            if (mario.isFireMario()) {
                region = runningFireMario.getKeyFrame(stateTimer, true);
            } else {
                region = mario.isGrownUp() ? runningBig.getKeyFrame(stateTimer, true) : runningSmall.getKeyFrame(stateTimer, true);
            }
            break;
        case FALLING:
        case STANDING:
        default:
            if (mario.isFireMario()) {
                if (mario.isFiring()) {
                    region = firingAnimation.getKeyFrame(0, true);
                } else {
                    region = standingFireMario;
                }
            } else {
                region = mario.isGrownUp() ? standingBig : standingSmall;
            }
            break;
        }
        return region;
    }

    @Override
    public final void setPlayer(final Mario mario) {
        this.mario = mario;
        this.b2body = mario.getBody();
    }

    @Override
    public final float getStateTimer() {
        return this.stateTimer;
    }
}
