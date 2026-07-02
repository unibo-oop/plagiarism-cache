package view.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import controller.StageStarter;
import utility.StageData;
/**
 * The main GUI that manages MapGUI and PlayerGUI
 */
public class MenuScreen implements Screen {
    private final Game mainScreen;
    private Stage stage;
    private Skin skin;
    private Table table;

    private PlayerGUI player1;
    private PlayerGUI player2;
    private MapGUI map;
    private TextButton playButton;
    private final float buttonDistance = 10;

    private SpriteBatch batch;

    private Dialog dialog;
    private Stage stageDialog;

    private InputMultiplexer inputMultiplexer;
    /**
     * Loads the given GUIs and it links them
     * @param mainscreen
     *          The manager of the screen
     * @param player1
     *          The first player's GUI
     * @param player2
     *          The second player's GUI
     * @param map
     *          The map's GUI
     */
    public MenuScreen(final Game mainscreen, final PlayerGUI player1, final PlayerGUI player2, final MapGUI map) {
        this.mainScreen = mainscreen;
        this.player1 = player1;
        this.player2 = player2;
        this.map = map;
    }

    /**
     * Draws the listeners to be added by ImageSelector, selects the architecture of the GUI
     */
    @Override
    public void show() {
        this.inputMultiplexer = new InputMultiplexer();
        this.batch = new SpriteBatch();
        this.stage = new Stage();
        this.stageDialog = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("Skin/comic-ui.json"));
        this.table = new Table();
        this.table.setFillParent(true);

        this.playButton = new TextButton("PLAY NOW!!", skin);

        this.dialog = new Dialog("", skin);
        this.dialog.text("Enter name of players!!").setColor(Color.BLACK);
        this.dialog.button("OK");
        //position of every element in the window
        this.table.add(this.player1.GUI()).prefSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.table.row();
        this.table.add(this.player2.GUI()).prefSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.table.row();
        this.table.add(this.map.GUI()).prefSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.table.row();
        this.table.add(this.playButton).padBottom(this.buttonDistance);
        this.stage.addActor(this.table);

        this.inputMultiplexer.addProcessor(this.stageDialog);
        this.inputMultiplexer.addProcessor(this.stage);
        Gdx.input.setInputProcessor(this.inputMultiplexer);
    }

    /**
     * Draws and updates the current GUI views
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();
        //background
        this.map.getSelected().draw(this.batch);
        this.batch.end();
        //draw all the buttons and text fields
        this.stage.act();
        this.stage.draw();
        //draw the dialog if it is set the method dialog.show
        this.stageDialog.act();
        this.stageDialog.draw();
        //check for the change of the screen
        if (this.playButton.isPressed()) {
            if (this.isAllSet()) {
                this.changeScreen();
            } else {
                this.dialog.show(this.stageDialog);
            }
        }
    }

    /**
     * Resizes based on the screen resolution
     */
    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().setScreenSize(width, height);
        this.stageDialog.getViewport().setScreenSize(width, height);
        this.batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        this.dispose();
    }
    /**
     * Disposes the elements that are not needed when the game starts
     */
    @Override
    public void dispose() {
        this.stage.dispose();
        this.stageDialog.dispose();
        this.batch.dispose();
        this.skin.dispose();
    }

    private boolean isAllSet() {
        if (this.player1.getPlayertext().isEmpty() || this.player2.getPlayertext().isEmpty()) {
            return false;
        }
        return true;
    }

    private void changeScreen() {
        StageData temp = new StageData(this.player1.getIndexselected(), this.player2.getIndexselected(),
                                       this.map.getSelected());
        temp.setNamePlayer1(this.player1.getPlayertext());
        temp.setNamePlayer2(this.player2.getPlayertext());
        new StageStarter(this.mainScreen, temp);
    }
}
