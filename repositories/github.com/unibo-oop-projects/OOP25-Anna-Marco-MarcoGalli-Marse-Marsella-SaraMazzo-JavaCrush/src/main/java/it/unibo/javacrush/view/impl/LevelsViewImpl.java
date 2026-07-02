package it.unibo.javacrush.view.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.javacrush.common.AppEventType;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.Event;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.view.api.LevelsView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Implementation of the {@link LevelsView} interface.
 */
public class LevelsViewImpl implements LevelsView {

    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;
    private static final int LEVEL_4 = 4;
    private static final int LEVEL_5 = 5;
    private static final int SPACING = 30;
    private static final int PADDING = 20;
    private static final int BT_WIDTH = 200;
    private static final int BT_HEIGHT = 40;
    private final BorderPane root;

    /**
     * Constructor of {@link LevelsViewImpl}.
     * 
     * @param appController the controller to notify when buttons are pressed
     * @param levelManager the level manager to handle level logic
     */
    public LevelsViewImpl(final AppController appController, final LevelManager levelManager) {

        final VBox levels = new VBox(SPACING);
        levels.setAlignment(Pos.CENTER); // Centra tutto!

        final HBox back = new HBox(SPACING);
        back.setAlignment(Pos.CENTER_LEFT);

        final Button easy = new Button("Easy");
        easy.setPrefWidth(BT_WIDTH);
        easy.setPrefHeight(BT_HEIGHT);
        final Button medium = new Button("Medium");
        medium.setPrefWidth(BT_WIDTH);
        medium.setPrefHeight(BT_HEIGHT);
        final Button hard = new Button("Hard");
        hard.setPrefWidth(BT_WIDTH);
        hard.setPrefHeight(BT_HEIGHT);
        final Button expert = new Button("Expert");
        expert.setPrefWidth(BT_WIDTH);
        expert.setPrefHeight(BT_HEIGHT);
        final Button crazyGravity = new Button("Crazy Gravity");
        crazyGravity.setPrefWidth(BT_WIDTH);
        crazyGravity.setPrefHeight(BT_HEIGHT);
        levels.getChildren().addAll(easy, medium, hard, expert, crazyGravity);

        final Button menu = new Button("Menu");
        menu.setPrefWidth(BT_WIDTH / 2);
        menu.setPrefHeight(BT_HEIGHT);
        back.getChildren().add(menu);

        this.root = new BorderPane();

        final String path = LevelsViewImpl.class.getResource("/gameBackground.png").toExternalForm();
        this.root.setStyle("-fx-background-image: url('" + path + "'); "
                 + "-fx-background-size: cover; "
                 + "-fx-background-position: center; "
                 + "-fx-background-repeat: no-repeat;");

        this.root.setPadding(new Insets(PADDING));
        this.root.setCenter(levels);
        this.root.setBottom(back);

        menu.setOnAction(e -> {
            final Event goToMenuEvent = new Event() {

                @Override
                public AppEventType type() {
                    return AppEventType.GO_TO_MENU;
                }

                @Override
                public Optional<Integer> id() {
                    return Optional.empty();
                }

            };
            appController.notifyEvent(goToMenuEvent);
        });

        easy.setOnAction(e -> startLevelEvent(appController, LEVEL_1));
        medium.setOnAction(e -> startLevelEvent(appController, LEVEL_2));
        hard.setOnAction(e -> startLevelEvent(appController, LEVEL_3));
        expert.setOnAction(e -> startLevelEvent(appController, LEVEL_4));
        crazyGravity.setOnAction(e -> startLevelEvent(appController, LEVEL_5));

    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP", 
        justification = "JavaFX requires returning the actual Node instance to attach it to the Scene graph."
        + " Defensive copying is not applicable for UI components."
    )
    @Override
    public Parent getView() {
        return this.root;
    }

    private void startLevelEvent(final AppController appController, final int levelID) {
        final Event startLevelEvent = new Event() {

            @Override
            public AppEventType type() {
                return AppEventType.START_LEVEL;
            }

            @Override
            public Optional<Integer> id() {
                return Optional.of(levelID);
            }

        };
        appController.notifyEvent(startLevelEvent);
    }
}
