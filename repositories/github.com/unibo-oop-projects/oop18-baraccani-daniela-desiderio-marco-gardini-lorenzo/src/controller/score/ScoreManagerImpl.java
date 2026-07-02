package controller.score;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

/**
 * Implements a manager of a score list. It has methods for write a new score,
 * read all scores from file.
 *
 */
public class ScoreManagerImpl implements ScoreManager {

    private static final int MAXSCORES = 10;
    private final ScoreList scoreBoard;
    private final String filePath;

    /**
     * Create the data structure sorted by a comparator.
     * 
     * @param filePath the path of the file to be written;
     */
    public ScoreManagerImpl(final String filePath) {
        this.scoreBoard = new ScoreList(MAXSCORES, (a, b) -> Integer.compare(b.getPoints(), a.getPoints()));
        this.filePath = filePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeScore(final Score score) throws IOException {
        Objects.requireNonNull(score);
        this.checkIfLoad();

        this.scoreBoard.add(score);
        try (OutputStream bufferedStream = new BufferedOutputStream(new FileOutputStream(this.filePath));
                ObjectOutputStream objectStream = new ObjectOutputStream(bufferedStream);) {
            objectStream.writeInt(this.scoreBoard.getList().size());
            for (final Score scoreTmp : this.scoreBoard.getList()) {
                objectStream.writeObject(scoreTmp);
            }
        } finally {
            this.scoreBoard.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Score> getScoreboard() throws IOException {
        this.checkIfLoad();
        return this.scoreBoard.getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eraseScoreBoard() throws IOException {
        new PrintWriter(this.filePath).close();
    }

    /*
     * Load the score reading all scores from file
     */
    private void readScores() throws IOException {
        this.createOrLoadFile();

        try (InputStream bufferedStream = new BufferedInputStream(new FileInputStream(this.filePath));
                ObjectInputStream objectStream = new ObjectInputStream(bufferedStream);) {

            final int numberScores = objectStream.readInt();

            for (int i = 0; i < numberScores && numberScores > 0; i++) {
                this.scoreBoard.add((Score) objectStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            this.eraseScoreBoard();
            throw new IOException(e);
        }
    }

    /*
     * Creates the scoreboard file if it's missing
     */
    private void createOrLoadFile() throws IOException {
        final File file = new File(this.filePath);
        if (!file.exists()) {
            file.createNewFile();
            try (OutputStream bufferedStream = new BufferedOutputStream(new FileOutputStream(this.filePath));
                    ObjectOutputStream objectStream = new ObjectOutputStream(bufferedStream);) {
                objectStream.writeInt(0);
            }
        }

    }

    /*
     * Return true if the list is loaded, otherwise false
     */
    private void checkIfLoad() throws IOException {
        if (this.scoreBoard.isEmpty()) {
            this.readScores();
        }
    }

}
