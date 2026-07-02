package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.ShopController;
import controller.ShopControllerImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.shop.ShopItem;
import model.statistic.Statistics;

public class ShopViewImpl implements ShopView {

    private final View view; 
    private final Pane pane;
    private final List<ImageView> skins = new ArrayList<>(); 
    private final List<ShopItem> shopSkins; 
    private int skinsCounter;
    private final ShopController shopController; 

    private static final int FONT_SIZE = 18; 
    private static final int BUTTON_WIDTH = 130; 
    private static final int BUTTON_X = 340; 
    private static final int BUY_BUTTON_Y = 320; 
    private static final int SELECT_BUTTON_Y = 370; 
    private static final int SQUARE_WIDTH = 50; 
    private static final int ARROW_BUTTON_X = 240; 
    private static final int ARROW_DXBUTTON_X = 510; 
    private static final int ARROW_BUTTON_Y = 200; 
    private static final int SQUARE_HEIGTH = 40; 
    private static final int SHOPTITLE_X = 330; 
    private static final int SKIN_X = 357; 
    private static final int SKIN_Y = 130; 
    private static final int ARC = 20;
    private static final int BACKGROUND_WIDTH = 854;
    private static final int BACKGROUND_HEIGTH = 480;
    private static final int HOMEBTN_X = 700;
    private static final int MYSTBOX_HEIGHT = 80; 
    private static final int MYSTBOX_X = 40;
    private static final int MYSTBOX_Y = 310; 
    private static final int COINS_X = 10;
    private static final int COINS_Y = 30; 

    public ShopViewImpl(final View view, final Pane pane, final Statistics statistics) {
        super(); 
        this.view = view; 
        this.pane = pane;
        this.shopController = new ShopControllerImpl(this, statistics);
        skinsCounter = 0;
        shopSkins = this.shopController.getShopModel().getItems();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.pane.getChildren().clear();

        final ImageView title = new ImageView(new Image("Shop.png"));
        title.setLayoutX(SHOPTITLE_X);
        title.setLayoutY(0); 

        final ImageView playerSkin = new ImageView(new Image("PlayerSkin.png")); 
        playerSkin.setLayoutX(SKIN_X);
        playerSkin.setLayoutY(SKIN_Y);

        final ImageView programmerSkin = new ImageView(new Image("ProgrammerSkin.png")); 
        programmerSkin.setLayoutX(SKIN_X);
        programmerSkin.setLayoutY(SKIN_Y);

        final ImageView dinoSkin = new ImageView(new Image("DinoSkin.png")); 
        dinoSkin.setLayoutX(SKIN_X);
        dinoSkin.setLayoutY(SKIN_Y);

        skins.add(playerSkin); 
        skins.add(programmerSkin); 
        skins.add(dinoSkin);

        final Button buy = new Button(); 
        buy.setLayoutX(BUTTON_X);
        buy.setLayoutY(BUY_BUTTON_Y);
        buy.setPrefWidth(BUTTON_WIDTH);
        buy.setTextAlignment(TextAlignment.CENTER);
        buy.setFont(new Font("Arial", FONT_SIZE));
        buy.setText("BUY");

        if (this.shopController.getShopModel().checkPayment(shopSkins.get(skinsCounter), this.shopController.getShopModel().getTotalCoins())) {
            buy.setOnAction(e -> {
                this.shopController.getShopModel().shopItemPayment(shopSkins.get(skinsCounter)); 
                System.out.println(this.shopController.getShopModel().getTotalCoins()); 
                renderCoins(); 
                shopController.render(); 
            });
        } else {
            buy.setDisable(true);
        }

        final Button select = new Button(); 
        select.setLayoutX(BUTTON_X);
        select.setLayoutY(SELECT_BUTTON_Y);
        select.setPrefWidth(BUTTON_WIDTH);
        select.setTextAlignment(TextAlignment.CENTER);
        select.setFont(new Font("Arial", FONT_SIZE));
        select.setText("SELECT"); 
        select.setDisable(true);

        if (this.shopController.getShopModel().getPurchasedItems().contains(shopSkins.get(skinsCounter)) && !this.shopController.getShopModel().isSelected(shopSkins.get(skinsCounter).getName())) {
            select.setDisable(false);
            select.setOnAction(e -> {
                this.shopController.getShopModel().setSelected(shopSkins.get(skinsCounter).getName());
                try {
                    this.shopController.getShopModel().writeSkinOnFile();
                } catch (IOException e1) {
                    System.out.println("Unsaved selected skin");
                }
                select.setDisable(true);
            });
        }

        final ImageView dxArr = new ImageView(); 
        dxArr.setImage(new Image("ArrowDx.png"));
        dxArr.setFitHeight(SQUARE_HEIGTH); 
        dxArr.setPreserveRatio(true);

        final ImageView sxArr = new ImageView(); 
        sxArr.setImage(new Image("ArrowSx.png"));
        sxArr.setFitHeight(SQUARE_HEIGTH); 
        sxArr.setPreserveRatio(true);

        final ImageView startIm = new ImageView(); 
        startIm.setImage(new Image("StartGame.png"));
        startIm.setFitHeight(MYSTBOX_HEIGHT);
        startIm.setPreserveRatio(true);

        final ImageView mysteryBoxIm = new ImageView(); 
        mysteryBoxIm.setImage(new Image("MysteryBox.png"));
        mysteryBoxIm.setFitHeight(MYSTBOX_HEIGHT);
        mysteryBoxIm.setPreserveRatio(true);

        final Button dxArrow = new Button(); 
        dxArrow.setLayoutX(ARROW_DXBUTTON_X);
        dxArrow.setLayoutY(ARROW_BUTTON_Y);
        dxArrow.setPrefWidth(SQUARE_WIDTH);
        dxArrow.setGraphic(dxArr); 
        dxArrow.setOnAction(e -> {
            this.pane.getChildren().remove(skins.get(skinsCounter));
            skinsCounter = this.shopController.increaseSkinCounter();
            this.shopController.render();
        });

        final Button sxArrow = new Button(); 
        sxArrow.setLayoutX(ARROW_BUTTON_X);
        sxArrow.setLayoutY(ARROW_BUTTON_Y);
        sxArrow.setPrefWidth(SQUARE_WIDTH);
        sxArrow.setGraphic(sxArr);
        sxArrow.setOnAction(e -> {
            this.pane.getChildren().remove(skins.get(skinsCounter));
            skinsCounter = this.shopController.decreaseSkinCounter();
            this.shopController.render();
        });

        final Button startGame = new Button(); 
        startGame.setLayoutX(HOMEBTN_X);
        startGame.setLayoutY(MYSTBOX_Y);
        startGame.setPrefWidth(SQUARE_WIDTH);
        startGame.setGraphic(startIm);
        startGame.setOnAction(e -> {
            pane.getChildren().clear(); 
            this.view.getController().start();
            try {
                this.shopController.close();
            } catch (IOException e1) {
                System.out.println("Unsaved items");
            }
        });

        final Button mysteryBox = new Button(); 
        mysteryBox.setLayoutX(MYSTBOX_X);
        mysteryBox.setLayoutY(MYSTBOX_Y);
        mysteryBox.setPrefWidth(MYSTBOX_HEIGHT);
        mysteryBox.setGraphic(mysteryBoxIm);
        mysteryBox.setOnAction(e -> {
            final String res = this.shopController.getShopModel().misteryBoxPayment();
            final Alert alert = new Alert(AlertType.INFORMATION); 
            alert.setHeaderText(null); 
            alert.setTitle("Premio"); 
            if (!res.isEmpty()) {
                alert.setContentText(res); 
                alert.showAndWait(); 
            } else {
                alert.setContentText("No money"); 
                alert.showAndWait(); 
            }
            this.shopController.render();
        });
        this.renderCoins();

        final Rectangle rectangle = new Rectangle(345, 120, 120, 170); 
        rectangle.setFill(Color.LIGHTPINK);
        rectangle.setArcHeight(ARC);
        rectangle.setArcWidth(ARC);
        final Rectangle outerRec = new Rectangle(340, 115, 130, 180); 
        outerRec.setFill(Color.BLACK);
        outerRec.setArcHeight(ARC);
        outerRec.setArcWidth(ARC);

        final Image shopWallpaper = new Image("ShopBackground.jpg", BACKGROUND_WIDTH, BACKGROUND_HEIGTH, false, true); 
        final BackgroundImage shopBackground = new BackgroundImage(shopWallpaper, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT); 

        this.pane.getChildren().add(buy); 
        this.pane.getChildren().add(select); 
        this.pane.getChildren().add(startGame); 
        this.pane.getChildren().add(dxArrow); 
        this.pane.getChildren().add(sxArrow); 
        this.pane.getChildren().add(title); 
        this.pane.getChildren().add(outerRec); 
        this.pane.getChildren().add(rectangle);
        this.pane.getChildren().add(skins.get(skinsCounter)); 
        this.pane.getChildren().add(mysteryBox); 

        this.pane.setBackground(new Background(shopBackground));
    }

    private void renderCoins() {
        final Text coins = new Text("Coins: " + this.shopController.getTotalCoins()); 
        coins.setFont(new Font("Arial", FONT_SIZE));
        coins.setLayoutX(COINS_X);
        coins.setLayoutY(COINS_Y);
        this.pane.getChildren().add(coins); 
    }

}
