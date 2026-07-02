package rogue.view.inventory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import rogue.controller.inventory.InventoryController;
import rogue.model.events.EventSubscriber;
import rogue.model.events.InventoryEvent;
import rogue.model.items.inventory.Inventory;
import rogue.model.items.inventory.OutOfInventoryException;
import rogue.model.items.rings.RingImpl;
import rogue.model.items.rings.RingType;
import rogue.model.items.scroll.ScrollImpl;
import rogue.model.items.scroll.ScrollType;
import rogue.view.ItemImageGenerator;
import rogue.view.ItemImageGeneratorImpl;

/**
 * Class that controls the player's {@link Inventory} view.
 */
public class InventoryViewImpl implements Initializable, EventSubscriber, InventoryView {

    private static final int NUM_COLS = 4;
    private static final int NUM_ROWS = 5;
    private static final int QUANTITY_FONT_SIZE = 17;
    private static final String FONT = "verdana";

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private GridPane inventoryGrid;
    @FXML  private GridPane ringAndScrollGrid;

    private InventoryController controller;
    private Optional<Integer> swapping = Optional.empty();
    private final ItemImageGenerator itemI = new ItemImageGeneratorImpl();

    /*
     * Background images for empty slot.
     */
    private final BackgroundImage emptyB = new BackgroundImage(new Image(ClassLoader.getSystemResource("images/emptyIcon.png").toExternalForm(), 32, 32, false, true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT); 

    /**
     * Pass the player to the controller.
     * @param controller of the InventoryView
     */
    public void init(final InventoryController controller) {
        this.controller = controller;
    }

    /**
     * Gives the updated number for the Model inventory.
     * @param col 
     * @param row
     * @return index for the model inventory.
     */
    private int indexConv(final int col, final int row) {
        return row * 4 + col + 1;
    }

    /**
     * Creates a javafx text with the given string.
     * @param string to put in the text
     * @return the wanted text.
     */
    private Text createText(final String string) {
        final Text ret = new Text();
        ret.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR, QUANTITY_FONT_SIZE));
        ret.setFill(Color.ORANGE);
        ret.setStrokeWidth(1); 
        ret.setStroke(Color.BLACK);
        ret.setText(string);
        return ret;
    }
    /**
     * Updates the entire View to the current inventory status.
     * @param event 
     * @throws OutOfInventoryException 
     */
    @Subscribe
    public void update(final InventoryEvent<Inventory> event) throws OutOfInventoryException {
        /*
         * Remove everything in the current InventoryGrid in order to update it.
         * Also removes everything in the current ScrollContainerGrid.
         */
        inventoryGrid.getChildren().clear();
        ringAndScrollGrid.getChildren().clear();
        /*
         * Populate the inventoryGrid and reset swapping
         */
        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                gridInsert(i, j);
            }
        }
        swapping = Optional.empty();
        /*
         * Make scrollContainer.
         */
        final Pane scrollContainer = new StackPane();
        if (controller.getActiveScroll() != null) {
            scrollContainer.setBackground(new Background(new BackgroundImage(itemI.getImage(new ScrollImpl(ScrollType.SCROLL_I)),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));
            scrollContainer.getChildren().add(createText(String.valueOf(controller.getActiveScrollDuration())));
        } else {
            scrollContainer.setBackground(new Background(emptyB));
        }
        ringAndScrollGrid.add(scrollContainer, 1, 0);
        /*
         * Make RingContainer
         */
        final Pane ringContainer = new StackPane();
        if (controller.checkActiveRing()) {
            /*
             * Rings have the same icon.
             */
            ringContainer.setBackground(new Background(new BackgroundImage(itemI.getImage(new RingImpl(RingType.PROTECTION)),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));
        } else {
            ringContainer.setBackground(new Background(emptyB));
        }
        ringContainer.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                controller.onRingContainer();
            }
        });
        ringAndScrollGrid.add(ringContainer, 0, 0);

    }

    private void gridInsert(final int col, final int row) throws OutOfInventoryException {
        final int invIndex = indexConv(col, row);
        final Pane pane = new StackPane();
        if (controller.getItem(col, row) != null) {
            /*
             * Check current item slot and update pane image and quantity text.
             */
            pane.setBackground(new Background(new BackgroundImage(itemI.getImage(controller.getItem(col, row)),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));
            pane.getChildren().add(createText(String.valueOf(controller.getAmount(col, row))));
        } else {
            /*
             * empty slot.
             */
            pane.setBackground(new Background(emptyB));
        }
        /*
         * Set each slots events.
         */
        pane.setOnMouseClicked(e -> {
            final MouseButton button = e.getButton();
            /*
             * USE ITEM
             */
            if (button.equals(MouseButton.PRIMARY)) {
                /*
                 * Remove eventual swapping event
                 */
                swapping = Optional.empty();
                controller.onPrimaryClick(col, row);
            }
            /*
             * REMOVE ITEM
             */
            if (button.equals(MouseButton.SECONDARY)) {
                /*
                 * Remove eventual swapping event
                 */
                swapping = Optional.empty();
                controller.onSecondaryClick(col, row);
            }
            /*
             * SWAP
             */
            if (button.equals(MouseButton.MIDDLE)) {
                if (swapping.isEmpty()) {
                    /*
                     * make clicked slot swapping slot.
                     */
                    swapping = Optional.of(invIndex);
                } else {
                    controller.onMiddleClick(col, row, swapping.get());
                    swapping = Optional.empty();
                }
            }
        });
        inventoryGrid.add(pane, col, row);
    }

    /**
     * Method that initializes the InventoryView.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        Platform.runLater(() -> {
            /*
             * Update inventoryGrid and ringAndScrollGrid constraints.
             */
            final ColumnConstraints colConstraintsRS = new ColumnConstraints();
            colConstraintsRS.setHgrow(Priority.NEVER);
            ringAndScrollGrid.getColumnConstraints().add(colConstraintsRS);

            final RowConstraints rowConstraintsRS = new RowConstraints();
            rowConstraintsRS.setVgrow(Priority.NEVER);
            ringAndScrollGrid.getRowConstraints().add(rowConstraintsRS);

            for (int i = 0; i < NUM_COLS; i++) {
                final ColumnConstraints colConstraints = new ColumnConstraints();
                colConstraints.setHgrow(Priority.NEVER);
                inventoryGrid.getColumnConstraints().add(colConstraints);
            }

            for (int i = 0; i < NUM_ROWS; i++) {
                final RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setVgrow(Priority.NEVER);
                inventoryGrid.getRowConstraints().add(rowConstraints);
            }
            /*
             * Create grid.
             */
            controller.getInventory().register(this);
            controller.getScrollContainer().register(this);
            try {
                update(new InventoryEvent<>(controller.getInventory()));
            } catch (OutOfInventoryException e) {
                e.printStackTrace();
            }
        });
    }
}
