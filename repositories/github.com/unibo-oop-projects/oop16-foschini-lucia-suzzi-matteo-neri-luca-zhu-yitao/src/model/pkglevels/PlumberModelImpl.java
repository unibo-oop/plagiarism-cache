package model.pkglevels;

import java.awt.Dimension;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.PlumberControllerImpl;
import model.pkgplayer.Player;
import view.CustomGameLevelCreator;
import view.GameLevel;
import view.GameLevelImpl;

/**
 * Model class for the game.
 * 
 * 
 *
 */
public class PlumberModelImpl extends java.util.Observable implements PlumberModel {
    private boolean isACustomLevel;

    private int nMosse, contDefaultTube;

    private int currentLevel;
    private int levelBeforeUpdate;
    private int currentLevelScore;
    private int currentLvlMinScore;
    private boolean isSolutionTesting, scoreSaved;
    private int nextLine;

    private int nextColumn;
    private boolean error;
    private int lastOutput = -1;
    private int nextInput = 2;

    private boolean correctT = true;
    private int righe, colonne;
    // contains all buttons and pipes
    private Map<Pair<JButton, Pipe>, Pair<Integer, Integer>> sol2 = new HashMap<>();
    // contains the button and the associated pipe
    private Map<JButton, Pipe> test = new HashMap<>();
    // contains the solutions
    private final Map<Pair<Pair<String, Integer>, Pair<Integer, Integer>>, Pair<Integer, Integer>> readedSolution = new HashMap<>();

    private final Map<Pair<Pipe, Integer>, Pair<Integer, Integer>> testRead = new HashMap<>();

    private final List<JButton> pressedButtons = new ArrayList<>();

    private JButton btnToChange;
    private ImageIcon img;

    private int x;
    private int y;
    private int joptionResult;
    private final Player pl;
    private int curr;
    private final GameLevelImpl gameLev;
    private CustomGameLevelCreator customG;
    private final PlumberControllerImpl controller = PlumberControllerImpl.getControllerInstance();

    /**
     * Standard levels constructor.
     * 
     * @param glvl
     *            game level view
     * @param i
     *            current level index
     */
    public PlumberModelImpl(final GameLevelImpl glvl, final int i) {

        gameLev = glvl;
        pl = PlumberControllerImpl.getControllerInstance().getPlayer();
        this.curr = i;
        this.levelBeforeUpdate = i - 1;

    }

    /**
     * Class constructor.
     * 
     * @param g
     *            level creator window
     * @param i
     *            level creator counter
     * @param r
     *            lines
     * @param c
     *            columns
     */
    public PlumberModelImpl(final CustomGameLevelCreator g, final int i, final int r, final int c) {

        customG = g;
        pl = PlumberControllerImpl.getControllerInstance().getPlayer();
        gameLev = customG;
        isACustomLevel = true;
        this.righe = r;
        this.colonne = c;
        this.curr = i;

    }

    /**
     * Get the current level.
     * 
     * @return current level
     */
    public GameLevel getGameLev() {
        return this.gameLev;
    }

    @Override
    public void saveUserInfo() {

        String readed, name, newString = "";
        final String newL = new String("" + pl.getLevel());
        String[] line;

        final File temp = new File(ImageLoaderImpl.getNewPath() + "tempLogin.txt");

        try (FileWriter fr = new FileWriter(temp); BufferedWriter bfw = new BufferedWriter(fr)) {

            final FileReader read = new FileReader(ImageLoaderImpl.getLoaderInstance().getLoginFile());
            final BufferedReader br = new BufferedReader(read);
            while ((readed = br.readLine()) != null) {

                line = readed.split(" ");
                name = line[1];
                final int oldScore = Integer.parseInt(line[3]);
                int index;

                index = readed.indexOf(line[0]);
                if (name.equals(pl.getName())) {

                    if (currentLevelScore > oldScore) { // updates the player
                                                        // score
                        newString = newL + readed.substring(index + 1, readed.length() - line[3].length())
                                + currentLevelScore;
                    } else {

                        newString = newL + readed.substring(index + 1, readed.length()); // no
                                                                                         // updates
                                                                                         // needed,
                                                                                         // rewrites
                                                                                         // the
                                                                                         // same
                                                                                         // string
                    }

                    bfw.write(newString);
                    bfw.newLine();

                } else {
                    bfw.write(readed);
                    bfw.newLine();
                }
            }
            br.close();
            ImageLoaderImpl.getLoaderInstance().getLoginFile().delete(); // delete
                                                                         // the
                                                                         // old
                                                                         // file

        } catch (final IOException e) {

            e.printStackTrace();
        }
        temp.renameTo(ImageLoaderImpl.getLoaderInstance().getLoginFile()); // renamte
                                                                           // the
                                                                           // temp
                                                                           // file
                                                                           // to
                                                                           // the
                                                                           // old
                                                                           // file
        scoreSaved = true;

    }

    @Override
    public boolean isALvlNumber(final String s) {
        boolean isLevel = false;
        final String[] splitted = s.split(" ");
        if (splitted.length == 1 || splitted.length == 3) {
            isLevel = true;
        }
        return isLevel;
    }

    @Override
    public int readTopTenScores(final String lNumber, final File f) {
        int counter = 0;
        int minScore = 0;
        int tempScore;
        String readedString;
        String[] line;
        String levelNumber = lNumber;
        boolean readCompleted = false;
        if (gameLev.isCustom()) {
            levelNumber = "C" + levelNumber; // custom level saved with C before
                                             // level number
        }
        try (BufferedReader bfw = new BufferedReader(
                new FileReader(ImageLoaderImpl.getLoaderInstance().getScoreFile()))) {
            while (!readCompleted && (readedString = bfw.readLine()) != null) {
                // checks if it's a level number and match the current level
                if (isALvlNumber(readedString) && readedString.equals(levelNumber)) {
                    // must read all user info for the found level
                    while ((readedString = bfw.readLine()) != null && !isALvlNumber(readedString)) {
                        line = readedString.split(" ");
                        tempScore = Integer.parseInt(line[1]);
                        if (minScore == 0) {
                            minScore = tempScore;
                        } else if (tempScore < minScore) {
                            minScore = tempScore;
                        }

                        counter++;

                    }
                    readCompleted = true;
                }

            }

        } catch (final FileNotFoundException e) {

            e.printStackTrace();
        } catch (final IOException e) {

            e.printStackTrace();
        }

        this.currentLvlMinScore = minScore;
        return counter;
    }

    @Override
    public void saveLevelsScores() {

        final int numberOfPlayer = readTopTenScores(this.currentLevel + "",
                ImageLoaderImpl.getLoaderInstance().getScoreFile());
        String readed, newString = "";
        String[] line = null;
        boolean insertedScore = false;
        final File temp = new File(ImageLoaderImpl.getNewPath() + "tmpScore.txt");
        // first time saving this level, need to append the user info, rewrite
        // not needed
        if (numberOfPlayer == 0) {

            try (FileWriter fr = new FileWriter(ImageLoaderImpl.getLoaderInstance().getScoreFile(), true);
                    BufferedWriter bfw = new BufferedWriter(fr)) {

                if (gameLev.isCustom()) {
                    bfw.write("C" + this.currentLevel);

                } else {
                    bfw.write("" + this.currentLevel);
                }
                bfw.newLine();
                bfw.write(pl.getName() + " " + currentLevelScore);
                bfw.newLine();

            } catch (final IOException e) {
                e.printStackTrace();
            }

        } else {

            try (FileWriter fr = new FileWriter(temp); BufferedWriter bfw = new BufferedWriter(fr)) {

                final FileReader read = new FileReader(ImageLoaderImpl.getLoaderInstance().getScoreFile());
                final BufferedReader br = new BufferedReader(read);

                String level = "", adaptedLevel = "";
                if (gameLev.isCustom()) {
                    adaptedLevel = "C" + this.currentLevel;
                } else {
                    adaptedLevel = "" + this.currentLevel;
                }
                while ((readed = br.readLine()) != null) {

                    if (isALvlNumber(readed)) {
                        bfw.write(readed);
                        bfw.newLine();

                        level = readed;
                        readed = br.readLine();

                    }

                    if (level.equals(adaptedLevel)) {
                        if (readed != null) {
                            line = readed.split(" ");
                        }
                        // Top 10 not completed yet, insert the current player
                        // result
                        if (numberOfPlayer < 10 && !insertedScore) {
                            newString = pl.getName() + " " + currentLevelScore;
                            bfw.write(newString);
                            bfw.newLine();
                            bfw.write(readed);
                            bfw.newLine();
                            insertedScore = true;
                            // top 10 full, need to find min score and overwrite
                            // it
                        } else if (numberOfPlayer == 10) {
                            // player with minScore found, must replace with the
                            // current player
                            if (Integer.parseInt(line[1]) == this.currentLvlMinScore
                                    && this.currentLevelScore > currentLvlMinScore) {
                                newString = pl.getName() + " " + currentLevelScore;
                                bfw.write(newString);
                                bfw.newLine();

                                insertedScore = true;
                            } else {
                                bfw.write(readed);
                                bfw.newLine();
                            }
                        } else {
                            bfw.write(readed);
                            bfw.newLine();
                        }

                    } else {

                        bfw.write(readed);
                        bfw.newLine();

                    }
                }

                br.close();
                ImageLoaderImpl.getLoaderInstance().getScoreFile().delete();

            } catch (final IOException e) {
                e.printStackTrace();

            }

            temp.renameTo(ImageLoaderImpl.getLoaderInstance().getScoreFile());
            scoreSaved = true;
        }

    }

    @Override
    public void readSolutions(final File f) {
        String style, readedString;
        int cont = 0;
        int dir, x, y;
        String[] line = null;
        boolean foundLevel = false;

        try (BufferedReader bfw = new BufferedReader(new FileReader(f))) {
            while (!foundLevel) {
                readedString = bfw.readLine();
                if (readedString != null) {
                    line = readedString.split(" ");
                } else {
                    foundLevel = true;
                }

                if (isALvlNumber(readedString) && Integer.parseInt(line[0]) == this.curr) {

                    righe = Integer.parseInt(line[1]);
                    colonne = Integer.parseInt(line[2]);

                    foundLevel = true;
                }

            }
            //
            while ((readedString = bfw.readLine()) != null && !(isALvlNumber(readedString))) {
                // gets the level info
                style = readedString.substring(2, 4); // pipe name

                dir = Integer.parseInt(readedString.substring(0, 1)); // pipe
                                                                      // direction

                x = Integer.parseInt(readedString.substring(5, 6)); // pipe
                                                                    // position

                y = Integer.parseInt(readedString.substring(7, 8)); // pipe
                                                                    // position

                final Pair<Integer, Integer> p = new Pair<>(x, y);
                final Pair<String, Integer> p1 = new Pair<>(style, dir);
                // Pair with button and internal counter
                final Pair<Pipe, Integer> tubePair = new Pair<>(gameLev.getTube(style), cont++);
                testRead.put(tubePair, p);
                // map that contains the solution, used to build the grid
                readedSolution.put(new Pair<Pair<String, Integer>, Pair<Integer, Integer>>(p1, p), p);

            }

        } catch (final IOException e) {

            e.printStackTrace();
        }
        foundLevel = false;

    }

    /**
     * Method used to set the basic configuration for buttons and panel.
     * 
     * @param index
     *            index in the image list
     * @param btnToset
     *            button to change image
     * @param gb
     *            contraint for inserting in the grid
     * @param p
     *            panel that contains the buttons
     * @param setX
     *            grid's x position
     * @param setY
     *            grid's y position
     * @param newX
     *            new dimension(width)
     * @param newY
     *            new dimension(height)
     */
    public void setLevelStartConfig(final int index, final JButton btnToset, final GridBagConstraints gb,
            final JPanel p, final int setX, final int setY, final int newX, final int newY) {
        final ImageIcon i = ImageLoaderImpl.resizeIcon(gameLev.getAllTubes().get(index).getEmptyPipe(), newX, newY);

        btnToset.setIcon(i);
        test.put(btnToset, gameLev.getAllTubes().get(index));

        // gridmap with the buttons
        sol2.put(new Pair<JButton, Pipe>(btnToset, gameLev.getAllTubes().get(index)),
                new Pair<Integer, Integer>(setX, setY));
        btnToset.addActionListener(basicButtonListener());
        btnToset.setPreferredSize(new Dimension(newX, newY));
        if (newX < 67) {
            gb.weightx = 1;
            gb.weighty = 1;
        }
        gb.fill = GridBagConstraints.REMAINDER;
        gb.gridheight = 1;
        gb.gridwidth = 1;

        gb.gridx = setY;
        gb.gridy = setX + 1;
        p.add(btnToset, gb);
    }

    @Override
    public void createLevelDesign(final int lines, final int columns, final JPanel panel, final int newX,
            final int newY) {
        final GridBagConstraints gb = new GridBagConstraints();

        JButton jb;
        for (x = 0; x < lines; x++) {

            for (y = 0; y < columns; y++) {
                gb.gridwidth = GridBagConstraints.HORIZONTAL;
                final Random r = new Random();
                jb = new JButton();
                if (readedSolution.containsValue(new Pair<Integer, Integer>(x, y))) {
                    // if in the solution, in the (x,y) position there is a pipe
                    // which name begins with..
                    // "C", then obtain a random pipe with the C name
                    if (readedSolution.entrySet().stream().anyMatch(z -> z.getValue().getX() == x
                            && z.getValue().getY() == y && z.getKey().getX().getX().substring(0, 1).equals("C"))) {
                        final int j = r.nextInt(4);

                        setLevelStartConfig(j, jb, gb, panel, x, y, newX, newY);
                        // it's a vertical or horizontal pipe
                    } else {
                        final int j = r.nextInt(2);
                        setLevelStartConfig(j + 4, jb, gb, panel, x, y, newX, newY);

                    }
                    // in the position (x,y) in the solutionMap, there is no
                    // pipe
                } else {
                    // random choose across all pipes
                    final int i = r.nextInt(6);
                    setLevelStartConfig(i, jb, gb, panel, x, y, newX, newY);

                }

            }
        }

    }

    @Override
    public ActionListener solutionListener() {

        ActionListener a = (s) -> {
            if (!isACustomLevel && !(gameLev instanceof CustomGameLevelCreator)) {
                gameLev.getTimer().stop();
            }
            isSolutionTesting = true;

            if (!sol2.isEmpty()) {

                while (correctT) {

                    for (final Map.Entry<Pair<JButton, Pipe>, Pair<Integer, Integer>> t : sol2.entrySet()) {
                        // correct pipe position, match the current tested
                        // position
                        if (t.getValue().getX() == nextLine && t.getValue().getY() == nextColumn && correctT) {

                            btnToChange = t.getKey().getX();
                            // the water can pass through the pipe
                            if ((lastOutput + t.getKey().getY().getInput() == 0
                                    || lastOutput + t.getKey().getY().getOutput() == 0)) {

                                // dynamic image changing
                                changeButtonImages(t);
                                // only if it's building a custom level..if the
                                // button it's correct must be
                                // inserted in the map that will be saved later
                                if (isACustomLevel) {
                                    final int tubeX = t.getValue().getX();
                                    final int tubeY = t.getValue().getY();
                                    final Pipe pipe = t.getKey().getY();
                                    final JButton bt = t.getKey().getX();
                                    final Pair<Integer, Integer> coordPair = new Pair<>(tubeX, tubeY);
                                    final Pair<JButton, Pipe> tubeSpec = new Pair<>(bt, pipe);
                                    customG.getMap().put(tubeSpec, coordPair);
                                }

                                if (lastOutput + t.getKey().getY().getOutput() == 0) {

                                    t.getKey().getY().switchIO();
                                }

                                lastOutput = t.getKey().getY().getOutput();
                                // determines the next position in the grid to
                                // test
                                solutionSwitch();

                            } else {
                                error = true;
                            }

                        }

                    }
                    // correct solution
                    if ((nextLine == righe && nextColumn == colonne - 1)) {

                        if (!isACustomLevel) {
                            gameLev.getFinish().setIcon(ImageLoaderImpl.getLoaderInstance().getFinishImage());
                            // if some pipe where set to the correct direction
                            // during the level build phase...
                            // they must be counted as moves
                            while (contDefaultTube < testRead.size()) {
                                contDefaultTube++;
                                nMosse++;
                            }
                            currentLevelScore = (100 * (100 - gameLev.getProgressValue())) / nMosse;
                            pl.updateScore(currentLevelScore);

                            accessNextLevel("Level Completed",
                                    "Congratulazioni hai vinto, puoi passare oltre......Punteggio: "
                                            + this.currentLevelScore);
                        } else {
                            customG.getFinish().setIcon(ImageLoaderImpl.getLoaderInstance().getFinishImage());

                            customG.getSaveButt().setEnabled(true);

                        }
                        correctT = false;
                        resetInputs();

                    }
                    // solution incorrect, went out of borders, or the pipe was
                    // incorrect for his position(water won't pass)
                    if ((((nextColumn == this.colonne || nextColumn < 0 || nextLine == this.righe || nextLine < 0))
                            || error) && correctT) {

                        if (!isACustomLevel) {

                            showFailureOptions("Level failure", "Hai creato una inondazione");
                            setChanged();
                            notifyObservers("Hai creato una inondazione");
                        } else {

                            customG.setNewMap();
                            JOptionPane.showMessageDialog(gameLev, "Hai creato una inondazione");
                            resetInputs();

                        }

                        correctT = false;
                    }
                }

            }

            for (final Map.Entry<Pair<JButton, Pipe>, Pair<Integer, Integer>> t : sol2.entrySet()) {

                t.getKey().getX().setIcon(t.getKey().getY().getEmptyPipe());
            }

            correctT = true;

        };
        return a;

    }

    /**
     * Method used to change the button image and replace it with the "flowing
     * water images".
     * 
     * @param t
     *            map element that contains the button and the pipe
     */
    public void changeButtonImages(final Map.Entry<Pair<JButton, Pipe>, Pair<Integer, Integer>> t) {
        int i = 0;
        isSolutionTesting = true;
        btnToChange = t.getKey().getX();
        while (i <= 3) {

            // checks the water direction and choose the next image to show
            t.getKey().getY().changeIconOnSolution(t.getKey().getY().getDir(), nextInput, i);
            img = t.getKey().getY().getImg();
            t.getKey().getX().doClick();
            try {
                Thread.sleep(100);
            } catch (final InterruptedException e) {

                e.printStackTrace();
            }

            i++;

        }
    }

    /**
     * Computes the next movement on the grid.
     * 
     * @param direction
     */
    public void solutionSwitch() {
        switch (lastOutput) {
        case 1:
            nextInput = 1;
            nextLine--;
            break;
        case -1:
            nextInput = 2;
            nextLine++;
            break;
        case 2:
            nextInput = 3;
            nextColumn--;
            break;
        case -2:
            nextColumn++;
            nextInput = 4;
            break;
        default:
            break;

        }
    }

    private void resetInputs() {
        isSolutionTesting = false;
        nextInput = 2;
        lastOutput = -1;
        nextLine = 0;
        nextColumn = 0;
    }

    @Override
    public void inizializeCountersStatistics() {

        gameLev.setProgressValue(0);
        isSolutionTesting = false;
        error = false;
        gameLev.update("0");
        resetInputs();
        nMosse = 0;

    }

    @Override
    public ActionListener basicButtonListener() {

        return (e) -> {

            if (!isSolutionTesting) {
                nMosse++;

                if (!(gameLev instanceof CustomGameLevelCreator)) {
                    gameLev.update("" + nMosse);
                }

                final JButton bt = (JButton) e.getSource();

                final Pipe t = test.get(bt);

                final PipeImpl next = (PipeImpl) ImageLoaderImpl.getLoaderInstance().getTube(t.getType()).getNextTube();
                // add the pressed button to the list which contains all the
                // pressed buttons
                if (!pressedButtons.contains(bt)) {
                    pressedButtons.add(bt);
                    contDefaultTube++;

                }

                // update map with the "new" button cofig
                final Pair<Integer, Integer> p2 = sol2.get(new Pair<JButton, Pipe>(bt, t));

                try {

                    bt.setIcon(next.getEmptyPipe());
                    // must change the current button reference in the map
                    // because button's image has changed
                    if (test.containsKey(bt)) {
                        test.remove(bt, t);
                        sol2.remove(new Pair<JButton, Pipe>(bt, t), p2);

                        test.put(bt, next);

                        sol2.put(new Pair<JButton, Pipe>(bt, next), p2);

                    }

                } catch (final Exception ex) {
                    System.out.println(ex);
                }

            } else {

                btnToChange.setIcon(img);
                SwingUtilities.updateComponentTreeUI(gameLev);

            }
        };

    }

    @Override
    public void checkIncreaseLevel(final Player p) {

        if (curr == p.getLevel() && !isACustomLevel) {
            levelBeforeUpdate = curr;
            p.setLevel(curr + 1);
            curr++;
        } else {
            curr++;
        }

    }

    @Override
    public void accessNextLevel(final String title, final String message) {
        if (isACustomLevel) {

        }
        // last level reached, can't progress
        if (this.levelBeforeUpdate == controller.getGameWindowModel().getMaxLevel(gameLev.isCustom()) - 1
                && this.curr == controller.getGameWindowModel().getMaxLevel(gameLev.isCustom())) {
            // must save the level score only once
            if (!scoreSaved) {
                currentLevel = this.curr;
                saveUserInfo();

                saveLevelsScores();
            }
            // the view shows the message and returns the selected option
            joptionResult = gameLev.getJOptionResult(title,
                    "Hai raggiunto il massimo dei livelli, torna al menù. Punteggio livello:" + currentLevelScore,
                    new String[] { "Quit to menu", "Show Level Stats" });

            switch (joptionResult) {
            // quit to menu
            case 0:

                controller.enableView();

                gameLev.dispose();
                break;
            // show current level stats
            case 1:
                gameLev.setEnabled(false);
                new LevelStats(this.currentLevel, gameLev.isCustom(), message, title);
                break;
            default:
                accessNextLevel(title, message);

            }

        } else {
            if (!scoreSaved) {
                currentLevel = this.curr;
                checkIncreaseLevel(pl);

                saveUserInfo();

                saveLevelsScores();
            }

            joptionResult = gameLev.getJOptionResult(title, message,
                    new String[] { "Next level", "Quit to menu", "Show Level Stats" });

            switch (joptionResult) {
            // access to next level
            case 0:
                gameLev.dispose();
                new StandardGameLevel(this.pl, this.gameLev.getWindow(), curr, gameLev.isCustom());
                break;
            case 1:

                gameLev.dispose();
                controller.enableView();
                ;
                break;
            case 2:
                gameLev.setEnabled(false);
                new LevelStats(this.currentLevel, gameLev.isCustom(), message, title);
                break;
            default:
                accessNextLevel(title, message);

            }

        }

    }

    @Override
    public void showFailureOptions(final String title, final String message) {

        joptionResult = gameLev.getJOptionResult(title, message, new String[] { "Restart level", "Quit to menu" });

        switch (joptionResult) {
        // restart level
        case 0:
            gameLev.dispose();
            gameLev.getTimer().stop();
            inizializeCountersStatistics();
            new StandardGameLevel(this.pl, controller.getView(), curr, gameLev.isCustom());
            break;
        case 1:
            gameLev.getTimer().stop();
            controller.enableView();
            gameLev.dispose();
            break;
        default:
            showFailureOptions(title, message);

        }

    }

    @Override
    public int getLines() {
        return this.righe;
    }

    @Override
    public int getColumns() {
        return this.colonne;
    }

    @Override
    public void resetMaps() {
        this.test = new HashMap<>();
        this.sol2 = new HashMap<>();
    }

}
