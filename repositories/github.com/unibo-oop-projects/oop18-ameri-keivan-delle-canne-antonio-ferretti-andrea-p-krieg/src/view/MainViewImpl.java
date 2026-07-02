package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import util.MenuVariablesUtils;

/**
 * at each update, updates the user interface according to the actual state of
 * the view elements.
 */
public class MainViewImpl implements MainView {

    private static final double REDUCTION_OF_SIDE_MENU = 5;

    private final Stage stage;
    private final ViewElement topLeft;
    private final ViewElement bottomLeft;
    private final ViewElement center;

    private final BorderPane root;
    private final VBox side;


    /**
     * @param screenDimension the screen dimension
     * 
     * @param upLeft          the top side menu
     * 
     * @param downLeft        the down side menu
     * 
     * @param center          the center of the stage (the map)
     */
    public MainViewImpl(final Pair<Double, Double> screenDimension, final ViewElement upLeft,
            final ViewElement downLeft, final ViewElement center) {
        final AnchorPane anchor = new AnchorPane();
        this.root = new BorderPane();
        this.side = new VBox();
        this.stage = new Stage();
        this.stage.setHeight(screenDimension.getKey());
        this.stage.setWidth(screenDimension.getValue());
        this.stage.setMinHeight(MenuVariablesUtils.HEIGHT_MIN);
        this.stage.setMinWidth(MenuVariablesUtils.WIDTH_MIN);
        this.center = center;
        this.topLeft = upLeft;
        this.bottomLeft = downLeft;

        final Scene scene = new Scene(anchor);
        anchor.prefHeightProperty().bind(scene.heightProperty());
        anchor.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(anchor.heightProperty());
        root.prefWidthProperty().bind(anchor.widthProperty());
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        anchor.getChildren().clear();
        anchor.getChildren().add(root);
        updateSide();

        stage.getIcons().add(new Image(MenuVariablesUtils.MENU_ICON));
        this.stage.setScene(scene);
    }

    /** @{inheritDoc} **/
    @Override
    public void update() {

        updateCenter();
        this.stage.show();
    }

    private void updateSide() {
        final VBox top = (VBox) this.topLeft.get();
        final VBox bottom = (VBox) this.bottomLeft.get();

        top.setMinHeight(side.getHeight() / 2);
        bottom.setMinHeight(side.getHeight() / 2);

        top.prefHeightProperty().bind(side.heightProperty().divide(2));
        top.prefWidthProperty().bind(side.widthProperty());

        bottom.prefHeightProperty().bind(side.heightProperty().divide(2));
        bottom.prefWidthProperty().bind(side.widthProperty());

        side.getChildren().addAll(top, bottom);

        side.prefHeightProperty().bind(root.heightProperty());
        side.prefWidthProperty().bind(root.widthProperty().divide(REDUCTION_OF_SIDE_MENU));

        this.root.setLeft(side);
    }

    private void updateCenter() {
        final Region center = this.center.get();
        final AnchorPane anchoras = new AnchorPane();
        AnchorPane.setBottomAnchor(center, 0.0);
        AnchorPane.setLeftAnchor(center, 0.0);
        AnchorPane.setRightAnchor(center, 0.0);
        AnchorPane.setTopAnchor(center, 0.0);
        anchoras.getChildren().add(center);

        this.root.setCenter(anchoras);
    }

    /** @{inheritDoc} **/
    @Override
    public void exit() {
        stage.close();
    }

}
