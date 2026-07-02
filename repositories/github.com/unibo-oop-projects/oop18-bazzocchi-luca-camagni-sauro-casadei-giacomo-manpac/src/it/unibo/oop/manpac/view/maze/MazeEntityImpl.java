package it.unibo.oop.manpac.view.maze;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.manpac.model.Entity;

/**
 * Implementation of the MazeEntity interface.
 */
public class MazeEntityImpl implements MazeEntity {
    private final TiledMap map;
    private Entity entity;
    private final Body body;
    private final Fixture fixture;

    /**
     * Constructor for the MazeEntityImpl class.
     * 
     * @param mundus The world which the entity will be created in
     * @param limits The hitbox borders of the entity
     * @param map    The tiled map
     */
    public MazeEntityImpl(final World mundus, final Rectangle limits, final TiledMap map) {
        final BodyDef bdef = new BodyDef();
        final FixtureDef fdef = new FixtureDef();
        final PolygonShape shape = new PolygonShape();
        final World world = mundus;
        final Rectangle bounds = limits;
        this.map = map;
        // creating the body definition
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2), (bounds.getY() + bounds.getHeight() / 2));
        // create the body and put it into the world
        body = world.createBody(bdef);
        // define the shape of the hit box
        shape.setAsBox(bounds.getWidth() / 2, bounds.getHeight() / 2);
        // creating the fixture definition
        fdef.shape = shape;
        fdef.density = 0f;
        fdef.friction = 0f;
        fdef.restitution = 0f;
        // attach the fixture definitions to the body
        fixture = body.createFixture(fdef);
        // set the user data of this fixture so that refers to this whole class (for
        // colliding purposes)
        fixture.setUserData(this);

    }

    /**
     * {@inheritDoc}
     */
    public Cell getCell(final int index) {
        final TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(index);
        return layer.getCell((int) (body.getPosition().x / MazeCreatorImpl.TILES_SIZE_IN_PIXELS),
                (int) (body.getPosition().y / MazeCreatorImpl.TILES_SIZE_IN_PIXELS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * {@inheritDoc}
     */
    public Fixture getFixture() {
        return fixture;
    }

    /**
     * {@inheritDoc}
     */
    public Body getBody() {
        return this.body;
    }

    /**
     * {@inheritDoc}
     */
    public void setCollisionProperties(final int filterBit, final Entity entity) {
        final Filter filter = new Filter();
        this.entity = entity;
        filter.categoryBits = (short) filterBit;
        if (filterBit == Collision.PILL_BIT || filterBit == Collision.POWER_BIT) {
            filter.maskBits = Collision.PACMAN_BIT;
        } else if ((filterBit != Collision.SPAWN_BIT)) {
            filter.maskBits = Collision.PACMAN_BIT | Collision.PHANTOM_BIT;
        }
        fixture.setFilterData(filter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
        result = prime * result + ((fixture == null) ? 0 : fixture.hashCode());
        result = prime * result + ((map == null) ? 0 : map.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final MazeEntity other = (MazeEntity) obj;
        if (!this.body.equals(other.getBody())) {
            return false;
        }
        if (!this.entity.equals(other.getEntity())) {
            return false;
        }
        return (this.fixture.equals(other.getFixture()));
    }

}
