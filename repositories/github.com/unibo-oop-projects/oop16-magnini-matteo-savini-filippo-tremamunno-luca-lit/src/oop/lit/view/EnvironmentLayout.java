package oop.lit.view;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import oop.lit.controller.Controller;
import oop.lit.model.GameModel;
import oop.lit.view.controller.ViewControllerManagerImpl;

/**
 * A controller class for the actions permitted in the simulator environment.
 */
public class EnvironmentLayout {

    private static final String LEFT_TITLE = "GRUPPI";
    private static final String RIGHT_TITLE = "OPZIONI";
    private static final double LAYOUT_HEIGHT = 600.0;
    private static final double LAYOUT_WIDTH = 1100.0;
    private static final double SEPARATOR_WIDTH = 10.0;
    private static final double LABEL_FONT_SIZE = 18.0;
    private static final double SIDE_COLUMN_WIDTH = 250.0;
    private static final double TITLE_HEIGHT = 30.0;

    private final BorderPane environment = new BorderPane();

    private final VBox elementVBox = new VBox();
    private final VBox optionVBox = new VBox();
    private final VBox scrollVBox = new VBox();
    private final HBox boardHBox = new HBox();

    private final BoardView boardView;
    private final TabPane leftPane = new TabPane();
    private final ScrollPane rightScroll = new ScrollPane();

    private final Label leftColumnTitle = new Label(LEFT_TITLE);
    private final Label rightColumnTitle = new Label(RIGHT_TITLE);

    private final Separator leftVertical = new Separator(Orientation.VERTICAL);
    private final Separator rightVertical = new Separator(Orientation.VERTICAL);
    private final Separator leftHorizontal = new Separator();
    private final Separator rightHorizontal = new Separator();

    // Riferimento alla classe main dell'applicazione.
    // private MainApp mainApp;

    /**
     * The class constructor. It initializes non-graphic elements and assembles the graphic elements
     * into the actual environment layout.
     * 
     * @param scene
     *         the scene of the graphic interface
     * @param gameModel
     *         the model of the desired game
     * @param controller
     *         the game controller
     */
    public EnvironmentLayout(final Scene scene, final GameModel gameModel, 
                                       final Controller controller) {
        final ViewControllerManagerImpl vcm = new ViewControllerManagerImpl(controller);
        new GroupViewerView(this.leftPane, gameModel.getGroupViewer(), vcm.getSelectionAndAction());

        final Camera camera = new Camera();
        this.boardView = new BoardView(gameModel.getBoard(), vcm, camera);
        this.boardView.initPane();
        vcm.getSelectionAndAction().editSelectionOnBoard(boardView);
        vcm.getDragAndDrop().makeTargetFitDragAndDrop(boardView);
        vcm.getSelectionAndAction().editKeyboard(scene, camera);

        this.leftVertical.setMouseTransparent(true);
        this.leftVertical.setPrefSize(SEPARATOR_WIDTH, LAYOUT_HEIGHT);
        this.leftVertical.setMinWidth(SEPARATOR_WIDTH);
        this.leftVertical.setMaxWidth(SEPARATOR_WIDTH);

        this.rightVertical.setPrefSize(SEPARATOR_WIDTH, LAYOUT_HEIGHT);
        this.rightVertical.setMinWidth(SEPARATOR_WIDTH);
        this.rightVertical.setMaxWidth(SEPARATOR_WIDTH);

        this.leftColumnTitle.setPrefSize(SIDE_COLUMN_WIDTH, TITLE_HEIGHT);
        this.leftColumnTitle.setMinSize(SIDE_COLUMN_WIDTH, TITLE_HEIGHT);
        this.leftColumnTitle.setFont(new Font("Bauhaus 93", LABEL_FONT_SIZE));
        this.leftColumnTitle.setAlignment(Pos.CENTER);

        this.rightColumnTitle.setPrefSize(SIDE_COLUMN_WIDTH, TITLE_HEIGHT);
        this.rightColumnTitle.setMinSize(SIDE_COLUMN_WIDTH, TITLE_HEIGHT);
        this.rightColumnTitle.setFont(new Font("Bauhaus 93", LABEL_FONT_SIZE));
        this.rightColumnTitle.setAlignment(Pos.CENTER);

        this.boardView.getPane().setPrefSize(LAYOUT_HEIGHT - this.leftVertical.getPrefWidth() 
                                     - this.rightVertical.getPrefWidth(), LAYOUT_HEIGHT);
        this.boardView.getPane().setBackground(new Background(new BackgroundFill(Color.WHEAT, null, null)));

        this.rightScroll.setPrefSize(SIDE_COLUMN_WIDTH, LAYOUT_HEIGHT - TITLE_HEIGHT - SEPARATOR_WIDTH);
        this.rightScroll.setContent(scrollVBox);
        this.rightScroll.setFitToWidth(true);
        this.scrollVBox.setFillWidth(true);

        VBox.setVgrow(this.leftPane, Priority.ALWAYS);
        this.elementVBox.setFillWidth(true);
        this.elementVBox.getChildren().addAll(this.leftColumnTitle, this.leftHorizontal, this.leftPane);
        this.elementVBox.setMaxWidth(SIDE_COLUMN_WIDTH);

        VBox.setVgrow(this.rightScroll, Priority.ALWAYS);
        this.optionVBox.setFillWidth(true);
        this.optionVBox.getChildren().addAll(this.rightColumnTitle, this.rightHorizontal, this.rightScroll);
        this.optionVBox.setMaxWidth(SIDE_COLUMN_WIDTH);

        HBox.setHgrow(this.boardView.getPane(), Priority.ALWAYS);
        this.boardHBox.setFillHeight(true);
        this.boardHBox.getChildren().addAll(this.leftVertical, this.boardView.getPane(), this.rightVertical);

        this.environment.setPrefSize(LAYOUT_WIDTH, LAYOUT_HEIGHT);
        this.environment.setLeft(elementVBox);
        this.environment.setRight(optionVBox);
        this.environment.setCenter(boardHBox);
        new GameActionsView(gameModel, controller, this.scrollVBox);
    }

    /**
     * A getter for the main container of this class.
     * 
     * @return the border pane that is the main working environment of the LIT application.
     */
    public BorderPane getEnvironment() {
        return this.environment;
    }

    /**
     * A getter for the width property of the board canvas.
     * 
     * @return the width of the main simulator environment.
     */
    protected double getBoardWidth() {
        return this.boardView.getPane().getWidth();
    }

    /**
     * A getter for the height property of the board canvas.
     * 
     * @return the height of the main simulator environment.
     */
    protected double getBoardHeight() {
        return this.boardView.getPane().getHeight();
    }

}
