package it.unibo.pacman.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import it.unibo.pacman.controller.game.EVKeyInput;
import it.unibo.pacman.model.utilities.Difficulty;
import it.unibo.pacman.view.utilities.BackImagePanel;
import it.unibo.pacman.view.utilities.PacFont;
/**
 * An implementation of {@link ViewableUI}. The View of the end game.
 */
public class EndView implements ViewableUI {

    private final GUIFactory gf;
    private JFrame frame;
    private BackImagePanel panel;
    private GridBagConstraints gbc;
    private JTextField jtf;
    private final Difficulty difficulty;
    private final MainMenuView mmv;
    private final int score;
    private final boolean hasWon;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int SCORE_THRESHOLD = 1_000_000;
    private static final double SMALLER_SCALE = 1.2;
    private static final String PATH = "Images/Backgrounds/EndViewPacMan.png";
    /**
     * Create an {@link EndView}.
     * @param mmv the mainmenuview.
     * @param gf the GUIFactory.
     * @param score the final score.
     * @param difficulty the difficulty of game.
     * @param hasWon if the player has won or not.
     */
    public EndView(final MainMenuView mmv, final GUIFactory gf, final int score, final Difficulty difficulty, final boolean hasWon) {
        this.difficulty = difficulty;
        this.score = score;
        this.gf = gf;
        this.mmv = mmv;
        this.hasWon = hasWon;
        loadEndView();
        frame.add(panel);
        frame.setSize(panel.getSize());
        frame.setVisible(false);
    }

    private void loadEndView() {
        loadFrame();
        loadPanel();
        loadScore();
        loadPlayerNameInput();
        loadSubmitButton();
    }

    private void loadFrame() {
        frame = gf.createFrame();
        if (hasWon) {
            frame.setTitle("YOU WON!");
        } else {
            frame.setTitle("YOU LOST!");
        }
    }

    private void loadPanel() {
        panel = gf.createBackImagePanel(new GridBagLayout(), PATH);
        panel.setSize(WIDTH, HEIGHT);
        gbc = new GridBagConstraints();
    }

    private void loadScore() {
        final JLabel scoreLabel = gf.createLabel();
        if (score > SCORE_THRESHOLD) {
            scoreLabel.setFont(new PacFont(SMALLER_SCALE).getFont());
        }
        scoreLabel.setText("YOUR SCORE: " + score);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(scoreLabel, gbc);
    }

    private void loadPlayerNameInput() {
        jtf = gf.createTextInputField(true, 10);
        final JScrollPane jsp = new JScrollPane(jtf);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(jsp, gbc);
    }

    private void loadSubmitButton() {
        final JButton submit = gf.createMenuButton("SUBMIT", new HandlerAdapter());
        jtf.addKeyListener(new EVKeyInput(submit));
        submit.setBorderPainted(false);
        submit.setForeground(Color.WHITE);
        submit.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(submit, gbc);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void show() {
        this.frame.setVisible(true);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void close() {
        this.frame.dispose();
    }

    private void submitAction() {
        if (jtf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this.frame, "INSERT A VALID NAME!");
        } else {
            mmv.getLbc().writeScore(jtf.getText(), score, difficulty);
            close();
            mmv.show();
        }
    }

    private class HandlerAdapter implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            EndView.this.submitAction();
        }
    }
}
