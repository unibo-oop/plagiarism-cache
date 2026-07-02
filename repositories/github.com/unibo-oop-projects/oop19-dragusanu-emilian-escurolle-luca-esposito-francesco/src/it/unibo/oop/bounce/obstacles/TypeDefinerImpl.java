package it.unibo.oop.bounce.obstacles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;

import it.unibo.oop.bounce.ball.Entity;
import it.unibo.oop.bounce.manager.Manager;
import it.unibo.oop.bounce.screens.PlayScreen;

public class TypeDefinerImpl extends Entity implements TypeDefiner {
	
	private Body body;
	private static final int RECTANGLE = 1;
	private static final int POLYGON = 2;
	private static final int BLOCK = 4;
	private static final int CHECKPOINT = 5;
	private static final int THORN = 6;
	private static final int RING = 7;
	private static final int LIFE = 8;
	private static final int FINISH_BLOCK = 9;
	private static final int HALF_BLOCK = 10;
	private static final int DEFLATER = 12;
	private static final int PUMPER = 11;
	private static final int END_BLOCK = 13;
	private Fixture fixture;

	public TypeDefinerImpl(final PlayScreen playscreen, final float x, final float y, final MapObject object) {
		super(playscreen, x, y, object);
	}

	@Override
	public final void setEntity(final int shapeType, final int obstacleId) {
		final BodyDef bdf = new BodyDef();
		final PolygonShape shape = new PolygonShape();
		final FixtureDef fixDef = new FixtureDef();
		bdf.type = BodyDef.BodyType.StaticBody;
		if (shapeType == RECTANGLE) {
			final Rectangle rect = ((RectangleMapObject) getObject()).getRectangle();
			bdf.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
			body = getPlayScreen().getWorld().createBody(bdf);
			shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
			if (obstacleId == BLOCK) {
				fixDef.filter.categoryBits = Manager.BLOCK_ID;
				fixDef.filter.maskBits = Manager.BOUNCE_ID;
			} else if (obstacleId == THORN) {
				fixDef.filter.categoryBits = Manager.THORN_ID;
				fixDef.filter.maskBits = Manager.BOUNCE_ID;
			} else if (obstacleId == RING) {
				fixDef.filter.categoryBits = Manager.RING_ID;
				fixDef.filter.maskBits = Manager.BOUNCE_ID;
			} else if (obstacleId == FINISH_BLOCK) {
				fixDef.filter.categoryBits = Manager.FINISH_BLOCK_ID;
				fixDef.filter.maskBits = Manager.BOUNCE_ID;
			} else if (obstacleId == DEFLATER) {
				fixDef.filter.categoryBits = Manager.DEFLATER_ID;
				fixDef.filter.maskBits = Manager.BOUNCE_ID;
			} else if (obstacleId == PUMPER) {
				fixDef.filter.categoryBits = Manager.PUMPER_ID;
				fixDef.filter.maskBits = Manager.BOUNCE_ID;
			} else if (obstacleId == END_BLOCK) {
				fixDef.filter.categoryBits = Manager.END_BLOCK_ID;
				fixDef.filter.maskBits = Manager.BOUNCE_ID;
			}
			fixDef.shape = shape;
			fixture = body.createFixture(fixDef);
		} else if (shapeType == POLYGON) {
			final Polygon polygon = ((PolygonMapObject) getObject()).getPolygon();
			bdf.position.set(polygon.getX(), polygon.getY());
			body = getPlayScreen().getWorld().createBody(bdf);
			shape.setAsBox(polygon.getScaleX(), polygon.getScaleY());
			if (obstacleId == CHECKPOINT) {
				fixDef.filter.categoryBits = Manager.CHECKPOINT_ID;
				fixDef.filter.maskBits = Manager.BOUNCE_ID;
			} else if (obstacleId == LIFE) {
				fixDef.filter.categoryBits = Manager.LIFE_ID;
				fixDef.filter.maskBits = Manager.BOUNCE_ID;
			} else if (obstacleId == HALF_BLOCK) {
				fixDef.filter.categoryBits = Manager.HALFBLOCK;
				fixDef.filter.maskBits = Manager.BOUNCE_ID;
			}
			fixDef.shape = shape;
			fixture = body.createFixture(fixDef);
		}
	}

	public final Body getBody() {
		return body;
	}

	public final void setCategoryFilter(final int filterID) {
		final Filter filter = new Filter();
		filter.categoryBits = (short) filterID;
		fixture.setFilterData(filter);
	}

	@Override
	public final Fixture getFixture() {

		return fixture;
	}

}
