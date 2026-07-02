package spacesurvival.view.game.utilities;

import spacesurvival.view.utilities.GraphicsLayoutUtils;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import spacesurvival.view.utilities.FactoryGUIs;

/**
 * Implement a component view for enemy counting.
 */
public class CounterEnemies extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JLabel stringEnemies;
    private final JLabel counter;

    /**
     * Initialize and create all graphics components.
     */
    public CounterEnemies() {
        super(new FlowLayout());
        super.setOpaque(false);

        this.stringEnemies = new JLabel(GraphicsLayoutUtils.ENEMIES_STRING);
        this.counter = new JLabel();

        super.add(FactoryGUIs.createPanelGridBagUnionComponentsVertical(java.util.List.of(
                this.stringEnemies, FactoryGUIs.encapsulatesInPanelFlow(this.counter)),
                FactoryGUIs.INSET_H3));
    }

    /**
     * Set the counter of enemies in HUD from int parameter.
     * 
     * @param count the int representing the number of current enemies
     */
    public void setCounter(final int count) {
        this.counter.setText(Integer.toString(count));
    }

    /**
     * Set the counter of enemies in HUD from string parameter.
     * 
     * @param count the string representing the number of current enemies
     */
    public void setCounter(final String count) {
        this.counter.setText(count);
    }

    /**
     * Set the font to all the HUD.
     * 
     * @param font to set
     */
    public void setFontAll(final Font font) {
        this.stringEnemies.setFont(font);
        this.counter.setFont(font);
    }

    /**
     * Set the foreground color of the text to all the HUD.
     * 
     * @param color to set
     */
    public void setForegroundAll(final Color color) {
        this.stringEnemies.setForeground(color);
        this.counter.setForeground(color);
    }
}
