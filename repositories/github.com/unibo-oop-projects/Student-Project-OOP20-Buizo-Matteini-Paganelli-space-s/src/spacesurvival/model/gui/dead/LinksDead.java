package spacesurvival.model.gui.dead;

import spacesurvival.utilities.LinkActionGUI;

/**
 * LinksMenu contains dead links with buttons.
 */
public enum LinksDead {
    /**
     * Link to GUI Menu.
     */
    MENU_BTN("Menu", LinkActionGUI.LINK_MENU),

    /**
     * Link to GUI Quit.
     */
    QUIT_BTN("Quit", LinkActionGUI.LINK_QUIT);

    private final String text;
    private final LinkActionGUI linkActionGUI;

    /**
     * Create link for menu GUI to a other GUI.
     * @param name other GUI.
     * @param linkActionGUI
     */
    LinksDead(final String text, final LinkActionGUI linkActionGUI) {
        this.text = text;
        this.linkActionGUI = linkActionGUI;
    }

    /**
     * Get button text.
     * @return button text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Get button text.
     * @return button text.
     */
    public LinkActionGUI getAction() {
        return this.linkActionGUI;
    }

    /**
     * Description of linkDead.
     */
    @Override
    public String toString() {
        return "LinksDead{"
                + "name='" + text + '\'' 
                + ", actionGUI=" + linkActionGUI +  '}';
    }
}
