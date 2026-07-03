package model.pkglevels;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import controller.PlumberControllerImpl;
import view.GameLevelImpl;
import view.UtilityClass;

/**
 * Window that shows the current level statistics.
 * 
 * 
 *
 */
public class LevelStats extends UtilityClass {

    /**
     * 
     */
    private static final long serialVersionUID = -8557119656696510611L;
    private GameLevelImpl gl = (GameLevelImpl) PlumberControllerImpl.getControllerInstance().getLevel();
    private String levelToRead = "";
    private String message, title;
    private List<Pair<String, Integer>> levelsStatsList = new ArrayList<>();
    private final File gameplay = ImageLoaderImpl.getLoaderInstance().getScoreFile();

    /**
     * Constructor of the class.
     *
     * @param level
     *            current level
     * @param custom
     *            is a custom level or not
     * @param message
     *            message to show on the joptionpane
     * @param title
     *            title to show
     */
    public LevelStats(final int level, final boolean custom, final String message, final String title) {

        super("Exit Stats Window");

        gl = (GameLevelImpl) PlumberControllerImpl.getControllerInstance().getLevel();
        // gl=(GameLevelImpl) o;
        if (custom) {
            levelToRead = "C" + level;
        } else {
            levelToRead = "" + level;
        }

        readLevelStats();
        orderScores();
        this.message = message;
        this.title = title;
        // super.scrollPane = new JScrollPane(tx);

        // super.utilityLabel.setText(readString);
        super.getLabel().setText(super.getReadedString());
        SwingUtilities.updateComponentTreeUI(this);
    }

    void readLevelStats() {

        String readed = "";
        String[] splitted = null;
        boolean completed = false;

        try (BufferedReader bS = new BufferedReader(new FileReader(gameplay))) {
            while ((readed = bS.readLine()) != null && !completed) {
                if (PlumberControllerImpl.getControllerInstance().getModel().isALvlNumber(readed)
                        && readed.equals(levelToRead)) {
                    while ((readed = bS.readLine()) != null
                            && !PlumberControllerImpl.getControllerInstance().getModel().isALvlNumber(readed)) {
                        splitted = readed.split(" ");
                        final Pair<String, Integer> pair = new Pair<String, Integer>(splitted[0],
                                Integer.parseInt(splitted[1]));
                        levelsStatsList.add(pair);
                        ;
                    }
                    completed = true;
                }

            }

        } catch (final FileNotFoundException e) {

            e.printStackTrace();
        } catch (final IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    protected ActionListener buttonListener() {
        return e -> {
            this.dispose();

            gl.setEnabled(true);
            gl.setVisible(true);

            PlumberControllerImpl.getControllerInstance().getModel().accessNextLevel(title, message);

        };
    }

    @Override
    protected void closeWindow() {
        this.dispose();
        gl.setEnabled(true);
        gl.setVisible(true);

        PlumberControllerImpl.getControllerInstance().getModel().accessNextLevel(title, message);

    }

    /**
     * Method that orders the current level scores.
     * 
     */
    protected void orderScores() {

        int maxScore = levelsStatsList.get(0).getY();
        int copied = 0;// player added to the map
        final int length = levelsStatsList.size(); // number of players
        int foundIndex = 0;
        final List<Pair<String, Integer>> tempList = new ArrayList<>();

        super.setReadString(super.getReadedString() + "Position    Name    Score" + "<br>");
        for (int i = 0, j = 1; copied < length; i++) {
            // read the whole list, must add the max score
            if (i >= levelsStatsList.size() && copied != length) {

                final Pair<String, Integer> p = new Pair<String, Integer>(levelsStatsList.get(foundIndex).getX(),
                        maxScore);
                tempList.add(p);
                copied++;
                // uses html tag to set newLines
                super.setReadString(super.getReadedString() + j + ")  " + p.getX() + " " + p.getY() + "<br><br>");
                j++;
                // remove the player found
                levelsStatsList.remove(foundIndex);

                i = 0;
                foundIndex = 0;
                // reset the maxscore to the first score
                if (copied != length) {
                    maxScore = levelsStatsList.get(0).getY();
                }
            }
            // max score found, haven't copied all player
            if (copied != length && i < levelsStatsList.size() && maxScore < levelsStatsList.get(i).getY()) {
                maxScore = levelsStatsList.get(i).getY();
                foundIndex = i;

            }

        }

        levelsStatsList = tempList;

        super.setReadString(super.getReadedString() + "</html>");

    }

}
