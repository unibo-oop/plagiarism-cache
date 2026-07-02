package it.unibo.scat.view.menu.usernamepanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.api.ViewActionsInterface;
import it.unibo.scat.view.components.CustomTextField;
import it.unibo.scat.view.util.AudioManager;
import it.unibo.scat.view.util.AudioTrack;

/**
 * Panel that allows the user to enter a username,
 * choose a ship, and start the game.
 */
public final class UsernamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String USERNAME = "USERNAME";
    private static final int VERTICAL_SPACE = 20;
    private final transient ViewActionsInterface menuActionsInterface;
    private CustomTextField usernameField;

    /**
     * Creates the username panel and initializes all UI components.
     *
     * @param menuActionsInterface interface used to handle menu actions
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Intentional exposure")
    public UsernamePanel(final ViewActionsInterface menuActionsInterface) {
        this.menuActionsInterface = menuActionsInterface;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(UIConstants.PANELS_BG_COLOR);
        setBorder(UIConstants.PANELS_BORDER);

        initUsernameText();
        initUsernameField();
        initShipText();
        initButtonsWrapper();
        initPlayButton();
    }

    /**
     * Initializes the label prompting the user to enter a username.
     */
    private void initUsernameText() {
        final JLabel label = new JLabel("ENTER USERNAME");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(UIConstants.FONT_M);
        label.setFocusable(false);
        label.setForeground(Color.GREEN);

        add(Box.createVerticalGlue());
        add(label);
    }

    /**
     * Initializes the username input field with placeholder behavior.
     */
    private void initUsernameField() {
        usernameField = new CustomTextField();
        usernameField.setAlignmentX(CENTER_ALIGNMENT);
        usernameField.setText(USERNAME);
        usernameField.setForeground(Color.GRAY);
        usernameField.setHorizontalAlignment(JTextField.CENTER);

        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                if (USERNAME.equals(usernameField.getText())) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(final FocusEvent e) {
                if (usernameField.getText().isBlank()) {
                    usernameField.setText(USERNAME);
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });

        add(Box.createVerticalStrut(VERTICAL_SPACE));
        add(usernameField);
    }

    /**
     * Initializes the label prompting the user to choose a ship.
     */
    private void initShipText() {
        final JLabel label = new JLabel("CHOOSE SHIP");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(UIConstants.FONT_M);
        label.setFocusable(false);
        label.setForeground(Color.GREEN);

        add(Box.createVerticalGlue());
        add(label);
        add(Box.createVerticalStrut(VERTICAL_SPACE));
    }

    /**
     * Initializes the wrapper containing the ship selection buttons.
     */
    private void initButtonsWrapper() {
        final ButtonsWrapper buttonsWrapper = new ButtonsWrapper(menuActionsInterface);
        buttonsWrapper.setOpaque(false);

        final int width = 450;
        final int height = width / 3;
        final Dimension d = new Dimension(width, height);
        buttonsWrapper.setPreferredSize(d);
        buttonsWrapper.setMaximumSize(d);

        add(buttonsWrapper);
    }

    /**
     * Initializes the play button and its mouse interactions.
     * Starts the game if input and selection are valid.
     */
    private void initPlayButton() {
        final String baseText = " PLAY ";
        final String hoverText = "<PLAY>";
        final Font font = UIConstants.FONT_XXL;

        final JLabel playButton = new JLabel(baseText);
        playButton.setFocusable(false);
        playButton.setFont(font);
        playButton.setForeground(Color.RED);
        playButton.setAlignmentX(CENTER_ALIGNMENT);
        playButton.setHorizontalAlignment(SwingConstants.CENTER);
        playButton.setVerticalAlignment(SwingConstants.CENTER);

        final FontMetrics fm = getFontMetrics(font);
        final int maxWidth = fm.charWidth('W') * 7 + getInsets().left
                + getInsets().right;
        final int maxHeight = fm.getHeight() * 2 + getInsets().top
                + getInsets().bottom;

        final Dimension d = new Dimension(maxWidth, maxHeight);
        playButton.setPreferredSize(d);
        playButton.setMinimumSize(d);
        playButton.setMaximumSize(d);

        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                super.mouseClicked(e);
                if (usernameField.getText().isBlank() || USERNAME.equals(usernameField.getText())
                        || menuActionsInterface.getChosenShipIndex() < 0) {
                    return;
                }

                menuActionsInterface.setUsername(usernameField.getText());
                menuActionsInterface.showGamePanel();
                menuActionsInterface.startGame();
                new AudioManager().play(AudioTrack.OPTION_SELECTED, false);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                playButton.setText(hoverText);
                playButton.setForeground(Color.WHITE);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                new AudioManager().play(AudioTrack.MOUSE_OVER, false);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                playButton.setText(baseText);
                playButton.setForeground(Color.RED);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        add(Box.createVerticalGlue());
        add(playButton);
        add(Box.createVerticalGlue());
    }
}
