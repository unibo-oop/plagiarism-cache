package view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import static utility.Constants.DOP;
import model.Platform;
import view.stage.FighterView;
import view.stage.TrapView;
/**
 * Draws and manages the entire stage and its component from the view perspective
 */
public class StageView implements Screen {

	private final World world;
	private OrthographicCamera camera;

	private SpriteBatch batch;
	private Texture platformImage;
	private Sprite background;
	private Platform platform;

	private FighterView playersView;
	private TrapView trapview;
	private final Game maingame;
	/**
	 * Links all the arguments given and draws the entire stage
	 * @param world
	 * 			The world in which to create the game
	 * @param camera
	 * 			The camera used to display the game
	 * @param playersView
	 * 			Used to manage the view of the Fighters
	 * @param trapview
	 * 			Used to manage the view of the Trap
	 * @param background
	 * 			The background chosen in the main menu
	 * @param game
	 * 			The main game in which to display the fight
	 */
	public StageView(final World world, final OrthographicCamera camera, final FighterView playersView, final TrapView trapview,
					 final Sprite background, final Game game) {
		this.world = world;
		this.camera = camera;
		this.playersView = playersView;
		this.trapview = trapview;
		this.batch = new SpriteBatch();
		this.background = background;
		this.maingame = game;
	}

	/**
	 * Draws the platform
	 */
	@Override
	public void show() {
		this.platformImage = new Texture("1200px-SSBU-Battlefield.png");
	}

	/**
	 * Constantly renders the game ad updates its view
	 * @param delta
	 * 			The single frame measured in seconds
	 */
	@Override
	public void render(float delta) {
		this.playersView.update(this.camera, this.batch);
    	world.step(1/60f, 8, 3);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	this.batch.begin();
    	this.drawBackground();
    	this.batch.draw(this.platformImage,
    	                (float) this.platform.getPoint().getX(), (float) this.platform.getPoint().getY(),
    	                this.platform.getWidth(), this.platform.getHeight());
    	this.trapview.drawTrap(this.batch);
    	this.playersView.drawPlayers(this.batch);
		this.batch.end();
		if (this.playersView.isAnyoneOut(this.camera).isPresent()) {
		    this.maingame.setScreen(new EndingScreen(this.maingame,
		                                             this.playersView.isAnyoneOut(this.camera)
		                                                             .get()
		                                                             .getNamePlayer()));
		}
	}

	/**
	 * Resizes the camera based on the position of the Fighters
	 */
	@Override
	public void resize(int width, int height) {
    	batch.setProjectionMatrix(this.camera.combined);
    	this.background.setSize(Gdx.graphics.getDisplayMode().width / DOP, Gdx.graphics.getDisplayMode().width / DOP);
    }
	@Override
	public void pause() {
	}
	@Override
	public void resume() {
	}
	@Override
	public void hide() {
	}
    @Override
    public void dispose() {
    }

    /**
     * Sets the platform
     * @param platform
     * 		The platform to be set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    private void drawBackground() {
    	this.background.setCenter(this.camera.viewportWidth/2, this.camera.viewportHeight/2);
    	this.background.draw(this.batch);
    }
}
