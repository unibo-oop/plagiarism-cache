package it.tbt.view.javafx;

import it.tbt.commons.customtypes.ItemPair;
import it.tbt.commons.resourceloader.ImageLoader;
import it.tbt.controller.modelmanager.InventoryPhase;
import it.tbt.controller.modelmanager.InventoryState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.items.Item;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;

import java.util.Map;

/**
 * The {@code JavaFxInventoryView} class represents a JavaFX implementation of the inventory view.
 * It extends the {@code AbstractJavaFxView} class and implements the {@code GameView} interface.
 */
public class JavaFxInventoryView extends AbstractJavaFxView {

    private static final double BORDER_SCALE = 25;
    private static final double COLUMN_WIDTH = 100.0 / 3.0;
    private final double viewWidth;
    private final double viewHeight;

    private final InventoryState inventoryState;
    private final Background bg;
    /**
     * Creates a new instance of {@code JavaFxInventoryView} with the specified inventory
     * controller, stage, scene, and inventory state.
     *
     * @param inventoryController the inventory controller
     * @param stage               the stage
     * @param scene               the scene
     * @param inventoryState      the inventory state
     */
    protected JavaFxInventoryView(final ViewController inventoryController, final Stage stage,
                                  final Scene scene, final InventoryState inventoryState) {
        super(inventoryController, stage, scene);
        this.inventoryState = inventoryState;
        viewWidth = scene.getWidth() - (scene.getWidth() / JavaFxInventoryView.BORDER_SCALE);
        viewHeight = scene.getHeight() - (scene.getHeight() / JavaFxInventoryView.BORDER_SCALE);
        this.bg = new Background(
                new BackgroundImage(
                        new Image(ImageLoader.getInstance().getFilePath(inventoryState.getClass())),
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
            final VBox root = new VBox();


            root.getChildren().clear();

            int counter = 0;
            final VBox inventoryList = new VBox();
            for (final Map.Entry<Item, Integer> x
                    : inventoryState.getInventory().entrySet()) {
                final Label label = new Label(new ItemPair(x.getKey(), x.getValue()).toString());
                if (inventoryState.getPhase() == InventoryPhase.INVENTORY
                        && inventoryState.getItemSelected() == -1
                        && counter == inventoryState.getItemFocus()) {
                    label.setStyle("-fx-background-color: yellow;");
                }
                if (inventoryState.getPhase() == InventoryPhase.INVENTORY && inventoryState.getItemSelected() != -1) {
                    if (counter == inventoryState.getItemSelected()) {
                        label.setStyle("-fx-background-color: lightblue;");
                    }
                    if (counter == inventoryState.getItemFocus()) {
                        label.setStyle("-fx-background-color: yellow;");
                    }
                }
                inventoryList.getChildren().add(label);
                counter++;
            }
            if (inventoryList.getChildren().size() == 0) {
                final Label label = new Label("Inventario vuoto");
                inventoryList.getChildren().add(label);
            }

            // Create a label for the inventory title
            final Label inventoryTitle = new Label("Inventory");
            inventoryTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            inventoryList.setFocusTraversable(false);

            // Create a VBox for the inventory
            final VBox inventoryBox = new VBox(10, inventoryTitle, inventoryList);
            inventoryBox.setPadding(new Insets(10));

            final VBox memberBox = new VBox();
            final Label memberTitle = new Label("Member details");
            memberTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            memberTitle.setFocusTraversable(false);
            memberBox.getChildren().add(memberTitle);
            // Create party member list
            final VBox partyList = new VBox();
            counter = 0;
            for (final Ally ally
                    : this.inventoryState.getPartyMembers()) {
                final Label label = new Label(ally.getName());
                if (counter < this.inventoryState.getPartySize()) {
                    label.setStyle("-fx-border-color: red;");
                }
                if (inventoryState.getPhase() == InventoryPhase.MEMBERS
                        && inventoryState.getAllySelected() != -1
                        && counter == inventoryState.getAllySelected()) {
                    label.setStyle(label.getStyle() + "-fx-background-color: lightblue;");
                }
                if ((inventoryState.getPhase() == InventoryPhase.INVENTORY && inventoryState.getItemSelected() != -1
                        || inventoryState.getPhase() == InventoryPhase.MEMBERS && inventoryState.getAllySelected() == -1
                        || inventoryState.getPhase() == InventoryPhase.MEMBERS && inventoryState.getAllySelected() != -1)
                        && counter == inventoryState.getAllyFocused()) {
                    label.setStyle(label.getStyle() + "-fx-background-color: yellow;");
                    memberBox.getChildren().addAll(new Label("Name: " + ally.getName()),
                                                    new Label("HP: " + ally.getHealth() + "/" + ally.getMaxHealth()),
                                                    new Label("Status: " + ally.getStatuses().toString()),
                                                    new Label("Skill: "
                                                            + ally.getSkills().get(0).getAttackMultiplier()),
                                                    new Label("Attack: " + ally.getAttack()),
                                                    new Label("Speed: " + ally.getSpeed()),
                                                    new Label("Weapon: "
                                                            + (ally.getWeapon().isPresent()
                                                                    ? ally.getWeapon().get().toString() : "")),
                                                    new Label("Armor: "
                                                            + (ally.getArmor().isPresent()
                                                                    ? ally.getArmor().get().toString() : ""))
                    );
                }

                partyList.getChildren().add(label);
                counter++;
            }


            // Create a label for the party title
            final Label partyTitle = new Label("Party");
            partyTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            partyList.setFocusTraversable(false);
            // Create a VBox for the party members
            final VBox partyBox = new VBox(10, partyTitle, partyList);
            partyBox.setPadding(new Insets(10));
            // Create a BorderPane as the root pane
            final GridPane pane = new GridPane();
            pane.setHgap(10);
            final ColumnConstraints column1 = new ColumnConstraints();
            final ColumnConstraints column2 = new ColumnConstraints();
            final ColumnConstraints column3 = new ColumnConstraints();
            column1.setPercentWidth(COLUMN_WIDTH);
            column2.setPercentWidth(COLUMN_WIDTH);
            column3.setPercentWidth(COLUMN_WIDTH);
            pane.getColumnConstraints().addAll(column1, column2, column3);
            inventoryBox.setStyle("-fx-background-color: white;");
            memberBox.setStyle("-fx-background-color: white;");
            partyBox.setStyle("-fx-background-color: white;");
            pane.add(inventoryBox, 0, 0);
            pane.add(partyBox, 1, 0);
            pane.add(memberBox, 2, 0);
            pane.setStyle("-fx-background-color: transparent;"); // Set background color
            root.setBackground(this.bg);
            pane.setMaxHeight(viewHeight);
            pane.setMaxWidth(viewWidth);
            root.getChildren().add(pane);
            root.setAlignment(Pos.CENTER);
            this.getScene().setRoot(root);
        });
    }
}
