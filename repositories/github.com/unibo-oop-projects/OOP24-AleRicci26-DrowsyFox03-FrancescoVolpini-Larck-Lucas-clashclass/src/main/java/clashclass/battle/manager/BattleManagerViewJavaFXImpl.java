package clashclass.battle.manager;

import clashclass.battle.battlereport.BattleReportView;
import clashclass.battle.battlereport.BattleReportViewJavaFXImpl;
import clashclass.commons.GameConstants;
import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.commons.CommonGameObjectFactoryImpl;
import clashclass.engine.GameEngine;
import clashclass.view.graphic.Graphic;
import clashclass.view.graphic.components.UIRendererImpl;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

/**
 * Represents a {@link BattleManagerView} JavaFX implementation.
 */
public class BattleManagerViewJavaFXImpl implements BattleManagerView {
    private static final double ANCHOR_OFFSET = 20.0;
    private static final double SIZE_MULTIPLIER = 0.15;
    private static final double FONT_SIZE_MULTIPLIER = 0.1;
    private static final String FONT_SIZE_PROP = "-fx-font-size: ";
    private static final String PIXEL_PROP = "px;";
    private final AnchorPane root;
    private final Button endBattleButton;
    private final Label battleTimeLabel;
    private final List<ToggleButton> troopToggles = new ArrayList<>();
    private HBox troopTogglesContainer;
    private BattleManagerController controller;
    private double togglesFontSize;
    private final GameObject uiObject;

    /**
     * Constructs the view.
     *
     * @param root the root reference
     * @param gameEngine the game engine
     */
        public BattleManagerViewJavaFXImpl(final AnchorPane root, final GameEngine gameEngine) {
        this.root = root;
        this.root.setStyle("-fx-background-color: #0A8F32;");

        this.endBattleButton = new Button("End Battle");
        root.getChildren().add(endBattleButton);
        AnchorPane.setTopAnchor(endBattleButton, ANCHOR_OFFSET);
        AnchorPane.setLeftAnchor(endBattleButton, ANCHOR_OFFSET);
        endBattleButton.prefWidthProperty().bind(root.widthProperty().multiply(SIZE_MULTIPLIER));
        endBattleButton.prefHeightProperty().bind(root.heightProperty().multiply(SIZE_MULTIPLIER));

        endBattleButton.widthProperty().addListener((obs, oldVal, newVal) -> {
            final double newFontSize = newVal.doubleValue() * FONT_SIZE_MULTIPLIER;
            endBattleButton.setStyle(FONT_SIZE_PROP + newFontSize + PIXEL_PROP);
        });
        endBattleButton.setOnAction(event -> {
            this.controller.endBattle();
        });

        final var canvas = (Canvas) this.root.lookup("#canvas");
        canvas.setOnMousePressed(event -> {
            if (event.isConsumed()) {
                return;
            }

            final double scaleX = canvas.getWidth() / GameConstants.SCREEN_WIDTH;
            final double scaleY = canvas.getHeight() / GameConstants.SCREEN_HEIGHT;
            final double worldX = event.getSceneX() / scaleX;
            final double worldY = event.getSceneY() / scaleY;

            this.controller.createTroop(new Vector2D(worldX, worldY));
        });

        this.battleTimeLabel = new Label("");
        this.battleTimeLabel.setTextFill(Color.WHITE);
        this.battleTimeLabel.setTextAlignment(TextAlignment.RIGHT);
        root.getChildren().add(battleTimeLabel);
        AnchorPane.setTopAnchor(battleTimeLabel, ANCHOR_OFFSET);
        AnchorPane.setRightAnchor(battleTimeLabel, 0.0);
        battleTimeLabel.prefWidthProperty().bind(root.widthProperty().multiply(SIZE_MULTIPLIER));
        battleTimeLabel.prefHeightProperty().bind(root.heightProperty().multiply(SIZE_MULTIPLIER));
        battleTimeLabel.widthProperty().addListener((obs, oldVal, newVal) -> {
            final double newFontSize = newVal.doubleValue() * FONT_SIZE_MULTIPLIER;
            battleTimeLabel.setStyle(FONT_SIZE_PROP + newFontSize + PIXEL_PROP);
        });

        final var factory = new CommonGameObjectFactoryImpl();
        this.uiObject = factory.createUIElement();
        this.uiObject.getComponentOfType(UIRendererImpl.class).get()
                .setDrawFunction(this::drawUI);
        gameEngine.addGameObject(uiObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setController(final BattleManagerController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArmyCampTroops(final BattleManagerModel model) {
        final var player = model.getBattleVillage().getPlayer();
        final var troopTypes = player.getArmyCampTroopTypes();

        final var toggleGroup = new ToggleGroup();
        this.troopToggles.clear();
        troopTypes.forEach(troopType -> {
            final var toggle = new ToggleButton(troopType.toString().toUpperCase(Locale.getDefault())
                    + "\n" + "[" + player.getArmyCampTroopCount(troopType) + "]");
            toggle.setWrapText(true);
            toggle.setToggleGroup(toggleGroup);
            this.togglesFontSize = toggle.getWidth() * FONT_SIZE_MULTIPLIER;
            toggle.widthProperty().addListener((obs, oldVal, newVal) -> {
                this.togglesFontSize = newVal.doubleValue() * FONT_SIZE_MULTIPLIER;
                if (toggle.isSelected()) {
                    toggle.setStyle(FONT_SIZE_PROP + this.togglesFontSize
                            + "px;-fx-border-width: 3px; -fx-border-color: blue;");
                } else {
                    toggle.setStyle(FONT_SIZE_PROP + this.togglesFontSize + PIXEL_PROP);
                }
            });
            toggle.setOnAction(event -> {
                if (event.isConsumed()) {
                    return;
                }
                if (toggle.isSelected()) {
                    this.controller.setCurrentSelectedTroop(troopType);
                }
                event.consume();
            });
            toggle.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                if (isSelected) {
                    toggle.setStyle(FONT_SIZE_PROP + this.togglesFontSize
                            + "px;-fx-border-width: 3px; -fx-border-color: blue;");
                } else {
                    toggle.setStyle(FONT_SIZE_PROP + this.togglesFontSize + PIXEL_PROP);
                }
            });
            toggle.prefWidthProperty().bind(root.widthProperty().multiply(SIZE_MULTIPLIER));
            toggle.prefHeightProperty().bind(root.heightProperty().multiply(SIZE_MULTIPLIER));
            this.troopToggles.add(toggle);
        });

        if (!this.troopToggles.isEmpty()) {
            this.controller.setCurrentSelectedTroop(troopTypes.stream().findFirst().get());
            this.troopToggles.getFirst().setSelected(true);
        }

        this.troopTogglesContainer = new HBox(10);
        this.troopToggles.forEach(this::addToggle);
        root.getChildren().add(1, troopTogglesContainer);
        AnchorPane.setBottomAnchor(troopTogglesContainer, ANCHOR_OFFSET);
        AnchorPane.setLeftAnchor(troopTogglesContainer, ANCHOR_OFFSET);
    }

        private void addToggle(final ToggleButton toggle) {
        this.troopTogglesContainer.getChildren().add(toggle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateArmyCampTroopsCount(final BattleManagerModel model) {
        final var player = model.getPlayerVillage().getPlayer();
        final var troopTypes = player.getArmyCampTroopTypes().stream().toList();

        IntStream.range(0, troopTypes.size()).forEach(i -> {
            final var troopType = troopTypes.get(i);
            this.troopToggles.get(i).setText(troopType.toString().toUpperCase(Locale.getDefault())
                    + "\n" + "[" + player.getArmyCampTroopCount(troopType) + "]");
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.root.getChildren().remove(this.endBattleButton);
        this.root.getChildren().remove(this.troopTogglesContainer);
        this.root.getChildren().remove(this.battleTimeLabel);
        this.uiObject.destroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endBattle(final BattleManagerModel model) {
        Platform.runLater(model::showBattleReport);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BattleReportView buildBattleReportView() {
        return new BattleReportViewJavaFXImpl(this.root);
    }

        private void drawUI(final Graphic graphic) {
        Platform.runLater(() -> {
            if (this.controller != null) {
                final var remainingTime = this.controller.getBattleRemainingTime();
                this.battleTimeLabel.setText("Time: " + remainingTime);

                if (this.controller.isBattleTimeFinished()) {
                    this.controller.endBattle();
                }
            }
        });
    }
}
