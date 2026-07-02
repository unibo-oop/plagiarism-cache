package giocoscudetto.view.impl.initialize;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JPanel;

import giocoscudetto.view.api.initialize.DefaultPanel;

import javax.swing.JButton;

/**
 * This class represents the default panel of the game, 
 * it contains the default fonts for the title, the buttons and the exit button.
 */
public class DefaultPanelImpl extends JPanel implements DefaultPanel {

    /**
     * Font used for the title, buttons and exit button of the game.
     */
    protected static final String FONT_SELECTED = Font.MONOSPACED;

    /**
     * Constants used for the resizing of the fonts.
     */
    protected static final int TITLE_FONT_RESIZING = 15;

    /**
     * Constants used for the resizing of the fonts.
     */
    protected static final int SWITCHER_BUTTON_FONT_RESIZING = 40;
    private static final long serialVersionUID = 1L;
    private static final int TITLE_FONT_REDUCTION = 15;
    private static final int BUTTON_FONT_REDUCTION = 25;
    private static final int SWITCHER_BUTTON_FONT_REDUCTION = 40;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int minimumWidht = screenSize.width / 2;

    //Creating different font for each component.
    private final Font titleFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / TITLE_FONT_REDUCTION);
    private final Font buttonFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / BUTTON_FONT_REDUCTION);
    private final Font exitFont = new Font(FONT_SELECTED, Font.BOLD, minimumWidht / SWITCHER_BUTTON_FONT_REDUCTION);

    /**
     * {@InheritDoc}.
     */
    @Override
    public final Font getTitleFont() {
        return this.titleFont;
    }

    /**
     * {@InheritDoc}.
     */
    @Override
    public final Font getButtonFont() {
        return this.buttonFont;
    }

    /**
     * {@InheritDoc}.
     */
    @Override
    public final Font getExitFont() {
        return this.exitFont;
    }

    /**
     * This method creates a component with the specified font, text color and background color.
     * 
     * @param component the component to create.
     * @param font the font to set for the component.
     * @param textColor the text color to set for the component.
     * @param backgroundColor the background color to set for the component.
     * @return the created component.
     */
    protected final JComponent createComponent(final JComponent component, final Font font,
                                                final Color textColor, final Color backgroundColor) {
        component.setFont(font);
        component.setForeground(textColor);
        component.setBackground(backgroundColor);

        if (component instanceof JButton) {
            ((JButton) component).setFocusPainted(false);
        }
        return component;
    }
}
