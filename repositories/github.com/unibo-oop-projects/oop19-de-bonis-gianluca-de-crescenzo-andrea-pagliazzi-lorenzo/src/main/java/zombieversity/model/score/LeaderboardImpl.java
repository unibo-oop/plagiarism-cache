package zombieversity.model.score;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Implementation of {@link Leaderboard} to keep track of old and new scores.
 */
public class LeaderboardImpl implements Leaderboard {

    private static final String SEP = System.getProperty("file.separator");
    private static final String FOLDER = System.getProperty("user.home") + SEP + ".zombieversity";
    private static final String LEADERBOARD_NAME = "leaderboard.json";
    private static final String LEADERBOARD_PATH = FOLDER + SEP + LEADERBOARD_NAME;

    private final Type listType; 
    private List<Score> leaderboard;
    private Score lastScore;
    private Gson json;

    /**
     * Set up the leaderboard to start managing scores.
     */
    public LeaderboardImpl() {
        this.leaderboard = new ArrayList<>();
        this.listType = new TypeToken<ArrayList<Score>>() { }.getType();
        this.setUpJson();
        this.setUpIO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void handleScore(final Score score) {
        this.lastScore = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Score getLastScore() {
        return this.lastScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateLeaderboard() {
        final int finalPosition;
        int position = 0;

        if (!this.leaderboard.isEmpty()) {
            for (final Score score : this.leaderboard) {
                if (this.lastScore.getKills() > score.getKills()) {
                    break;
                }
                position++;
            }
        }

        finalPosition = position + 1;
        this.lastScore.setPosition(position + 1);
        this.leaderboard.stream()
                        .filter(s -> s.getPosition() >= finalPosition)
                        .forEach(s -> s.setPosition(s.getPosition() + 1));
        this.leaderboard.add(position, this.lastScore);
        this.updateFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Score> getLeaderboard() {
        return this.leaderboard;
    }

    private void updateFile() {
        try (OutputStream out = Files.newOutputStream(Paths.get(LEADERBOARD_PATH));) {
            final String toBeSaved = json.toJson(this.leaderboard, this.listType);
            out.write(toBeSaved.getBytes("UTF-8"));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        try (InputStream input = Files.newInputStream(Paths.get(LEADERBOARD_PATH));) {
            final String reader = new String(input.readAllBytes(), "UTF-8");
            this.leaderboard = json.fromJson(reader, this.listType);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpJson() {
        this.json = new GsonBuilder().registerTypeAdapter(Score.class, new ScoreAdapter()).create();
    }

    private void setUpIO() {
        final File folder = new File(FOLDER);
        boolean callFile = false;

        if (!folder.exists()) {
            if (folder.mkdir()) {
                callFile = true;
            }
        } else {
            callFile = true;
        }

        if (callFile) {
            this.createFile();
        }
    }

    private void createFile() {
        final File lbFile = new File(LEADERBOARD_PATH);

        try {
            if (!lbFile.exists()) {
                if (lbFile.createNewFile()) {
                    this.updateFile();
                }
            } else {
                this.readFile();
                if (lbFile.createNewFile()) {
                    this.updateFile();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
