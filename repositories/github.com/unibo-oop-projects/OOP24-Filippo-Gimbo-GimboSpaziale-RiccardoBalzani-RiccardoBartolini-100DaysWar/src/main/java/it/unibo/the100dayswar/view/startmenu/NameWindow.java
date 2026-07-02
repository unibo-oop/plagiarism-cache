package it.unibo.the100dayswar.view.startmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;
import it.unibo.the100dayswar.commons.utilities.impl.LoadPixelFont;

/**
 * Utility class that implements a custom dialog to insert the player's name.
 */
public final class NameWindow {

    private static final int MAX_LENGTH = 8;
    private static final String RESOURCES = "startmenu/";
    private static final String BACKGROUND_IMAGE = RESOURCES + "calvry.jpg";

    private static final int COLUMNS = 15;

    private static final int BACKGROUND_WIDTH = 500;
    private static final int BACKGROUND_HEIGHT = 600;

    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 60;

    private static final float FONT_SIZE = 15f;
    private static final Font FONT = LoadPixelFont.getFontWithSize(FONT_SIZE);
    private static final String OK_BUTTON_TEXT = "Ok";
    private static final String CANCEL_BUTTON_TEXT = "Cancel";

    /**
     * Private constructor to hide the implicit public one.
     */
    private NameWindow() {
    }

    /**
     * Asks the user for a username by displaying a custom dialog.
     *
     * @param parent the parent frame or null if there is no parent
     * 
     * @return the valid username entered wrapped in an Optional, 
     *         or Optional.empty if the user cancelled/closed the dialog
     */
    public static Optional<String> askUsername(final JFrame parent) {
        final NameDialog dialog = new NameDialog(parent, MAX_LENGTH, BACKGROUND_IMAGE);
        dialog.setVisible(true);
        return dialog.getUsername();
    }

    /**
     * A custom modal dialog for entering a name with a background image.
     */
    private static final class NameDialog extends JDialog {

        private static final long serialVersionUID = 1L;

        private final int maxLength;
        private final String backgroundPath;
        private final JTextField textField = new JTextField(COLUMNS);
        private String username;

        /**
         * Constructs the dialog.
         *
         * @param parent         the parent frame
         * @param maxLength      the maximum allowed length for the username
         * @param backgroundPath path to the background image
         */
        NameDialog(final JFrame parent, final int maxLength, final String backgroundPath) {
            super(parent, "Username Required", true);
            this.maxLength = maxLength;
            this.backgroundPath = backgroundPath;

            initUI();
        }

        /**
         * Initializes the user interface components.
         * It creates panels, buttons, labels, and sets up event handling.
         */
        private void initUI() {
            final ImageIcon backgroundIcon = (ImageIcon) IconLoader.loadIcon(this.backgroundPath);

            /*
             * Main panel that paints the background
             */
            final JPanel backgroundPanel = new JPanel() {
                private static final long serialVersionUID = 1L;

                @Override
                protected void paintComponent(final Graphics g) {
                    super.paintComponent(g);
                    if (backgroundIcon != null) {
                        g.drawImage(
                            backgroundIcon.getImage(),
                            0, 0,
                            getWidth(), getHeight(),
                            this
                        );
                    }
                }
            };
            backgroundPanel.setLayout(new BorderLayout());
            backgroundPanel.setPreferredSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGHT));
 
            final JLabel nameLabel = createNameLabel();

            final JPanel topPanel = createTopPanel(nameLabel);
            final JPanel centerPanel = createCentralPanel();
            final JPanel buttonPanel = createButtonPanel();

            /*
             * Create and add the buttons
             */
            final JButton okButton = createButton(OK_BUTTON_TEXT);
            final JButton cancelButton = createButton(CANCEL_BUTTON_TEXT);
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            /*
             * Add sub-panels to background panel
             */
            backgroundPanel.add(topPanel, BorderLayout.NORTH);
            backgroundPanel.add(centerPanel, BorderLayout.CENTER);
            backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

            finalDialogSetUp(backgroundPanel);

            /*
             * set-up of all the action listeners
             */
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    handleOk();
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    username = null;
                    dispose();
                }
            });

            this.textField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    handleOk();
                }
            });
        }

        /**
         * Validates and sets the username when "OK" is pressed.
         * Closes the dialog if valid, otherwise shows an error and lets the user retry.
         */
        private void handleOk() {
            final String input = textField.getText() == null ? "" : textField.getText().trim();

            if (input.isEmpty()) {
                showErrorDialog("Username cannot be empty. Please try again.");
                return;
            } else if (input.length() > this.maxLength) {
                showErrorDialog("Username cannot exceed " + this.maxLength + " characters. Please try again.");
                return;
            }

            this.username = input;
            dispose();
        }

        /**
         * Shows a small error dialog (replacing or augmenting the original flow).
         *
         * @param message the error message to display
         */
        private void showErrorDialog(final String message) {
            JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }

        /**
         * Gets the username wrapped in an Optional.
         * 
         * @return the valid username entered wrapped in an Optional, 
         *         or Optional.empty if the user cancelled/closed the dialog
         */
        public Optional<String> getUsername() {
            return Optional.ofNullable(this.username);
        }

        /**
         * Creates the label that prompts the user to enter their username.
         *
         * @return a JLabel with the desired text and styling
         */
        private JLabel createNameLabel() {
            final JLabel nameLabel = new JLabel("Enter your username:");
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            nameLabel.setFont(LoadPixelFont.getFontWithSize(FONT_SIZE));
            return nameLabel;
        }

        /**
         * Creates a transparent top panel holding the main label.
         *
         * @param nameLabel the label prompting for username
         * @return a JPanel with the label
         */
        private JPanel createTopPanel(final JLabel nameLabel) {
            final JPanel topPanel = new JPanel();
            topPanel.setOpaque(false);
            topPanel.add(nameLabel);
            return topPanel;
        }

        /**
         * Creates a transparent central panel holding the text field.
         *
         * @return a JPanel with the text field
         */
        private JPanel createCentralPanel() {
            final JPanel centerPanel = new JPanel();
            centerPanel.setOpaque(false);
            this.textField.setFont(FONT);
            centerPanel.add(this.textField);
            return centerPanel;
        }

        /**
         * Creates a transparent panel for holding buttons at the bottom.
         *
         * @return a JPanel to hold buttons
         */
        private JPanel createButtonPanel() {
            final JPanel buttonPanel = new JPanel();
            buttonPanel.setOpaque(false);
            return buttonPanel;
        }

        /**
         * Creates a JButton with a specific text and styling.
         *
         * @param text the text to display on the button
         * @return a JButton with the desired properties
         */
        private JButton createButton(final String text) {
            final JButton button = new JButton(text);
            button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            button.setFont(FONT);
            return button;
        }

        /**
         * Finalize the set-up of the dialog.
         * 
         * @param backgroundPanel the background panel
         */
        private void finalDialogSetUp(final JPanel backgroundPanel) {
            this.getContentPane().add(backgroundPanel);
            this.setResizable(false);
            this.pack();
            this.setLocationRelativeTo(getParent());
        }
    }
}
