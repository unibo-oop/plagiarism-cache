package talisman.view.menu;

import java.awt.Component;
import java.awt.LayoutManager;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel that contains the main menu options.
 * 
 * @author Alberto Arduini
 *
 */
public class MainMenuOptionsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String START_TEXT = "Start game";
    private static final String EXIT_TEXT = "Quit game";
    private static final int OPTIONS_OFFSET = 10;

    /**
     * A listener that reacts when an option is selected in the main menu.
     * 
     * @author Alberto Arduini
     *
     */
    public interface OptionListener extends EventListener {
        void optionSelected();
    }

    private final JButton startButton;
    private final JButton quitButton;

    /**
     * Creates a new options panel.
     */
    public MainMenuOptionsPanel() {
        final LayoutManager layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        this.startButton = new JButton();
        this.startButton.setText(MainMenuOptionsPanel.START_TEXT);
        this.add(this.wrapInPanel(this.startButton, true));

        this.quitButton = new JButton();
        this.quitButton.setText(MainMenuOptionsPanel.EXIT_TEXT);
        this.add(this.wrapInPanel(this.quitButton, false));
    }

    /**
     * Adds a listener for the start option.
     * 
     * @param listener the listener
     */
    public void addStartListener(final OptionListener listener) {
        this.startButton.addActionListener(l -> listener.optionSelected());
    }

    /**
     * Adds a listener for the quit option.
     * 
     * @param listener the listener
     */
    public void addQuitListener(final OptionListener listener) {
        this.quitButton.addActionListener(l -> listener.optionSelected());
    }

    private JPanel wrapInPanel(final Component component, final boolean addOffset) {
        final JPanel panel = new JPanel();
        panel.add(component);
        if (addOffset) {
            panel.setBorder(BorderFactory.createEmptyBorder(0, 0, MainMenuOptionsPanel.OPTIONS_OFFSET, 0));
        }
        return panel;
    }
}
