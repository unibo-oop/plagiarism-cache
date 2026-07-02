package view.pointsrecap;

import java.util.List;
import java.util.Map;

import controller.Controller;
import enums.GameMode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.common.Counter;
import model.enemy.Enemy;
import view.JavaFXView;

/**
 * This class create a Panel that display the points for the Points
 * Recapitulation Scene. The class extends the JavaFX's class AnchorPane.
 */
public final class PointsRecapPanel extends BorderPane {

    // Generic Magic Numbers.
    private static final double PANEL_PREF_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double PANEL_PREF_HEIGHT = JavaFXView.STAGE_DIMESNION;
    private static final double BAR_LAYOUT_X = JavaFXView.STAGE_DIMESNION / 2.85;
    private static final double BAR_LAYOUT_Y = JavaFXView.STAGE_DIMESNION / 1.36;
    private static final int DURATION_ANIMATIONS_MS = 100;
    private static final String POINTS = "PTS";

    // Top Magic Numbers.
    private static final double TOP_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double TOP_HEIGHT = JavaFXView.STAGE_DIMESNION / 6.0;
    private static final double SPACING_TOP = JavaFXView.STAGE_DIMESNION / 30.0;
    private static final double SPACING_HI_SCORE = JavaFXView.STAGE_DIMESNION / 25.0;

    // Right Magic Numbers.
    private static final double RIGHT_WIDTH = JavaFXView.STAGE_DIMESNION / 3.0;
    private static final double RIGHT_HEIGHT = JavaFXView.STAGE_DIMESNION - TOP_HEIGHT;
    private static final double SPACING_RIGHT = JavaFXView.STAGE_DIMESNION / 20.0;
    private static final double RIGHT_POINTS_SPACING = JavaFXView.STAGE_DIMESNION / 30.0;

    // Bottom Magic Numbers.
    private static final double BOTTOM_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double BOTTOM_HEIGHT = JavaFXView.STAGE_DIMESNION / 10.0;

    // Center Magic Numbers.
    private static final double CENTER_WIDTH = JavaFXView.STAGE_DIMESNION / 3.0;
    private static final double CENTER_HEIGHT = JavaFXView.STAGE_DIMESNION - TOP_HEIGHT - BOTTOM_HEIGHT;
    private static final double SPACING_CENTER = JavaFXView.STAGE_DIMESNION / 27.0;
    private static final double CENTER_BOX_SPACING = JavaFXView.STAGE_DIMESNION / 60.0;
    private static final double CENTER_INSET_TOP = 0.0;
    private static final double CENTER_INSET_RIGHT = 0.0;
    private static final double CENTER_INSET_BOTTOM = JavaFXView.STAGE_DIMESNION / 13.0;
    private static final double CENTER_INSET_LEFT = 0.0;

    // Left Magic Numbers.
    private static final double LEFT_WIDTH = JavaFXView.STAGE_DIMESNION / 3.0;
    private static final double LEFT_HEIGHT = JavaFXView.STAGE_DIMESNION - TOP_HEIGHT - BOTTOM_HEIGHT;
    private static final double SPACING_LEFT = JavaFXView.STAGE_DIMESNION / 20.0;
    private static final double LEFT_POINTS_SPACING = JavaFXView.STAGE_DIMESNION / 30.0;

    // The controller of the game.
    private final Controller controller;
    // Boolean for the animation.
    private boolean animationCompleted;
    // Number of players.
    private int nPlayer;

    // All the points.
    private int hiScore;
    private int totalPointsP1;
    private int pointsNormalP1;
    private int pointsFastP1;
    private int pointsPowerP1;
    private int pointsArmorP1;
    private int totalPointsP2;
    private int pointsNormalP2;
    private int pointsFastP2;
    private int pointsPowerP2;
    private int pointsArmorP2;

    // All the tank destroyed.
    private int destroyedNormalTankP1;
    private int destroyedFastTankP1;
    private int destroyedPowerTankP1;
    private int destroyedArmorTankP1;
    private int destroyedTotalP1;
    private int destroyedNormalTankP2;
    private int destroyedFastTankP2;
    private int destroyedPowerTankP2;
    private int destroyedArmorTankP2;
    private int destroyedTotalP2;

    // All the deltas.
    private int deltaNormalTank;
    private int deltaFastTank;
    private int deltaPowerTank;
    private int deltaArmorTank;

    // All the text for points and tanks destroyed.
    private final Text textPointsNormalP1;
    private final Text textPointsFastP1;
    private final Text textPointsPowerP1;
    private final Text textPointsArmorP1;
    private final Text textDestroyedNormalP1;
    private final Text textDestroyedFastP1;
    private final Text textDestroyedPowerP1;
    private final Text textDestroyedArmorP1;
    private final Text textTotalP1;
    private final Text textPointsNormalP2;
    private final Text textPointsFastP2;
    private final Text textPointsPowerP2;
    private final Text textPointsArmorP2;
    private final Text textDestroyedNormalP2;
    private final Text textDestroyedFastP2;
    private final Text textDestroyedPowerP2;
    private final Text textDestroyedArmorP2;
    private final Text textTotalP2;

    // All the counters.
    private int counterPoints1P1;
    private int counterPoints2P1;
    private int counterPoints3P1;
    private int counterPoints4P1;
    private int counterTanks1P1;
    private int counterTanks2P1;
    private int counterTanks3P1;
    private int counterTanks4P1;
    private int counterTotalP1;
    private int counterPoints1P2;
    private int counterPoints2P2;
    private int counterPoints3P2;
    private int counterPoints4P2;
    private int counterTanks1P2;
    private int counterTanks2P2;
    private int counterTanks3P2;
    private int counterTanks4P2;
    private int counterTotalP2;

    // All the animations.
    private final Timeline animationPoints1P1;
    private final Timeline animationPoints2P1;
    private final Timeline animationPoints3P1;
    private final Timeline animationPoints4P1;
    private final Timeline animationTankDestroyed1P1;
    private final Timeline animationTankDestroyed2P1;
    private final Timeline animationTankDestroyed3P1;
    private final Timeline animationTankDestroyed4P1;
    private final Timeline animationTotalP1;
    private final Timeline animationPoints1P2;
    private final Timeline animationPoints2P2;
    private final Timeline animationPoints3P2;
    private final Timeline animationPoints4P2;
    private final Timeline animationTankDestroyed1P2;
    private final Timeline animationTankDestroyed2P2;
    private final Timeline animationTankDestroyed3P2;
    private final Timeline animationTankDestroyed4P2;
    private final Timeline animationTotalP2;

    /**
     * Constructor method.
     * 
     * @param controller the controller of the game.
     */
    public PointsRecapPanel(final Controller controller) {
        this.controller = controller;
        if (controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
            this.nPlayer = 2;
        } else {
            this.nPlayer = 1;
        }
        this.animationCompleted = false;
        textPointsNormalP1 = PointsRecapUtils.createText("  ", Color.WHITE);
        textPointsFastP1 = PointsRecapUtils.createText("  ", Color.WHITE);
        textPointsPowerP1 = PointsRecapUtils.createText("  ", Color.WHITE);
        textPointsArmorP1 = PointsRecapUtils.createText("  ", Color.WHITE);
        textDestroyedNormalP1 = PointsRecapUtils.createText("  ", Color.WHITE);
        textDestroyedFastP1 = PointsRecapUtils.createText("  ", Color.WHITE);
        textDestroyedPowerP1 = PointsRecapUtils.createText("  ", Color.WHITE);
        textDestroyedArmorP1 = PointsRecapUtils.createText("  ", Color.WHITE);
        textTotalP1 = PointsRecapUtils.createText("  ", Color.WHITE);
        textPointsNormalP2 = PointsRecapUtils.createText("  ", Color.WHITE);
        textPointsFastP2 = PointsRecapUtils.createText("  ", Color.WHITE);
        textPointsPowerP2 = PointsRecapUtils.createText("  ", Color.WHITE);
        textPointsArmorP2 = PointsRecapUtils.createText("  ", Color.WHITE);
        textDestroyedNormalP2 = PointsRecapUtils.createText("  ", Color.WHITE);
        textDestroyedFastP2 = PointsRecapUtils.createText("  ", Color.WHITE);
        textDestroyedPowerP2 = PointsRecapUtils.createText("  ", Color.WHITE);
        textDestroyedArmorP2 = PointsRecapUtils.createText("  ", Color.WHITE);
        textTotalP2 = PointsRecapUtils.createText("  ", Color.WHITE);
        animationPoints1P1 = new Timeline();
        animationPoints2P1 = new Timeline();
        animationPoints3P1 = new Timeline();
        animationPoints4P1 = new Timeline();
        animationTankDestroyed1P1 = new Timeline();
        animationTankDestroyed2P1 = new Timeline();
        animationTankDestroyed3P1 = new Timeline();
        animationTankDestroyed4P1 = new Timeline();
        animationTotalP1 = new Timeline();
        animationPoints1P2 = new Timeline();
        animationPoints2P2 = new Timeline();
        animationPoints3P2 = new Timeline();
        animationPoints4P2 = new Timeline();
        animationTankDestroyed1P2 = new Timeline();
        animationTankDestroyed2P2 = new Timeline();
        animationTankDestroyed3P2 = new Timeline();
        animationTankDestroyed4P2 = new Timeline();
        animationTotalP2 = new Timeline();
        init();
    }

    /*
     * Method that initialize the panel.
     */
    private void init() {
        setPrefSize(PANEL_PREF_WIDTH, PANEL_PREF_HEIGHT);
        createBackground();
        setValues();
        createTop();
        createRight();
        createBottom();
        createLeft();
        createCenter();
        createBar();
        createAnimations();
        playAnimations();
    }

    /*
     * Method that set the background of the Scene.
     */
    private void createBackground() {
        final BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        final Background background = new Background(backgroundFill);
        setBackground(background);
    }

    /*
     * Method that sets the points and the tanks destroyed for the scene
     * recapitulation.
     */
    private void setValues() {
        this.hiScore = controller.getHiScore();
        final List<Map<Enemy, Counter>> destroyedTanks = controller.getGameStatus().getKilledTank();

        /* SET DELTAS */
        deltaNormalTank = Enemy.NORMAL.getPoints();
        deltaFastTank = Enemy.FAST.getPoints();
        deltaPowerTank = Enemy.POWER.getPoints();
        deltaArmorTank = Enemy.ARMORED.getPoints();

        /* CALCULATES VALUES FOR PLAYER ONE */
        if (controller.getGameMode().equals(GameMode.ONE_PLAYER)
                || controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
            final Map<Enemy, Counter> destroyedTanksPlayerOne = destroyedTanks.get(0);
            destroyedNormalTankP1 = destroyedTanksPlayerOne.get(Enemy.NORMAL).getValue();
            pointsNormalP1 = destroyedNormalTankP1 * deltaNormalTank;
            destroyedFastTankP1 = destroyedTanksPlayerOne.get(Enemy.FAST).getValue();
            pointsFastP1 = destroyedFastTankP1 * deltaFastTank;
            destroyedPowerTankP1 = destroyedTanksPlayerOne.get(Enemy.POWER).getValue();
            pointsPowerP1 = destroyedPowerTankP1 * deltaPowerTank;
            destroyedArmorTankP1 = destroyedTanksPlayerOne.get(Enemy.ARMORED).getValue();
            pointsArmorP1 = destroyedArmorTankP1 * deltaArmorTank;
            destroyedTotalP1 = destroyedNormalTankP1 + destroyedFastTankP1 + destroyedPowerTankP1
                    + destroyedArmorTankP1;
            totalPointsP1 = pointsNormalP1 + pointsFastP1 + pointsPowerP1 + pointsArmorP1;
            // Try to set highest score.
            controller.setMyHiScore(totalPointsP1);
            controller.setHiScore(totalPointsP1);
        }
        /* CALCULATES VALUES FOR PLAYER TWO */
        if (controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
            final Map<Enemy, Counter> destroyedTanksPlayerTwo = destroyedTanks.get(1);
            destroyedNormalTankP2 = destroyedTanksPlayerTwo.get(Enemy.NORMAL).getValue();
            pointsNormalP2 = destroyedNormalTankP2 * deltaNormalTank;
            destroyedFastTankP2 = destroyedTanksPlayerTwo.get(Enemy.FAST).getValue();
            pointsFastP2 = destroyedFastTankP2 * deltaFastTank;
            destroyedPowerTankP2 = destroyedTanksPlayerTwo.get(Enemy.POWER).getValue();
            pointsPowerP2 = destroyedPowerTankP2 * deltaPowerTank;
            destroyedArmorTankP2 = destroyedTanksPlayerTwo.get(Enemy.ARMORED).getValue();
            pointsArmorP2 = destroyedArmorTankP2 * deltaArmorTank;
            destroyedTotalP2 = destroyedNormalTankP2 + destroyedFastTankP2 + destroyedPowerTankP2
                    + destroyedArmorTankP2;
            totalPointsP2 = pointsNormalP2 + pointsFastP2 + pointsPowerP2 + pointsArmorP2;
            // Try to set highest score.
            controller.setMyHiScore(totalPointsP2);
            controller.setHiScore(totalPointsP2);
        }
    }

    /*
     * Method that creates the top of the scene.
     */
    private void createTop() {
        final VBox topPane = new VBox(SPACING_TOP);
        topPane.setPrefWidth(TOP_WIDTH);
        topPane.setPrefHeight(TOP_HEIGHT);
        topPane.setAlignment(Pos.CENTER);

        final Text hiScore = PointsRecapUtils.createText("HI-SCORE", Color.RED);
        final Text scoreNumber = PointsRecapUtils.createText(Integer.toString(this.hiScore), Color.ORANGE);
        final HBox totalText = new HBox(SPACING_HI_SCORE);
        totalText.setAlignment(Pos.CENTER);
        totalText.getChildren().addAll(hiScore, scoreNumber);

        final Text stage = PointsRecapUtils.createText("STAGE " + Integer.toString(controller.getCurrentStageNumber()),
                Color.WHITE);

        topPane.getChildren().addAll(totalText, stage);
        setTop(topPane);
    }

    /*
     * Method that create the right of the scene. Displayed only when two players
     * mode is active.
     */
    private void createRight() {
        final VBox rightPane = new VBox(SPACING_RIGHT);
        rightPane.setPrefWidth(RIGHT_WIDTH);
        rightPane.setPrefHeight(RIGHT_HEIGHT);
        rightPane.setAlignment(Pos.CENTER_RIGHT);

        if (nPlayer == 2) {
            final Text player = PointsRecapUtils.createText("II-PLAYER", Color.RED);
            final Text score = PointsRecapUtils.createText(Integer.toString(this.totalPointsP2), Color.ORANGE);
            final Text pts1 = PointsRecapUtils.createText(POINTS, Color.WHITE);
            final Text pts2 = PointsRecapUtils.createText(POINTS, Color.WHITE);
            final Text pts3 = PointsRecapUtils.createText(POINTS, Color.WHITE);
            final Text pts4 = PointsRecapUtils.createText(POINTS, Color.WHITE);
            final Text total = PointsRecapUtils.createText("TOTAL", Color.TRANSPARENT);

            final HBox hbPoints1 = new HBox(RIGHT_POINTS_SPACING);
            hbPoints1.setAlignment(Pos.CENTER_RIGHT);
            hbPoints1.getChildren().addAll(textPointsNormalP2, pts1);
            final HBox hbPoints2 = new HBox(RIGHT_POINTS_SPACING);
            hbPoints2.setAlignment(Pos.CENTER_RIGHT);
            hbPoints2.getChildren().addAll(textPointsFastP2, pts2);
            final HBox hbPoints3 = new HBox(RIGHT_POINTS_SPACING);
            hbPoints3.setAlignment(Pos.CENTER_RIGHT);
            hbPoints3.getChildren().addAll(textPointsPowerP2, pts3);
            final HBox hbPoints4 = new HBox(RIGHT_POINTS_SPACING);
            hbPoints4.setAlignment(Pos.CENTER_RIGHT);
            hbPoints4.getChildren().addAll(textPointsArmorP2, pts4);

            rightPane.getChildren().addAll(player, score, hbPoints1, hbPoints2, hbPoints3, hbPoints4, total);
        }

        setRight(rightPane);
    }

    /*
     * Method that creates the bottom of the scene. Almost nothing is done.
     */
    private void createBottom() {
        final VBox bottomPane = new VBox();
        bottomPane.setPrefWidth(BOTTOM_WIDTH);
        bottomPane.setPrefHeight(BOTTOM_HEIGHT);
        bottomPane.setAlignment(Pos.BOTTOM_CENTER);
        setBottom(bottomPane);
    }

    /*
     * Method that creates the left of the scene. This part is relative of the first
     * player.
     */
    private void createLeft() {
        final VBox leftPane = new VBox(SPACING_LEFT);
        leftPane.setPrefWidth(LEFT_WIDTH);
        leftPane.setPrefHeight(LEFT_HEIGHT);
        leftPane.setAlignment(Pos.CENTER_RIGHT);

        final Text player = PointsRecapUtils.createText("I-PLAYER", Color.RED);
        final Text score = PointsRecapUtils.createText(Integer.toString(this.totalPointsP1), Color.ORANGE);
        final Text pts1 = PointsRecapUtils.createText(POINTS, Color.WHITE);
        final Text pts2 = PointsRecapUtils.createText(POINTS, Color.WHITE);
        final Text pts3 = PointsRecapUtils.createText(POINTS, Color.WHITE);
        final Text pts4 = PointsRecapUtils.createText(POINTS, Color.WHITE);
        final Text total = PointsRecapUtils.createText("TOTAL", Color.WHITE);

        final HBox hbPoints1 = new HBox(LEFT_POINTS_SPACING);
        hbPoints1.setAlignment(Pos.CENTER_RIGHT);
        hbPoints1.getChildren().addAll(textPointsNormalP1, pts1);
        final HBox hbPoints2 = new HBox(LEFT_POINTS_SPACING);
        hbPoints2.setAlignment(Pos.CENTER_RIGHT);
        hbPoints2.getChildren().addAll(textPointsFastP1, pts2);
        final HBox hbPoints3 = new HBox(LEFT_POINTS_SPACING);
        hbPoints3.setAlignment(Pos.CENTER_RIGHT);
        hbPoints3.getChildren().addAll(textPointsPowerP1, pts3);
        final HBox hbPoints4 = new HBox(LEFT_POINTS_SPACING);
        hbPoints4.setAlignment(Pos.CENTER_RIGHT);
        hbPoints4.getChildren().addAll(textPointsArmorP1, pts4);

        leftPane.getChildren().addAll(player, score, hbPoints1, hbPoints2, hbPoints3, hbPoints4, total);
        setLeft(leftPane);
    }

    /*
     * Method that creates the center of the scene. This part displays the tank
     * destroyed.
     */
    private void createCenter() {
        final VBox centerPane = new VBox(SPACING_CENTER);
        centerPane.setPadding(new Insets(CENTER_INSET_TOP, CENTER_INSET_RIGHT, CENTER_INSET_BOTTOM, CENTER_INSET_LEFT));
        centerPane.setPrefWidth(CENTER_WIDTH);
        centerPane.setPrefHeight(CENTER_HEIGHT);
        centerPane.setAlignment(Pos.BOTTOM_CENTER);

        final HBox hbNormalTank = new HBox(CENTER_BOX_SPACING);
        final ImageView rightArrow1 = PointsRecapUtils.getRightArrowImageView();
        hbNormalTank.setAlignment(Pos.BOTTOM_CENTER);
        hbNormalTank.getChildren().addAll(textDestroyedNormalP1, PointsRecapUtils.getLeftArrowImageView(),
                PointsRecapUtils.getNormalTankImageView(), rightArrow1, textDestroyedNormalP2);

        final HBox hbFastTank = new HBox(CENTER_BOX_SPACING);
        final ImageView rightArrow2 = PointsRecapUtils.getRightArrowImageView();
        hbFastTank.setAlignment(Pos.BOTTOM_CENTER);
        hbFastTank.getChildren().addAll(textDestroyedFastP1, PointsRecapUtils.getLeftArrowImageView(),
                PointsRecapUtils.getFastTankImageView(), rightArrow2, textDestroyedFastP2);

        final HBox hbPowerTank = new HBox(CENTER_BOX_SPACING);
        final ImageView rightArrow3 = PointsRecapUtils.getRightArrowImageView();
        hbPowerTank.setAlignment(Pos.BOTTOM_CENTER);
        hbPowerTank.getChildren().addAll(textDestroyedPowerP1, PointsRecapUtils.getLeftArrowImageView(),
                PointsRecapUtils.getPowerTankImageView(), rightArrow3, textDestroyedPowerP2);

        final HBox hbArmoredTank = new HBox(CENTER_BOX_SPACING);
        final ImageView rightArrow4 = PointsRecapUtils.getRightArrowImageView();
        hbArmoredTank.setAlignment(Pos.BOTTOM_CENTER);
        hbArmoredTank.getChildren().addAll(textDestroyedArmorP1, PointsRecapUtils.getLeftArrowImageView(),
                PointsRecapUtils.getArmorTankImageView(), rightArrow4, textDestroyedArmorP2);

        final HBox total = new HBox(CENTER_BOX_SPACING);
        total.setAlignment(Pos.BOTTOM_CENTER);
        final ImageView falseTank = PointsRecapUtils.getRightArrowImageView();
        falseTank.setVisible(false);
        total.getChildren().addAll(textTotalP1, PointsRecapUtils.getRightArrowImageView(), falseTank,
                PointsRecapUtils.getRightArrowImageView(), textTotalP2);

        if (nPlayer == 2) {
            rightArrow1.setVisible(true);
            rightArrow2.setVisible(true);
            rightArrow3.setVisible(true);
            rightArrow4.setVisible(true);
        }

        centerPane.getChildren().addAll(hbNormalTank, hbFastTank, hbPowerTank, hbArmoredTank, total);
        setCenter(centerPane);
    }

    /*
     * Method that place the white bar for the total.
     */
    private void createBar() {
        final ImageView bar = PointsRecapUtils.getWhitebar();
        bar.setLayoutX(BAR_LAYOUT_X);
        bar.setLayoutY(BAR_LAYOUT_Y);
        this.getChildren().add(bar);
    }

    /*
     * Method that animates the points and the tanks destroyed counters.
     */
    private void createAnimations() {
        /* ANIMATION FOR NORMAL TANK POINTS ON THE LEFT */
        animationPoints1P1.setCycleCount(Timeline.INDEFINITE);
        animationPoints1P1.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
            textPointsNormalP1.setText(Integer.toString(counterPoints1P1));
            counterPoints1P1 += deltaNormalTank;
            if (counterPoints1P1 >= (pointsNormalP1 + deltaNormalTank)) {
                animationPoints1P1.stop();
                animationPoints2P1.play();
            }
        }));
        /* ANIMATION FOR FAST TANK POINTS ON THE LEFT */
        animationPoints2P1.setCycleCount(Timeline.INDEFINITE);
        animationPoints2P1.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
            textPointsFastP1.setText(Integer.toString(counterPoints2P1));
            counterPoints2P1 += deltaFastTank;
            if (counterPoints2P1 >= (pointsFastP1 + deltaFastTank)) {
                animationPoints2P1.stop();
                animationPoints3P1.play();
            }
        }));
        /* ANIMATION FOR POWER TANK POINTS ON THE LEFT */
        animationPoints3P1.setCycleCount(Timeline.INDEFINITE);
        animationPoints3P1.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
            textPointsPowerP1.setText(Integer.toString(counterPoints3P1));
            counterPoints3P1 += deltaPowerTank;
            if (counterPoints3P1 >= (pointsPowerP1 + deltaPowerTank)) {
                animationPoints3P1.stop();
                animationPoints4P1.play();
            }
        }));
        /* ANIMATION FOR ARMORED TANK POINTS ON THE LEFT */
        animationPoints4P1.setCycleCount(Timeline.INDEFINITE);
        animationPoints4P1.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
            textPointsArmorP1.setText(Integer.toString(counterPoints4P1));
            counterPoints4P1 += deltaArmorTank;
            if (counterPoints4P1 >= (pointsArmorP1 + deltaArmorTank)) {
                animationPoints4P1.stop();
            }
        }));
        /* ANIMATION FOR NORMAL TANK DESTROYED ON THE LEFT */
        animationTankDestroyed1P1.setCycleCount(Timeline.INDEFINITE);
        animationTankDestroyed1P1.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
            if (counterTanks1P1 < 10) {
                textDestroyedNormalP1.setText(" " + Integer.toString(counterTanks1P1));
            } else {
                textDestroyedNormalP1.setText(Integer.toString(counterTanks1P1));
            }
            counterTanks1P1 += 1;
            if (counterTanks1P1 >= (destroyedNormalTankP1 + 1)) {
                animationTankDestroyed1P1.stop();
                animationTankDestroyed2P1.play();
            }
        }));
        /* ANIMATION FOR FAST TANK DESTROYED ON THE LEFT */
        animationTankDestroyed2P1.setCycleCount(Timeline.INDEFINITE);
        animationTankDestroyed2P1.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
            if (counterTanks2P1 < 10) {
                textDestroyedFastP1.setText(" " + Integer.toString(counterTanks2P1));
            } else {
                textDestroyedFastP1.setText(Integer.toString(counterTanks2P1));
            }
            counterTanks2P1 += 1;
            if (counterTanks2P1 >= (destroyedFastTankP1 + 1)) {
                animationTankDestroyed2P1.stop();
                animationTankDestroyed3P1.play();
            }
        }));
        /* ANIMATION FOR POWER TANK DESTROYED ON THE LEFT */
        animationTankDestroyed3P1.setCycleCount(Timeline.INDEFINITE);
        animationTankDestroyed3P1.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
            if (counterTanks3P1 < 10) {
                textDestroyedPowerP1.setText(" " + Integer.toString(counterTanks3P1));
            } else {
                textDestroyedPowerP1.setText(Integer.toString(counterTanks3P1));
            }
            counterTanks3P1 += 1;
            if (counterTanks3P1 >= (destroyedPowerTankP1 + 1)) {
                animationTankDestroyed3P1.stop();
                animationTankDestroyed4P1.play();
            }
        }));
        /* ANIMATION FOR ARMOR TANK DESTROYED ON THE LEFT */
        animationTankDestroyed4P1.setCycleCount(Timeline.INDEFINITE);
        animationTankDestroyed4P1.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
            if (counterTanks4P1 < 10) {
                textDestroyedArmorP1.setText(" " + Integer.toString(counterTanks4P1));
            } else {
                textDestroyedArmorP1.setText(Integer.toString(counterTanks4P1));
            }
            counterTanks4P1 += 1;
            if (counterTanks4P1 >= (destroyedArmorTankP1 + 1)) {
                animationTankDestroyed4P1.stop();
                animationTotalP1.play();
            }
        }));
        /* ANIMATION FOR TOTAL TANK DESTROYED ON THE LEFT */
        animationTotalP1.setCycleCount(Timeline.INDEFINITE);
        animationTotalP1.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
            if (counterTotalP1 < 10) {
                textTotalP1.setText(" " + Integer.toString(counterTotalP1));
            } else {
                textTotalP1.setText(Integer.toString(counterTotalP1));
            }
            counterTotalP1 += 1;
            if (counterTotalP1 >= (destroyedTotalP1 + 1)) {
                animationTotalP1.stop();
                if (nPlayer == 2) {
                    animationPoints1P2.play();
                    animationTankDestroyed1P2.play();
                } else {
                    animationCompleted = true;
                }
            }
        }));

        if (nPlayer == 2) {
            /* ANIMATION FOR NORMAL TANK POINTS ON THE RIGHT */
            animationPoints1P2.setCycleCount(Timeline.INDEFINITE);
            animationPoints1P2.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
                textPointsNormalP2.setText(Integer.toString(counterPoints1P2));
                counterPoints1P2 += deltaNormalTank;
                if (counterPoints1P2 >= (pointsNormalP2 + deltaNormalTank)) {
                    animationPoints1P2.stop();
                    animationPoints2P2.play();
                }
            }));
            /* ANIMATION FOR FAST TANK POINTS ON THE RIGHT */
            animationPoints2P2.setCycleCount(Timeline.INDEFINITE);
            animationPoints2P2.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
                textPointsFastP2.setText(Integer.toString(counterPoints2P2));
                counterPoints2P2 += deltaFastTank;
                if (counterPoints2P2 >= (pointsFastP2 + deltaFastTank)) {
                    animationPoints2P2.stop();
                    animationPoints3P2.play();
                }
            }));
            /* ANIMATION FOR POWER TANK POINTS ON THE RIGHT */
            animationPoints3P2.setCycleCount(Timeline.INDEFINITE);
            animationPoints3P2.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
                textPointsPowerP2.setText(Integer.toString(counterPoints3P2));
                counterPoints3P2 += deltaPowerTank;
                if (counterPoints3P2 >= (pointsPowerP2 + deltaPowerTank)) {
                    animationPoints3P2.stop();
                    animationPoints4P2.play();
                }
            }));
            /* ANIMATION FOR ARMORED TANK POINTS ON THE RIGHT */
            animationPoints4P2.setCycleCount(Timeline.INDEFINITE);
            animationPoints4P2.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
                textPointsArmorP2.setText(Integer.toString(counterPoints4P2));
                counterPoints4P2 += deltaArmorTank;
                if (counterPoints4P2 >= (pointsArmorP2 + deltaArmorTank)) {
                    animationPoints4P2.stop();
                }
            }));
            /* ANIMATION FOR NORMAL TANK DESTROYED ON THE RIGHT */
            animationTankDestroyed1P2.setCycleCount(Timeline.INDEFINITE);
            animationTankDestroyed1P2.getKeyFrames()
                    .add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
                        if (counterTanks1P2 < 10) {
                            textDestroyedNormalP2.setText(" " + Integer.toString(counterTanks1P2));
                        } else {
                            textDestroyedNormalP2.setText(Integer.toString(counterTanks1P2));
                        }
                        counterTanks1P2 += 1;
                        if (counterTanks1P2 >= (destroyedNormalTankP2 + 1)) {
                            animationTankDestroyed1P2.stop();
                            animationTankDestroyed2P2.play();
                        }
                    }));
            /* ANIMATION FOR FAST TANK DESTROYED ON THE RIGHT */
            animationTankDestroyed2P2.setCycleCount(Timeline.INDEFINITE);
            animationTankDestroyed2P2.getKeyFrames()
                    .add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
                        if (counterTanks2P2 < 10) {
                            textDestroyedFastP2.setText(" " + Integer.toString(counterTanks2P2));
                        } else {
                            textDestroyedFastP2.setText(Integer.toString(counterTanks2P2));
                        }
                        counterTanks2P2 += 1;
                        if (counterTanks2P2 >= (destroyedFastTankP2 + 1)) {
                            animationTankDestroyed2P2.stop();
                            animationTankDestroyed3P2.play();
                        }
                    }));
            /* ANIMATION FOR POWER TANK DESTROYED ON THE RIGHT */
            animationTankDestroyed3P2.setCycleCount(Timeline.INDEFINITE);
            animationTankDestroyed3P2.getKeyFrames()
                    .add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
                        if (counterTanks3P2 < 10) {
                            textDestroyedPowerP2.setText(" " + Integer.toString(counterTanks3P2));
                        } else {
                            textDestroyedPowerP2.setText(Integer.toString(counterTanks3P2));
                        }
                        counterTanks3P2 += 1;
                        if (counterTanks3P2 >= (destroyedPowerTankP2 + 1)) {
                            animationTankDestroyed3P2.stop();
                            animationTankDestroyed4P2.play();
                        }
                    }));
            /* ANIMATION FOR ARMOR TANK DESTROYED ON THE RIGHT */
            animationTankDestroyed4P2.setCycleCount(Timeline.INDEFINITE);
            animationTankDestroyed4P2.getKeyFrames()
                    .add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
                        if (counterTanks4P2 < 10) {
                            textDestroyedArmorP2.setText(" " + Integer.toString(counterTanks4P2));
                        } else {
                            textDestroyedArmorP2.setText(Integer.toString(counterTanks4P2));
                        }
                        counterTanks4P2 += 1;
                        if (counterTanks4P2 >= (destroyedArmorTankP2 + 1)) {
                            animationTankDestroyed4P2.stop();
                            animationTotalP2.play();
                        }
                    }));
            /* ANIMATION FOR TOTAL TANK DESTROYED ON THE RIGHT */
            animationTotalP2.setCycleCount(Timeline.INDEFINITE);
            animationTotalP2.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
                if (counterTotalP2 < 10) {
                    textTotalP2.setText(" " + Integer.toString(counterTotalP2));
                } else {
                    textTotalP2.setText(Integer.toString(counterTotalP2));
                }
                counterTotalP2 += 1;
                if (counterTotalP2 >= (destroyedTotalP2 + 1)) {
                    animationTotalP2.stop();
                    animationCompleted = true;
                }
            }));
        }
    }

    /*
     * Method that start the animation of the counters.
     */
    private void playAnimations() {
        animationPoints1P1.play();
        animationTankDestroyed1P1.play();
    }

    /**
     * Method that returns true if the points animation is completed, false
     * otherwise.
     * 
     * @return true if the points animation is completed, false otherwise.
     */
    public boolean isAnimationcompleted() {
        return this.animationCompleted;
    }

    /**
     * Method that completes all animations.
     */
    public void completeAnimation() {
        animationPoints1P1.stop();
        animationPoints2P1.stop();
        animationPoints3P1.stop();
        animationPoints4P1.stop();
        animationTankDestroyed1P1.stop();
        animationTankDestroyed2P1.stop();
        animationTankDestroyed3P1.stop();
        animationTankDestroyed4P1.stop();
        animationTotalP1.stop();
        animationPoints1P2.stop();
        animationPoints2P2.stop();
        animationPoints3P2.stop();
        animationPoints4P2.stop();
        animationTankDestroyed1P2.stop();
        animationTankDestroyed2P2.stop();
        animationTankDestroyed3P2.stop();
        animationTankDestroyed4P2.stop();
        animationTotalP2.stop();

        if (pointsNormalP1 < 10) {
            textPointsNormalP1.setText(" " + Integer.toString(pointsNormalP1));
        } else {
            textPointsNormalP1.setText(Integer.toString(pointsNormalP1));
        }
        if (pointsFastP1 < 10) {
            textPointsFastP1.setText(" " + Integer.toString(pointsFastP1));
        } else {
            textPointsFastP1.setText(Integer.toString(pointsFastP1));
        }
        if (pointsPowerP1 < 10) {
            textPointsPowerP1.setText(" " + Integer.toString(pointsPowerP1));
        } else {
            textPointsPowerP1.setText(Integer.toString(pointsPowerP1));
        }
        if (pointsArmorP1 < 10) {
            textPointsArmorP1.setText(" " + Integer.toString(pointsArmorP1));
        } else {
            textPointsArmorP1.setText(Integer.toString(pointsArmorP1));
        }
        if (destroyedNormalTankP1 < 10) {
            textDestroyedNormalP1.setText(" " + Integer.toString(destroyedNormalTankP1));
        } else {
            textDestroyedNormalP1.setText(Integer.toString(destroyedNormalTankP1));
        }
        if (destroyedFastTankP1 < 10) {
            textDestroyedFastP1.setText(" " + Integer.toString(destroyedFastTankP1));
        } else {
            textDestroyedFastP1.setText(Integer.toString(destroyedFastTankP1));
        }
        if (destroyedPowerTankP1 < 10) {
            textDestroyedPowerP1.setText(" " + Integer.toString(destroyedPowerTankP1));
        } else {
            textDestroyedPowerP1.setText(Integer.toString(destroyedPowerTankP1));
        }
        if (destroyedArmorTankP1 < 10) {
            textDestroyedArmorP1.setText(" " + Integer.toString(destroyedArmorTankP1));
        } else {
            textDestroyedArmorP1.setText(Integer.toString(destroyedArmorTankP1));
        }
        if (destroyedTotalP1 < 10) {
            textTotalP1.setText(" " + Integer.toString(destroyedTotalP1));
        } else {
            textTotalP1.setText(Integer.toString(destroyedTotalP1));
        }

        if (nPlayer == 2) {
            if (pointsNormalP2 < 10) {
                textPointsNormalP2.setText(" " + Integer.toString(pointsNormalP2));
            } else {
                textPointsNormalP2.setText(Integer.toString(pointsNormalP2));
            }
            if (pointsFastP2 < 10) {
                textPointsFastP2.setText(" " + Integer.toString(pointsFastP2));
            } else {
                textPointsFastP2.setText(Integer.toString(pointsFastP2));
            }
            if (pointsPowerP2 < 10) {
                textPointsPowerP2.setText(" " + Integer.toString(pointsPowerP2));
            } else {
                textPointsPowerP2.setText(Integer.toString(pointsPowerP2));
            }
            if (pointsArmorP2 < 10) {
                textPointsArmorP2.setText(" " + Integer.toString(pointsArmorP2));
            } else {
                textPointsArmorP2.setText(Integer.toString(pointsArmorP2));
            }
            if (destroyedNormalTankP2 < 10) {
                textDestroyedNormalP2.setText(" " + Integer.toString(destroyedNormalTankP2));
            } else {
                textDestroyedNormalP2.setText(Integer.toString(destroyedNormalTankP2));
            }
            if (destroyedFastTankP2 < 10) {
                textDestroyedFastP2.setText(" " + Integer.toString(destroyedFastTankP2));
            } else {
                textDestroyedFastP2.setText(Integer.toString(destroyedFastTankP2));
            }
            if (destroyedPowerTankP2 < 10) {
                textDestroyedPowerP2.setText(" " + Integer.toString(destroyedPowerTankP2));
            } else {
                textDestroyedPowerP2.setText(Integer.toString(destroyedPowerTankP2));
            }
            if (destroyedArmorTankP2 < 10) {
                textDestroyedArmorP2.setText(" " + Integer.toString(destroyedArmorTankP2));
            } else {
                textDestroyedArmorP2.setText(Integer.toString(destroyedArmorTankP2));
            }
            if (destroyedTotalP2 < 10) {
                textTotalP2.setText(" " + Integer.toString(destroyedTotalP2));
            } else {
                textTotalP2.setText(Integer.toString(destroyedTotalP2));
            }
        }

        animationCompleted = true;
    }

}
