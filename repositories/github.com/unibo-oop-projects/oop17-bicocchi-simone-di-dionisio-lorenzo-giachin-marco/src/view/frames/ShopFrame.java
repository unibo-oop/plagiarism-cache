package view.frames;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.container.Shop;
import view.AbstractCustomButton;
import view.Category;

/**
 * The frame of the shop.
 */
public class ShopFrame extends AbstractShopInventoryFrame {

    @Override
    public void start(final Stage newPrimaryStage) throws Exception {
        try {
            setStage(newPrimaryStage);
            setFrame();
            setScene(getMainPane(), SCREEN_WIDTH / 2, SHOPINVENTORYSCREENHEIGHT);
            setExitOperation();
            getStage().setTitle("SHOP");
        } catch (IllegalArgumentException ex) {
            getStage().setIconified(false);
        }
    }

    @Override
    protected void generateImages(final Category category) {
        List<AbstractCustomButton> images = new LinkedList<>();
        List<CustomLabel> labels = new LinkedList<>();
        getController().getShop().get(category.toString()).forEach(e -> {
            images.add(new ShopButton(new ImageView(new Image(Shop.class.getClassLoader().getResource(e.getFirst().getUrl()).toExternalForm())), e.getFirst().getName(), category,
                    (int) e.getFirst().getValue()));
            labels.add(new CustomLabel(e.getFirst().getPrice() + "€"));
        });
        GridPane pane = new GridPane();
        pane.setHgap(PANEGAP);
        pane.setVgap(PANEGAP);
        images.forEach(e -> pane.add(e, 2, images.indexOf(e)));
        labels.forEach(e -> pane.add(e, 1, labels.indexOf(e)));
        getMainPane().setRight(pane);
    }

    @Override
    public void refreshInventory(final Category cat) {
        this.getView().refreshInventory(cat);
    }

    /**
     * Custom button for the items of the inventory.
     */
    public class ShopButton extends AbstractCustomButton {

        /**
         * 
         * @param img
         *            is the image of the item
         * @param item
         *            is the name of the item
         * @param cat
         *            is the category of the item
         * @param value 
         */
        public ShopButton(final ImageView img, final String item, final Category cat, final int value) {
            super(img, item, cat, value);
        }

        @Override
        public void setAction() {
            this.setOnAction(e -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("");
                alert.setContentText("Are you sure to buy " + getItem() + "?");
                alert.initStyle(StageStyle.UTILITY);
                alert.setGraphic(null);
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().add(buttonTypeCancel);
                alert.getDialogPane().setStyle(
                        "-fx-background-color: white;" + "-fx-font-size: 25px;\r\n" + "-fx-font-weight: bold;");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    if (!getController().buy(getItem())) {
                        alert.close();
                        Alert error = new Alert(AlertType.WARNING);
                        error.setTitle("");
                        error.setHeaderText("");
                        error.setContentText("Not enough money");
                        error.initStyle(StageStyle.UTILITY);
                        error.getDialogPane().setStyle(
                                "-fx-background-color: white;" + "-fx-font-size: 25px;\r\n" + "-fx-font-weight: bold;");
                        error.showAndWait();
                    } else {
                        refreshInventory(getCategory());
                        getController().checkAndSetMainItem();
                        getMoney().setText(Integer.toString(getController().getBalance()) + "€");
                        alert.close();
                    }
                } else {
                    alert.close();
                }
            });
        }
    }
}
