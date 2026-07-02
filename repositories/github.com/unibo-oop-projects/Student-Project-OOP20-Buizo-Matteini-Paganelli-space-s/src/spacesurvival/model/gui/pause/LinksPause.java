package spacesurvival.model.gui.pause;

import spacesurvival.utilities.LinkActionGUI;

/**
 * LinksPause contains pause links with buttons.
 */
public enum LinksPause {
    /**
     * Link to GUI game.
     */
    RESUME_BUTTON("Resume", LinkActionGUI.LINK_BACK),

    /**
     * Link to GUI sound.
     */
    SOUND_BUTTON("Sound", LinkActionGUI.LINK_SOUND),

    /**
     * Link to GUI help.
     */
    HELP_BUTTON("Help", LinkActionGUI.LINK_HELP),

    /**
     * Link to GUI quit.
     */
    QUIT_BUTTON("Quit", LinkActionGUI.LINK_QUIT);

    private final String text;
    private final LinkActionGUI linkActionGUI;

    /**
     * Create link for menu GUI to a other GUI.
     * @param name other GUI.
     * @param linkActionGUI
     */
    LinksPause(final String text, final LinkActionGUI linkActionGUI) {
        this.text = text;
        this.linkActionGUI = linkActionGUI;
    }

    public String getText() {
        return this.text;
    }

    public LinkActionGUI getIdGUI() {
        return this.linkActionGUI;
    }
}
