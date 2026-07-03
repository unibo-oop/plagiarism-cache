package breakout.view.levels;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import breakout.controller.Controller;
import breakout.controller.levels.LevelManager;
import breakout.model.entities.BrickStructure;
import breakout.model.entities.BrickType;
import breakout.model.levels.Grid;
import breakout.model.levels.LevelBuilder;
import breakout.model.levels.LevelImpl;
import breakout.view.MainMenu;
import breakout.view.graphics.Backgrounds;
import breakout.view.graphics.Colors;
import breakout.view.graphics.Fonts;
import breakout.view.graphics.GraphicStyle;
import breakout.view.graphics.Images;
import breakout.view.graphics.Images.ColoredEntities;
import breakout.view.utils.TitledChoiceBox;
import breakout.view.utils.TitledTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * The scene where utent can create a new level.
 */
public class LevelEditor extends Scene {

    private static final Font LOGO_FONT = Fonts.ADVANCED_FONT.get(115);
    private static final Font BUTTON_FONT = Fonts.PIXEL_FONT.get(16);
    private static final double CHOICE_BOX_WIDTH = 132;
    private static final double CHOICE_BOX_HEIGHT = 20;
    private static final double RIGHT_BOX_WIDTH = 25;
    private static final double LEFT_BOX_WIDTH = 156;
    private static final double BOTTOM_PANE_HEIGHT = 115;
    private static final double BOTTOM_BUTTON_INSETS = 17;
    private static final double BRICK_HEIGHT = 24;
    private static final int BRICK_PER_ROW = 20;
    private static final int ROWS = 12;
    private static final int VBOX_SPACING = 45;
    private static final int MAX_LENGHT = 7;
    private static final int BUTTON_SPACING = 50;

    private final Stage mainStage;
    private final LevelManager l = new LevelManager();
    private final BorderPane mainPane = new BorderPane();
    private boolean checkSave = true;
    private final Map<Pane, Pair<Integer, Integer>> elenco = new HashMap<>();
    private final Grid<Pane> elenco2 = new Grid<>(ROWS, BRICK_PER_ROW);
    private Grid<Pair<BrickType, Colors>> listaBrick = new Grid<>(ROWS, BRICK_PER_ROW);

    private final GraphicStyle sceneStyle;

    private final Pane topBox = new Pane();
    private final Text breakout = new Text("LEVEL EDITOR");

    private final TitledChoiceBox<BrickStructure> bricks = new TitledChoiceBox<>("Brick Type", new ChoiceBox<>(),
            CHOICE_BOX_WIDTH, CHOICE_BOX_HEIGHT);
    private final TitledChoiceBox<Colors> colors = new TitledChoiceBox<>("Colors ", new ChoiceBox<>(), CHOICE_BOX_WIDTH,
            CHOICE_BOX_HEIGHT);
    private final TitledChoiceBox<Backgrounds> background = new TitledChoiceBox<>("Background", new ChoiceBox<>(),
            CHOICE_BOX_WIDTH, CHOICE_BOX_HEIGHT);
    private final TitledTextField levelName = new TitledTextField("Level Name", new TextField(), CHOICE_BOX_WIDTH,
            CHOICE_BOX_HEIGHT);
    private final TitledTextField spawnProb = new TitledTextField("Spawn\nProbability\n%", new TextField(),
            CHOICE_BOX_WIDTH, CHOICE_BOX_HEIGHT);
    private final VBox leftVBox = new VBox(bricks, colors, background, levelName, spawnProb);

    private final GridPane griglia = new GridPane(); // matrice di bottoni
    private final BorderPane centralBox = new BorderPane();

    private final Button save = new Button("Save"); // bottone salvataggio
    private final Button delete = new Button("Delete"); // bottone eliminazione
    private final Button reset = new Button("Reset"); // bottone reset
    private final Button load = new Button("Load"); // bottone carica
    private final Button exit = new Button("Exit"); // bottone chiudia
    private final HBox buttons = new HBox(reset, save, load, delete, exit);

    private final Pane rightPane = new Pane();

    /**
     * @param mainStage
     *            the stage where the scene is called
     * @param style
     *            the style of the scene
     */
    public LevelEditor(final Stage mainStage, final GraphicStyle style) {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());
        this.mainStage = mainStage;
        this.sceneStyle = style;
        this.rightPane.setPrefWidth(RIGHT_BOX_WIDTH);
        this.mainPane.setRight(rightPane);
        this.buttons.setPrefSize(this.mainStage.getWidth(), BOTTOM_PANE_HEIGHT);
        this.setLeftBox();
        this.mainPane.setLeft(leftVBox);
        this.setBottomBox();
        this.mainPane.setBottom(buttons);
        this.setTopBox();
        this.mainPane.setTop(topBox);
        this.setCenterBox();
        this.mainPane.setCenter(centralBox);
        this.mainPane.setId("menu_image_level");
        this.setRoot(mainPane);
        this.getStylesheets().add("stylesheet.css");
    }

    /**
     * Set the left part in the scene.
     */
    private void setLeftBox() {
        this.leftVBox.setSpacing(VBOX_SPACING);
        this.leftVBox.setPadding(new Insets(10));
        this.leftVBox.setPrefWidth(LEFT_BOX_WIDTH);

        this.leftVBox.getChildren().stream().filter(child -> child instanceof TitledChoiceBox<?>).forEach(box -> {
            ((TitledChoiceBox<?>) box).setVBoxSpacing(8);
            ((TitledChoiceBox<?>) box).setTextStyle(BUTTON_FONT, sceneStyle.getTextStyle());
        });
        this.leftVBox.getChildren().stream().filter(child -> child instanceof TitledTextField).forEach(box -> {
            ((TitledTextField) box).setVBoxSpacing(8);
            ((TitledTextField) box).setTextStyle(BUTTON_FONT, sceneStyle.getTextStyle());
        });

        ((TextField) levelName.getTextField()).setOnKeyPressed(e -> {
            if (((TextField) levelName.getTextField()).getText().length() >= MAX_LENGHT) {
                ((TextField) levelName.getTextField()).deletePreviousChar();
            }
        });

        this.bricks.getChoiceBox().getItems().addAll(Arrays.asList(BrickStructure.values()));
        this.bricks.getChoiceBox().setOnAction(e -> {
            this.colors.getChoiceBox().getItems().clear();
            if (this.bricks.getChoiceBox().getValue().equals(BrickStructure.UNBREAKABLE)) {
                this.colors.getChoiceBox().setValue(Colors.SILVER);
                this.colors.getChoiceBox().getItems().addAll(Colors.ADVANCED_UNBREAKABLE);
            } else if (this.bricks.getChoiceBox().getValue().equals(BrickStructure.SIMPLE)) {
                this.colors.getChoiceBox().setValue(Colors.BLACK);
                this.colors.getChoiceBox().getItems().addAll(Colors.ADVANCED_SIMPLE);
            } else if (this.bricks.getChoiceBox().getValue().equals(BrickStructure.HARD)) {
                this.colors.getChoiceBox().setValue(Colors.BLACK);
                this.colors.getChoiceBox().getItems().addAll(Colors.ADVANCED_HARD);
            }
        });

        this.background.getChoiceBox().getItems().addAll(Arrays.asList(Backgrounds.values()));
        this.background.getChoiceBox().setValue(Backgrounds.EARTH);
        this.background.getChoiceBox().setOnAction(e -> {
            this.centralBox.setBackground(new Background(new BackgroundImage(
                    Images.getImages().getBackgroundImage(this.background.getChoiceBox().getValue()),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));
        });

    }

    /**
     * Set the bottom part in the scene.
     */
    private void setBottomBox() {
        this.buttons.setAlignment(Pos.TOP_RIGHT);
        this.buttons.setPadding(new Insets(BOTTOM_BUTTON_INSETS));
        this.buttons.setSpacing(this.mainStage.getWidth() / BUTTON_SPACING);
        this.buttons.getChildren().stream().filter(c -> c instanceof Button).forEach(button -> {
            ((Button) button).setPrefSize(CHOICE_BOX_WIDTH, CHOICE_BOX_HEIGHT * 2);
            ((Button) button).setFont(BUTTON_FONT);
        });
        this.exit.setOnMouseClicked(e -> {
            if (!listaBrick.isEmpty() && !checkSave) {
                final Alert alert = new Alert(AlertType.NONE, "All unsaved data will be lost, are you sure?",
                        new ButtonType("Ok", ButtonData.OK_DONE), new ButtonType("Return", ButtonData.CANCEL_CLOSE));
                if (alert.showAndWait().get().getButtonData() == ButtonData.OK_DONE) {
                    mainStage.setScene(new MainMenu(mainStage));
                }

                alert.close();
            } else {
                mainStage.setScene(new MainMenu(mainStage));
            }
        });

        /**
         * Restore the current level.
         */
        this.reset.setOnMouseClicked(e -> {
            if (!listaBrick.isEmpty() && !checkSave) {
                final Alert alert = new Alert(AlertType.NONE, "All unsaved data will be lost, are you sure?",
                        new ButtonType("Ok", ButtonData.OK_DONE), new ButtonType("Return", ButtonData.CANCEL_CLOSE));
                if (alert.showAndWait().get().getButtonData() == ButtonData.OK_DONE) {
                    refresh();
                }
                alert.close();
            } else {
                this.listaBrick.clear();
                this.elenco.forEach((x, y) -> {
                    ((ImageView) x.getChildren().get(0)).setImage(null);
                });
                checkSave = true;
            }
        });

        /**
         * Save the create Level.
         */
        this.save.setOnMouseClicked(e -> {
            try {
                final LevelImpl level = new LevelBuilder().list(listaBrick)
                                                          .name(this.levelName.getTextField().getText())
                                                          .background(this.background.getChoiceBox().getValue())
                                                          .spawnProb(Integer.parseInt(
                                                                  this.spawnProb.getTextField().getText()))
                                                          .build();
                this.checkSave = true;
                l.saveLevel(level);
                final Alert alert = new Alert(AlertType.NONE,
                        "Level " + this.levelName.getTextField().getText() + " is saved",
                        new ButtonType("Ok", ButtonData.OK_DONE));
                alert.showAndWait().ifPresent(button -> {

                    alert.close();
                });
            } catch (Exception exception) {
                final Alert alert = new Alert(AlertType.NONE, "Invalid Argument",
                        new ButtonType("Ok", ButtonData.OK_DONE));
                alert.showAndWait().ifPresent(button -> {

                    alert.close();
                });
            }
        });

        /**
         * Load a saved Level.
         */
        this.load.setOnMouseClicked(e -> {
            final List<String> levelNames = Controller.get().getAvailableLevels().stream().map(level -> level.getName())
                                                      .collect(Collectors.toList());

            if (!levelNames.isEmpty()) {
                final ChoiceDialog<String> loadlLevel = new ChoiceDialog<>(levelNames.get(0), levelNames);
                loadlLevel.setGraphic(null);
                final Optional<String> fileName = loadlLevel.showAndWait();
                if (fileName.isPresent()) {
                    try {
                        final LevelImpl level = (LevelImpl) l.loadLevel(fileName.get());
                        refresh();
                        this.listaBrick = level.getGrid();

                        this.listaBrick.getPresent().forEach((k, v) -> {
                            selectImage(
                                    (ImageView) this.elenco2.getElement(k.getKey(), k.getValue()).getChildren().get(0),
                                    v.getKey().getStructure(), v.getValue());
                        });
                        this.background.getChoiceBox().setValue(level.getBackground());
                        this.centralBox.setBackground(new Background(new BackgroundImage(
                                Images.getImages().getBackgroundImage(this.background.getChoiceBox().getValue()),
                                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT)));
                        this.levelName.getTextField().setText(level.getName());
                        this.spawnProb.getTextField().setText(String.valueOf(level.getSpawnProb()));
                    } catch (IOException | ClassNotFoundException e1) {
                        final Alert alert = new Alert(AlertType.ERROR, "I/O Error", ButtonType.CLOSE);
                        alert.showAndWait();
                    }
                }
            } else {
                final Alert alert = new Alert(AlertType.INFORMATION, "No levels to load", ButtonType.CLOSE);
                alert.showAndWait();
            }

        });

        /**
         * Delete a saved Level.
         */
        this.delete.setOnMouseClicked(e -> {
            final List<String> levelNames = Controller.get().getAvailableLevels().stream().map(level -> level.getName())
                                                      .collect(Collectors.toList());
            if (!levelNames.isEmpty()) {
                final ChoiceDialog<String> loadlLevel = new ChoiceDialog<>(levelNames.get(0), levelNames);
                loadlLevel.setGraphic(null);
                final Optional<String> fileName = loadlLevel.showAndWait();
                if (fileName.isPresent()) {
                    l.deleteLevel(fileName.get());
                }
            } else {
                final Alert alert = new Alert(AlertType.INFORMATION, "No levels to delete", ButtonType.CLOSE);
                alert.showAndWait();
            }

        });
    }

    /**
     * Set the top part in the scene.
     */
    private void setTopBox() {
        this.topBox.getChildren().add(breakout);
        this.breakout.setFont(LOGO_FONT);
        this.breakout.setId("logo");
        this.breakout.applyCss();
        this.breakout.relocate(this.mainStage.getWidth() / 2 - this.breakout.getBoundsInLocal().getWidth() / 2, 0);
    }

    /**
     * Set the middle part in the scene.
     */
    private void setCenterBox() {
        this.centralBox.setStyle(
                "-fx-background-image: url(\"Images/Backgrounds/earth.jpg\");-fx-background-size: stretch;-fx-border-color:white");
        this.centralBox.setTop(griglia);
        this.griglia.setPrefSize(this.mainStage.getWidth() - LEFT_BOX_WIDTH - RIGHT_BOX_WIDTH,
                this.mainStage.getHeight() - this.breakout.getBoundsInLocal().getHeight() - BOTTOM_PANE_HEIGHT);
        populateGrid();
    }

    /**
     * Put all image views in the grid.
     */
    private void populateGrid() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < BRICK_PER_ROW; j++) {
                final Pair<Integer, Integer> x = new Pair<>(i, j);
                final ImageView b = new ImageView();
                b.setFitWidth(this.griglia.getPrefWidth() / BRICK_PER_ROW);
                b.setFitHeight(BRICK_HEIGHT);
                final Pane imagePane = new Pane(b);
                imagePane.setOnMouseClicked(e -> {
                    if (e.getButton().equals(MouseButton.PRIMARY)) {
                        this.checkSave = false;
                        selectImage(b, this.bricks.getChoiceBox().getValue(), this.colors.getChoiceBox().getValue());
                        final Pair<BrickType, Colors> pair = new Pair<>(
                                this.toBrickType(this.bricks.getChoiceBox().getValue()),
                                colors.getChoiceBox().getValue());
                        if (colors.getChoiceBox().getValue() != null) {
                            listaBrick.add(elenco.get(imagePane).getKey(), elenco.get(imagePane).getValue(), pair);
                        }
                    } else if (e.getButton().equals(MouseButton.SECONDARY)) {
                        listaBrick.removeAt(elenco.get(imagePane).getKey(), elenco.get(imagePane).getValue());
                        b.setImage(null);
                        if (listaBrick.isEmpty()) {
                            this.checkSave = true;
                        }
                    }
                });
                imagePane.setOnMouseMoved(e -> {
                    if (e.isControlDown()) {
                        this.checkSave = false;
                        selectImage(b, this.bricks.getChoiceBox().getValue(), this.colors.getChoiceBox().getValue());
                        final Pair<BrickType, Colors> pair = new Pair<>(
                                this.toBrickType(this.bricks.getChoiceBox().getValue()),
                                colors.getChoiceBox().getValue());
                        if (colors.getChoiceBox().getValue() != null) {
                            listaBrick.add(elenco.get(imagePane).getKey(), elenco.get(imagePane).getValue(), pair);
                        }
                    } else if (e.isAltDown()) {
                        listaBrick.removeAt(elenco.get(imagePane).getKey(), elenco.get(imagePane).getValue());
                        b.setImage(null);
                        if (listaBrick.isEmpty()) {
                            this.checkSave = true;
                        }
                    }
                });
                imagePane.setStyle("-fx-border-color:white");
                elenco.put(imagePane, x);
                elenco2.add(x.getKey(), x.getValue(), imagePane);
                this.griglia.add(imagePane, j, i);
            }

        }
    }

    /**
     * Change the image in the image view.
     * 
     * @param b
     *            the image view to change
     * @param brick
     *            the type of the image
     * @param color
     *            the color of the image
     */
    private void selectImage(final ImageView b, final BrickStructure brick, final Colors color) {
        if (brick == BrickStructure.SIMPLE) {
            b.setImage(Images.getImages().getColoredImage(ColoredEntities.SIMPLE_BRICKS, color));
        } else if (brick == BrickStructure.HARD) {
            b.setImage(Images.getImages().getColoredImage(ColoredEntities.HARD_BRICKS, color));
        } else if (brick == BrickStructure.UNBREAKABLE) {
            b.setImage(Images.getImages().getColoredImage(ColoredEntities.UNBREAKABLE_BRICKS, color));
        }
    }

    /**
     * Change the name of BrickType.
     */
    private BrickType toBrickType(final BrickStructure structure) {
        BrickType brickType = null;
        switch (structure) {
        case SIMPLE:
            brickType = BrickType.SIMPLE_ADVANCED;
            break;
        case HARD:
            brickType = BrickType.HARD_ADVANCED;
            break;
        case UNBREAKABLE:
            brickType = BrickType.UNBREAKABLE_ADVANCED;
            break;
        default:
            break;
        }
        return brickType;
    }

    /**
     * Clear all the grid.
     */
    private void refresh() {
        this.listaBrick.clear();
        this.elenco.forEach((x, y) -> {
            ((ImageView) x.getChildren().get(0)).setImage(null);
        });
        checkSave = true;
    }

}
