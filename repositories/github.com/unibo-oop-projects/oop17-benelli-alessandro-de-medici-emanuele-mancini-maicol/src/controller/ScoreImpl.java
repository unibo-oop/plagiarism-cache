package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import utilities.Utilities;

/**
 * Class that manages score.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class ScoreImpl implements Score {

    private static final ScoreImpl SINGLETON = new ScoreImpl();
    private double time;
    private Timeline scoreTimer;
    private boolean isRunning;
    private final DecimalFormat decimalFormat;
    private final Text textScore;
    private final Text textLeaderboard;
    private final List<Double> scoresList;
    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String APP_DIRECTORY = ".watcha-way";
    private static final String DEFAULT_FILE = "score";
    private static final String DEFAULT_EXT = ".txt";
    private static final File FILE = new File(
            HOME + SEPARATOR + APP_DIRECTORY + SEPARATOR + DEFAULT_FILE + DEFAULT_EXT);

    /**
     * Constructor.
     */
    private ScoreImpl() {
        this.textScore = new Text();
        this.textLeaderboard = new Text();
        this.decimalFormat = new DecimalFormat("#0.00");
        this.scoresList = new ArrayList<Double>();
    }

    @Override
    public void startScore() {
        if (!this.isRunning) {
            this.isRunning = true;
            this.scoreTimer = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    time += 0.001;
                    textScore.setText("Time: " + decimalFormat.format(time));
                }
            }));
            this.scoreTimer.setCycleCount(Animation.INDEFINITE);
            this.scoreTimer.play();
        }
    }

    @Override
    public void addScoreToPane(final Pane root) {
        this.textScore
                .setStyle(String.format("-fx-font-size: %dpx;" + " -fx-fill: #fea056;" + " -fx-font-family: \"Impact\";"
                        + " -fx-stroke: #323232;\n" + " -fx-stroke-width: 2px;", (int) (Utilities.H / 24)));

        root.getChildren().add(this.textScore);
        this.textScore.setTranslateY(Utilities.H / 21);
        this.textScore.setTranslateX(Utilities.W / 42);
    }

    @Override
    public void addLeaderboardToPane(final Pane root) {
        this.textLeaderboard
                .setStyle(String.format("-fx-font-size: %dpx;" + " -fx-fill: #fea056;" + " -fx-font-family: \"Impact\";"
                        + " -fx-stroke: #323232;\n" + " -fx-stroke-width: 2px;", (int) (Utilities.H / 16)));

        root.getChildren().add(this.textLeaderboard);
        this.textLeaderboard.setTranslateY(Utilities.H / 4.5);
    }

    @Override
    public Text getText() {
        return this.textScore;
    }

    @Override
    public double getTime() {
        return this.time;
    }

    @Override
    public void stopTimer() {
        if (this.isRunning) {
            this.isRunning = false;
            this.scoreTimer.stop();
        }
    }

    @Override
    public void resetTimer() {
        this.time = 0;
    }

    @Override
    public void leaderboard() {
        try {
            this.saveFile();
            this.loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads and reads file where scores are saved.
    private void loadFile() throws IOException {
        this.checkFile();

        try {
            final FileReader fileReader = new FileReader(FILE);
            final BufferedReader bufferedReader = new BufferedReader(fileReader);
            this.scoresList.clear();

            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                this.scoresList.add(Double.parseDouble(line));
            }

            fileReader.close();
            bufferedReader.close();
        } catch (

        IOException e) {
            e.printStackTrace();
        }

        Collections.sort(this.scoresList);

        scoresList.stream().forEach(score -> {
            if (score.equals(this.time)) {
                if (score.equals(this.scoresList.get(0))) {
                    this.textLeaderboard.setText("NEW RECORD!" + System.getProperty("line.separator") + "Time: "
                            + this.decimalFormat.format(this.time) + " s");
                } else {
                    this.textLeaderboard.setText("Position in leaderboard: " + (this.scoresList.lastIndexOf(score) + 1)
                            + System.getProperty("line.separator") + "Time: " + this.decimalFormat.format(this.time)
                            + " s");
                }
                this.textLeaderboard.setTextAlignment(TextAlignment.CENTER);
            }
        });
    }

    // Saves scores in the file.
    private void saveFile() throws IOException {
        this.checkFile();

        try {
            final Writer output = new BufferedWriter(new FileWriter(FILE, true));
            output.write(Double.toString(this.time) + System.getProperty("line.separator"));
            output.close();
        } catch (IOException e) {
            throw new IOException("Unable to save score");
        }
    }

    // Checks if the file exists.
    private void checkFile() throws IOException {
        if (!FILE.exists()) {
            final File parent = new File(HOME + SEPARATOR + APP_DIRECTORY);
            if (!parent.exists()) {
                final boolean ret = parent.mkdir();
                if (ret) {
                    this.createFile();
                }
            } else {
                this.createFile();
            }
        }
    }

    // Creates the file.
    private void createFile() throws IOException {
        try {
            FILE.createNewFile();
        } catch (IOException e) {
            throw new IOException("An error occurred during the creation of the score's file");
        }
    }

    /**
     * Returns an instance of ScoreImpl.
     * 
     * @return an instance of ScoreImpl
     */
    public static ScoreImpl get() {
        return SINGLETON;
    }

}
