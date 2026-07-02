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
import javax.swing.JTextField;

/**
 * Panel inside the startup sequence that allows the user to enter
 * their player name for leaderboard and match tracking.
 * * @author Andrea Reggiani
 */
public final class PlayerNamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int INSET_PADDING = 15;
    private static final int FONT_SIZE = 50;
    private static final int FIELD_COLUMNS = 15;
    private static final int COMPONENT_WIDTH = 250;
    private static final int COMPONENT_HEIGHT = 40;

    private static final int MAX_CHARACTERS_LIMIT = 15;

    private static final int GRIDX0 = 0;
    private static final int GRIDY0 = 0;
    private static final int GRIDY1 = 1;
    private static final int GRIDY2 = 2;
    private static final int GRIDY3 = 3;

    /**
     * Constructs the panel displaying a text field for name insertion
     * and confirmation action buttons.
     * 
     * @param nameSubmitted consumer to handle the submitted player name
     * @param backClicked listener for the back button action
     */
    public PlayerNamePanel(final Consumer<String> nameSubmitted, final ActionListener backClicked) {
        super();
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING);
        gbc.gridx = GRIDX0;

        /*
         * The title of the Panel
         */
        final JLabel title = new JLabel("Enter Your Name");
        title.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        title.setForeground(Color.WHITE);
        gbc.gridy = GRIDY0;
        this.add(title, gbc);

        /*
         * Field top insert the name of the player
         */
        final JTextField txtName = new JTextField(FIELD_COLUMNS);
        txtName.setPreferredSize(new Dimension(COMPONENT_WIDTH, COMPONENT_HEIGHT));
        txtName.setHorizontalAlignment(JTextField.CENTER);
        /*
         * default name if the player decides to leave it blank
         */
        txtName.setText("Player"); 
        gbc.gridy = GRIDY1;
        this.add(txtName, gbc);
        /*
         * Confirm button Panel
         */
        final JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setPreferredSize(new Dimension(COMPONENT_WIDTH, COMPONENT_HEIGHT));
        btnConfirm.addActionListener(e -> {

            /*
             * This method ask the JTextField graphics 
             * component and takes the text 
             * string currently inside it.
             */
            final String enteredName = txtName.getText().trim();
            /*
             * trim functionality
             * Its job is to eliminate all white spaces that are located at the left and right of the string. 
             * It does not affect the spaces in the middle of the text.
             */
            if (enteredName.length() >= MAX_CHARACTERS_LIMIT) {
                nameSubmitted.accept("DefaultPal");
            } else {
                if (enteredName.isEmpty()) {
                nameSubmitted.accept("Player");
            } else {
                nameSubmitted.accept(enteredName);
            }
            }
        });
        gbc.gridy = GRIDY2;
        this.add(btnConfirm, gbc);

        /*
         * Button to get Back
         */
        final JButton btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(COMPONENT_WIDTH, COMPONENT_HEIGHT));
        btnBack.addActionListener(backClicked);
        gbc.gridy = GRIDY3;
        this.add(btnBack, gbc);
    }
}
