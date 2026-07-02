package it.unibo.briscoola.view.impl.menu;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;

/**
 * Component that extends {@link JDialog}.
 * It's used to show to the user the rules of the game on screen.
 *
 * @author Adam Paolo Razzino
 */
public final class RulesDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    /**
     * Builds an element of {@link JDialog} over the parent Frame.
     * It contains the rules of the game.
     *
     * @param parentFrame {@link JFrame} owner of the {@link JDialog}
     */
    public RulesDialog(final Window parentFrame) {
        super(parentFrame, "Rules of the Game");
        final int width = 550;
        final int height = 600;
        final int bgR = 30;
        final int bgG = 100;
        final int bgB = 72;
        final int borderRadius = 20;
        final int textFontSize = 14;
        final int buttonTextSize = 16;
        final int buttonBorderThickness = 1;
        final int buttonVerticalBorder = 10;
        final int buttonHorizontalBorder = 30;
        final int hgap = 0;
        final int vgap = 15;

        setSize(width, height);
        setLocationRelativeTo(parentFrame);
        final Color greenBg = new Color(bgR, bgG, bgB);
        final JTextArea textArea = new JTextArea(getRulesText());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.BOLD, textFontSize));
        textArea.setBackground(greenBg);
        textArea.setForeground(Color.WHITE);
        textArea.setBorder(BorderFactory.createEmptyBorder(borderRadius, borderRadius, borderRadius, borderRadius));
        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        final JButton closeButton = new JButton("CLOSE");
        closeButton.setFont(new Font("Serif", Font.BOLD, buttonTextSize));
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, buttonBorderThickness),
                BorderFactory.createEmptyBorder(buttonVerticalBorder,
                        buttonHorizontalBorder,
                        buttonVerticalBorder,
                        buttonHorizontalBorder)
        ));
        closeButton.addActionListener(e -> dispose());
        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, hgap, vgap));
        buttonPanel.setBackground(greenBg);
        buttonPanel.add(closeButton);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private String getRulesText() {
        return """
                BRISCOLA GAME RULES

                1. SETUP
                • The player enters an optional name.
                • The player is required to choose the game Difficulty.
                • Each player is given 3 cards.
                • The Briscola card gets chosen and put on the table face up.
                2. CARD EXPLANATION:
                The Cards follow a power logic following the order of the sequent list
                In which there will be listed the points of each card as well
                TWO -> 0
                FOUR -> 0
                FIVE -> 0
                SIX -> 0
                SEVEN -> 0
                EIGHT/SOLDIER -> 2
                NINE/HORSE -> 3
                TEN/KING -> 4
                THREE -> 10
                ONE/ACE -> 11
                3. YOUR TURN
                Select the card to be played based on:
                 - Cards on the table:
                       Remember that the briscola seed rules over every other
                       If there is no briscola on the table the leadSeed rules
                4. CPU TURN
                The CPU elaborates what is on the table and makes its move
                based on the chosen difficulty
                5. DETERMINE WINNER
                The game decides the winner of the round and starts a new round
                The next round is started by the winner of the latest
                This process repeats until the deck and every player's hand is empty
                6. LEADERBOARD SCORING
                The player will see his points on the leaderboard
                If high enough
                """;
    }
}
