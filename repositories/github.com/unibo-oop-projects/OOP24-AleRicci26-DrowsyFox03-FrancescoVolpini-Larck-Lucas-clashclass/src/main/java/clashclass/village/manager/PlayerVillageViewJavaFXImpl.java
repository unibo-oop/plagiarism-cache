package clashclass.village.manager;

import clashclass.shop.ShopMenuJavaFXImpl;
import clashclass.shop.ShopMenuView;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Represents a {@link PlayerVillageView} implementation.
 */
public class PlayerVillageViewJavaFXImpl implements PlayerVillageView {
    private static final double BUTTONS_ANCHOR_OFFSET = 20.0;
    private static final double BUTTONS_WIDTH_MULTIPLIER = 0.1;
    private static final double BUTTONS_HEIGHT_MULTIPLIER = 0.15;
    private static final double FONT_SIZE_MULTIPLIER = 0.2;
    private static final String PIXEL_PROP = "px;";
    private final AnchorPane root;
    private final Button battleButton;
    private final Button shopButton;
    private PlayerVillageController controller;

    /**
     * Constructs the view.
     *
     * @param root the UI root node
     */
        public PlayerVillageViewJavaFXImpl(final AnchorPane root) {
        this.root = root;
        this.root.setStyle("-fx-background-color: #0A8F32;");

        this.battleButton = new Button("Battle");
        root.getChildren().add(battleButton);
        AnchorPane.setBottomAnchor(battleButton, BUTTONS_ANCHOR_OFFSET);
        AnchorPane.setLeftAnchor(battleButton, BUTTONS_ANCHOR_OFFSET);
        battleButton.prefWidthProperty().bind(root.widthProperty().multiply(BUTTONS_WIDTH_MULTIPLIER));
        battleButton.prefHeightProperty().bind(root.heightProperty().multiply(BUTTONS_HEIGHT_MULTIPLIER));

        battleButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            final double newFontSize = newVal.doubleValue() * FONT_SIZE_MULTIPLIER;
            battleButton.setStyle("-fx-font-size: " + newFontSize + PIXEL_PROP);
        });
        battleButton.setOnAction(event -> this.openBattleMode());

        this.shopButton = new Button("Shop");
        root.getChildren().add(shopButton);
        AnchorPane.setBottomAnchor(shopButton, BUTTONS_ANCHOR_OFFSET);
        AnchorPane.setRightAnchor(shopButton, BUTTONS_ANCHOR_OFFSET);
        shopButton.prefWidthProperty().bind(root.widthProperty().multiply(BUTTONS_WIDTH_MULTIPLIER));
        shopButton.prefHeightProperty().bind(root.heightProperty().multiply(BUTTONS_HEIGHT_MULTIPLIER));

        shopButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            final double newFontSize = newVal.doubleValue() * FONT_SIZE_MULTIPLIER;
            shopButton.setStyle("-fx-font-size: " + newFontSize + PIXEL_PROP);
        });
        shopButton.setOnAction(event -> this.openShop());

        final var canvas = (Canvas) this.root.lookup("#canvas");
        canvas.setOnMousePressed(event -> { });
    }

        private void openBattleMode() {
        this.controller.openBattleMode();
    }

        private void openShop() {
        this.controller.openShop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setController(final PlayerVillageController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final PlayerVillageModel model) {
        //this.redraw(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        Platform.runLater(() -> {
            this.root.getChildren().remove(this.battleButton);
            this.root.getChildren().remove(this.shopButton);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShopMenuView getShopMenuView() {
        return new ShopMenuJavaFXImpl(this.root);
    }

    // private void redraw(final PlayerVillageModel model) {
    // uiGameObject.getComponentOfType(UIRendererImpl.class).ifPresent(uiRenderer -> {
    // uiRenderer.setDrawFunction(graphics -> {
    // graphics.drawRectangle(this.uiGameObject, "#FF0000", new Rect2D(
    // new VectorInt2D(20, 100),
    // new VectorInt2D(300, 50)));
    // TODO ...
    //});
    //});
    //}
}
