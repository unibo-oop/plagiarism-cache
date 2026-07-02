package it.dpg.minigames.punchygame.view;

import it.dpg.minigames.base.view.AbstractMinigameView;
import it.dpg.minigames.punchygame.controller.input.InputObserver;
import it.dpg.minigames.punchygame.controller.input.PunchLeft;
import it.dpg.minigames.punchygame.controller.input.PunchRight;
import it.dpg.minigames.punchygame.model.Direction;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.apache.commons.lang3.tuple.Pair;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation for PunchygameView and extension of AbstractMinigameView,
 * that represents the entire view for PunchyMinigame
 * @author Davide Picchiotti
 * @see AbstractMinigameView
 * @see PunchygameView
 * @see it.dpg.minigames.punchygame.PunchyMinigame
 * */

public class PunchygameViewImpl extends AbstractMinigameView implements PunchygameView {

    private static final double WIDTH = 1200;
    private static final double HEIGHT = 800;
    private static final int SACKS_SLOT = 6;
    private static final Color BG_COLOR = Color.WHITE;
    private static final Color SACK_COLOR = Color.CRIMSON;

    private static final double UNIT = WIDTH/48;
    private static final double SACK_WIDTH = WIDTH/12 + UNIT;
    private static final double SACK_HEIGHT = HEIGHT/2 - 4*UNIT;
    private static final double CHAR_WIDTH = WIDTH/12 + 2*UNIT;
    private static final double CHAR_HEIGHT = HEIGHT/2;

    private static final String SCORE_STRING = "SCORE: ";
    private static final String COMBO_STRING = "\nCOMBO: x";
    private static final String TIMER_STRING = "TIMER: ";
    private static final String PUNCH_IMAGE = "images/punchygame/punch.png";
    private static final String PUNCH_AUDIO = "sounds/punchygame/punch_sound.mp3";
    private static final String MISS_SOUND = "sounds/punchygame/miss_sound.mp3";

    private Text scoreText;
    private Text timerText;
    private ImageView charView;
    private AudioClip punchAudio;
    private AudioClip missSound;
    private List<Pair<Rectangle, Rectangle>> sacksPair;
    private InputObserver observer;

    public PunchygameViewImpl() {
        scoreText = new Text();
        timerText = new Text();
        charView = new ImageView();
        punchAudio = new AudioClip(ClassLoader.getSystemResource(PUNCH_AUDIO).toString());
        missSound = new AudioClip(ClassLoader.getSystemResource(MISS_SOUND).toString());
        sacksPair = new ArrayList<>();
    }

    @Override
    protected Scene createScene() {
        Scene scene = new Scene(createGroup(), WIDTH, HEIGHT, BG_COLOR);

        scene.setOnKeyPressed(k -> {
            if(k.getCode() == KeyCode.LEFT) {
                observer.notifyInput(new PunchLeft());
            } else if(k.getCode() == KeyCode.RIGHT) {
                observer.notifyInput(new PunchRight());
            }
        });

        return scene;
    }

    @Override
    public void updateSacks(final List<Direction> sacks) {
        sacksPair.forEach(sp -> {
            int i = sacksPair.indexOf(sp);
            if(sacks.get(i) == Direction.LEFT) {
                paintSacks(i, SACK_COLOR, BG_COLOR);
            } else {
                paintSacks(i, BG_COLOR, SACK_COLOR);
            }
        });
    }

    @Override
    public void updateScore(final int score, final int combo) {
        Platform.runLater(
                () -> scoreText.setText(
                        new StringBuilder(SCORE_STRING)
                                .append(score)
                                .append(COMBO_STRING)
                                .append(combo)
                                .toString()
                )
        );
    }

    @Override
    public void updateTimer(final float timer) {
        Platform.runLater(
                () -> timerText.setText(TIMER_STRING.concat(new DecimalFormat("#.0").format(timer)))
        );
    }

    @Override
    public void updateBoxer(final Direction boxerDirection) {
        if(boxerDirection == Direction.LEFT) {
            Platform.runLater(
                    () -> charView.setScaleX(-1)
            );
        } else {
            Platform.runLater(
                    () -> charView.setScaleX(1)
            );
        }
    }

    @Override
    public void playPunchSound() {
        punchAudio.play(0.2);
    }

    @Override
    public void playMissSound() {
        missSound.play(0.2);
    }

    @Override
    public void setInputObserver(final InputObserver observer) {
        this.observer = observer;
    }

    private Group createGroup() {
        Group g = new Group();

        scoreText = new Text(UNIT, 20, SCORE_STRING);
        scoreText.setFont(new Font(20));
        g.getChildren().add(scoreText);

        timerText = new Text(WIDTH - 5*UNIT, 20, TIMER_STRING);
        timerText.setFont(new Font(20));
        g.getChildren().add(timerText);

        List<Rectangle> sacks = setupSacks();
        g.getChildren().addAll(sacks);

        sacksPair = setupSacksPair(sacks);

        Image charImage = new Image(ClassLoader.getSystemResourceAsStream(PUNCH_IMAGE), CHAR_WIDTH, CHAR_HEIGHT, false, false);
        charView = new ImageView(charImage);
        charView.setX(WIDTH/2 - 3*UNIT);
        charView.setY(CHAR_HEIGHT);
        g.getChildren().add(charView);

        return g;
    }

    private List<Pair<Rectangle, Rectangle>> setupSacksPair(final List<Rectangle> sacks) {
        int firstLeft = SACKS_SLOT/2 - 1;
        int firstRight = SACKS_SLOT/2;
        List<Pair<Rectangle, Rectangle>> pair = new ArrayList<>();
        while(firstLeft >= 0 && firstRight < sacks.size()) {
            pair.add(Pair.of(sacks.get(firstLeft), sacks.get(firstRight)));
            firstLeft--;
            firstRight++;
        }

        return pair;
    }

    private List<Rectangle> setupSacks() {
        List<Rectangle> sacks = Stream
                .generate(() -> new Rectangle(SACK_WIDTH, SACK_HEIGHT, BG_COLOR))
                .peek(r -> {
                    r.setArcHeight(20);
                    r.setArcWidth(20);
                    r.setY(HEIGHT/2);
                })
                .limit(SACKS_SLOT)
                .collect(Collectors.toList());

        double startX = 2*UNIT;
        for(int i = 1; i <= SACKS_SLOT; i++) {
            sacks.get(i-1).setX(startX);
            sacks.get(i-1).setY(HEIGHT/2);

            if(i == SACKS_SLOT/2) {
                startX += CHAR_WIDTH + SACK_WIDTH;
            } else {
                startX += SACK_WIDTH + 2*UNIT;
            }
        }

        return sacks;
    }

    private void paintSacks(final int index, final Color leftColor, final Color rightColor) {
        Platform.runLater(() -> {
            this.sacksPair.get(index).getRight().setFill(rightColor);
            this.sacksPair.get(index).getLeft().setFill(leftColor);
        });
    }
}
