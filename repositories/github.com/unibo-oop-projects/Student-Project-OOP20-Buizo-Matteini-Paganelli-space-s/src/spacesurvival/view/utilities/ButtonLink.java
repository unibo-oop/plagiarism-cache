package spacesurvival.view.utilities;

import spacesurvival.utilities.LinkActionGUI;

import javax.swing.JButton;

/**
 * Implements a button which saves a linkAction the previous GUI with the next one.
 */
public class ButtonLink extends JButton {
    private static final long serialVersionUID = -9089585597865020287L;
    private LinkActionGUI actionCurrent;
    private LinkActionGUI actionNext;

    /**
     * Get the link of the button it belongs to.
     * @return LinkActionGUI current link.
     */
    public LinkActionGUI getCurrentLink() {
        return this.actionCurrent;
    }

    /**
     * Get the link of the button for the next link.
     * @return LinkActionGUI next link.
     */
    public LinkActionGUI getNextLink() {
        return this.actionNext;
    }

    /**
     * Set the link of the button it belongs to.
     * @param currentActionGUI current link.
     */
    public void setCurrentLink(final LinkActionGUI currentActionGUI) {
        this.actionCurrent = currentActionGUI;
    }

    /**
     * Set the link of the button it belongs to.
     * @param nextActionGUI next link.
     */
    public void setNextLink(final LinkActionGUI nextActionGUI) {
        this.actionNext = nextActionGUI;
    }

    /**
     * Describes the links of the button link.
     */
    @Override
    public String toString() {
        return "ButtonLink [actionCurrent=" + actionCurrent + ", actionNext=" + actionNext + "]";
    }



}
