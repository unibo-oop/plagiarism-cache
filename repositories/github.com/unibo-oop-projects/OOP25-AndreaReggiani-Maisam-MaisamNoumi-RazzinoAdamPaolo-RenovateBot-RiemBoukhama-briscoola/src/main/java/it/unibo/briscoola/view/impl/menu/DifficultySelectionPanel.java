package it.unibo.briscoola.view.impl.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibo.briscoola.model.api.attributes.Difficulty;

/**
 * Panel in the startup that allows the player to select 
 * the CPU difficulty before starting the match.
 * 
 * @author Andrea Reggiani
 */
public final class DifficultySelectionPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int INSET_PADDING = 15;
    private static final int FONT_SIZE = 60;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;

    private static final int GRIDX0 = 0;
    private static final int GRIDY0 = 0;
    private static final int GRIDY1 = 1;
    private static final int GRIDY2 = 2;
    private static final int GRIDY3 = 3;
    private static final int GRIDY4 = 4;

    /**
     * Constructs a panel for selecting the difficulty.
     * 
     * @param chooseDifficulty the consumer triggered when a button is clicked
     * @param backClicked button to go back on previous page
     */
    public DifficultySelectionPanel(final Consumer<Difficulty> chooseDifficulty, final ActionListener backClicked) {

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING);
        gbc.gridx = GRIDX0;

        final JLabel title = new JLabel("Select CPU Difficulty");
        title.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        title.setForeground(Color.WHITE);
        gbc.gridy = GRIDY0;
        this.add(title, gbc);

        final JButton btnEasy = new JButton("Easy");
        btnEasy.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnEasy.addActionListener(e -> chooseDifficulty.accept(Difficulty.EASY));
        gbc.gridy = GRIDY1;
        this.add(btnEasy, gbc);

        final JButton btnMedium = new JButton("Medium");
        btnMedium.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnMedium.addActionListener(e -> chooseDifficulty.accept(Difficulty.MEDIUM));
        gbc.gridy = GRIDY2;
        this.add(btnMedium, gbc);

        final JButton btnHard = new JButton("Hard");
        btnHard.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnHard.addActionListener(e -> chooseDifficulty.accept(Difficulty.HARD));
        gbc.gridy = GRIDY3;
        this.add(btnHard, gbc);

        final JButton btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnBack.addActionListener(backClicked);
        gbc.gridy = GRIDY4;
        this.add(btnBack, gbc);
    }
}
