package it.unibo.oop.bounce.obstacles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import it.unibo.oop.bounce.game.Bounce;
import it.unibo.oop.bounce.screens.PlayScreen;

public class ObstaclesCreatorImpl implements ObstaclesCreator {
	
	private final TiledMap map;
	private final PlayScreen playscreen;
	private Bounce game;
	private static final int BLOCK = 4;
	private static final int CHECKPOINT = 5;
	private static final int THORN = 6;
	private static final int RING = 7;
	private static final int LIFE = 8;
	private static final int FINISH_BLOCK = 9;
	private static final int HALFBLOCK = 10;
	private static final int PUMPER = 11;
	private static final int DEFLATER = 12;
	private static final int END_BLOCK = 13;

	public ObstaclesCreatorImpl(final PlayScreen playscreen, final Bounce game) {
		this.playscreen = playscreen;
		this.map = playscreen.getMap();
		this.game = game;
	}

	@Override
	public final void createObstacles() {
		for (int i = 1; i < END_BLOCK; i++) {

			for (final MapObject rObject : map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {
				final Rectangle rectangle = ((RectangleMapObject) rObject).getRectangle();
				switch (i) {
				case BLOCK:
					new Block(playscreen, rectangle.getX(), rectangle.getY(), rObject);
					break;
				case THORN:
					new Thorn(playscreen, rectangle.getX(), rectangle.getY(), rObject);
					break;
				case DEFLATER:
					new Deflater(playscreen, rectangle.getX(), rectangle.getY(), rObject);
					break;
				case PUMPER:
					new Pumper(playscreen, rectangle.getX(), rectangle.getY(), rObject);
					break;
				case RING:
					new Ring(playscreen, rectangle.getX(), rectangle.getY(), rObject);
					break;
				case FINISH_BLOCK:
					new FinishBlock(playscreen, rectangle.getX(), rectangle.getY(), rObject, game);
					break;
				case END_BLOCK:
					new EndBlock(playscreen, rectangle.getX(), rectangle.getY(), rObject);
					break;
				default:
					break;
				}

			}
		}
		for (int i = 0; i < END_BLOCK; i++) {
			for (final MapObject obj : map.getLayers().get(i).getObjects().getByType(PolygonMapObject.class)) {
				final Polygon polygon = ((PolygonMapObject) obj).getPolygon();
				switch (i) {
				case CHECKPOINT:
					new Checkpoint(playscreen, polygon.getX(), polygon.getY(), obj);
					break;
				case LIFE:
					new Life(playscreen, polygon.getX(), polygon.getY(), obj);
					break;
				case HALFBLOCK:
					new HalfBlock(playscreen, polygon.getX(), polygon.getY(), obj);
					break;
				default:
					break;
				}
			}
		}
	}
}
