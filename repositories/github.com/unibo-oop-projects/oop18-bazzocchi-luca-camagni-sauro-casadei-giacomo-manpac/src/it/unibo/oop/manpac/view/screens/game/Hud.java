package it.unibo.oop.manpac.view.screens.game;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import it.unibo.oop.manpac.view.screens.menu.GUIFactory;
import it.unibo.oop.manpac.view.screens.menu.GUIFactoryImpl;

/**
 * Used to overlay information during the game.
 *
 */
public final class Hud implements Disposable {
    // for draw the edges of table and all element of hud
    private static final boolean DEBUG_MODE = false;

    private static final String CURRENT_SCORE = "SCORE";
    private static final String HIGH_SCORE = "HIGH SCORE";

    private static final String LIVES_PATH = "Sprites/Pacman_Left.png";

    private static final String FONT_PATH_LABEL = "Fonts/arial.fnt";
    private static final float FONT_SIZE = 0.3f;

    private static final float SPACE_SCORE = 30;
    private static final float PAD_TOP_BOTTOM = 15;
    private static final float SPACE_LIVES = 5;

    // area where to draw the hud
    private final Stage stage;

    // labels for number of current and high score
    private final Label currentScoreNumberLabel;
    private final Label highScoreNumberLabel;

    // table where to draw the Pac-Man's lives
    private final Table tableLives;
    // image of the Pac-Man's lives
    private final Texture life;

    /**
     * Constructor of Hud.
     * 
     * @param windowsWidth  Window width
     * @param windowsHeight Window height
     * @param spriteBatch   The {@link SpriteBatch} of the game
     */
    public Hud(final float windowsWidth, final float windowsHeight, final SpriteBatch spriteBatch) {
        Objects.requireNonNull(spriteBatch, "The SpriteBatch in Hud is null!");

        // general table of the Hud
        final Table tableBig = new Table();
        // table for score
        final Table tableScore;

        final GUIFactory gf = new GUIFactoryImpl();

        // Draw all edges
        if (DEBUG_MODE) {
            this.stage.setDebugAll(true);
        }
        this.stage = new Stage(new FitViewport(windowsWidth, windowsHeight, new OrthographicCamera()), spriteBatch);

        this.life = new Texture(LIVES_PATH);

        // Set table to fill stage
        tableBig.setFillParent(true);

        //generate a list of 4 same label
        final List<Label> labels = Stream
                .generate(() -> gf.createLabel("", gf.createBitmapFont(FONT_PATH_LABEL), Color.WHITE))
                .peek(label -> label.setFontScale(FONT_SIZE))
                .limit(4).collect(Collectors.toList());

//        +----------------+--------------------+
//        |    [0]SCORE    |    [1]HIGHSCORE    |
//        +----------------+--------------------+
//        |      [3]0      |        [4]0        |
//        +----------------+--------------------+
        // first and second label for only text score (first line)
        labels.get(0).setText(CURRENT_SCORE);
        labels.get(1).setText(HIGH_SCORE);

        // third and fourth label for number of score (second line)
        this.currentScoreNumberLabel = labels.get(2);
        this.highScoreNumberLabel = labels.get(3);


        tableScore = new Table();

        for (int i = 0; i < labels.size(); i++) {
            // add the label in the table
            final Cell<Label> c = tableScore.add(labels.get(i));
            if (Math.floorMod(i + 1, 2) == 0) {
                // if it is the second label of the line, set the space and go to a new line
                c.padLeft(SPACE_SCORE);
                tableScore.row();
            }
        }

        tableBig.add(tableScore).padTop(PAD_TOP_BOTTOM).expandX();
        tableBig.row();

        this.tableLives = new Table();

        tableBig.add(this.tableLives).expandY().bottom().padBottom(PAD_TOP_BOTTOM).left();

        this.stage.addActor(tableBig);
    }

    /**
     * To have a copy of the Matrix4 used by the camera in the Hud.
     * 
     * @return A copy of Matrix4 of the camera
     */
    public com.badlogic.gdx.math.Matrix4 getMatrix4FromCam() {
        return stage.getCamera().combined.cpy();
    }

    /**
     * To set on the Hud the high score.
     * 
     * @param highScore The high score of the game
     */
    public void setHighScore(final int highScore) {
        this.highScoreNumberLabel.setText(Integer.toString(highScore));
    }

    /**
     * To set on the Hud the current score.
     * 
     * @param currentScore The current score of the game
     */
    public void setCurrentScore(final int currentScore) {
        this.currentScoreNumberLabel.setText(Integer.toString(currentScore));
    }

    /**
     * To set on the Hud the Pac-Man's lives.
     * 
     * @param pacmanLives the lives of Pac-Man
     */
    public void setPacManLives(final int pacmanLives) {
        this.tableLives.clearChildren();

        Stream.generate(() -> new Image(this.life)).limit(pacmanLives)
                .forEach(life -> tableLives.add(life).padLeft(SPACE_LIVES));
    }

    /**
     * Draw the Hud.
     */
    public void draw() {
        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.life.dispose();
    }
}
