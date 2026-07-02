package clashclass.shop;


import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import java.util.stream.IntStream;

/**
 * Represents a {@link ShopMenuView} JavaFX implementation.
 */
public class ShopMenuJavaFXImpl implements ShopMenuView {
    private static final double BUTTON_WIDTH_MULTIPLIER = 0.15;
    private static final double FONT_SIZE_MULTIPLIER = 0.1;
    private static final double GRID_CELL_GAP = 30.0;
    private static final double ANCHOR_OFFSET = 20.0;
    private final AnchorPane root;
    private final GridPane grid;
    private final Button backButton;
    private ShopMenuController controller;

    /**
     * Constructs the view.
     *
     * @param root the root UI node
     */
        public ShopMenuJavaFXImpl(final AnchorPane root) {
        this.root = root;

        this.grid = new GridPane();
        grid.setHgap(GRID_CELL_GAP);
        grid.setVgap(GRID_CELL_GAP);
        grid.setStyle("-fx-background-color: #FFFFFFBB; -fx-padding: 20px; -fx-border-color: black;");

        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);

        IntStream.iterate(0, i -> i + 1)
                .limit(4)
                .forEach(i -> grid.getColumnConstraints().add(new ColumnConstraints()));
        IntStream.iterate(0, i -> i + 1)
                .limit(2)
                .forEach(i -> grid.getRowConstraints().add(new RowConstraints()));

        root.getChildren().add(grid);

        this.backButton = new Button("Go Back");
        AnchorPane.setTopAnchor(backButton, ANCHOR_OFFSET);
        AnchorPane.setRightAnchor(backButton, ANCHOR_OFFSET);
        backButton.prefWidthProperty().bind(root.widthProperty().multiply(BUTTON_WIDTH_MULTIPLIER));
        backButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            final double newFontSize = newVal.doubleValue() * FONT_SIZE_MULTIPLIER;
            backButton.setStyle("-fx-font-size: " + newFontSize + "px;");
        });
        backButton.setOnAction(event -> this.goBackToVillage());

        root.getChildren().add(backButton);
    }

        private void goBackToVillage() {
        this.controller.goBackToVillage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setController(final ShopMenuController controller) {
        this.controller = controller;
        final var items = controller.getShopManager().getShopItems();

        IntStream.iterate(0, i -> i + 1)
                .limit(Math.min(items.size(), 8))
                .forEach(i -> {
                    final ShopItem item = items.get(i);
                    final Button button = new Button(
                            item.getBuilding().getName() + "\n"
                             + item.getResourceType() + "\n"
                             + item.getPrice()
                    );
                    button.setWrapText(true);
                    button.setTextAlignment(TextAlignment.CENTER);
                    button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

                    final int row = i / 4;
                    final int col = i % 4;
                    grid.add(button, col, row);

                    button.setOnAction(e -> controller.tryToBuyItem(item));
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        grid.setVisible(true);
        backButton.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        grid.setVisible(false);
        backButton.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.root.getChildren().remove(this.grid);
        this.root.getChildren().remove(this.backButton);
    }
}
