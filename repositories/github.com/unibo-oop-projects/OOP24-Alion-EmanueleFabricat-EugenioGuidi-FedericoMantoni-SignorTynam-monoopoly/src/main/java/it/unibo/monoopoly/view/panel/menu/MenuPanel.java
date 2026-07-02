package it.unibo.monoopoly.view.panel.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.monoopoly.controller.menu.api.MenuController;

/**
 * Panel showed at the start of the application.
 */
public final class MenuPanel extends JPanel {

    private static final String ARIAL_FONT = "Arial";
    private static final long serialVersionUID = 1L;
    private static final int MARGIN_TOP_TITLE = 70;
    private static final int PADX_TITLE = 250;
    private static final int PADY_TITLE = 50;
    private static final int PADX_BUTTON = 80;
    private static final int PADY_BUTTON = 40;
    private static final int FONT_SIZE_TITLE = 60;
    private static final int FONT_SIZE_BUTTON = 20;
    private static final Color GREEN_MONOPOLY = new Color(0xecfcf4);

    /**
     * The istance of {@link SelectionPanel}.
     */
    private final SelectionPanel numberSelectionPanel;
    /**
     * The istance of {@link NameSelectorPanel}.
     */
    private NameSelectorPanel nameSelectorPanel;
    /**
     * The controller of the menu.
     */
    private final transient MenuController controller;
    /**
     * The list of colors used in the game to represent players.
     */
    private final List<Color> colors;

    /**
     * Construct and inizialize the MenuPanel.
     * 
     * @param controller the istance of {@link MenuController}, needed to initialize
     *                   the game based on player inputs
     * @param colors     the colors used in the game to represent the players
     */
    public MenuPanel(final MenuController controller, final List<Color> colors) {
        super();

        this.numberSelectionPanel = new SelectionPanel(this::showNamePanel);
        this.colors = new ArrayList<>(colors);
        this.controller = controller;
        final JButton start = new JButton("START");
        final JLabel monoopoly = new JLabel("MONOOPOLY");
        final JPanel title = new JPanel(new BorderLayout());
        final JPanel selection = new JPanel(new GridBagLayout());

        this.setLayout(new GridBagLayout());

        this.setBackground(GREEN_MONOPOLY);

        start.setFont(new Font(ARIAL_FONT, Font.BOLD, FONT_SIZE_BUTTON));
        start.setPreferredSize(start.getPreferredSize());

        title.setBackground(Color.RED);

        monoopoly.setFont(new Font(ARIAL_FONT, Font.BOLD, FONT_SIZE_TITLE));
        monoopoly.setForeground(Color.WHITE);
        monoopoly.setHorizontalAlignment(SwingConstants.CENTER);
        title.add(monoopoly, BorderLayout.CENTER);

        start.addActionListener(e -> {
            start.setVisible(false);
            this.add(selection, getSelectionConstraints());
            selection.add(numberSelectionPanel, getBasicConstraints());
        });

        this.add(start, getButtonCostraints());
        this.add(title, getTitleConstraints());
        this.revalidate();
        this.repaint();
    }

    private ActionListener getBackListener(final JPanel panel) {
        return new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                nameSelectorPanel.setVisible(false);
                numberSelectionPanel.setVisible(true);
                panel.revalidate();
                panel.repaint();
            }
        };
    }

    private void showNamePanel(final int nPlayers) {
        this.remove(numberSelectionPanel);
        this.nameSelectorPanel = new NameSelectorPanel(colors, controller, getBackListener(this), nPlayers);
        this.add(this.nameSelectorPanel, getNamesConstraint());
    }

    private GridBagConstraints getButtonCostraints() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 1;
        gbc.ipadx = PADX_BUTTON;
        gbc.ipady = PADY_BUTTON;
        return gbc;
    }

    private GridBagConstraints getTitleConstraints() {
        final GridBagConstraints out = new GridBagConstraints();
        out.anchor = GridBagConstraints.NORTH;
        out.fill = GridBagConstraints.NONE;
        out.gridwidth = GridBagConstraints.REMAINDER;
        out.gridx = 0;
        out.gridy = 0;
        out.weightx = 1;
        out.weighty = 1;
        out.ipadx = PADX_TITLE;
        out.ipady = PADY_TITLE;
        out.insets = new Insets(MARGIN_TOP_TITLE, 10, 10, 10);
        return out;
    }

    private GridBagConstraints getSelectionConstraints() {
        final GridBagConstraints out = new GridBagConstraints();
        out.anchor = GridBagConstraints.CENTER;
        out.fill = GridBagConstraints.NONE;
        out.gridx = 0;
        out.gridy = 1;
        out.weighty = 1;
        out.insets = new Insets(10, 10, 10, 10);
        return out;
    }

    private GridBagConstraints getNamesConstraint() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }

    private GridBagConstraints getBasicConstraints() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }
}
