package it.unibo.pacman.view;

import static it.unibo.pacman.model.utilities.Difficulty.HARD;
import static it.unibo.pacman.model.utilities.Difficulty.NORMAL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.unibo.pacman.controller.LeaderboardController;
import it.unibo.pacman.controller.LeaderboardControllerImpl;
import it.unibo.pacman.controller.SoundController;
import it.unibo.pacman.controller.game.GameController;
import it.unibo.pacman.controller.game.GameControllerImpl;
import it.unibo.pacman.controller.mapeditor.MapEditorController;
import it.unibo.pacman.controller.mapeditor.MapEditorControllerImpl;
import it.unibo.pacman.controller.utilities.MapIO;
import it.unibo.pacman.view.utilities.BackImagePanel;
import it.unibo.pacman.view.utilities.MapSize;
import it.unibo.pacman.view.utilities.PacFont;

/**
 * Defines the main menu of the game.
 *
 */
public class MainMenuView implements ViewableUI {

    private static final int INSETS = 20;

    private static final int WIDTH = 600;
    private static final int HEIGHT_TITLE = 200;
    private static final int HEIGHT_MAINMENU = 400;
    private static final String PATH = "Images/Backgrounds/";
    private static final String TITLE_IMAGE_PATH = PATH + "TitlePacMan.png";
    private static final String MAIN_BACKGROUND_IMAGE_PATH = PATH + "MainMenuViewPacMan.png";
    private static final String SETTINGS_BACKGROUND_IMAGE_PATH = PATH + "MainMenuViewPacMan2.png";

    private final JFrame frame;
    private final GUIFactory gFactory;
    private GameController gc;
    private final LeaderboardController lbc;
    private MapEditorController mapEditor;
    private final SoundController sc;

    /**
     * Creates a main menu.
     * 
     * @param gameTitle is the title of the game
     */
    public MainMenuView(final String gameTitle) {
        this.gFactory = new GUIFactoryImpl();
        this.lbc = new LeaderboardControllerImpl(this, this.gFactory);
        this.sc = new SoundController();
        this.frame = this.gFactory.createFrame();
        this.frame.setTitle(gameTitle);
        this.load();
    }

    private void load() {
        this.loadTitleImage();
        this.loadMainOptionList(new JPanel());
        this.frame.pack();
    }

    private void loadTitleImage() {
        final JPanel title = new BackImagePanel(new FlowLayout(), TITLE_IMAGE_PATH);
        title.setPreferredSize(new Dimension(WIDTH, HEIGHT_TITLE));
        this.frame.add(title, BorderLayout.NORTH);
    }

    private void loadMainOptionList(final JPanel oldPanel) {
        final JPanel optionList = this.gFactory.createMainMenuOptionPanel(new GridBagLayout(), 
                MAIN_BACKGROUND_IMAGE_PATH, WIDTH, HEIGHT_MAINMENU);
        final GridBagConstraints costr = this.gFactory.createConstraints(INSETS);

        final JButton jbStart = gFactory.createMenuButton("START GAME", e -> {
            this.loadSettingList(optionList);
        });
        optionList.add(jbStart, costr);
        costr.gridy++;
        final JButton jbMapEditor = gFactory.createMenuButton("CREATE MAP", e -> {
            this.loadMapEditorSize(optionList);
        });
        optionList.add(jbMapEditor, costr);
        costr.gridy++;
        final JButton jbRanking = gFactory.createMenuButton("RANKING", e -> {
            this.loadLeaderboardDifficulty(optionList);
        });
        optionList.add(jbRanking, costr);
        costr.gridy++;
        final JButton jbQuit = gFactory.createMenuButton("QUIT", e -> {
            System.exit(0);
        });
        optionList.add(jbQuit, costr);

        this.switchPanels(oldPanel, optionList);
    }

    private void loadLeaderboardDifficulty(final JPanel oldPanel) {
        final JPanel optionList = this.gFactory.createMainMenuOptionPanel(new GridBagLayout(), 
                SETTINGS_BACKGROUND_IMAGE_PATH, WIDTH, HEIGHT_MAINMENU);
        final GridBagConstraints costr = this.gFactory.createConstraints(INSETS);

        costr.gridx = 0;
        costr.gridy = 0;
        final JButton jbNormal = gFactory.createMenuButton("NORMAL", e -> {
            this.close();
            this.lbc.setView(NORMAL);
        });
        optionList.add(jbNormal, costr);
        final JButton jbHard = gFactory.createMenuButton("HARD", e -> {
            this.close();
            this.lbc.setView(HARD);
        });
        costr.gridx = 0;
        costr.gridy = 1;
        optionList.add(jbHard, costr);
        final JButton jbReturn = gFactory.createMenuButton("RETURN", e -> {
            this.loadMainOptionList(optionList);
        });
        costr.gridx = 0;
        costr.gridy = 2;
        optionList.add(jbReturn, costr);
        this.switchPanels(oldPanel, optionList);
    }

    private void loadSettingList(final JPanel oldPanel) {
        final JPanel optionList = this.gFactory.createMainMenuOptionPanel(new GridBagLayout(), 
                SETTINGS_BACKGROUND_IMAGE_PATH, WIDTH, HEIGHT_MAINMENU);
        final GridBagConstraints costr = this.gFactory.createConstraints(INSETS);

        final JTextArea text = this.gFactory.createTextArea(Color.ORANGE, Color.BLACK, false);
        text.setOpaque(false);
        text.setText("CHOOSE MAP:");
        optionList.add(text, costr);
        costr.gridx = costr.gridx + 10;
        final List<String> names = new LinkedList<>();
        MapIO.getMapNames().stream().forEach(s -> names.add(s));
        final JComboBox<String> mapName = this.gFactory.createSelector(names);
        mapName.setBackground(Color.ORANGE);
        mapName.setForeground(Color.BLACK);
        mapName.setFont(new PacFont().getFont());
        optionList.add(mapName, costr);

        costr.gridy++;
        costr.gridx = costr.gridx - 10;
        final JTextArea music = this.gFactory.createTextArea(Color.ORANGE, Color.BLACK, false);
        music.setOpaque(false);
        music.setText("MUSIC:");
        optionList.add(music, costr);

        costr.gridx += 10;
        final List<String> sound = new LinkedList<>(Arrays.asList("ON", "OFF"));
        final JComboBox<String> selectSound = this.gFactory.createSelector(sound);
        selectSound.setBackground(Color.ORANGE);
        selectSound.setForeground(Color.BLACK);
        selectSound.setFont(new PacFont().getFont());
        optionList.add(selectSound, costr);

        costr.gridy++;
        costr.gridx = costr.gridx - 10;
        final JTextArea difficulty = this.gFactory.createTextArea(Color.ORANGE, Color.BLACK, false);
        difficulty.setOpaque(false);
        difficulty.setText("DIFFICULTY:");
        optionList.add(difficulty, costr);

        costr.gridx += 10;
        final List<String> difficulties = new LinkedList<>(Arrays.asList(NORMAL.toString(), HARD.toString()));
        final JComboBox<String> selectDiff = this.gFactory.createSelector(difficulties);
        selectDiff.setBackground(Color.ORANGE);
        selectDiff.setForeground(Color.BLACK);
        selectDiff.setFont(new PacFont().getFont());
        optionList.add(selectDiff, costr);

        costr.gridy++;
        costr.gridx -= 10;
        final JButton jbReturn = gFactory.createMenuButton("RETURN", e -> {
            this.loadMainOptionList(optionList);
        });
        optionList.add(jbReturn, costr);

        costr.gridx += 10;
        final JButton jbStart = gFactory.createMenuButton("START", e -> {
            this.gc = new GameControllerImpl(this, this.gFactory);
            if (((String) selectSound.getSelectedItem()).equals("ON")) {
                sc.setMusicdOn();
            } else {
                sc.setMusicOff();
            }
            sc.setSounds();
            if (selectDiff.getSelectedItem().equals(NORMAL.toString())) {
                this.gc.startGame((String) mapName.getSelectedItem(), NORMAL);
            }
            if (selectDiff.getSelectedItem().equals(HARD.toString())) {
                this.gc.startGame((String) mapName.getSelectedItem(), HARD);
            }
            this.switchPanels(optionList, oldPanel);
        });
        optionList.add(jbStart, costr);

        this.switchPanels(oldPanel, optionList);
    }


    private void loadMapEditorSize(final JPanel oldPanel) {
        final JPanel optionList = this.gFactory.createMainMenuOptionPanel(new GridBagLayout(), 
                SETTINGS_BACKGROUND_IMAGE_PATH, WIDTH, HEIGHT_MAINMENU);
        final GridBagConstraints costr = this.gFactory.createConstraints(INSETS);

        final JTextArea text = this.gFactory.createTextArea(null, Color.BLACK, false);
        text.setOpaque(false);
        text.setText("MAP SIZE:");
        optionList.add(text, costr);

        costr.gridx += 10;
        final List<String> names = new LinkedList<>();
        Arrays.asList(MapSize.class.getEnumConstants()).forEach(e -> names.add(e.toString()));
        final JComboBox<String> mapName = this.gFactory.createSelector(names);
        mapName.setBackground(Color.ORANGE);
        mapName.setForeground(Color.BLACK);
        mapName.setFont(new PacFont().getFont());
        optionList.add(mapName, costr);

        costr.gridx -= 10;
        costr.gridy++;
        final JButton jbReturn = gFactory.createMenuButton("RETURN", e -> {
            this.loadMainOptionList(optionList);
        });
        optionList.add(jbReturn, costr);

        costr.gridx += 10;
        final JButton ok = this.gFactory.createMenuButton("OK", e1 -> {
            final String selectedSize = (String) mapName.getSelectedItem();
            this.mapEditor = new MapEditorControllerImpl(this, this.gFactory, 
                    MapSize.getEnumBySize(selectedSize).getHeight(), MapSize.getEnumBySize(selectedSize).getWidth());
            this.mapEditor.showEditor();
            this.switchPanels(optionList, oldPanel);
        });
        optionList.add(ok, costr);

        this.switchPanels(oldPanel, optionList);
    }

    private void switchPanels(final JPanel oldPanel, final JPanel newPanel) {
        this.frame.remove(oldPanel);
        this.frame.add(newPanel, BorderLayout.CENTER);
        this.frame.pack();
    }

    /**
     * Gets the leaderboard controller.
     * 
     * @return leaderboardController
     */
    public LeaderboardController getLbc() {
        return lbc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        this.frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.frame.setVisible(false);
    }

}
