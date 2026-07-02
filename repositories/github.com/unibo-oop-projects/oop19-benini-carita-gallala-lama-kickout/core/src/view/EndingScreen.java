package view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import view.loading.LoadingScreen;
/**
 * Draws the ending screen and provides the player the choice to return to the main menu or to exit the game
 *
 */
public class EndingScreen implements Screen {

    private final Game mainGame;
    private Skin skin;
    private TextButton menuButton;
    private TextButton exitButton;
    private Stage stage;
    private Table table;
    private final String loser;
    private Label title;
	/**
	 * Gets the Game and the String of the player that lost and displays the ending screen
	 * @param mainGame
	 * 			The game for which the screen will be changed
	 * @param loser
	 * 			The name of the player that lost
	 */
    public EndingScreen(final Game mainGame, final String loser) {
        this.mainGame = mainGame;
        this.loser = loser;
    }

    /**
     * Draws the two buttons, sets up a listener and draw the loser
     */
    @Override
    public void show() {
        this.skin = new Skin(Gdx.files.internal("Skin/comic-ui.json"));
        this.menuButton = new TextButton("MENU", skin);
        this.exitButton = new TextButton("EXIT", skin);
        this.title = new Label("THE LOSER IS " + this.loser, this.skin, "title");
        this.stage = new Stage(new ScreenViewport());
        this.table = new Table();
        this.table.setFillParent(true);

        this.table.add(this.title);
        this.table.row();
        this.table.add(this.menuButton);
        this.table.row();
        this.table.add(this.exitButton);

        this.stage.addActor(this.table);
        Gdx.input.setInputProcessor(this.stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        this.playAgain();
        this.exit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().setScreenSize(width, height);
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

    private void playAgain() {
        if (this.menuButton.isPressed()) {
            this.mainGame.setScreen(new LoadingScreen(this.mainGame));
        }
    }

    private void exit() {
        if (this.exitButton.isPressed()) {
            Gdx.app.exit();
        }
    }
}
