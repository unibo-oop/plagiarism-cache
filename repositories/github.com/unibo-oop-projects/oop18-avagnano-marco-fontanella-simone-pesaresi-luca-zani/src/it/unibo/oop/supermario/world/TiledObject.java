package it.unibo.oop.supermario.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import it.unibo.oop.supermario.character.RigidBody;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * Class defines TiledObject.
 */
public class TiledObject extends RigidBody {

    private final TiledMapTileSet tileSet;
    private Fixture fixture;

    /**
     * TiledObjectImpl constructor.
     * 
     * @param playScreen Playscreen object
     * @param x          x object's coordinate
     * @param y          y object's coordinate
     * @param obj        object of tiled map
     */
    public TiledObject(final PlayScreen playScreen, final float x, final float y, final MapObject obj) {
        super(playScreen, x, y, obj);
        final TiledMap map = playScreen.getMap();
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        if (obj.getProperties().containsKey("pipe")) {
            setCategoryFilter(GameManager.PIPE_BIT);
        } else {
            setCategoryFilter(GameManager.GROUND_BIT);
        }
    }

    @Override
    protected final void defBody() {
        final Rectangle rect = ((RectangleMapObject) getObj()).getRectangle();
        final BodyDef bdf = new BodyDef();
        final PolygonShape shape = new PolygonShape();
        final FixtureDef fixDef = new FixtureDef();
        bdf.type = BodyDef.BodyType.StaticBody;
        bdf.position.set((rect.getX() + rect.getWidth() / 2) / GameManager.PPM,
                (rect.getY() + rect.getHeight() / 2) / GameManager.PPM);
        b2body = getPS().getWorld().createBody(bdf);
        shape.setAsBox((rect.getWidth() / 2) / GameManager.PPM, (rect.getHeight() / 2) / GameManager.PPM);
        fixDef.shape = shape;
        fixture = b2body.createFixture(fixDef);
    }

    /**
     * Set bit to fixture.
     * 
     * @param filterBit bit
     */
    public void setCategoryFilter(final int filterBit) {
        final Filter filter = new Filter();
        filter.categoryBits = (short) filterBit;
        fixture.setFilterData(filter);
    }

    /**
     * Get cell of respective TiledObject in the map.
     * 
     * @return cell
     */
    public TiledMapTileLayer.Cell cell() {
        final TiledMapTileLayer layer = (TiledMapTileLayer) getPS().getMap().getLayers().get(1);
        return layer.getCell((int) (b2body.getPosition().x * GameManager.PPM / 16),
                (int) (b2body.getPosition().y * GameManager.PPM / 16));
    }
    /**
     * Fixture getter.
     * 
     * @return fixture
     */
    public Fixture getFixture() {
        return fixture;
    }
    /**
     * TileSet getter.
     * 
     * @return tileSet
     */
    public TiledMapTileSet getTileSet() {
        return tileSet;
    }
}
