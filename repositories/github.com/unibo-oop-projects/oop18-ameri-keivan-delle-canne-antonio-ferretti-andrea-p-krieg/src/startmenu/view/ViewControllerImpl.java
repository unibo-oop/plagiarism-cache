package startmenu.view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import startmenu.controller.MainController;
import util.MenuVariablesUtils;

/**
 * This is the view for the main menu.
 */
public class ViewControllerImpl implements ViewController {

    private static final double SPACING = 15;
    private static final double HEIGHT_DIFFERENCE_MENU = 1.25;

    private int currentItem;
    private VBox menuBox;
    private MainController main;
    private final Stage stage;
    private boolean mainSet;

    /**
     * @param screenDimension the size of the screen
     */
    public ViewControllerImpl(final Pair<Double, Double> screenDimension) {
        this.stage = new Stage();
        this.stage.setHeight(screenDimension.getKey());
        this.stage.setWidth(screenDimension.getValue());
        this.stage.setMinHeight(MenuVariablesUtils.HEIGHT_MIN);
        this.stage.setMinWidth(MenuVariablesUtils.WIDTH_MIN);
        this.stage.centerOnScreen();
        stage.getIcons().add(new Image(MenuVariablesUtils.MENU_ICON));
    }

    /** {@inheritDoc} **/
    @Override
    public void setController(final MainController controller) {
        if (this.mainSet) {
            throw new IllegalStateException();
        }
        this.main = controller;
        this.mainSet = true;
    }

    private Parent createContent() {
        final Pane root = new Pane();
        root.setPrefSize(stage.getWidth(), stage.getHeight());
        final ImageView bg = new ImageView(
                "menu" + MenuVariablesUtils.SEPARATOR + "main" + MenuVariablesUtils.SEPARATOR + "start_background.jpg");
        bg.fitHeightProperty().bind(root.heightProperty());
        bg.fitWidthProperty().bind(root.widthProperty());

        final MenuItem startGame = new MenuItem("Start Game");
        startGame.setOnActivate(() -> this.newGame());

        final MenuItem credits = new MenuItem("Credits");
        credits.setActive(false);
        credits.setOnActivate(() -> this.credits());

        final MenuItem exit = new MenuItem("Exit");
        exit.setActive(false);
        exit.setOnActivate(() -> System.exit(0));

        final MenuItem mode = new MenuItem("Game Mode");
        mode.setActive(false);
        mode.setOnActivate(() -> this.chooseGameMode());

        this.menuBox = new VBox(10, startGame, mode, credits, exit);
        this.menuBox.setAlignment(Pos.CENTER);
        this.menuBox.prefHeightProperty().bind(root.heightProperty().multiply(HEIGHT_DIFFERENCE_MENU));
        this.menuBox.prefWidthProperty().bind(root.widthProperty());

        this.getMenuItem(0).setActive(true);

        root.getChildren().add(bg);
        root.getChildren().add(menuBox);
        return root;
    }

    private MenuItem getMenuItem(final int index) {
        return (MenuItem) menuBox.getChildren().get(index);
    }

    private static class MenuItem extends HBox {
        private final Text text;
        private Runnable script;

        MenuItem(final String name) {
            super(SPACING);
            setAlignment(Pos.CENTER);

            text = new Text(name);
            text.setFont(MenuVariablesUtils.GREATER_FONT);

            getChildren().add(text);
        }

        public void setActive(final boolean b) {
            text.setFill(b ? Color.RED : Color.GREY);
        }

        public void setOnActivate(final Runnable r) {
            script = r;
        }

        public void activate() {
            if (script != null) {
                script.run();
            }
        }
    }

    /** {@inheritDoc} **/
    @Override
    public void draw() {
        currentItem = 0;
        final Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.UP) && currentItem > 0) {
                getMenuItem(currentItem).setActive(false);
                getMenuItem(--currentItem).setActive(true);
            }

            if (e.getCode().equals(KeyCode.DOWN) && currentItem < this.menuBox.getChildren().size() - 1) {
                getMenuItem(currentItem).setActive(false);
                getMenuItem(++currentItem).setActive(true);
            }

            if (e.getCode().equals(KeyCode.ENTER)) {
                getMenuItem(currentItem).activate();
            }
        });
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    /** {@inheritDoc} **/
    @Override
    public void newGame() {
        this.main.callGameMenu(this.stage.getHeight(), this.stage.getWidth());
        this.stage.close();
    }

    /** {@inheritDoc} **/
    @Override
    public void chooseGameMode() {
        this.gameModeDialog();
    }

    private void gameModeDialog() {
        final Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Choose Game Mode");
        alert.setHeaderText(null);
        final DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getButtonTypes().add(ButtonType.CANCEL);
        dialogPane.setContent(gameModeDialogContent(alert));
        ((Stage) dialogPane.getScene().getWindow()).getIcons().add(new Image(MenuVariablesUtils.MENU_ICON));
        alert.showAndWait();
    }

    private GridPane gameModeDialogContent(final Alert alert) {
        final GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(SPACING);
        main.getGameModes().forEach(mode -> {
            final int col = this.main.getGameModes().indexOf(mode);
            final Label text = new Label(mode.getDescription());
            text.setFont(MenuVariablesUtils.MEDIUM_FONT);
            final Button button = new Button("Choose");
            button.setOnAction(e -> {
                this.main.setGameMode(col);
                alert.close();
            });
            gridPane.add(text, 0, col);
            gridPane.add(button, 1, col);
        });
        return gridPane;
    }

    /** {@inheritDoc} **/
    @Override
    public void credits() {
        final Scene credScene;
        final Pane cred = new Pane();

        final ImageView creditsImage = new ImageView("menu" + MenuVariablesUtils.SEPARATOR + "main"
                + MenuVariablesUtils.SEPARATOR + "credits_background.jpg");

        cred.getChildren().add(creditsImage);

        credScene = new Scene(cred);
        credScene.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ESCAPE)) {
                this.draw();
            }
        });

        this.stage.setScene(credScene);
        this.stage.setHeight(stage.getHeight());
        this.stage.setWidth(stage.getWidth());
        creditsImage.fitHeightProperty().bind(credScene.heightProperty());
        creditsImage.fitWidthProperty().bind(credScene.widthProperty());
        this.stage.centerOnScreen();
        this.stage.show();

    }

    /** {@inheritDoc} **/
    @Override
    public Stage getPrimaryStage() {
        return this.stage;
    }

}
