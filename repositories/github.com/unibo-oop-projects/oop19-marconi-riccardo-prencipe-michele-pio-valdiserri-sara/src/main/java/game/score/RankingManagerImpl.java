package game.score;

import game.logics.GameMode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class manipulate the file for the ranking.
 *
 */
public class RankingManagerImpl implements RankingManager {

  private static final int NUM_PLAYER_RANKING = 10;
  private static final String HIGHSCORE_FILE = "scores.dat";
  private static final String UTF_8 = "UTF-8";
  private static final String SP = File.separator;
  private List<HighScore> scores;

  /**
   * Constructor.
   */
  public RankingManagerImpl() {
    scores = new ArrayList<HighScore>();
    loadScoreFile();
    arrayControl();
  }

  /**
   * Control for the max size of the ranking list.
   */
  private void arrayControl() {
    List<HighScore> scores;
    scores = getScores();
    int size = scores.size();
    final int max = 10;
    while (size < max) {
      scores.add(new HighScore("-", 0, GameMode.NO_MODE, 0));
      size = scores.size();
    }
  }

  private List<HighScore> getScores() {
    loadScoreFile();
    return scores;
  }

  @Override
  public void addScore(final String name, final int score, 
      final GameMode gameMode, final int size) {
    scores.add(new HighScore(name, score, gameMode, size));
    sort();
    if (scores.size() == NUM_PLAYER_RANKING + 1) {
      scores.remove(NUM_PLAYER_RANKING);
    }
    updateScoreFile();
  }

  @Override
  public List<HighScore> getHighscore() {
    return this.scores;
  }

  @Override
  public String getNameAtPosition(final int i) {
    return scores.get(i).getName();
  }

  @Override
  public int getScoreAtPosition(final int i) {
    if (!scores.isEmpty()) {
      return scores.get(i).getScore();
    } else {
      return 0;
    }
  }

  @Override
  public void clear() {
    String jarPath = "";
    String completePath = "";
    try {
      jarPath = URLDecoder.decode(getClass().getProtectionDomain()
          .getCodeSource().getLocation().getPath(), UTF_8);
    } catch (final UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    completePath = jarPath.substring(0, jarPath.lastIndexOf("/")) + SP + HIGHSCORE_FILE;
    final File f = new File(completePath);
    if (f.exists()) {
      try {
        f.delete();
      } catch (final Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public boolean isPresent() {
    String jarPath = "";
    String completePath = "";
    try {
      jarPath = URLDecoder.decode(getClass()
          .getProtectionDomain().getCodeSource().getLocation().getPath(), UTF_8);
    } catch (final UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    completePath = jarPath.substring(0, jarPath.lastIndexOf("/")) + SP + HIGHSCORE_FILE;
    final File f = new File(completePath);
    return f.exists();
  }

  /**
   * Update the file (if exists) with a new score.
   */
  private void updateScoreFile() {

    String jarPath = "";
    try {
      jarPath = URLDecoder.decode(getClass()
          .getProtectionDomain().getCodeSource().getLocation().getPath(), UTF_8);
    } catch (final UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }

    final String completePath = jarPath
        .substring(0, jarPath.lastIndexOf("/")) + SP + HIGHSCORE_FILE;
    final File f = new File(completePath);
    try {
      if (f.exists() || f.createNewFile()) {
        final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(scores);
        out.close();
      }

    } catch (final Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Load all score from file.
   */
  @SuppressWarnings("unchecked")
  private void loadScoreFile() {
    String jarPath = "";
    String completePath = "";
    try {
      jarPath = URLDecoder.decode(getClass()
          .getProtectionDomain().getCodeSource().getLocation().getPath(), UTF_8);

    } catch (final UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    completePath = jarPath.substring(0, jarPath.lastIndexOf("/")) + SP + HIGHSCORE_FILE;
    final File f = new File(completePath);
    if (f.exists()) {
      try {
        final ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));

        scores = (ArrayList<HighScore>) in.readObject();
        in.close();
      } catch (final Exception e) {
        e.printStackTrace();
      }
    } else {
      this.updateScoreFile();
    }
  }

  /**
   * Sort all of the scores.
   */
  private void sort() {
    final Comparator<HighScore> comparator = new Comparator<HighScore>() {

      @Override
      public int compare(final HighScore score1, final HighScore score2) {
        final int sc1 = score1.getScore();
        final int sc2 = score2.getScore();

        if (sc1 > sc2) {
          return -1;
        } else if (sc1 < sc2) {
          return +1;
        } else {
          return 0;
        }
      }
    };

    Collections.sort(scores, comparator);
  }
}
