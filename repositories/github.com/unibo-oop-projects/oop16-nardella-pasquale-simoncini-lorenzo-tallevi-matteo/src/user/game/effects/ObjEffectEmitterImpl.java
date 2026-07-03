package user.game.effects;

import user.enums.GameSprites;
import user.enums.Objects;
import user.game.ObjViewController;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represents an effect emitter.
 */
public class ObjEffectEmitterImpl extends GameObject implements ObjEffectEmitter {

    private static final int MEDIUM_RANGE = 70;
    private static final int BIG_RANGE = 240;
    private static final int MEDIUM_NUMBER = 10;
    private static final int BIG_NUMBER = 30;
    private static final double MINIMUM_IMAGE_SPEED = 0.4;
    private static final double MAXIMUM_IMAGE_SPEED = 0.6;
    private static final double BIG_SCALE = 6;
    private static final double BIG_LIGHT_SCALE = 3;
    private static final double SHOCK_LITTLE_INIT = 0.3;
    private static final double SHOCK_LITTLE_FIN = 1;
    private static final double SHOCK_BIG_INIT = 0.3;
    private static final double SHOCK_BIG_FIN = 3.1;
    private static final double PIXELLIGHT_ALPHA = 0.5;
    private static final double PIXELLIGHT_ANIMSPEED = 0.3;
    private static final int MEDIUM_MIN_PARTICLES = 6;
    private static final int MEDIUM_MAX_PARTICLES = 12;
    private static final int MEDIUM_SHAKE_VALUE = 17;
    private static final int BIG_SHAKE_VALUE = 80;

    // used by bigExplosionEffect
    private static final double BIG_EXPLOSIONS_PARTICLE_RADIUS = 150;
    private static final int BIG_EXPLOSIONS_DELAY = 17;
    private int explosionsLeft = 3;
    private int explosionCounter = BIG_EXPLOSIONS_DELAY;
    private boolean exploding;

    @Override
    public void create() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        if (exploding) {
            // loop explosions
            explosionCounter--;
            if (explosionCounter <= 0) {
                if (explosionsLeft > 0) {
                    effectBig();
                    explosionCounter = BIG_EXPLOSIONS_DELAY;
                    explosionsLeft--;
                } else {
                    exploding = false;
                    z().instanceDestroy(this);
                }
            }
        }
    }

    @Override
    public void draw() {
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

    @Override
    public void createSmallExplosion() {
        final GameObject explosion = z().instanceCreate(this.getX(), this.getY(), Objects.EFFECT.getValue());
        explosion.setSpriteIndex(GameSprites.SMALL_EXPLOSION.getValue());
        explosion.setImageSpeed(1);
        z().instanceDestroy(this);
    }

    @Override
    public void createMediumExplosion() {

        final int number = MEDIUM_NUMBER;
        z().soundPlay("explosionMedium");
        for (int i = 0; i < number; i++) {
            final double angle = z().random(360);
            final double distance = z().random(MEDIUM_RANGE);
            final GameObject explosion = z().instanceCreate(this.getX() + z().lengthDirX(distance, angle),
                    this.getY() + z().lengthDirY(distance, angle), Objects.EFFECT.getValue());
            explosion.setSpriteIndex(z().choose(GameSprites.MEDIUM_EXPLOSION_0.getValue(), GameSprites.MEDIUM_EXPLOSION_1.getValue()));
            explosion.setImageSpeed(z().randomRange(MINIMUM_IMAGE_SPEED, MAXIMUM_IMAGE_SPEED));
        }

        // by default it is the light gradient
        z().instanceAdd(getX(), getY(), new ObjFadingElement());

        // shockwave
        final ObjShockwave shock = z().instanceAdd(getX(), getY(), new ObjShockwave());
        if (z().instanceExists(shock)) {
            shock.setInitialScale(SHOCK_LITTLE_INIT);
            shock.setFinalScale(SHOCK_LITTLE_FIN);
        }

        // pixel light
        final ObjEffect eff = z().instanceAdd(getX(), getY(), new ObjEffect());
        if (z().instanceExists(eff)) {
            eff.setDepth(10);
            eff.setSpriteIndex(GameSprites.PIXEL_LIGHT.getValue());
            eff.setImageSpeed(PIXELLIGHT_ANIMSPEED);
            eff.setImageAlpha(PIXELLIGHT_ALPHA);
        }

        // shoot some particles
        final int num = (int) z().randomRange(MEDIUM_MIN_PARTICLES, MEDIUM_MAX_PARTICLES);
        for (int i = 0; i < num; i++) {
            z().instanceAdd(getX(), getY(), new ObjParticle());
        }
        final ObjViewController view = (ObjViewController) z().instanceGet(Objects.VIEW_CONTROLLER.getValue());
        if (z().instanceExists(view)) {
            view.setShake(MEDIUM_SHAKE_VALUE);
        }
        // clear me
        z().instanceDestroy(this);
    }

    @Override
    public void createBigExplosion() {
        effectBig();

        // keep exploding
        exploding = true;
    }

    private void effectBig() {
        final int number = BIG_NUMBER;
        z().soundPlay("explosionBoss");
        for (int i = 0; i < number; i++) {
            final double angle = z().random(360);
            final double distance = z().random(BIG_RANGE);
            final GameObject explosion = z().instanceCreate(this.getX() + z().lengthDirX(distance, angle),
                    this.getY() + z().lengthDirY(distance, angle), Objects.EFFECT.getValue());
            explosion.setSpriteIndex(GameSprites.BIG_EXPLOSION.getValue());
            explosion.setImageSpeed(z().randomRange(MINIMUM_IMAGE_SPEED, MAXIMUM_IMAGE_SPEED));
            explosion.setImageXscale(BIG_SCALE);
            explosion.setImageXscale(BIG_SCALE);
        }

        // light
        final GameObject light = z().instanceCreate(getX(), getY(), Objects.FADING_ELEMENT.getValue());
        if (z().instanceExists(light)) {
            light.setImageXscale(BIG_LIGHT_SCALE);
            light.setImageYscale(BIG_LIGHT_SCALE);
        }

        // shockwave
        final ObjShockwave shock = (ObjShockwave) z().instanceCreate(getX(), getY(), Objects.SHOCKWAVE.getValue());
        if (z().instanceExists(shock)) {
            shock.setInitialScale(SHOCK_BIG_INIT);
            shock.setFinalScale(SHOCK_BIG_FIN);
        }

        // pixel light
        final ObjEffect eff = (ObjEffect) z().instanceCreate(getX(), getY(), Objects.EFFECT.getValue());
        if (z().instanceExists(eff)) {
            eff.setDepth(10);
            eff.setSpriteIndex(GameSprites.PIXEL_LIGHT.getValue());
            eff.setImageSpeed(PIXELLIGHT_ANIMSPEED);
            eff.setImageAlpha(PIXELLIGHT_ALPHA);

            eff.setImageXscale(BIG_LIGHT_SCALE * 3);
            eff.setImageYscale(BIG_LIGHT_SCALE * 3);
        }

        // shoot some particles
        final int num = (int) z().randomRange(MEDIUM_MIN_PARTICLES, MEDIUM_MAX_PARTICLES);
        for (int i = 0; i < num; i++) {
            z().instanceAdd(getX() + z().randomRange(-BIG_EXPLOSIONS_PARTICLE_RADIUS, BIG_EXPLOSIONS_PARTICLE_RADIUS),
                    getY() + z().randomRange(-BIG_EXPLOSIONS_PARTICLE_RADIUS, BIG_EXPLOSIONS_PARTICLE_RADIUS),
                    new ObjParticle());
        }

        final ObjViewController view = (ObjViewController) z().instanceGet(Objects.VIEW_CONTROLLER.getValue());
        if (z().instanceExists(view)) {
            view.setShake(BIG_SHAKE_VALUE);
        }
    }
}
