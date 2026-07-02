package view.mainmenu;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import controller.file.FileController;
import controller.file.FileControllerImpl;
import enums.GameFont;
import enums.SceneImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import view.JavaFXView;

/**
 * Class for the Main Menu Panel. Implemented in JavaFX. The class extends the
 * JavaFX's class BordePane.
 */
public final class MainMenuPanel extends BorderPane {

    // Panel Magic Numbers.
    private static final double PANEL_PREF_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double PANEL_PREF_HEIGHT = JavaFXView.STAGE_DIMESNION;

    // Top Magic Numbers.
    private static final double TOP_SPACING = JavaFXView.STAGE_DIMESNION / 60.0;
    private static final double TOP_INSET_TOP = JavaFXView.STAGE_DIMESNION / 15.0;
    private static final double TOP_INSET_RIGHT = 0.0;
    private static final double TOP_INSET_BOTTOM = JavaFXView.STAGE_DIMESNION / 17.0;
    private static final double TOP_INSET_LEFT = JavaFXView.STAGE_DIMESNION / 10.0;
    private static final double TOP_FONT_SIZE = JavaFXView.STAGE_DIMESNION / 30.0;
    private static final double STRING_LENGTH_ONE = 1;
    private static final double STRING_LENGTH_TWO = 2;
    private static final double STRING_LENGTH_THREE = 3;
    private static final double STRING_LENGTH_FOUR = 4;
    private static final double STRING_LENGTH_FIVE = 5;
    private static final double STRING_LENGTH_SIX = 6;

    // Center Magic Numbers.
    private static final double CENTER_SPACING = JavaFXView.STAGE_DIMESNION / 60.0;
    private static final double CENTER_INSET_TOP = JavaFXView.STAGE_DIMESNION / 20.0;
    private static final double CENTER_INSET_RIGHT = 0.0;
    private static final double CENTER_INSET_BOTTOM = 0.0;
    private static final double CENTER_INSET_LEFT = JavaFXView.STAGE_DIMESNION / 3.75;
    private static final double MAIN_IMAGE_WIDTH = JavaFXView.STAGE_DIMESNION / 1.3;
    private static final double MAIN_IMAGE_HEIGHT = JavaFXView.STAGE_DIMESNION / 4.0;
    private static final double MENU_ITEM_SPACING = 10.0;
    // Bottom Magic Numbers.
    private static final double BOTTOM_SPACING = JavaFXView.STAGE_DIMESNION / 30.0;
    private static final double BOTTOM_INSET_TOP = JavaFXView.STAGE_DIMESNION / 10.0;
    private static final double BOTTOM_INSET_RIGHT = 0.0;
    private static final double BOTTOM_INSET_BOTTOM = JavaFXView.STAGE_DIMESNION / 20.0;
    private static final double BOTTOM_INSET_LEFT = 0.0;
    private static final double TEXT_COMPANY_FONT_SIZE = JavaFXView.STAGE_DIMESNION / 24.0;
    private static final double TEXT_COPYRIGHT_FONT_SIZE = JavaFXView.STAGE_DIMESNION / 30.0;
    private static final double TEXT_RIGHTS_FONT_SIZE = JavaFXView.STAGE_DIMESNION / 30.0;

    // List of the menu items.
    private List<MainMenuItem> items;
    // The file controller.
    private final FileController fc;
    // The controller of the game.
    private final Controller controller;

    /**
     * Constructor for the main menu panel.
     * 
     * @param controller the controller of the game.
     */
    public MainMenuPanel(final Controller controller) {
        this.controller = controller;
        fc = new FileControllerImpl();
        init();
    }

    /*
     * Method that create the Main Menu from steps.
     */
    private void init() {
        items = new ArrayList<MainMenuItem>();
        setPrefSize(PANEL_PREF_WIDTH, PANEL_PREF_HEIGHT);
        createBackground();
        createTop();
        createCenter();
        createBottom();
    }

    /*
     * Method that creates the background of the Main Menu.
     */
    private void createBackground() {
        final BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        final Background background = new Background(backgroundFill);
        setBackground(background);
    }

    /*
     * Method that creates the top of the Main Menu.
     */
    private void createTop() {
        // Set the layout.
        final VBox vBox = new VBox(TOP_SPACING);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPadding(new Insets(TOP_INSET_TOP, TOP_INSET_RIGHT, TOP_INSET_BOTTOM, TOP_INSET_LEFT));

        // Set the text.
        String myHiScore = Integer.toString(controller.getMyHiScore());
        if (controller.getMyHiScore() == 0) {
            myHiScore = "I-     00 ";
        } else if (myHiScore.length() == STRING_LENGTH_ONE) {
            myHiScore = "I-     0" + myHiScore + " ";
        } else if (myHiScore.length() == STRING_LENGTH_TWO) {
            myHiScore = "I-     " + myHiScore + " ";
        } else if (myHiScore.length() == STRING_LENGTH_THREE) {
            myHiScore = "I-    " + myHiScore + " ";
        } else if (myHiScore.length() == STRING_LENGTH_FOUR) {
            myHiScore = "I-   " + myHiScore + " ";
        } else if (myHiScore.length() == STRING_LENGTH_FIVE) {
            myHiScore = "I-  " + myHiScore + " ";
        } else if (myHiScore.length() == STRING_LENGTH_SIX) {
            myHiScore = "I- " + myHiScore + " ";
        }
        String hiScore = Integer.toString(controller.getHiScore());
        hiScore = " HI- " + hiScore;
        final Text score = new Text(myHiScore + hiScore);
        score.setFill(Color.WHITE);
        score.setFont(fc.getFont(GameFont.PRESS_START));
        score.setStyle("-fx-font-size: " + Double.toString(TOP_FONT_SIZE));

        vBox.getChildren().addAll(score);
        setTop(vBox);
    }

    /*
     * Method that creates the center of the Main Menu.
     */
    private void createCenter() {
        // Set the layout.
        final VBox vBoxCenter = new VBox(CENTER_SPACING);
        vBoxCenter.setAlignment(Pos.CENTER);
        final VBox vBoxMenuItem = new VBox(MENU_ITEM_SPACING);
        vBoxMenuItem.setAlignment(Pos.CENTER);
        vBoxMenuItem
                .setPadding(new Insets(CENTER_INSET_TOP, CENTER_INSET_RIGHT, CENTER_INSET_BOTTOM, CENTER_INSET_LEFT));

        // Set the main image.
        final ImageView ivMainTitle = new ImageView(fc.getSceneImage(SceneImage.MAIN_TITLE));
        ivMainTitle.setFitWidth(MAIN_IMAGE_WIDTH);
        ivMainTitle.setFitHeight(MAIN_IMAGE_HEIGHT);

        // Set the items of the menu.
        final MainMenuItem mi1 = new MainMenuItem("1 PLAYER");
        final MainMenuItem mi2 = new MainMenuItem("2 PLAYERS");
        final MainMenuItem mi3 = new MainMenuItem("CONSTRUCTION");
        items.add(mi1);
        items.add(mi2);
        items.add(mi3);

        vBoxMenuItem.getChildren().addAll(items);
        vBoxCenter.getChildren().addAll(ivMainTitle, vBoxMenuItem);
        setCenter(vBoxCenter);
    }

    /*
     * Method that creates the bottom of the Main Menu.
     */
    private void createBottom() {
        // Set the layout.
        final VBox vBox = new VBox(BOTTOM_SPACING);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(BOTTOM_INSET_TOP, BOTTOM_INSET_RIGHT, BOTTOM_INSET_BOTTOM, BOTTOM_INSET_LEFT));

        // Set the company text.
        final Text company = new Text("namcot");
        company.setFont(fc.getFont(GameFont.NAMCO));
        company.setFill(Color.RED);
        company.setStyle("-fx-font-weight: bold; -fx-font-size: " + Double.toString(TEXT_COMPANY_FONT_SIZE) + ";");

        // Set the copyright text.
        final Text copyright = new Text("© 1980 1985 NAMCO LTD.");
        copyright.setFill(Color.WHITE);
        copyright.setFont(fc.getFont(GameFont.PRESS_START));
        copyright.setStyle("-fx-font-weight: bold; -fx-font-size: " + Double.toString(TEXT_COPYRIGHT_FONT_SIZE) + ";");

        // Set the rights text.
        final Text rights = new Text("ALL RIGHTS RESERVED");
        rights.setFill(Color.WHITE);
        rights.setFont(fc.getFont(GameFont.PRESS_START));
        rights.setStyle("-fx-font-weight: bold; -fx-font-size: " + Double.toString(TEXT_RIGHTS_FONT_SIZE) + ";");

        vBox.getChildren().addAll(company, copyright, rights);
        setBottom(vBox);
    }

    /**
     * Getter method for the list of the menu items.
     * 
     * @return the list of the menu items.
     */
    public List<MainMenuItem> getMenuItems() {
        return items;
    }
}
