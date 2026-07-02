package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;
import it.unibo.oop.utilities.Settings;

/**
 * Custom {@link javax.swing.JPanel} used as template for game's menu. It
 * defines standard buttons, layout and background image.
 */
public class MenuPanel extends BackgroundPanel implements MenuInterface {

    private static final long serialVersionUID = 1L;
    private static final Color COMPONENTS_COLOR = new Color(255, 220, 130);
    private static final Color FONT_COLOR = Color.WHITE;
    private static final int PANEL_WIDTH = Settings.MENU_DIMENSION.width - 10;
    private static final int PANEL_HEIGHT = Settings.MENU_DIMENSION.height / 2 - 10;
    private static final int FONT_SIZE = PANEL_HEIGHT / 8;
    private static final int TOP_INSET = PANEL_HEIGHT / 30;
    private static final int LEFT_INSET = PANEL_HEIGHT / 30;
    private static final int BOTTOM_INSET = PANEL_HEIGHT / 30;
    private static final int RIGHT_INSET = PANEL_HEIGHT / 30;
    private static final Dimension PREF_SIZE = new Dimension(PANEL_WIDTH / 5, (int) (PANEL_HEIGHT / 7));
    private final GridBagConstraints cnst = new GridBagConstraints();
    private final List<StateObserver> obsList;

    /**
     * Creates a new panel.
     */
    public MenuPanel() {
        super("/background.jpg");

        this.cnst.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET);
        this.cnst.gridy = 0;
        this.setLayout(new GridBagLayout());
        this.obsList = new ArrayList<>();
    }

    @Override
    public void addComponent(final JComponent cmp) {
        if (cmp instanceof JButton) {
            this.customizeButton((JButton) cmp);
        }
        this.add(cmp, cnst);
        cnst.gridy++;

    }

    @Override
    public void addComponents(final JComponent... cmps) {
        final JPanel nestedPanel = this.customPanel(PANEL_WIDTH, PANEL_HEIGHT);
        nestedPanel.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT / 2));
        Arrays.asList(cmps).forEach(e -> {
            if (e instanceof JLabel) {
                this.customizeLabel((JLabel) e);
            }
            e.setAlignmentX(Component.CENTER_ALIGNMENT);
            nestedPanel.add(e);
        });
        this.addComponent(nestedPanel);
    }

    private JPanel customPanel(final int width, final int height) {
        final JPanel nestedPanel = new JPanel();
        nestedPanel.setLayout(new BoxLayout(nestedPanel, BoxLayout.PAGE_AXIS));
        nestedPanel.setPreferredSize(new Dimension(width, height));
        nestedPanel.setOpaque(false);
        return nestedPanel;
    }

    private JButton customizeButton(final JButton btn) {
        btn.setPreferredSize(PREF_SIZE);
        btn.setMinimumSize(PREF_SIZE);
        btn.setBackground(COMPONENTS_COLOR);
        return btn;
    }

    private JLabel customizeLabel(final JLabel label) {
        label.setFont(new Font("AppStyle", Font.PLAIN, FONT_SIZE));
        label.setForeground(FONT_COLOR);
        return label;
    }

    @Override
    public void addObserver(final StateObserver obs) {
        this.obsList.add(obs);
    }

    @Override
    public void addStateButton(final StateButton... btns) {
        Arrays.asList(btns).forEach(btn -> {
            final JButton jBtn = new JButton(btn.getName());
            jBtn.addActionListener(
                    (e) -> this.doAction(obs -> new Thread(() -> obs.stateAction(btn.getState())).start()));
            this.addComponent(jBtn);
        });
    }

    @Override
    public void setIcon(final String path) {
        final URL imgURL = MenuPanel.class.getResource(path);
        final ImageIcon icon = new ImageIcon(imgURL);
        final JLabel label = new JLabel(icon);
        final JPanel nestedPanel = this.customPanel(PANEL_WIDTH, PANEL_HEIGHT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        nestedPanel.add(label);
        this.addComponent(nestedPanel);
    }

    @Override
    public void doAction(final Consumer<StateObserver> action) {
        this.obsList.forEach(action);
    }

    /**
     * Class for a custom button with an {@link AppState}.
     */
    public static class StateButton {
        private final String name;
        private final AppState state;

        public StateButton(final String name, final AppState state) {
            this.name = name;
            this.state = state;
        }

        public String getName() {
            return this.name;
        }

        public AppState getState() {
            return this.state;
        }
    }
}