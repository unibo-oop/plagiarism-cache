package it.tbt.view.javafx;

import it.tbt.commons.resourceloader.ImageLoader;
import it.tbt.controller.modelmanager.shop.ShopItem;
import it.tbt.controller.modelmanager.shop.ShopState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * UI of the shop.
 */
public class JavaFxShopView extends AbstractJavaFxView {

    private static final double BORDER_SCALE = 25;
    private static final double GAP_WIDTH = 50;
    private static final double COLUMN_WIDTH_P = 50.0; // 50%
    private final ShopState shopState;
    private final Scene scene;
    private final Background bg;

    /**
     * Default constructor.
     * @param viewController
     * @param stage
     * @param scene
     * @param shopState
     */
    protected JavaFxShopView(
        final ViewController viewController,
        final Stage stage,
        final Scene scene,
        final ShopState shopState
    ) {
        super(viewController, stage, scene);
        this.scene = scene;
        this.shopState = shopState;
        this.bg = new Background(new BackgroundImage(
            new Image(ImageLoader.getInstance().getFilePath(shopState.getClass())),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(1.0, 1.0, true, true, false, false)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        Platform.runLater(() -> {
            // setup root
            final VBox root = new VBox();
            root.getChildren().clear();

            // party items on the left
            final VBox partyItemsBox = new VBox();
            int count = 0;
            for (final ShopItem item : shopState.getPartyItems()) {
                final Label label = new Label(
                    item.getName()
                    + " x" + item.getCount()
                    + " $" + item.getValue()
                );
                if (count == shopState.getPartyFocus()) {
                    if (shopState.isPartyListFocused()) {
                        label.setStyle("-fx-background-color: yellow;");
                    } else {
                        label.setStyle("-fx-background-color: lightblue;");
                    }
                }
                partyItemsBox.getChildren().add(label);
                count++;
            }
            // shop items on the right
            final VBox shopItemsBox = new VBox();
            count = 0;
            for (final ShopItem item : shopState.getShopItems()) {
                final Label label = new Label(
                    item.getName()
                    + " x" + item.getCount()
                    + " $" + item.getValue()
                );
                if (count == shopState.getShopFocus()) {
                    if (shopState.isPartyListFocused()) {
                        label.setStyle("-fx-background-color: lightblue;");
                    } else {
                        label.setStyle("-fx-background-color: yellow;");
                    }
                }
                shopItemsBox.getChildren().add(label);
                count++;
            }

            final Label partyTitle = new Label("Party");
            partyTitle.setStyle("-fx-font-weight: bold;");
            final Label partySubTitle = new Label("wallet: " + shopState.getPartyWallet());
            partySubTitle.setStyle("-fx-font-weight: lighter;");
            final VBox partyBox = new VBox(10, partyTitle, partySubTitle, partyItemsBox);
            partyBox.setStyle("-fx-background-color: #F5F5F5;");

            final Label shopTitle = new Label("Shop");
            shopTitle.setStyle("-fx-font-weight: bold;");
            final Label shopSubTitle = new Label("wallet: " + shopState.getShopWallet());
            shopSubTitle.setStyle("-fx-font-weight: lighter;");
            final VBox shopBox = new VBox(10, shopTitle, shopSubTitle, shopItemsBox);
            shopBox.setStyle("-fx-background-color: #F5F5F5;");

            // main pane
            final GridPane pane = new GridPane();
            final ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(COLUMN_WIDTH_P);
            final ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(COLUMN_WIDTH_P);
            pane.getColumnConstraints().addAll(column1, column2);
            pane.setHgap(GAP_WIDTH);
            pane.add(partyBox, 0, 0);
            pane.add(shopBox, 1, 0);
            pane.setStyle("-fx-background-color: transparent;");
            pane.setMaxHeight(this.scene.getHeight() - (this.scene.getHeight() / BORDER_SCALE));
            pane.setMaxWidth(this.scene.getWidth() - (this.scene.getWidth() / BORDER_SCALE));

            // setup root
            root.getChildren().add(pane);
            root.setBackground(bg);
            root.setAlignment(Pos.CENTER);
            scene.setRoot(root);
        });
    }

}
