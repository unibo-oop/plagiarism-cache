package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.GameMode;
import game.Player;

/**
 *
 */
public final class EndScreen extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -3071972695129617473L;

    /**
     * A string identifying this panel.
     */
    public static final String TITLE = "End Screen";
    private static final int BACK_TO_MENU_PROPORTION = 15;
    private static final int TEXT_PROPORTION = 20;
    private static final int ROWS = 6;
    private static final int COLS = 5;
    private static final int FIRST_BUTTON_POSITION = 12;
    private static final int SECOND_BUTTON_POSITION = 4;
    private static final List<String> NOT_USEFUL = Arrays.asList("You're not that good....", "Try again", 
            "Maybe next time you won't die so miserably", "Why you clicked here?", 
            "Wow, that was a good one.... if you were a monkey", "Looser");
    private static final String SECRET = "I HATE SWING!";

    private final BackgroundAnimator bgAnimator = new BackgroundAnimator(TITLE);
    private final JButton backToMenu;
    private final JButton text;
    private final String funnyText;
    /**
     * @param mode gameMode played
     * @param player the player/s that played this game
     * @param score score achieved in this game
     * @param view view that displays this panel
     */
    public EndScreen(final GameMode mode, final List<Player> player, final int score, final View view) {
        super();
        this.funnyText = generateRandomText();
        this.backToMenu = new JButton("Back");
        if (mode.equals(GameMode.MULTIPLAYER)) {
            if (player.get(0).getHealth() > 0 && player.get(1).getHealth() > 0) {
                this.text = new JButton("You quitted");
            } else if (player.get(0).getHealth() > 0) {
                this.text = new JButton("Player 1 Won");
            } else {
                this.text = new JButton("Player 2 Won");
            }
        } else {
            this.text = new JButton("Score: " + score);
        }
        this.backToMenu.addActionListener(e -> view.resetToMenu());
        this.backToMenu.setBorder(BorderFactory.createEmptyBorder());
        this.backToMenu.setBackground(Color.BLACK);
        this.backToMenu.setForeground(Color.BLACK);
        this.backToMenu.setOpaque(false);
        this.text.setBorder(BorderFactory.createEmptyBorder());
        this.text.setBackground(Color.BLACK);
        this.text.setForeground(Color.BLACK);
        this.text.addActionListener(e -> JOptionPane.showMessageDialog(this, funnyText));
        this.text.setOpaque(false);
        this.setLayout(new GridLayout(ROWS, COLS));
        IntStream.range(0, FIRST_BUTTON_POSITION).forEach(i -> this.add(Box.createRigidArea(new Dimension(0, 0))));
        this.add(this.text);
        IntStream.range(0, SECOND_BUTTON_POSITION).forEach(i -> this.add(Box.createRigidArea(new Dimension(0, 0))));
        this.add(backToMenu);
        IntStream.range(0, FIRST_BUTTON_POSITION).forEach(i -> this.add(Box.createRigidArea(new Dimension(0, 0))));
        this.repaint();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.backToMenu.setFont(new Font("arial", 2, this.getHeight() / BACK_TO_MENU_PROPORTION));
        this.text.setFont(new Font("arial", 2, this.getHeight() / TEXT_PROPORTION));
        g.drawImage(bgAnimator.loadImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private String generateRandomText() {
        final Random random = new Random();
        if (random.nextInt(1000) == 4) {
            return SECRET;
        } else {
            return NOT_USEFUL.get(random.nextInt(NOT_USEFUL.size()));
        }
    }
}
