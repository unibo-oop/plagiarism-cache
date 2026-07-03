package com.geoquiz.view.menu;

import com.geoquiz.view.utility.Background;
import com.geoquiz.view.utility.ConfirmBox;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import com.geoquiz.controller.quiz.QuizController;
import com.geoquiz.controller.quiz.QuizControllerImpl;
import com.geoquiz.controller.quiz.TimeController;
import com.geoquiz.controller.ranking.Ranking;
import com.geoquiz.utility.Pair;
import com.geoquiz.view.button.MyButton;
import com.geoquiz.view.button.MyButtonFactory;
import com.geoquiz.view.label.MyLabel;
import com.geoquiz.view.label.MyLabelFactory;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The scene of game play.
 */
public class QuizGamePlay extends Scene {

    private static final Integer TIME = 10;
    private static final double POS_Y_BACK = 650;
    private static final double POS_1_X = 100;
    private static final double PB_OPACITY = 0.8;
    private static final double POS_PB_X = 200;
    private static final double POS_PB_Y = 50;
    private static final double PB_SIZE_X = 800;
    private static final double PB_SIZE_Y = 50;
    private static final double POS_X_ANSWERS = 350;
    private static final double POS_Y_ANSWERS = 300;
    private static final double POS_X_QUESTION = 350;
    private static final double POS_Y_QUESTION = 200;
    private static final double POS_X_HELPS_BOX = 850;
    private static final double POS_Y_HELPS_BOX = 550;
    private static final double QUESTION_FONT = 50;
    private static final double SCORE_FONT = 35;
    private static final double CATEGORY_FONT = 35;
    private static final double POS_X_SCORE_BOX = 1100;
    private static final double BUTTON_WIDTH = 350;
    private static final double HELPS_WIDTH = 400;
    private static final double ANSWERS_WIDTH = 550;
    private static final double POS_X_FLAG_IMAGE = 510;
    private static final double POS_Y_FLAG_IMAGE = 125;
    private static final double IMAGE_WIDTH = 250;
    private static final double IMAGE_HEIGHT = 150;
    private static final double POS_X_LIFE_BOX = 1100;
    private static final double POS_Y_LIFE_BOX = 50;
    private static final double POS_X_LABEL_BOX = 350;
    private static final double POS_Y_LABEL_BOX = 525;
    private static final double LABEL_FONT = 30;
    private static final double SLEEP = 1500;

    private final QuizController controller;
    private final TimeController timeController;
    private final Ranking ranking = Ranking.getInstance();

    private final Pane panel = new Pane();

    private final ProgressBar pb = new ProgressBar(0);
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(TIME * 100);
    private final Label timerLabel = new Label();
    private Timeline timeline;
    private Timeline freezeTimeline;
    private Timeline pauseTimeline;
    private final Text title = new Text(CategoryScene.getCategoryPressed().toString());
    private final VBox backBox = new VBox();
    private final VBox hboxBar = new VBox(20, pb, timerLabel);
    private final VBox answersBox = new VBox(5.0);
    private final VBox questionBox = new VBox();
    private final VBox categoryBox = new VBox(title);
    private final Label lifeLabel = new Label();
    private List<MyButton> answers;
    private final Label scoreLabel = new Label();
    private final MyLabel questionLabel;
    private ImageView flag;
    private final Stage mainStage;
    private final Label label = new Label();
    private boolean isAnswerCorrect;
    private boolean isFirstQuestion = true;

    /**
     * @param mainStage
     *            the stage where the scene is called.
     * @throws JAXBException
     *             for xml exception.
     * @throws IOException
     *             for exception
     */
    public QuizGamePlay(final Stage mainStage) throws JAXBException, IOException {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());

        this.mainStage = mainStage;

        this.controller = new QuizControllerImpl(CategoryScene.getCategoryPressed(), ModeScene.getModalityPressed(),
                Optional.of(LevelScene.getLevelPressed()));

        this.timeController = new TimeController(controller);

        final MyButton menu;

        title.setFont(Font.font(CATEGORY_FONT));
        menu = MyButtonFactory.createMyButton("MENU'", Color.BLUE, BUTTON_WIDTH);
        answers = this.createAnswersButtonList();
        questionLabel = MyLabelFactory.createMyLabel(createQuestionLabel(), Color.BLACK, QUESTION_FONT);
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 5em;");
        backBox.getChildren().addAll((Node) menu);

        timeStart();

        if (CategoryScene.getCategoryPressed().equals("BANDIERE")) {
            questionBox.setTranslateX(POS_X_FLAG_IMAGE);
            questionBox.setTranslateY(POS_Y_FLAG_IMAGE);
            flag = createQuestionImage();
            questionBox.getChildren().add(flag);
        } else {
            questionBox.setTranslateX(POS_X_QUESTION);
            questionBox.setTranslateY(POS_Y_QUESTION);
            questionBox.getChildren().add((Node) questionLabel);
        }
        answers.forEach(a -> answersBox.getChildren().add((Node) a));

        answers.forEach(b -> ((Node) b).setOnMouseClicked(e -> {
            try {
                this.answersEventHandler(b);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }));

        backBox.setTranslateX(POS_1_X);
        backBox.setTranslateY(POS_Y_BACK);

        answersBox.setTranslateX(POS_X_ANSWERS);
        answersBox.setTranslateY(POS_Y_ANSWERS);

        hboxBar.setTranslateX(POS_PB_X);
        hboxBar.setTranslateY(POS_PB_Y);
        pb.setOpacity(PB_OPACITY);
        pb.setMinSize(PB_SIZE_X, PB_SIZE_Y);

        ((Node) menu).setOnMouseClicked(event -> {
            if (timeline != null) {
                timeline.stop();
            }
            if (freezeTimeline != null) {
                freezeTimeline.stop();
            }
            if (pauseTimeline != null) {
                pauseTimeline.stop();
            }
            mainStage.setScene(new CategoryScene(mainStage));
        });

        StackPane.setAlignment(answersBox, Pos.CENTER);

        if (ModeScene.getModalityPressed().equals("CLASSICA")) {
            this.panel.getChildren().addAll(ModeScene.createBackgroundImage(), Background.createBackground(), backBox,
                    hboxBar, answersBox, questionBox, createHelpBox(), categoryBox, createScoreBox(), createLifeBox(),
                    createLabelBox());
        } else if (ModeScene.getModalityPressed().equals("ALLENAMENTO")) {
            this.panel.getChildren().addAll(ModeScene.createBackgroundImage(), Background.createBackground(), backBox,
                    hboxBar, answersBox, questionBox, categoryBox, createLabelBox());
        } else {
            this.panel.getChildren().addAll(ModeScene.createBackgroundImage(), Background.createBackground(), backBox,
                    hboxBar, answersBox, questionBox, categoryBox, createScoreBox(), createLifeBox(), createLabelBox());
        }
        this.setRoot(this.panel);
    }

    private List<MyButton> createAnswersButtonList() {
        return this.controller.showAnswers().stream()
                .map(a -> MyButtonFactory.createMyButton(a, Color.BLUE, ANSWERS_WIDTH)).collect(Collectors.toList());
    }

    private String createQuestionLabel() {
        return this.controller.showStringQuestion();
    }

    private ImageView createQuestionImage() {
        final Image image = new Image(this.controller.showImageQuestion());
        final ImageView imageView = new ImageView(image);
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);
        return imageView;
    }

    private VBox createHelpBox() {
        final MyButton freeze;
        final MyButton skip;
        final MyButton use5050;
        final VBox helpsBox = new VBox();

        freeze = MyButtonFactory.createMyButton("CONGELAMENTO", Color.RED, HELPS_WIDTH);
        skip = MyButtonFactory.createMyButton("SKIP", Color.RED, HELPS_WIDTH);
        use5050 = MyButtonFactory.createMyButton("50:50", Color.RED, HELPS_WIDTH);
        helpsBox.getChildren().addAll((Node) freeze, (Node) skip, (Node) use5050);
        helpsBox.setTranslateX(POS_X_HELPS_BOX);
        helpsBox.setTranslateY(POS_Y_HELPS_BOX);
        ((Node) freeze).setOnMouseClicked(e -> {
            if (this.controller.isFreezeAvailable()) {
                ((Node) freeze).setDisable(true);
                freeze.setText("Aiuto non pi� disponibile!");
                timeline.pause();
                freezeTimeline = new Timeline(
                        new KeyFrame(Duration.millis(this.controller.freeze()), a -> timeline.play()));
                freezeTimeline.playFromStart();
            }
        });
        ((Node) use5050).setOnMouseClicked(e -> {
            if (this.controller.is5050Available()) {
                this.answers.forEach(a -> {
                    if (this.controller.use5050().contains(a.getText())) {
                        a.setText("");
                        ((Node) a).setDisable(true);
                    }
                });
                ((Node) use5050).setDisable(true);
                use5050.setText("Aiuto non pi� disponibile!");
            }
        });
        ((Node) skip).setOnMouseClicked(e -> {
            if (this.controller.isSkipAvailable()) {
                this.controller.skip();
                timeline.stop();
                ((Node) skip).setDisable(true);
                skip.setText("Aiuto non pi� disponibile!");
                this.goToNextQuestion();
            }
        });
        return helpsBox;
    }

    private VBox createLabelBox() {
        final VBox labelBox = new VBox();
        labelBox.setTranslateX(POS_X_LABEL_BOX);
        labelBox.setTranslateY(POS_Y_LABEL_BOX);
        label.setText(this.getLabel());
        label.setFont(Font.font("Italic", FontWeight.BOLD, LABEL_FONT));
        labelBox.getChildren().add(label);
        return labelBox;
    }

    private VBox createLifeBox() {
        final VBox lifeBox = new VBox();
        lifeBox.setTranslateX(POS_X_LIFE_BOX);
        lifeBox.setTranslateY(POS_Y_LIFE_BOX);
        lifeLabel.setText("LIFE: " + this.getCurrentLife());
        lifeLabel.setFont(Font.font(SCORE_FONT));
        lifeBox.getChildren().add(lifeLabel);
        return lifeBox;
    }

    private VBox createScoreBox() {
        final VBox scoreBox = new VBox();
        scoreBox.setTranslateX(POS_X_SCORE_BOX);
        scoreLabel.setText("SCORE: " + this.getCurrentScore());
        scoreLabel.setFont(Font.font(SCORE_FONT));
        scoreBox.getChildren().add(scoreLabel);
        return scoreBox;
    }

    private int getCurrentLife() {
        return this.controller.getRemainingLives();
    }

    private int getCurrentScore() {
        return this.controller.getScore();
    }

    private String getLabel() {
        if (isFirstQuestion) {
            isFirstQuestion = false;
            return "";
        } else {
            label.setTextFill(isAnswerCorrect ? Color.GREEN : Color.RED);
            return isAnswerCorrect ? "RISPOSTA CORRETTA!" : "RISPOSTA ERRATA!";
        }
    }

    private void showCorrectAnswer() {
        answers.stream().filter(a -> a.getText().equals(this.controller.getCorrectAnswer())).findFirst().get()
                .setBackground(Color.GREEN);
    }

    private void answersEventHandler(final MyButton b) throws InterruptedException {
        this.controller.hitAnswer(Optional.of(b.getText()));
        answers.forEach(a -> {
            ((Node) a).setDisable(true);
        });
        if (this.controller.checkAnswer()) {
            ((Node) b).setOnMouseExited(e -> {
                b.setBackground(Color.GREEN);
            });

            b.setBackground(Color.GREEN);
            isAnswerCorrect = true;
        } else {
            showCorrectAnswer();
            ((Node) b).setOnMouseExited(e -> {
                b.setBackground(Color.RED);
            });
            b.setBackground(Color.RED);
            isAnswerCorrect = false;
        }
        label.setText(getLabel());
        timeline.stop();
        if (freezeTimeline != null) {
            freezeTimeline.stop();
        }
        pauseTimeline = new Timeline(new KeyFrame(Duration.millis(SLEEP), e -> this.goToNextQuestion()));
        pauseTimeline.playFromStart();
    }

    private Pair<String, String> savePair() {
        if (title.getText().equals("CAPITALI") || title.getText().equals("MONUMENTI")) {
            if (ModeScene.getModalityPressed().equals("CLASSICA")) {
                return new Pair<>(CategoryScene.getCategoryPressed(), LevelScene.getLevelPressed());
            } else {
                return new Pair<>(CategoryScene.getCategoryPressed(), ModeScene.getModalityPressed());
            }
        } else {
            return new Pair<>(CategoryScene.getCategoryPressed(), ModeScene.getModalityPressed());
        }
    }

    private String savePlayer() {
        return LoginMenuScene.getUsername();
    }

    private void timeStart() {
        if (timeline != null) {
            timeline.stop();
        }
        timerLabel.textProperty().bind(timeSeconds.divide(100).asString());
        pb.progressProperty().bind(timeSeconds.divide((this.timeController.getTime()) * 100.0));
        timeSeconds.set((this.timeController.getTime() + 1) * 100);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(this.timeController.getTime() + 1), e -> {
            showCorrectAnswer();
            isAnswerCorrect = false;
            label.setText(getLabel());
            this.controller.hitAnswer(Optional.empty());
            pauseTimeline = new Timeline(new KeyFrame(Duration.millis(SLEEP), k -> this.goToNextQuestion()));
            pauseTimeline.playFromStart();
        }, new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();

    }

    private void gameOver() {
        final Alert alert = ConfirmBox.getAlert("Il gioco è terminato! SCORE: " + this.getCurrentScore(), Color.BLACK);
        alert.show();
        mainStage.setScene(new CategoryScene(mainStage));
    }

    private void goToNextQuestion() {
        if (pauseTimeline != null) {
            pauseTimeline.stop();
        }
        if (freezeTimeline != null) {
            freezeTimeline.stop();
        }
        label.setText("");
        timeline.stop();
        if (!this.controller.gameOver()) {
            this.controller.nextQuestion();
            if (CategoryScene.getCategoryPressed().equals("BANDIERE")) {
                flag.setImage(new Image(this.controller.showImageQuestion()));
            } else {
                questionLabel.setText(this.createQuestionLabel());
            }
            if (!ModeScene.getModalityPressed().equals("ALLENAMENTO")) {
                scoreLabel.setText("SCORE: " + getCurrentScore());
                lifeLabel.setText("LIFE: " + getCurrentLife());
            }
            answers = this.createAnswersButtonList();
            answers.forEach(c -> ((Node) c).setOnMouseClicked(e -> {
                try {
                    this.answersEventHandler(c);

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }));
            answersBox.getChildren().clear();
            answers.forEach(a -> answersBox.getChildren().add((Node) a));
            timeStart();
        } else {
            try {
                this.ranking.save(savePlayer(), savePair(), getCurrentScore());

            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
            this.gameOver();
        }
    }
}
