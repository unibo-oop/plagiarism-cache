package view.frames;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The first view to be shown where you can choose between the login and the
 * ranking page.
 */
public class DeathFrame extends AbstractViewFrame {
    private static final String RESTART = "RESTART";
    private static final String TOMBSTONEURL = DeathFrame.class.getClassLoader().getResource("tombstone.png").toExternalForm();
    private static final double RESTARTBUTTONSIZE = 7;
    private static final double NAMEFONTSIZE = 5;
    private static final double TOMBSTONESIZE = 500;
    private TilePane mainPane = new TilePane();

    @Override
    public void start(final Stage newPrimaryStage) throws Exception {
        setStage(newPrimaryStage);
        setFrame();
        setScene(mainPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        setExitOperation();
    }

    @Override
    public void setFrame() {
        Button restart = new Button(RESTART);
        restart.setId("brickRedButton");
        restart.setStyle("-fx-font-size: " + RESTARTBUTTONSIZE + "em;");
        ImageView tomb = new ImageView(new Image(TOMBSTONEURL));
        tomb.setFitHeight(TOMBSTONESIZE);
        tomb.setFitWidth(TOMBSTONESIZE);
        VBox box = new VBox();
        box.getChildren().addAll(tomb, restart);
        StackPane stack = new StackPane();
        stack.setAlignment(Pos.CENTER);
        Text name = new Text(getController().getCharacterName());
        name.setStyle("-fx-font-size: " + NAMEFONTSIZE + "em;");
        stack.getChildren().addAll(tomb, name);
        mainPane.setId("defaultBackGroundColor");
        mainPane.getChildren().addAll(stack, restart);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.getStylesheets().add(DeathFrame.class.getClassLoader().getResource("application.css").toExternalForm());

        /*
         * Listeners
         */
        restart.setOnAction(e -> {
            clearStage();
            getController().restartGame();
        });
    }

    @Override
    public void clearStage() {
        getStage().getScene().getWindow().hide();
        getStage().close();
        getStage().setScene(null);
        mainPane.getChildren().clear();
    }
}
