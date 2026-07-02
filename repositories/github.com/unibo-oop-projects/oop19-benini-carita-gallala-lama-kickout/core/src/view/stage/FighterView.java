package view.stage;

import model.Direction;
import model.Fighter;
import utility.Pair;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import static utility.Constants.WIDTH_FIGHTER;
import static utility.Constants.HEIGHT_FIGHTER;
import static utility.Constants.DOP;

/**
 *  Draws the Fighters and constantly updates their Sprite
 */
public class FighterView {

    private InputMultiplexer multiplexer;
    private LinkedList<Pair<Fighter, AnimationManager>> fighters;
    private float elapsedTime;
    private final float rotation;

    private static final float SCALE = 0.35f;
    private static final float VELOCITY_TIME = 1.5f;
    private static final float MARGIN_MIN = 25;
    private static final float MARGIN_MAX = 60;
    private static final float ZOOM_IN_X = 0.01f;
    private static final float ZOOM_OUT_X = 0.005f;
    private static final float ZOOM_IN_Y = 0.02f;
    private static final float ORIGIN_Y = 0f;
    private static final float SCALE_Y = 1f;
    /**
     * When FighterView is called, an InputMultiplexer is created as well as a LinkedList of all the players currently in the game
     */
    public FighterView() {
        this.multiplexer = new InputMultiplexer();
        this.fighters = new LinkedList<>();
        this.elapsedTime = 0;
        this.rotation = 0;
        Gdx.input.setInputProcessor(this.multiplexer);
    }

    /**
     * For each player in the LinkedList, the correct Animation is drawn to the screen
     * @param batch
     */
    public void drawPlayers(final SpriteBatch batch) {

        for (Pair<Fighter, AnimationManager> player : this.fighters) {
            this.elapsedTime = this.elapsedTime + Gdx.graphics.getDeltaTime() / VELOCITY_TIME;
            batch.draw(player.getY().getAnimation(player.getX().getCurrentStatus()).getKeyFrame(elapsedTime, true),
                       player.getX().getBody().getPosition().x - WIDTH_FIGHTER / 2,
                       player.getX().getBody().getPosition().y - HEIGHT_FIGHTER / 2,
                       WIDTH_FIGHTER / 2f, ORIGIN_Y,
                       player.getY().getAnimation(player.getX().getCurrentStatus())
                                    .getKeyFrame(elapsedTime, true).getRegionWidth() * SCALE,
                       player.getY().getAnimation(player.getX().getCurrentStatus())
                                    .getKeyFrame(elapsedTime, true).getRegionHeight() * SCALE,
                       (player.getX().getCurrentDirection().equals(Direction.RIGHT) ? 1 : -1), SCALE_Y, this.rotation);
        }
    }

    /**
     * Sets the elapsedTime
     * @param elapsedTime
     * 			the elapsedTime to be set
     */
    public void setElapsedTime(final float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
    /**
     * Sets the listener
     * @param listener
     * 			the listener to be set
     */
	public void setListener(final InputProcessor listener) {
    	this.multiplexer.addProcessor(listener);
    }
	/**
	 * Sets the Fighter's body
	 * @param body
	 * 			the body to be set
	 */
    public void setBodyFighter(final Fighter body) {
        this.fighters.add(new Pair<Fighter, AnimationManager>(body, new AnimationManager(body.getFolderName())));
    }

    /**
     * Updates the current view of the game
     * @param camera
     * 			The camera to be updated
     * @param batch
     */
    public void update(final OrthographicCamera camera, final SpriteBatch batch) {
    	float effetivewidth = (camera.viewportWidth * camera.zoom) / 2;
    	float effetiveheight = (camera.viewportHeight * camera.zoom) / 2;

    	Point position = this.getMaxXY(effetivewidth, effetiveheight, camera);

    	if ((effetivewidth - position.x) < MARGIN_MIN) {
    		camera.zoom = camera.zoom + ZOOM_IN_X;
    	}

    	if ((effetivewidth - position.x) > MARGIN_MAX) {
    		camera.zoom = camera.zoom - ZOOM_OUT_X;
    	}

    	if ((effetiveheight - position.y) < MARGIN_MIN) {
    		camera.zoom = camera.zoom + ZOOM_IN_Y;
    	}
    	camera.zoom = MathUtils.clamp(camera.zoom, 1f, (Gdx.graphics.getDisplayMode().width / DOP) / camera.viewportWidth);

    	camera.update();
    	batch.setProjectionMatrix(camera.combined);
    }

	private Point getMaxXY(final float effetivewidth, final float effetiveheight, final  OrthographicCamera camera) {
	    Set<Integer> maxW = new HashSet<Integer>();
	    Set<Integer> maxH = new HashSet<Integer>();
	    Point temp = new Point();
		for (Pair<Fighter, AnimationManager> player : fighters) {
            maxW.add(this.getPositionCamera(player.getX().getBody().getPosition(), effetivewidth, effetiveheight, camera).x);
            maxH.add(this.getPositionCamera(player.getX().getBody().getPosition(), effetivewidth, effetiveheight, camera).y);
		}
		temp.setLocation(maxW.stream().max(Integer::compare).get().intValue(),
		                 maxH.stream().max(Integer::compare).get().intValue());
		return temp;
	}

	private Point getPositionCamera(final Vector2 position, final float width, final float height,
	                                  final OrthographicCamera camera) {
		Point temp = new Point();
		temp.x = (int) Math.abs(position.x - ((camera.viewportWidth * (1 - camera.zoom)) / 2) - width);
		temp.y = (int) Math.abs(position.y - ((camera.viewportHeight * (1 - camera.zoom)) / 2) - height);
		return temp;
	}

	/**
	 * Checks if any Fighter is out of the screen
	 * @param camera
	 * 			The camera currently used
	 * @return whether or not a Fighter is out of the screen
	 */
	public Optional<Fighter> isAnyoneOut(final OrthographicCamera camera) {
	    for (Pair<Fighter, AnimationManager> fighter : fighters) {
            if (fighter.getX().isOut(camera)) {
                return Optional.ofNullable(fighter.getX());
            }
        }
	    return Optional.empty();
	}

}
