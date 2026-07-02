package it.unibo.briscoola.view.impl.popup;

import it.unibo.briscoola.controller.impl.utils.Pair;
import it.unibo.briscoola.view.api.popup.PopupFactory;
import it.unibo.briscoola.view.api.popup.Popups;
import it.unibo.briscoola.view.impl.GameViewImpl;
import it.unibo.briscoola.view.impl.leaderboard.Leaderboard;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.Popup;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Factory class that hallows the creation of a specific {@link Popup}
 * through the use of a {@link Popups} element and the
 * message to be displayed.
 *
 * @author Adam Paolo Razzino
 */
public class PopupFactoryImpl implements PopupFactory {

    private static final int BG_R = 30;
    private static final int BG_G = 100;
    private static final int BG_B = 72;
    private static final int ROWS = 0;
    private static final int COLS = 1;
    private static final int H_GAP = 0;
    private static final int V_GAP = 15;
    private static final int POSITION_RATIO = 20;
    private static final int MEDIUM_FONT_SIZE = 24;
    private static final String FONT = "Serif";
    private final JButton exit;
    private final JRootPane root;
    private final Supplier<List<Pair<String, String>>> leaderboardSupplier;
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final int width = toolkit.getScreenSize().width / 7;
    private final int height = toolkit.getScreenSize().height / 3;
    private boolean isShowing;
    private Popup globalPopup;

    /**
     * Method that creates a factory to deploy {@link Popup} based on the {@link JRootPane}.
     * It accepts a {@link Supplier} to retrieve a {@link List} of {@link Pair} of {@link String}
     * to create a {@link Leaderboard} through a popup implementation.
     *
     * @param root {@link JRootPane} owner of the {@link Popup}
     * @param leaderboardSupplier {@link Supplier} to get the leaderboard list
     */
    public PopupFactoryImpl(final JRootPane root,
                            final Supplier<List<Pair<String, String>>> leaderboardSupplier) {
        this.root = Objects.requireNonNull(root, "JPaneRoot for Popup");
        this.leaderboardSupplier = leaderboardSupplier;
        this.exit = new JButton("Exit Game");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Popup create(final Popups popup, final String message) {
        switch (popup) {
            case WINNER -> {
                return this.roundWinnerPopup(message);
            }
            case ENDGAME -> {
                return this.endGamePopup(message);
            }
            case PAUSE -> {
                return this.pausePopup();
            }
        }
        return null;
    }

    private Popup roundWinnerPopup(final String message) {
        final Popup[] localPopupReference = new Popup[1];
        final int codePoint = 127_941;
        final String trophy = new String(Character.toChars(codePoint));
        final JComponent contentPane = new JPanel(new GridLayout(ROWS, COLS, H_GAP, V_GAP));
        final JLabel trophyLabel = new JLabel(trophy, SwingConstants.CENTER);
        final JLabel messageLabel = new JLabel(
                "<html><body style=\"text-align: justify;  text-justify: inter-word;\">" + message + "</body></html>",
                SwingConstants.CENTER);
        final Border line = BorderFactory.createLineBorder(Color.getHSBColor(30, 100, 72), 2);
        final Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        final java.awt.Dimension dynamicSize = contentPane.getPreferredSize();
        contentPane.setBorder(BorderFactory.createCompoundBorder(line, padding));
        contentPane.setBackground(new Color(BG_R, BG_G, BG_B));
        trophyLabel.setForeground(Color.WHITE);
        messageLabel.setForeground(Color.WHITE);
        trophyLabel.setFont(new Font(FONT, Font.BOLD, 32));
        messageLabel.setFont(new Font(FONT, Font.PLAIN, MEDIUM_FONT_SIZE));
        final JButton hideButton = new JButton("Ok");
        hideButton.addActionListener(e -> {
            this.isShowing = false;
            localPopupReference[0].hide();
        });
        contentPane.add(trophyLabel);
        contentPane.add(messageLabel);
        contentPane.add(hideButton);
        final int x;
        final int y;
        if (root.isShowing()) {
            final java.awt.Point parentLocation = root.getLocationOnScreen();
            x = parentLocation.x + (root.getWidth() - dynamicSize.width) / POSITION_RATIO;
            y = parentLocation.y + (root.getHeight() - dynamicSize.height) / POSITION_RATIO;
        } else {
            final java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = (screenSize.width - dynamicSize.width) / 2;
            y = (screenSize.height - dynamicSize.height) / 2;
        }
        this.isShowing = true;
        localPopupReference[0] = javax.swing.PopupFactory.getSharedInstance().getPopup(
                root,
                contentPane,
                x,
                y
        );
        globalPopup = localPopupReference[0];
        return localPopupReference[0];
    }

    private Popup endGamePopup(final String message) {
        final Popup[] localPopupReference = new Popup[1];
        final int codePoint = 127_942;
        final String trophy = new String(Character.toChars(codePoint));
        final JComponent contentPane = new JPanel(new GridLayout(ROWS, COLS, H_GAP, V_GAP));
        final Border line = BorderFactory.createLineBorder(Color.getHSBColor(30, 100, 72), 2);
        final Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        contentPane.setBorder(BorderFactory.createCompoundBorder(line, padding));
        contentPane.setBackground(new Color(BG_R, BG_G, BG_B));
        contentPane.setSize(toolkit.getScreenSize().width, toolkit.getScreenSize().height);
        final JLabel trophyLabel = new JLabel(trophy, SwingConstants.CENTER);
        final JLabel messageLabel = new JLabel(
                "<html><div style='text-align: center;'>" + message + "</div></html>",
                SwingConstants.CENTER);
        trophyLabel.setForeground(Color.WHITE);
        messageLabel.setForeground(Color.WHITE);
        trophyLabel.setFont(new Font(FONT, Font.BOLD, 32));
        messageLabel.setFont(new Font(FONT, Font.PLAIN, MEDIUM_FONT_SIZE));

        exit.setSize(contentPane.getSize().width / 3, contentPane.getSize().height / 3);
        exit.addActionListener(
                e -> {
                    this.isShowing = false;
                    final Window frame = SwingUtilities.getWindowAncestor(root);
                    if (frame != null) {
                        frame.dispose();
                    }
                }
        );
        final JButton returnHome = new JButton("Home");
        returnHome.addActionListener(e -> {
            final Window frame = SwingUtilities.getWindowAncestor(root);
            if (frame instanceof final GameViewImpl gameView) {
                gameView.showMainMenu();
            }
            if (localPopupReference[0] != null) {
                localPopupReference[0].hide();
            }
        });
        final JButton leaderboard = getLeaderboard();
        contentPane.add(trophyLabel);
        contentPane.add(messageLabel);
        contentPane.add(returnHome);
        contentPane.add(leaderboard);
        contentPane.add(exit);
        final int x;
        final int y;
        if (root.isShowing()) {
            final java.awt.Point parentLocation = root.getLocationOnScreen();
            x = parentLocation.x + (root.getWidth() - contentPane.getPreferredSize().width) / 2;
            y = parentLocation.y + (root.getHeight() - contentPane.getPreferredSize().height) / 2;
        } else {
            final java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = (screenSize.width - contentPane.getPreferredSize().width) / 2;
            y = (screenSize.height - contentPane.getPreferredSize().height) / 2;
        }
        localPopupReference[0] = javax.swing.PopupFactory.getSharedInstance().getPopup(
                root,
                contentPane,
                x,
                y
        );
        this.isShowing = true;
        globalPopup = localPopupReference[0];
        return localPopupReference[0];
    }

    private Popup pausePopup() {
        final Popup[] localPopupReference = new Popup[1];

        final JComponent contentPane = new JPanel(new GridLayout(ROWS, COLS, H_GAP, V_GAP));
        contentPane.setPreferredSize(new java.awt.Dimension(this.width, this.height));
        final Border line = BorderFactory.createLineBorder(Color.getHSBColor(30, 100, 72), 2);
        final Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        contentPane.setBorder(BorderFactory.createCompoundBorder(line, padding));
        contentPane.setBackground(new Color(BG_R, BG_G, BG_B));
        final JLabel pauseLabel = new JLabel("PAUSE", SwingConstants.CENTER);
        pauseLabel.setSize(contentPane.getSize().width / 3, contentPane.getSize().height / 3);
        pauseLabel.setFont(new Font(FONT, Font.BOLD, 32));
        pauseLabel.setForeground(Color.WHITE);
        contentPane.add(pauseLabel);
        final JButton returnHome = new JButton("Home");
        returnHome.addActionListener(e -> {
            final Window frame = SwingUtilities.getWindowAncestor(root);
            if (frame instanceof final GameViewImpl gameView) {
                gameView.showMainMenu();
            }
            if (localPopupReference[0] != null) {
                localPopupReference[0].hide();
            }
        });
        contentPane.add(returnHome);
        final JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            if (localPopupReference[0] != null) {
                localPopupReference[0].hide();
            }
        });
        contentPane.add(closeButton);
        exit.addActionListener(e -> {
                    this.isShowing = false;
                    final Window frame = SwingUtilities.getWindowAncestor(root);
                    frame.dispose();
                }
        );
        contentPane.add(exit);
        contentPane.invalidate();
        final java.awt.Dimension actualSize = contentPane.getPreferredSize();
        final int x;
        final int y;
        if (root.isShowing()) {
            final java.awt.Point parentLocation = root.getLocationOnScreen();
            x = parentLocation.x + (root.getWidth() - actualSize.width) / 2;
            y = parentLocation.y + (root.getHeight() - actualSize.height) / 2;
        } else {
            final java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = (screenSize.width - actualSize.width) / 2;
            y = (screenSize.height - actualSize.height) / 2;
        }
        localPopupReference[0] = javax.swing.PopupFactory.getSharedInstance().getPopup(
                this.root,
                contentPane,
                x,
                y
        );
        this.isShowing = true;
        return localPopupReference[0];
    }

    private JButton getLeaderboard() {
        final JButton leaderboard = new JButton("Leaderboard");
        leaderboard.addActionListener(e -> {
                    final Window parentFrame = SwingUtilities.getWindowAncestor(root);
                    final List<Pair<String, String>> scoreboard = this.leaderboardSupplier.get();
                    final Leaderboard leaderboardView = new Leaderboard(scoreboard);
                    final JDialog dialog = new JDialog(parentFrame, "Leaderboard", Dialog.ModalityType.APPLICATION_MODAL);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setLayout(new BorderLayout());
                    dialog.add(leaderboardView, BorderLayout.CENTER);
                    dialog.pack();
                    dialog.setLocationRelativeTo(parentFrame);
                    dialog.setVisible(true);
                }
        );
        return leaderboard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShowing() {
        return this.isShowing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeLatest() {
        if (globalPopup != null) {
            globalPopup.hide();
        }
    }
}
