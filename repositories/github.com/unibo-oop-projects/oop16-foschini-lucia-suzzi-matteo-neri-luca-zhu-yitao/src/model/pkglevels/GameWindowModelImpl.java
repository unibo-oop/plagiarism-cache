package model.pkglevels;

import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GameWindowControllerImpl;
import model.pkgplayer.Player;
import view.CustomGameLevelCreator;
import view.LogInWindow;

/**
 * Implementation GameWindow interface.
 * 
 * 
 *
 */
public class GameWindowModelImpl extends Observable implements GameWindowModel {
    private final GameWindowControllerImpl controller = GameWindowControllerImpl.getSingleton();
    private Map<JButton, Integer> levelMap = new HashMap<>();
    // lists of level numbers
    private List<Integer> fixedLevelNumbers = new ArrayList<>();
    private List<Integer> customLevelNumbers = new ArrayList<>();
    private int customCounter;
    private final Player p;

    /**
     * Constructor of the class.
     * 
     * @param p1
     *            current player
     */
    public GameWindowModelImpl(final Player p1) {
        this.p = p1;

    }

    @Override
    public void startGameLevel(final int lvl, final boolean custom) {
        new StandardGameLevel(p, controller.getView(), lvl, custom);

    }

    @Override
    public void checkPassedLevel(final Player p) {

        for (final Map.Entry<JButton, Integer> entry : levelMap.entrySet()) {
            // level number > player reached level
            if (entry.getValue() > p.getLevel()) {
                entry.getKey().setEnabled(false);
            } else {
                entry.getKey().setEnabled(true);
            }

        }

    }

    @Override
    public int getMaxLevel(final boolean custom) {
        if (!custom) {
            return this.fixedLevelNumbers.size();
        } else {
            return this.customLevelNumbers.size();
        }

    }

    @Override
    public void closeWindow() {
        controller.getView().dispose();
        new LogInWindow();
    }

    @Override
    public void startCustomLevel() {
        customCounter++;
        new CustomGameLevelCreator(controller.getView(), customCounter);
    }

    @Override
    public ActionListener customLevelClicked() {
        return e -> {
            controller.getView().setEnabled(false);
            startCustomLevel();
        };
    }

    @Override
    public void createLevelsButton(final JPanel inputPanel, final Map<JButton, Integer> inputMap,
            List<Integer> inputLevelList, final File inputFile, final boolean isCustom) {
        int i = 1;

        if (!isCustom) {
            fixedLevelNumbers = readLevels(inputFile);
            inputLevelList = fixedLevelNumbers;
            this.levelMap = inputMap;
        } else {
            customLevelNumbers = readLevels(inputFile);
            inputLevelList = readLevels(inputFile);
            customCounter = inputLevelList.size();
        }

        JButton bt;
        for (; i <= inputLevelList.size(); i++) {
            if (!inputMap.containsValue(i)) {
                bt = new JButton("" + i);

                inputMap.put(bt, i);
                inputPanel.add(bt);

                final ActionListener al = (e) -> {
                    controller.getView().setEnabled(false);
                    final JButton bt1 = (JButton) e.getSource();

                    startGameLevel(Integer.parseInt(bt1.getText()), isCustom);
                };
                bt.addActionListener(al);
            }

        }
        checkPassedLevel(p);

    }

    @Override
    public List<Integer> readLevels(final File inputFile) {
        final List<Integer> tempL = new ArrayList<>();
        String readedString = "";
        int levelNumber = 0;
        String[] readedLine;

        try (BufferedReader bfw = new BufferedReader(new FileReader(inputFile))) {
            while ((readedString = bfw.readLine()) != null && readedString.length() >= 1) {
                readedLine = readedString.split(" ");
                if (readedLine.length < 4) {

                    levelNumber = Integer.parseInt(readedLine[0]);

                    tempL.add(levelNumber);

                }

            }

        } catch (final FileNotFoundException e) {

            e.printStackTrace();
        } catch (final IOException e) {

            e.printStackTrace();
        }
        return tempL;

    }

    @Override
    public void enableWindow() {
        checkPassedLevel(p);
        setChanged();
        notifyObservers();
        controller.getView().updateCustomPanel();

        controller.getView().setEnabled(true);
        controller.getView().setVisible(true);

    }

}
