package view.game;

import view.TextureLocation;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import view.AbstractGameScene;
import view.EntityVisualLoader;
import javafx.scene.shape.Rectangle;

/**
 * This is the main game window. Static final fields will be updated when a controller is available. 
 */
public class GameScene extends AbstractGameScene implements Game {

    //Static constants
    private static final double CORNER_OFFSET = 3.0;
    private static final double LIVES_OFFSET = 5.0;
    private static final double LIVES_DIMENSION = 20.0;
    private static final double INSETS_TOP = 15;
    private static final double BASE_INSET = 25;
    private static final double RATIO = 10;
    private static final String LOADING_ERROR = "Errore di caricamento immagine: ";
    //Temporary constants
    private static final int LANE_NUM = 13;
    private static final int TIME = 15;
    //Property for the timer component
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(TIME * 100);
    //Global variables
    private List<LaneView> lanes;
    private final GridPane viewStack;
    private final GridPane timeGrid;
    private final GridPane scoreGrid;
    private final GridPane lives;
    private static final AnchorPane OUTER = new AnchorPane();
    //View elements to be changed dynamically
    private final Label scoreLabel;
    private final HBox livingRepr;

/*  static-access warning is suppressed because we wouldn't be able to call 
*   "setXAnchor" methods (which are static methods)
*    without raising a warning otherwise.           */

    /**
     * Constructor for the GameView.
     * @throws Exception when building the lanes and textures are not found.
     * @param height height of the scene
     * @param width width of the scene
     */
    @SuppressWarnings("static-access")
    public GameScene(final double width, final double height) {
/*      The AnchorPane which controls the outer components 
 *      of the window is referred to as "OUTER"
 */
        super(OUTER, width, height);

        /*TOP*/

        //A Grid that must be shown in the top-right corner of the window
        timeGrid = new GridPane();
        timeGrid.setHgap(10);
        //ProgressBar will show the correct remaining time #when a timer will be implemented
        ProgressBar timer = new ProgressBar();
        timer.progressProperty().bind(timeSeconds.divide(TIME * 100));
        Text timerText = new Text("Timer");
        timeGrid.add(timerText, 0, 0);
        timeGrid.add(timer, 1, 0);

        /*MID*/

        //A GridPane is used to keep all lanes.
        viewStack = new GridPane();
        lanes = new ArrayList<>();

        /*BOTTOM*/

/*      The following are the elements in the bottom corners of the window.
 *       The first component is a GridPane which shows the current score.
 *       This will be updated every time the frog progresses forward 
 *       or does something that involves score changes.
 *       This component will be anchored in the bottom-left corner.
 */
        scoreGrid = new GridPane();
        scoreGrid.setAlignment(Pos.BOTTOM_LEFT);
        scoreGrid.setHgap(10);
        scoreLabel = new Label(Integer.toString(0));
        Text scoreText = new Text("Score");
        scoreGrid.add(scoreText, 0, 0);
        scoreGrid.add(scoreLabel, 1, 0);

/*      The second component is another GridPane showing 
 *      the current remaining lives.
 *      This will be updated when a life is lost or gained.
 *      This component will be anchored in the bottom-right corner.
 */
        lives = new GridPane();
        lives.setHgap(LIVES_OFFSET);
        lives.setAlignment(Pos.BOTTOM_RIGHT);
        livingRepr = new HBox(10);
        updateLives(3);

        Text livesText = new Text("Lives");
        lives.add(livesText, 0, 0);
        lives.add(livingRepr, 1, 0);

        /*GENERAL*/

        //defining the properties of the outer AnchorPane
        OUTER.setPadding(new Insets(INSETS_TOP, BASE_INSET, BASE_INSET, BASE_INSET));
        //The timeGrid is anchored on the top right with a static offset
        OUTER.setTopAnchor(timeGrid, CORNER_OFFSET);
        OUTER.setRightAnchor(timeGrid, CORNER_OFFSET);
        //The score Grid is anchored on the bottom-left corner with the same static offset
        OUTER.setBottomAnchor(scoreGrid, CORNER_OFFSET);
        OUTER.setLeftAnchor(scoreGrid, CORNER_OFFSET);
        //The lives Grid is anchored on the bottom-right corner with the same static offset
        OUTER.setBottomAnchor(lives, CORNER_OFFSET);
        OUTER.setRightAnchor(lives, CORNER_OFFSET);

/*      The ListView that contains the lanes in the game is anchored 
 *      to both the left and the right of the window, but also to the top 
 *      with a sceneHeight/10 offset and to the bottom with the same offset. 
 *      1/10 of the window for each "border" is a fair proportion, 
 *      but it might be changed easily
 */
        OUTER.setLeftAnchor(viewStack, CORNER_OFFSET);
        OUTER.setRightAnchor(viewStack, CORNER_OFFSET);
        OUTER.setTopAnchor(viewStack, height / RATIO);
        OUTER.setBottomAnchor(viewStack, height / RATIO);

        //Finally, all corner elements are added to the outer AnchorPane
        OUTER.getChildren().add(timeGrid);
        OUTER.getChildren().add(scoreGrid);
        OUTER.getChildren().add(lives);
        OUTER.getChildren().add(viewStack);
    }

    private Image getImage(final String s) throws FileNotFoundException {
        return EntityVisualLoader.getVisualLoader().getResource(s);
    }

    private ImageView getImageView(final Image img) {
        return new ImageView(img);
    }

    /**
     * Adds a lane to the "lanes" list.
     * @param t The TextureLocation to be rendered on the lane (background image)
     * @param p The pane that contains the lane, so that we can use its height and width.
     */
    private void addLane(final TextureLocation t, final GridPane p, final int index) {
        try {
            LaneView l = new LaneView(getImageView(getImage(t.getUrl())),
                                        p.heightProperty().divide(LANE_NUM),
                                        p.widthProperty());
            lanes.add(l);
            p.addRow(index, l);
        } catch (FileNotFoundException e) {
            System.err.print(LOADING_ERROR + t.getUrl());
        }
    }

    /**
     * This method receives a collection of all elements to draw and calls the drawEntites() method
     * in each lane with the correct parameters.
     * @param toDraw collection of entities to be drawn.
     *          First Pair contains width and center of entity.
     *          Second Pair contains lane number and texture location.
     */
    public void drawEntities(final Collection<ViewableEntity> toDraw) {
        lanes.forEach(l -> l.prepare());
        for (ViewableEntity p : toDraw) {
            final LaneView lane = lanes.get(p.getLaneNum());
            try {
                final Image img = getImage(p.getTexture());
                lane.drawEntity(img, p.getPosition(), p.getWidth(), p.getDir().getAngle());
            } catch (FileNotFoundException e) {
                System.err.print(LOADING_ERROR + p.getTexture());
            }
        }
    }

    /**
     * Updates the score.
     * @param newScore the updated score.
     */
    public void updateScore(final Integer newScore) {
        this.scoreLabel.setText(Integer.toString(newScore));
    }

    /**
     * Updates the number of lives.
     * @param livesNum the number of lives to be shown.
     */
    public void updateLives(final Integer livesNum) {
      //#Currently using rectangles to represent lives. Will be modified in the future.
        livingRepr.getChildren().clear();
        IntStream.range(0, livesNum).mapToObj(i -> new Rectangle(LIVES_DIMENSION, LIVES_DIMENSION, Color.DARKGREEN))
                                   .forEach(b -> livingRepr.getChildren().add(b));
    }

    /**
     * Builds the game world grid and gets score, lives and other elements. 
     * It needs to be called after the creation of the GameScene
     * and before drawing any Entity on the Game world.
     */
    public void initialize() {
      //Instantiate LANE_NUM lanes + 1 last lane of dens.
        for (int i = 0; i < LANE_NUM; i++) {
            if (i == 0) {
                addLane(TextureLocation.LANE_END, viewStack, i);
            } else if (i > 0 && i < LANE_NUM / 2) {
                addLane(TextureLocation.WATER, viewStack, i);
            } else if (i == LANE_NUM / 2) {
                addLane(TextureLocation.LANE_MID, viewStack, i);
            } else if (i > LANE_NUM / 2 && i < LANE_NUM - 1) {
                addLane(TextureLocation.STREET, viewStack, i);
            } else if (i == LANE_NUM - 1) {
                addLane(TextureLocation.LANE_FIRST, viewStack, i);
            }
        }
    }

    @Override
    public void initialize(final String... args) {
    }
}

