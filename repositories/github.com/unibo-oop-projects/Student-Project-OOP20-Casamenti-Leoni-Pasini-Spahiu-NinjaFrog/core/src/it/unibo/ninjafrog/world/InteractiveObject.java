package it.unibo.ninjafrog.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.PlayScreen;

/**
 * InteractiveObject class definition. Represents an object that does something
 * when you hit it. Collision effects implemented in class extensions.
 */
public class InteractiveObject {
    /**
     * Name of the Asset used inside the TiledMap.
     */
    protected static final String ASSET_NAME = "NinjaFrogAsset";
    /**
     * Value to be added to the current id to get the next tile.
     */
    protected static final int NEXT_TILE = 1;
    /**
     * Value which indicates that the value of the cell has not yet been calculated.
     */
    protected static final int CELL_NOT_SET = -1;
    /**
     * Dimension of a box square in the map.
     */
    protected static final int WORLD_OBJ_DIM = 16;
    private static final int GRAPHICS_LAYER = 1;
    private static final int HALF = 2;
    private final World world;
    private final TiledMap map;
    private final Rectangle rectangle;
    private Body body;
    private Fixture fixture;
    private final MapObject object;
    private final PlayScreen screen;

    /**
     * Public constructor of a InteractiveObject.
     * 
     * @param screen The {@link it.unibo.ninjafrog.screens.PlayScreen PlayScreen}
     *               which contains the game world.
     * @param object The MapObject object which is going to be defined.
     */
    public InteractiveObject(final PlayScreen screen, final MapObject object) {
        if (object == null) {
            throw new IllegalStateException("Object can't be null.");
        }
        if (screen == null) {
            throw new IllegalStateException("Screen can't be null.");
        }
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.object = object;
        this.rectangle = ((RectangleMapObject) object).getRectangle();
        this.createBody();
    }

    private void createBody() {
        final BodyDef bodydef = new BodyDef();
        final FixtureDef fixdef = new FixtureDef();
        final PolygonShape shape = new PolygonShape();
        bodydef.type = BodyDef.BodyType.StaticBody;
        bodydef.position.set(scale(this.rectangle.getX() + halfOf(this.rectangle.getWidth())),
                scale(this.rectangle.getY() + halfOf(this.rectangle.getHeight())));
        this.body = this.world.createBody(bodydef);
        shape.setAsBox(scale(halfOf(this.rectangle.getWidth())), scale(halfOf(this.rectangle.getHeight())));
        fixdef.shape = shape;
        this.fixture = this.body.createFixture(fixdef);
    }

    /**
     * Utility method which scales the input by world PPM value.
     * 
     * @param value to be scaled.
     * @return Input value scaled.
     */
    protected float scale(final float value) {
        return value / GameConst.PPM;
    }

    private float halfOf(final float value) {
        return value / HALF;
    }

    /**
     * Setter of the category filter.
     * 
     * @param bit The short to be set as category bit of the object.
     */
    protected void setCategoryFilter(final short bit) {
        final Filter filter = new Filter();
        filter.categoryBits = bit;
        this.fixture.setFilterData(filter);
    }

    /**
     * Getter of the cell.
     * 
     * @return The cell itself.
     */
    protected TiledMapTileLayer.Cell getCell() {
        final TiledMapTileLayer graphLayer = (TiledMapTileLayer) map.getLayers().get(GRAPHICS_LAYER);
        return graphLayer.getCell(originalSizePos(this.body.getPosition().x),
                originalSizePos(this.body.getPosition().y));
    }

    private int originalSizePos(final float value) {
        return (int) (value * GameConst.PPM / WORLD_OBJ_DIM);
    }

    /**
     * Getter of the body's x position.
     * 
     * @return A float representing the body's x position.
     */
    protected Float getBodyXPos() {
        return this.body.getPosition().x;
    }

    /**
     * Getter of the body's y position.
     * 
     * @return A float representing the body's y position.
     */
    protected Float getBodyYPos() {
        return this.body.getPosition().y;
    }

    /**
     * Getter of the map.
     * 
     * @return the TiledMap object.
     */
    protected TiledMap getMap() {
        return this.map;
    }

    /**
     * Getter of the fixture.
     * 
     * @return the Fixture object.
     */
    protected Fixture getFixture() {
        return this.fixture;
    }

    /**
     * Getter of the object.
     * 
     * @return the MapObject.
     */
    protected MapObject getObject() {
        return this.object;
    }

    /**
     * Getter of the PlayScreen in which the world is created.
     * 
     * @return the {@link it.unibo.ninjafrog.screens.PlayScreen PlayScreen}.
     */
    protected PlayScreen getScreen() {
        return this.screen;
    }

}
