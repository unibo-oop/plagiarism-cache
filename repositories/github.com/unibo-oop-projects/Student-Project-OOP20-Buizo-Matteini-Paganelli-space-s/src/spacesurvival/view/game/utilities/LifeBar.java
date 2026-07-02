package spacesurvival.view.game.utilities;

import javax.swing.JProgressBar;

import spacesurvival.utilities.gameobject.LifeUtils;
import spacesurvival.view.game.utilities.logiccolor.LogicColor;

/**
 * Implement a component view for life's spaceship.
 */
public class LifeBar extends JProgressBar {
    private static final long serialVersionUID = 3237184366293073643L;

    private final LogicColor logicColor;

    /**
     * Initialize and create all graphics components, and set color with a logic of color.
     * @param logicColor logic for color.
     */
    public LifeBar(final LogicColor logicColor) {
        super();
        super.setStringPainted(true);
        this.logicColor = logicColor;
    }

    /**
     * Set value of progress bar.
     * @param life
     */
    public void setLife(final int life) {
        super.setValue(life);
        super.setForeground(this.logicColor.getColor(LifeUtils.SPACESHIP_LIFE, life));
    } 

    /**
     * Described value life.
     */
    @Override
    public String getString() {
        return "Life " + super.getValue();
    }
}
