package view.frames;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.AbstractCustomButton;
import view.Category;

/**
 * The frame of the inventory.
 */
public class InventoryFrame extends AbstractShopInventoryFrame {

    @Override
    public void start(final Stage newPrimaryStage) throws Exception {
        try {
            setStage(newPrimaryStage);
            setFrame();
            setScene(getMainPane(), SCREEN_WIDTH / 2, SHOPINVENTORYSCREENHEIGHT);
            setExitOperation();
            getStage().setTitle("INVENTORY");
        } catch (IllegalArgumentException ex) {
            getStage().setIconified(false);
        }
    }

    @Override
    protected void generateImages(final Category category) {
        List<AbstractCustomButton> images = new LinkedList<>();
        List<CustomLabel> labels = new LinkedList<>();
        try {
            getController().getInventory().get(category.toString()).forEach(e -> {
                images.add(new InventoryButton(new ImageView(InventoryFrame.class.getClassLoader().getResource(e.getFirst().getUrl()).toExternalForm()), e.getFirst().getName(), category,
                        (int) e.getFirst().getValue()));
                labels.add(new CustomLabel(e.getSecond() + ""));
            });
        } catch (NullPointerException e) {
        }
        GridPane pane = new GridPane();
        pane.setVgap(PANEGAP);
        pane.setHgap(PANEGAP);
        images.forEach(e -> pane.add(e, 2, images.indexOf(e)));
        labels.forEach(e -> pane.add(e, 1, labels.indexOf(e)));
        getMainPane().setRight(pane);
    }

    /**
     * Custom button for the items of the shop.
     */
    public class InventoryButton extends AbstractCustomButton {
        /**
         * 
         * @param img
         *            is the image of the item
         * @param item
         *            is the name of the item
         * @param newCategory
         *            is the category of the item
         * @param value 
         */
        public InventoryButton(final ImageView img, final String item, final Category newCategory, final int value) {
            super(img, item, newCategory, value);
        }

        @Override
        public void setAction() {
            this.setOnAction(ev -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("");
                alert.setContentText("Set: " + getItem() + " as preferred item for this category?");
                alert.initStyle(StageStyle.UTILITY);
                alert.setGraphic(null);
                alert.getDialogPane().setStyle("-fx-background-color: white");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().add(buttonTypeCancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    getController().setMainItem(getCategory().toString(), getItem(), true);
                } else {
                    alert.close();
                }
            });
        }
    }

    @Override
    public void refreshInventory(final Category cat) {
        this.setItemToShow(cat);
        this.setMoneyValue();
    }

}
