package it.unibo.bmbman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.bmbman.controller.game.GameController;
import it.unibo.bmbman.model.leaderboard.PlayerScoreImpl;
import it.unibo.bmbman.model.leaderboard.ScoreHandler;
import it.unibo.bmbman.view.utilities.BackgroundPanel;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/** 
 * Frame for game over.
 */
public class EndView {
    private static final Insets INSETS = new Insets(50, 25, 50, 25);
    private static final Insets TEXTINSET = new Insets(0, 10, 0, 0);
    private static final double WEIGHTX = 0.1;
    private final GUIFactoryImpl gui;
    private final MainMenuView mainView;
    private final JFrame f;
    private JPanel centerP; 
    private JPanel northP;
    private JButton enterName;
    private JButton nextLevel;
    private JTextField nameTextField;
    private String gameOverImagePath;
    private GridBagConstraints c;
    private final int score;
    private final GameController gameController;
    private final SinglePlayerView spv;
    private final PlayerScoreImpl ps;
    /**
     * 
     * @param mainMenuView 
     * @param gameController 
     * @param spv 
     * @param ps 
     */
    public EndView(final MainMenuView mainMenuView, final GameController gameController, final SinglePlayerView spv, final PlayerScoreImpl ps) {
        mainView = mainMenuView;
        this.gui = new GUIFactoryImpl();
        this.f = gui.createFrame();
        this.spv = spv;
        this.gameController = gameController;
        this.ps = ps;
        this.score = ps.getScore();
        loadEndView();
    }

    /**
     * Customize the GameOverView view frame.
     */
    private void loadEndView() {
        if (this.gameController.hasWon()) { 
            f.setTitle("BOMBERMAN - You WIN!");
        } else {
            f.setTitle("BOMBERMAN - GameOver");
        }

        f.setBackground(Color.black);
        saveGameOverImagePath();
        loadPanels();
        loadLabels();
        loadTextField();
        loadButtons();
    }

    /**
     * Used to loadLabels.
     */
    private void loadLabels() {
        JLabel titleLabel = gui.createLabel("");
        if (this.gameController.isGameOver()) {
            titleLabel = gui.createLabel("Game Over");
        } else {
            titleLabel = gui.createLabel("YOU WIN!");
        }
        final JLabel timeLabel = gui.createLabel("Game Time");
        final JLabel playerTimeLabel = gui.createLabel(this.spv.getTime());
        final JLabel scoreLabel = gui.createLabel("Score");
        final JLabel playerScoreLabel = gui.createLabel(String.valueOf(this.score));
        final JLabel enterYourName = gui.createLabel("Enter your name");
        northP.add(titleLabel, BorderLayout.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        centerP.add(timeLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        centerP.add(playerTimeLabel, c);
        c.gridx = 0;
        c.gridy = 1;
        centerP.add(scoreLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        centerP.add(playerScoreLabel, c);
        c.gridx = 0;
        c.gridy = 2;
        centerP.add(enterYourName, c);
    }
    /**
     * Used to load Panels.
     */
    private void loadPanels() {
        final JPanel backgroundP = new BackgroundPanel(loadImage(gameOverImagePath));
        backgroundP.setBackground(Color.BLACK);
        centerP = new JPanel(new GridBagLayout());
        centerP.setOpaque(false);
        northP = new JPanel();
        northP.setBackground(Color.BLACK);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = WEIGHTX;
        c.weighty = 0;
        c.insets = gui.createScaledInsets(INSETS);
        backgroundP.setLayout(new BorderLayout());
        backgroundP.add(centerP, BorderLayout.WEST);
        f.add(backgroundP, BorderLayout.CENTER);
        f.add(northP, BorderLayout.NORTH);
    }
    /**
     * Used to load buttons.
     */
    private void loadButtons() {
        final JButton returnB = gui.createReturnButton(this.f);
        returnB.addActionListener(e -> {
            this.f.setVisible(false);
            mainView.getFrame().setVisible(true);
        });
        enterName = gui.createButton("Save");
        enterName.setEnabled(false);
        enterName.setBorderPainted(true);
        enterName.addActionListener(e -> {
            ScoreHandler.checkAndWrite(this.gameController.getLevel().getLevel(), ps, nameTextField.getText(), this.spv.getTime());
            enterName.setEnabled(false);
        });
        c.gridx = 1;
        c.gridy = 3;
        centerP.add(enterName, c);
        if (this.gameController.hasWon() && this.gameController.getLevel().getLevel() < 3) {
            nextLevel = gui.createButton("Go to next level");
            nextLevel.setBorderPainted(true);
            nextLevel.setBackground(Color.darkGray);
            nextLevel.addActionListener(e -> {
                gameController.getLevel().levelUp();
                gameController.startGame();
                this.getFrame().setVisible(false);
            });
            c.gridx = 2;
            c.gridy = 3;
            centerP.add(nextLevel, c);
        }
    }
    /**
     * Used to load JTextField. 
     */
    private void loadTextField() {
        this.nameTextField = gui.createTextField();
        c.gridx = 0;
        c.gridy = 3;
        nameTextField.setMargin(gui.createScaledInsets(TEXTINSET));
        nameTextField.setCaretColor(Color.WHITE);
        centerP.add(nameTextField, c);
        nameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent event) {
                final String content = nameTextField.getText();
                if (!content.equals("")) {
                    enterName.setEnabled(true);
                } else {
                    enterName.setEnabled(false);
                }
            }
        });
    }
    /**
     * Used to get the actual frame.
     * @return frame 
     */
    public JFrame getFrame() {
        return this.f;
    }
    /**
     * Used to save the appropriete image according to screen resolution.
     */
    private void saveGameOverImagePath() {
        gameOverImagePath = "/image/" + ScreenToolUtils.getScreenRes() + "_GameOverImage.png";
    }

    private Image loadImage(final String text) {
        try {
            return ImageIO.read(getClass().getResource(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
