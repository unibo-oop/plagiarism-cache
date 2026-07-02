package view.gamegui;

import controller.file.FileController;
import controller.file.FileControllerImpl;
import enums.SceneImage;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import view.JavaFXView;

/**
 * Class that drawn the icons of the enemies to be spawned. Extends the JavaFX's
 * class GridPane.
 */
public final class EnemyIconsPanel extends GridPane {

    // Icons panel magic numbers.
    private static final double IMAGE_WIDTH = JavaFXView.STAGE_DIMESNION / 27.0;
    private static final double IMAGE_HEIGHT = JavaFXView.STAGE_DIMESNION / 27.0;
    private static final double PANEL_H_GAP = 5.0;
    private static final double PANEL_V_GAP = 1.0;

    // The file controller.
    private final FileController fc;

    /**
     * Constructor method.
     */
    public EnemyIconsPanel() {
        fc = new FileControllerImpl();
        init();
    }

    /*
     * Method that initialize the panel.
     */
    private void init() {
        createBackground();
        setHgap(PANEL_H_GAP);
        setVgap(PANEL_V_GAP);
    }

    /*
     * This method set the background.
     */
    private void createBackground() {
        final BackgroundFill backgroundFill = new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY);
        final Background background = new Background(backgroundFill);
        setBackground(background);
    }

    /**
     * Method that draw the icons in the grid.
     * 
     * @param nElements number of icons to drawn.
     */
    public void drawIcons(final int nElements) {
        getChildren().clear();
        int missingElements = nElements;
        final int columns = 2;
        final int raws = (nElements % 2 == 0) ? (nElements / 2) : (nElements / 2 + 1);
        for (int i = 0; i < raws; i++) {
            for (int j = 0; j < columns; j++) {
                if (missingElements > 0) {
                    final ImageView iv = new ImageView(fc.getSceneImage(SceneImage.ENEMY_ICON));
                    iv.setFitWidth(IMAGE_WIDTH);
                    iv.setFitHeight(IMAGE_HEIGHT);
                    add(iv, j, i);
                    missingElements--;
                }
            }
        }
    }

}
