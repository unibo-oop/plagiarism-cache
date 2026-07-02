package it.tbt.view.javafx;

import it.tbt.commons.resourceloader.ImageLoader;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.model.menu.api.MenuButton;
import it.tbt.model.menu.api.MenuItem;
import it.tbt.model.menu.api.MenuSelect;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * The {@code JavaFxMenuView} class represents a JavaFX implementation of the menu view.
 * It extends the {@code AbstractJavaFxView} class and implements the {@code GameView} interface.
 */
public class JavaFxMenuView extends AbstractJavaFxView {

    private final MenuState main;
    private final Background bg;

    /**
     * Creates a new instance of {@code JavaFxMenuView} with the specified menu controller, stage, scene, and menu state.
     *
     * @param menuController the menu controller
     * @param stage          the stage
     * @param scene          the scene
     * @param menuState      the menu state
     */
    public JavaFxMenuView(final ViewController menuController, final Stage stage, final Scene scene, final MenuState menuState) {
            super(menuController, stage, scene);
            this.main = menuState;
            this.bg = new Background(
                new BackgroundImage(
                        new Image(ImageLoader.getInstance().getFilePath(menuState.getClass())),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(1.0, 1.0, true, true, false, false)));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        Platform.runLater(() -> {
            final StackPane root = new StackPane();
            root.getChildren().clear();
            final VBox vbox = new VBox();
            final Label title = new Label(main.getTitle());
            title.setStyle("-fx-text-fill: white; -fx-font-size: 25px;");
            vbox.getChildren().add(title);
            int count = 0;
            //System.out.println(main.getFocus());
            for (final MenuItem item
                    : main.getItems()) {
                if (item instanceof MenuButton) {
                    final Button button = new Button(item.getText());
                    final Font buttonFont = Font.font("Arial", FontWeight.BOLD, 16);
                    if (count == main.getFocus()) {
                        button.setStyle("-fx-background-color: lightblue;");
                    }
                    button.setOnAction((event) -> {

                    });
                    button.setFont(buttonFont);
                    button.setFocusTraversable(false);
                    button.setMinWidth(vbox.getPrefWidth());
                    vbox.getChildren().add(button);
                } else if (item instanceof MenuSelect) {
                    final Label label = new Label(item.getText());
                    label.setFocusTraversable(false);
                    final Button button = new Button("<      " + ((MenuSelect) item).getLabel() + "      >");
                    final Font buttonFont = Font.font("Arial", FontWeight.BOLD, 16);
                    if (count == main.getFocus()) {
                        button.setStyle("-fx-background-color: lightblue;");
                    }
                    button.setFont(buttonFont);
                    button.setFocusTraversable(false);
                    button.setMinWidth(vbox.getPrefWidth());
                    vbox.getChildren().addAll(label, button);

                }
                count++;
            }
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);
            root.getChildren().add(vbox);
            root.setAlignment(Pos.CENTER);
            root.setBackground(this.bg);
            this.getScene().setRoot(root);

        });
    }
}
