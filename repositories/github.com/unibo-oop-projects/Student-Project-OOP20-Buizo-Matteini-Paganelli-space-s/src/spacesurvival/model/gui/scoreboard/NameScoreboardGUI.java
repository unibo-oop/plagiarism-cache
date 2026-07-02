package spacesurvival.model.gui.scoreboard;

/**
 * Enumeration for button of scoreboardGUI.
 */
public enum NameScoreboardGUI {
    /**
     * Button search.
     */
    SEARCH_BUTTON("Search"),

    /**
     * Button back.
     */
    BACK_BUTTON("Back");

    private final String name;

    /**
     * 
     * @param name
     */
    NameScoreboardGUI(final String name) {
        this.name = name;
    }

    /**
     * Get text button.
     * @return string of text.
     */
    public String getText() {
        return this.name;
    }
}
