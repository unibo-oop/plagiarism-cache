package it.unibo.ninjafrog.fruits;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.PlayScreen;

/**
 * Definition of a {@link it.unibo.ninjafrog.fruits.FruitPowerUp FruitPowerUp}
 * implementation.
 */
public final class FruitPowerUpImpl extends Sprite implements FruitPowerUp {
    private static final float FRUIT_RADIUS = 6 / GameConst.PPM;
    private static final float BOUNDS_WIDTH = 16 / GameConst.PPM;
    private static final float BOUNDS_HEIGHT = 12 / GameConst.PPM;
    private static final float SPEED_X = 1f;
    private static final float SPEED_Y = -2.5f;
    private static final int MELON_SCORE = 150;
    private static final int ORANGE_SCORE = 100;
    private static final int CHERRY_SCORE = 200;
    private static final int MELON_REGION_X = 486;
    private static final int CHERRY_REGION_X = 455;
    private static final int ORANGE_REGION_X = 520;
    private static final int FRUIT_REGION_Y = 9;
    private static final int FRUIT_WIDTH = 19;
    private static final int FRUIT_HEIGHT = 16;
    private final PlayScreen screen;
    private final World world;
    private final Body body;
    private boolean toDestroy;
    private boolean destroyed;
    private Vector2 velocity;
    private FruitType type;
    private final BodyDef fruitBody;
    private final TextureRegion region;

    /**
     * Public constructor of a FruitPowerUpImpl object.
     * 
     * @param screen PlayScreen of the game.
     * @param x      float position.
     * @param y      float position.
     * @param type   FruitType of fruit.
     */
    public FruitPowerUpImpl(final PlayScreen screen, final float x, final float y, final FruitType type) {
        this.screen = screen;
        this.world = screen.getWorld();
        this.type = type;
        toDestroy = false;
        destroyed = false;
        region = new TextureRegion(screen.getAtlas().findRegion("ninjaAndEnemies"));
        setPosition(x, y);
        setBounds(getX(), getY(), BOUNDS_WIDTH, BOUNDS_HEIGHT);
        fruitBody = new BodyDef();
        fruitBody.position.set(getX(), getY());
        defineItem(this.type);
        body = world.createBody(fruitBody);
        final FixtureDef fruitFixture = new FixtureDef();
        final CircleShape fruitShape = new CircleShape();
        fruitFixture.shape = fruitShape;
        fruitShape.setRadius(FRUIT_RADIUS);
        maskBits(fruitFixture);
        body.createFixture(fruitFixture).setUserData(this);
    }

    @Override
    public void collide() {
        screen.addScore(this);
        destroy();
        switch (type) {
        case MELON:
            screen.setDoubleJumpAbility(true);
            break;
        case ORANGE:
            break;
        case CHERRY:
            screen.addLife();
            break;
        default:
            throw new IllegalStateException("Illegal FruitType state");
        }
    }

    @Override
    public void draw(final Batch batch) {
        if (!destroyed) {
            super.draw(batch);
        }
    }

    @Override
    public void update(final float dt) {
        if (toDestroy && !destroyed) {
            world.destroyBody(body);
            destroyed = true;
        } else if (!destroyed) {
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
            body.setLinearVelocity(velocity);
        }
    }

    @Override
    public void reverseVelocity() {
        velocity.x = velocity.x * (-1);
    }

    private void defineItem(final FruitType type) {
        switch (type) {
        case MELON:
            velocity = new Vector2(SPEED_X, SPEED_Y);
            setRegion(region, MELON_REGION_X, FRUIT_REGION_Y, FRUIT_WIDTH, FRUIT_HEIGHT);
            fruitBody.type = BodyDef.BodyType.DynamicBody;
            break;
        case ORANGE:
            velocity = new Vector2(0, SPEED_Y);
            setRegion(region, ORANGE_REGION_X, FRUIT_REGION_Y, FRUIT_WIDTH, FRUIT_HEIGHT);
            fruitBody.type = BodyDef.BodyType.StaticBody;
            break;
        case CHERRY:
            velocity = new Vector2(SPEED_X, SPEED_Y);
            setRegion(region, CHERRY_REGION_X, FRUIT_REGION_Y, FRUIT_WIDTH, FRUIT_HEIGHT);
            fruitBody.type = BodyDef.BodyType.DynamicBody;
            break;
        default:
            break;
        }
    }

    private void maskBits(final FixtureDef fruitFixture) {
        fruitFixture.filter.categoryBits = GameConst.FRUIT;
        fruitFixture.filter.maskBits = GameConst.NINJA | GameConst.GROUND | GameConst.GROUND_OBJECT | GameConst.BRICK
                | GameConst.FRUITBOX;
    }

    private void destroy() {
        toDestroy = true;
    }

    @Override
    public int getScore() {
        int score = 0;
        switch (type) {
        case MELON:
            score = MELON_SCORE;
            break;
        case ORANGE:
            score = ORANGE_SCORE;
            break;
        case CHERRY:
            score = CHERRY_SCORE;
            break;
        default:
            throw new IllegalStateException("Illegal FruitType state");
        }
        return score;
    }

}
