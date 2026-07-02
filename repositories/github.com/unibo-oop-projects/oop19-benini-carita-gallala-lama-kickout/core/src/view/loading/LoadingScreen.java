package view.loading;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.awt.Point;
import controller.menu.MenuStarter;
import utility.AnimationGenerator;
/**
 * Creates a screen that allows for transitions between EndingScreen and MenuScreen and at when the application is started
 */
public class LoadingScreen implements Screen {

    private SpriteBatch batch;
    private Stage stage;
    private Table table;
    private Image title;
    private Sprite background;
    private float elapsedTime;
    private AnimationGenerator animationBars;
    private final String animationFolder = "Loading_images";

    private final Game mainScreen;
    private final Point animationposition;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
	/**
	 * Changes the screen using the given argument of type Game
	 * @param mainScreen
	 * 			The game for which the screen will be changed
	 */
    public LoadingScreen(final Game mainScreen) {
        this.mainScreen = mainScreen;
        this.animationposition = new Point(WIDTH / 5, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        Gdx.graphics.setWindowedMode(WIDTH, HEIGHT);
        Gdx.graphics.setResizable(false);
        this.batch = new SpriteBatch();
        this.stage = new Stage(new ScreenViewport());
        this.table = new Table();
        //elements of screen
        this.background = new Sprite(new Texture("Loading_Background.jpg"));
        this.background.setSize(WIDTH, HEIGHT);
        this.title = new Image(new Texture("Logo.png"));
        this.animationBars = new AnimationGenerator(this.animationFolder);
        //position of the elements
        this.table.setFillParent(true);
        this.table.add(title).center();
        this.stage.addActor(table);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta) {

        this.elapsedTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_CLEAR_VALUE);
        this.batch.begin();
        this.background.draw(this.batch);
        this.batch.draw(this.animationBars.getAnimation()
                                          .getKeyFrame(this.elapsedTime, true),
                        this.animationposition.x, this.animationposition.y);

        this.batch.end();
        this.stage.draw();

        if (this.isTimeToMenusScreen()) {
            new MenuStarter(this.mainScreen);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(int width, int height) {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        this.batch.dispose();
        this.stage.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        this.dispose();
    }

    private boolean isTimeToMenusScreen() {
        if (this.animationBars.getAnimation().isAnimationFinished(this.elapsedTime)) {
            return true;
        }
        return false;
    }
}
