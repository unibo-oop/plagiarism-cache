package boxhead.view;

import boxhead.controller.game.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EndView {

    private static final int HEIGHT = 480;
    private static final int WIDTH = 928;

    private static final int PLAY_LAYOUTX = 400;
    private static final int PLAY_LAYOUTY = 350;
    
    private static final int EXIT_LAYOUTX = 400;
    private static final int EXIT_LAYOUTY = 400;

    private static final int SOUND_WIDTH = 30;
    private static final int SOUND_HEIGHT = 30;
    private static final int SOUND_LAYOUTX = 700;
    private static final int SOUND_LAYOUTY = 50;

    private final AnchorPane pane;
    private final Scene scene;
    private Label playTime;
    private Label killCount;
    private Label maxStreak;

    /**
     * Construct a {@link EndView}.
     */
    public EndView() {
        this.pane = new AnchorPane();
        this.scene = new Scene(pane, WIDTH, HEIGHT);
        createBackGround();
        createButton();
        createLabel();
    }

    /**
     * 
     * @return the scene.
     */
    public final Scene getMenuScene() {
        return this.scene;
    }

    private void createBackGround() {
        final Image backgroundImage = new Image(getClass().getResourceAsStream("/backgroundMenu.png"), WIDTH, HEIGHT,
                false, true);

        final BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

        pane.setBackground(new Background(background));

    }
    
    private void createButton() {
        createPlayButton();
        createExitButton();
        createSoundButton();
    }

    private void createSoundButton() {
        final ImageView imgS = new ImageView(new Image(getClass().getResourceAsStream("/media/unmute.png")));
        final ImageView imgNS = new ImageView(new Image(getClass().getResourceAsStream("/media/mute.png")));
        imgS.setFitHeight(SOUND_HEIGHT);
        imgS.setFitWidth(SOUND_WIDTH);
        imgNS.setFitHeight(SOUND_HEIGHT);
        imgNS.setFitWidth(SOUND_WIDTH);

        final BoxheadButton button = new BoxheadButton("");
        if (GameState.soundOff == false) {
        	button.setGraphic(imgS);
        } else {
        	button.setGraphic(imgNS);
        }
        button.setLayoutY(SOUND_LAYOUTY);
        button.setLayoutX(SOUND_LAYOUTX);
        this.pane.getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (GameState.soundOff) {
                    button.setGraphic(imgS);
                } else {
                    button.setGraphic(imgNS);
                }
                GameState.soundOff = !GameState.soundOff;
            }

        });
    }

    private void createPlayButton() {
        final BoxheadButton playButton = new BoxheadButton("REPLAY");
        playButton.setLayoutX(PLAY_LAYOUTX);
        playButton.setLayoutY(PLAY_LAYOUTY);
        pane.getChildren().add(playButton);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
            	GameState.state = GameState.GameStateEnum.GAME;
            	GameState.change = true;
            	GameState.init = true;
            }
        });
    }
    
    private void createExitButton() {
        final BoxheadButton exitButton = new BoxheadButton("EXIT");
        exitButton.setLayoutX(EXIT_LAYOUTX);
        exitButton.setLayoutY(EXIT_LAYOUTY);
        pane.getChildren().add(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
            	GameState.state = GameState.GameStateEnum.END;
            	GameState.close = true;
            }
        });
    }
    
    /**
     * Method to assegnate the Labels to the pane.
     */
    private final void createLabel() {
    	this.playTime = new Label();
    	this.killCount = new Label();
    	this.maxStreak = new Label();
    	this.playTime.setLayoutX(700);
        this.playTime.setLayoutY(120);
        this.killCount.setLayoutX(700);
        this.killCount.setLayoutY(140);
        this.maxStreak.setLayoutX(700);
        this.maxStreak.setLayoutY(160);
    	this.pane.getChildren().add(this.playTime);
        this.pane.getChildren().add(this.killCount);
        this.pane.getChildren().add(this.maxStreak);
    }
    
    public final void renderPlayTime(final String time) {
    	this.playTime.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 13));
    	this.playTime.setText("You played for: " + time);
    }
    
    public final void renderKillCount(final String killCount) {
    	this.killCount.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 13));
    	this.killCount.setText("You killed about " + killCount + " zombies");
    }
    
    public final void renderMaxStreak(final String maxStreak) {
    	this.maxStreak.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 13));
    	this.maxStreak.setText("Your max streak was: " + maxStreak);
    }
}
