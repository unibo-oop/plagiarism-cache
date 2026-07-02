package pvz.view.gameview.impl;

import pvz.controller.gamecontroller.api.ViewListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;

/**
 * Toolbar component displaying plant selection buttons and game statistics (sun and kill count).
 */
public final class GameToolBar extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_FONT_SIZE = 14;
    private static final int BUTTON_WIDTH = 140;
    private static final int BUTTON_HEIGHT = 30;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 5;
    private static final int STRUT_WIDTH = 20;

    private final JButton peaButton = new JButton("Peashooter (50)");
    private final JButton snflButton = new JButton("Sunflower (25)");
    private final JButton wlButton = new JButton("Wall-nut (75)");
    private final JLabel sunCounterLabel = new JLabel("☀ Sun: 0");
    private final JLabel killCounterLabel = new JLabel("💀 Kills: 0");
    private transient ViewListener listener;

    /**
     * Constructs the toolbar and initializes UI components.
     *
     * @param scaling the scaling factor for UI elements
     */
    public GameToolBar(final double scaling) {
        super();
        initializeUI(scaling);
    }

    /**
     * Initializes the UI components and layout.
     *
     * @param scaling the scaling factor for UI elements
     */
    private void initializeUI(final double scaling) {
        setLayout(new FlowLayout(FlowLayout.LEFT,
                (int) (HORIZONTAL_GAP * scaling), (int) (VERTICAL_GAP * scaling)));
        setBackground(Color.LIGHT_GRAY);

        final Font font = new Font("SansSerif", Font.PLAIN, (int) (DEFAULT_FONT_SIZE * scaling));
        peaButton.setFont(font);
        snflButton.setFont(font);
        wlButton.setFont(font);
        sunCounterLabel.setFont(font);
        killCounterLabel.setFont(font);

        final int buttonWidth = (int) (BUTTON_WIDTH * scaling);
        final int buttonHeight = (int) (BUTTON_HEIGHT * scaling);

        peaButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        snflButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        wlButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        add(peaButton);
        add(snflButton);
        add(wlButton);
        add(Box.createHorizontalStrut((int) (STRUT_WIDTH * scaling)));
        add(sunCounterLabel);
        add(killCounterLabel);

        peaButton.addActionListener(e -> plantSelection(ViewListener.UserInputRoaster.PEASHOOTER));
        snflButton.addActionListener(e -> plantSelection(ViewListener.UserInputRoaster.SUNFLOWER));
        wlButton.addActionListener(e -> plantSelection(ViewListener.UserInputRoaster.WALLNUT));
    }

    /**
     * Sets the listener for handling plant selection inputs.
     *
     * @param listener the ViewListener instance
     */
    public void setViewListener(final ViewListener listener) {
        this.listener = listener;
    }

    /**
     * Notifies the listener of the selected plant type.
     *
     * @param input the selected plant input
     */
    private void plantSelection(final ViewListener.UserInputRoaster input) {
        if (listener != null) {
            listener.processInputRoaster(input);
        }
    }

    /**
     * Updates the sun and kill counters in the UI.
     *
     * @param sunCount  the current sun (currency) amount
     * @param killCount the number of zombies killed
     */
    public void updateStats(final int sunCount, final int killCount) {
        sunCounterLabel.setText("☀ Sun: " + sunCount);
        killCounterLabel.setText("💀 Kills: " + killCount);
    }
}
