package it.unibo.view.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.FixedWindowsComponent;
import it.unibo.model.impl.HitboxComponent;
import it.unibo.model.impl.LivesComponent;
import it.unibo.model.impl.PointsComponent;
import it.unibo.utilities.Constants;
import it.unibo.utilities.GameState;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import it.unibo.core.impl.GameEngineImpl;

/**
 * WindowGame represents the window of the game.
 * This class can be extended to customize the game window.
 */
public final class WindowGame extends Application {
    private Stage primaryStage;
    private boolean zKeyPressed;
    private final GameEngineImpl gameEngine = new GameEngineImpl();
    private RalphView ralphView;
    private FelixView felixView;
    private final AnchorPane root = new AnchorPane();
    private final Set<BrickView> bricksToPrint = new HashSet<>();
    private final Set<BirdView> birdsToPrint = new HashSet<>();
    private final Set<CakeView> cakesToPrint = new HashSet<>();
    private static final double WIDTH = 800.0;
    private static final double HEIGHT = 600.0;
    private static final double BACKGROUND_IMAGE_HEIGHT = 25.0;
    private static final double BACKGROUND_IMAGE_TOP_ANCHOR = 53.0;
    private static final double BACKGROUND_IMAGE_LEFT_ANCHOR = 0.0;
    private static final double BACKGROUND_IMAGE_RIGHT_ANCHOR = 0.0;
    private static final double BUILDING_TOP_WIDTH_SCALE = 1.45;
    private static final double BUILDING_TOP_HEIGHT_SCALE = 1.45;
    private static final double BUILDING_TOP_TOP_ANCHOR = 78.0;
    private static final double BUILDING_TOP_TRANSLATE_X = 243.5;
    private static final double BUILDING_TOP_TRANSLATE_Y = 9.0;
    private static final double BUILDING_CENTRE_WIDTH_SCALE = 1.45;
    private static final double BUILDING_CENTRE_HEIGHT_SCALE = 1.45;
    private static final double BUILDING_CENTRE_TRANSLATE_X = 400.0;
    private static final double BUILDING_CENTRE_TRANSLATE_Y = 100.0;
    private static final double BUILDING_CENTRE_BOTTOM_ANCHOR = 75.0;
    private static final double POINTS_VIEW_LEFT_ANCHOR = 150.0;
    private static final double MAIN_MENU_RIGHT_ANCHOR = 7.0;
    private static final double MAIN_MENU_TOP_ANCHOR = 7.0;
    private static final double HIGH_POINTS_VIEW_LEFT_ANCHOR = 0.0;
    private static final double HIGH_POINTS_VIEW_TOP_ANCHOR = 0.0;
    private static final double LIVES_VIEW_RIGHT_ANCHOR = 70.0;
    private static final double LIVES_VIEW_TOP_ANCHOR = 7.0;
    private static final double POINTS_VIEW_TOP_ANCHOR = 0.0;
    private static final int Z_KEY_PRESS_DURATION_MS = 1500;
    private static final Logger LOGGER = Logger.getLogger(WindowGame.class.getName());

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public void start(final Stage primaryStg) throws IOException {
        this.primaryStage = primaryStg;
        primaryStage.setResizable(false);

        final Pane blackPane = new Pane();
        blackPane.setPrefSize(WIDTH, HEIGHT);
        blackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        final MainMenu mainMenu = new MainMenu(primaryStage);
        final LivesComponent livesComponent = (LivesComponent) gameEngine.getGameController().getFelixController()
                .getFelix().getTheComponent(ComponentType.LIFE).get();
        final LivesView livesView = new LivesView(livesComponent);
        final Image backgroundImage = new Image("TopLine.png");
        final ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(BACKGROUND_IMAGE_HEIGHT);
        AnchorPane.setTopAnchor(backgroundImageView, BACKGROUND_IMAGE_TOP_ANCHOR);
        AnchorPane.setLeftAnchor(backgroundImageView, BACKGROUND_IMAGE_LEFT_ANCHOR);
        AnchorPane.setRightAnchor(backgroundImageView, BACKGROUND_IMAGE_RIGHT_ANCHOR);
        final Image buildingTopImage = new Image("building_top.png");
        final ImageView buildingTopImageView = new ImageView(buildingTopImage);
        buildingTopImageView.setFitWidth(buildingTopImage.getWidth() * BUILDING_TOP_WIDTH_SCALE);
        buildingTopImageView.setFitHeight(buildingTopImage.getHeight() * BUILDING_TOP_HEIGHT_SCALE);
        AnchorPane.setTopAnchor(buildingTopImageView, BUILDING_TOP_TOP_ANCHOR);
        AnchorPane.setLeftAnchor(buildingTopImageView, BACKGROUND_IMAGE_LEFT_ANCHOR);
        AnchorPane.setRightAnchor(buildingTopImageView, BACKGROUND_IMAGE_RIGHT_ANCHOR);
        buildingTopImageView.setTranslateX(BUILDING_TOP_TRANSLATE_X);
        buildingTopImageView.setTranslateY(BUILDING_TOP_TRANSLATE_Y);
        final Image newBackgroundImage = new Image("building_centre.png");
        final ImageView buildingCentreImageView = new ImageView(newBackgroundImage);
        buildingCentreImageView.setFitWidth(newBackgroundImage.getWidth() * BUILDING_CENTRE_WIDTH_SCALE);
        buildingCentreImageView.setFitHeight(newBackgroundImage.getHeight() * BUILDING_CENTRE_HEIGHT_SCALE);
        buildingCentreImageView.setTranslateX(BUILDING_CENTRE_TRANSLATE_X);
        buildingCentreImageView.setTranslateY(BUILDING_CENTRE_TRANSLATE_Y);
        AnchorPane.setBottomAnchor(buildingCentreImageView, BUILDING_CENTRE_BOTTOM_ANCHOR);
        final double centerX = (root.getWidth() - buildingCentreImageView.getFitWidth()) / 2;
        AnchorPane.setLeftAnchor(buildingCentreImageView, centerX);

        root.getChildren().addAll(blackPane, backgroundImageView, buildingTopImageView, buildingCentreImageView);
        AnchorPane.setRightAnchor(mainMenu, MAIN_MENU_RIGHT_ANCHOR);
        AnchorPane.setTopAnchor(mainMenu, MAIN_MENU_TOP_ANCHOR);
        AnchorPane.setRightAnchor(livesView, LIVES_VIEW_RIGHT_ANCHOR);
        AnchorPane.setTopAnchor(livesView, LIVES_VIEW_TOP_ANCHOR);

        final Entity felix = this.gameEngine.getGameController().getFelixController().getFelix();
        final Optional<Component> felixPointsComponent = felix.getTheComponent(ComponentType.POINTS);
        if (felixPointsComponent.isPresent()) {
            final PointsComponent felixPoints = (PointsComponent) felixPointsComponent.get();
            final PointsView pointsView = new PointsView(felixPoints);
            final HighPointsView highPointsView = new HighPointsView(felixPoints);
            pointsView.initializeView();
            highPointsView.initializeView();
            AnchorPane.setLeftAnchor(pointsView, POINTS_VIEW_LEFT_ANCHOR);
            AnchorPane.setTopAnchor(pointsView, POINTS_VIEW_TOP_ANCHOR);
            AnchorPane.setLeftAnchor(highPointsView, HIGH_POINTS_VIEW_LEFT_ANCHOR);
            AnchorPane.setTopAnchor(highPointsView, HIGH_POINTS_VIEW_TOP_ANCHOR);
            root.getChildren().addAll(pointsView, highPointsView);
        }

        root.getChildren().addAll(mainMenu, livesView);

        final Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wreck-it Ralph");
        primaryStage.show();

        switch (this.gameEngine.getGameController().getLevel()) {
            case 1:
                addWindowsGrid(Constants.Windows.BROKEN_1);
                break;
            case 2:
                addWindowsGrid(Constants.Windows.BROKEN_2);
                break;
            case 3:
                addWindowsGrid(Constants.Windows.BROKEN_3);
                break;
            default:
                break;
        }
        this.felixView = this.addFelixView();
        this.ralphView = this.addRalphView();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case S:
                    gameEngine.getGameController().moveFelixDown(event.getCode());
                    felixView.animate();
                    break;
                case A:
                    gameEngine.getGameController().moveFelixLeft(event.getCode());
                    felixView.animate();
                    break;
                case D:
                    gameEngine.getGameController().moveFelixRight(event.getCode());
                    felixView.animate();
                    break;
                case W:
                    gameEngine.getGameController().moveFelixUp(event.getCode());
                    felixView.animate();
                    break;
                case Z:
                    zKeyPressed = true;
                    final Thread timerThread = new Thread(() -> {
                        try {
                            Thread.sleep(Z_KEY_PRESS_DURATION_MS);
                            if (zKeyPressed) {
                                Platform.runLater(() -> {
                                    final Optional<Component> pointsComponentOptional = this.gameEngine
                                            .getGameController()
                                            .getFelixController().getFelix().getTheComponent(ComponentType.POINTS);
                                    final Optional<Component> hitboxComponentOptional = this.gameEngine
                                            .getGameController().getFelixController().getFelix()
                                            .getTheComponent(ComponentType.HITBOX);
                                    final HitboxComponent hitComp = (HitboxComponent) hitboxComponentOptional.get();
                                    final Optional<Pair<Double, Double>> windowPosition = hitComp
                                            .checkWindowsCollisions();

                                    if (windowPosition.isPresent()) {
                                        final Optional<Entity> windowEntity = this.gameEngine.getGameController()
                                                .getGamePerformance().getWindows().stream()
                                                .filter(w -> w.getPosition().equals(windowPosition.get()))
                                                .findFirst();
                                        if (windowEntity.isPresent()) {
                                            final FixedWindowsComponent fixedComponent = (FixedWindowsComponent) windowEntity
                                                    .get()
                                                    .getTheComponent(ComponentType.FIXEDWINDOWS).get();

                                            if (!fixedComponent.isFixed()) {
                                                this.gameEngine.getGameController().fixWindows(KeyCode.Z,
                                                        windowPosition.get());
                                                fixedAnimation(windowPosition.get());
                                                pointsComponentOptional.ifPresent(c -> ((PointsComponent) c)
                                                        .addPoints(Constants.Felix.FIXED_WINDOW_POINTS));

                                                if (this.felixView != null) {
                                                    root.getChildren().remove(this.felixView.getImageView());
                                                }
                                                this.felixView = this.addFelixView();
                                            }
                                        }
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                            LOGGER.log(Level.SEVERE, "An error occurred", e);
                        } finally {
                            zKeyPressed = false;
                        }
                    });
                    timerThread.start();
                    scene.setOnKeyReleased(releasedEvent -> {
                        if (releasedEvent.getCode() == KeyCode.Z) {
                            zKeyPressed = false;
                        }
                    });
                    break;
                default:
                    break;
            }
        });

        final AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (GameState.getGameState().equals(GameState.GAMEOVER)
                        || GameState.getGameState().equals(GameState.WIN)) {
                    this.stop();
                } else if (!GameState.getGameState().equals(GameState.PAUSED)) {
                    gameEngine.gameLoop(WindowGame.this);
                }
            }
        };
        animationTimer.start();
        primaryStage.setOnCloseRequest(event -> {
            LOGGER.info("Game closed");
            animationTimer.stop();
        });
    }

    /**
     * Creates and adds a grid of windows to the game.
     * 
     * @param broken the number of broken windows to be displayed.
     */
    private void addWindowsGrid(final int broken) {
        final Set<Entity> entities = this.gameEngine.getGameController().getWindowsController().windowsGrid(broken);
        entities.forEach(w -> {
            final WindowsView windowView = new WindowsView(w.getPosition());
            final FixedWindowsComponent fixComp = (FixedWindowsComponent) w.getTheComponent(ComponentType.FIXEDWINDOWS)
                    .get();
            if (fixComp.isFixed()) {
                root.getChildren().add(windowView.fixedwindows());
            } else {
                root.getChildren().add(windowView.brokenWindow());
            }
        });
    }

    /**
     * Plays the fixed animation for a window at the specified position.
     * 
     * @param windowPosition the position of the window to animate.
     */
    private void fixedAnimation(final Pair<Double, Double> windowPosition) {
        final List<Entity> windows = gameEngine.getGameController().getGamePerformance().getWindows();
        windows.stream()
                .filter(w -> w.getPosition().equals(windowPosition))
                .findFirst()
                .ifPresent(window -> {
                    final WindowsView windowView = new WindowsView(window.getPosition());
                    windowView.fixAnimation();
                    root.getChildren().add(windowView.fixedwindows());
                });
    }

    /**
     * Creates and adds a FelixView to the root pane.
     * 
     * @return the created FelixView.
     */
    private FelixView addFelixView() {
        final Entity felix = this.gameEngine.getGameController().getFelixController().getFelix();
        final FelixView felixView = new FelixView(felix);
        root.getChildren().add(felixView.getStandingFelix());
        return felixView;
    }

    /**
     * Creates and adds a RalphView to the root pane.
     * 
     * @return the created RalphView.
     */
    private RalphView addRalphView() {
        final Entity ralph = this.gameEngine.getGameController().getRalphController().getRalph();
        final RalphView ralphView = new RalphView(ralph);
        root.getChildren().add(ralphView.getStandingRalph());
        return ralphView;
    }

    /**
     * Updates the bird views in the game.
     */
    public void updateBird() {
        final Set<Entity> birds = this.gameEngine.getGameController().getBirdController().getBirds();
        birds.forEach(bird -> {
            final BirdView existingBirdView = birdsToPrint.stream()
                    .filter(view -> view.getBird().equals(bird))
                    .findFirst()
                    .orElse(null);
            if (existingBirdView == null) {
                final BirdView newBirdView = new BirdView(bird);
                root.getChildren().add(newBirdView.getImageView());
                birdsToPrint.add(newBirdView);
            } else {
                existingBirdView.animate();
            }
        });
        birdsToPrint.removeIf(birdview -> {
            if (birdview == null) {
                return false;
            }
            if (!birds.contains(birdview.getBird())) {
                root.getChildren().remove(birdview.getImageView());
                return true;
            }
            return false;
        });
        birdsToPrint.forEach(BirdView::animate);
    }

    /**
     * Updates the cake views in the game.
     */
    public void updateCake() {
        final Set<Entity> cakes = this.gameEngine.getGameController().getCakeController().getCakes();
        cakes.forEach(cake -> {
            final CakeView existCakeView = cakesToPrint.stream()
                    .filter(view -> view.getCake().equals(cake))
                    .findFirst()
                    .orElse(null);
            if (existCakeView == null) {
                final CakeView newCakeView = new CakeView(cake);
                root.getChildren().add(newCakeView.getImageView());
                cakesToPrint.add(newCakeView);
            } else {
                existCakeView.animate();
            }
        });

        cakesToPrint.removeIf(cakeView -> {
            if (cakeView == null) {
                return false;
            }
            if (!cakes.contains(cakeView.getCake())) {
                root.getChildren().remove(cakeView.getImageView());
                return true;
            }
            return false;
        });

        cakesToPrint.forEach(CakeView::animate);
    }

    /**
     * Updates the brick views in the game.
     */
    public void update() {
        final Set<Entity> bricks = this.gameEngine.getGameController().getBrickController().getBricks();
        bricks.forEach(brick -> {
            final BrickView existingBrickView = bricksToPrint.stream()
                    .filter(view -> view.getBrick().equals(brick))
                    .findFirst()
                    .orElse(null);

            if (existingBrickView == null) {
                final BrickView newBrickView = new BrickView(brick);
                root.getChildren().add(newBrickView.getImageView());
                bricksToPrint.add(newBrickView);
            } else {
                existingBrickView.animate();
            }
        });

        bricksToPrint.removeIf(brickView -> {
            if (!bricks.contains(brickView.getBrick())) {
                root.getChildren().remove(brickView.getImageView());
                return true;
            }
            return false;
        });

        if (ralphView == null) {
            ralphView = addRalphView();
        }
        ralphView.animate();
        bricksToPrint.forEach(BrickView::animate);
    }

    /**
     * Getter for the primary stage.
     * 
     * @return the primary stage.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public Stage getStage() {
        return this.primaryStage;
    }

    /**
     * Getter for the game engine.
     * 
     * @return the game engine.
     */
    public GameEngineImpl getGameEngine() {
        return this.gameEngine;
    }
}
