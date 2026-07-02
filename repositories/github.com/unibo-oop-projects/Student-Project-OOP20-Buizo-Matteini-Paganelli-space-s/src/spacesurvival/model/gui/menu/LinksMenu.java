package spacesurvival.model.gui.menu;
import spacesurvival.utilities.LinkActionGUI;

/**
 * LinksMenu contains menu links with buttons.
 */
public enum LinksMenu {
    /**
     * Link to GUI game.
     */
    START_BUTTON("Start", LinkActionGUI.LINK_GAME),

    /**
     * Link to GUI settings.
     */
    SETTINGS_BUTTON("Settings", LinkActionGUI.LINK_SETTING),

    /**
     * Link to GUI sound.
     */
    SOUND_BUTTON("Sound", LinkActionGUI.LINK_SOUND),

    /**
     * Link to GUI help.
     */
    HELP_BUTTON("Help", LinkActionGUI.LINK_HELP),

    /**
     * Link to quit.
     */
    QUIT_BUTTON("Quit", LinkActionGUI.LINK_QUIT);

    /**
     * Button text.
     */
    private final String text;

    /**
     * Associated link of the button.
     */
    private final LinkActionGUI linkActionGUI;

    /**
     * Create link for menu GUI to a other GUI.
     * @param name other GUI.
     * @param linkActionGUI
     */
    LinksMenu(final String text, final LinkActionGUI linkActionGUI) {
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
     * Description of linkMenu. 
     */
    @Override
    public String toString() {
        return "LinksMenu{" 
                + "name='" + text + '\'' 
                + ", actionGUI=" + linkActionGUI + '}';
    }
}
