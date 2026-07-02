package it.unibo.ninjafrog.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.PlayScreen;

/**
 * NonInteractiveObject class definition. Represents an object which has no
 * reaction when you hit it.
 */
public class NonInteractiveObject {
    private static final int HALF = 2;
    private final PlayScreen screen;
    private final Rectangle object;
    private final Short bit;

    /**
     * Public constructor of a NonInteractiveObject object.
     * 
     * @param screen The {@link it.unibo.ninjafrog.screens.PlayScreen PlayScreen}
     *               which contains the game world.
     * @param object The MapObject object which is going to be defined.
     * @param bit    The short to be set as category bit of the object.
     */
    public NonInteractiveObject(final PlayScreen screen, final MapObject object, final Short bit) {
        this.screen = screen;
        this.object = ((RectangleMapObject) object).getRectangle();
        this.bit = bit;
        this.createBody();
    }

    private void createBody() {
        final BodyDef bodydefinition = new BodyDef();
        final FixtureDef fixdefinition = new FixtureDef();
        final PolygonShape shape = new PolygonShape();
        bodydefinition.type = BodyDef.BodyType.StaticBody;
        bodydefinition.position.set(scale(object.getX() + halfOf(object.getWidth())),
                scale(object.getY() + halfOf(object.getHeight())));
        final Body body = this.screen.getWorld().createBody(bodydefinition);
        shape.setAsBox(scale(halfOf(object.getWidth())), scale(halfOf(object.getHeight())));
        fixdefinition.shape = shape;
        fixdefinition.filter.categoryBits = this.bit;
        body.createFixture(fixdefinition);
    }

    private float scale(final float value) {
        return value / GameConst.PPM;
    }

    private float halfOf(final float value) {
        return value / HALF;
    }

}
