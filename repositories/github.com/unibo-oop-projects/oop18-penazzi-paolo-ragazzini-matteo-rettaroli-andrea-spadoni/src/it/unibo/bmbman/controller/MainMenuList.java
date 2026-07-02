package it.unibo.bmbman.controller;

/**
 * Main menu option, when the application start.
 * 
 */
public enum MainMenuList {
    /**
     * Classic game mode with one player VS CPU.
    */
    SINGLE_PLAYER("Start Game"),
    /**
     * Score board based on points to complete each level.
     */
    LEADERBOARD("Leaderboard"),
    /**
     * Settings option menu.
     */
    SETTINGS("Settings"),
    /**
     * Help.
     */
    HELP("Help");
    private final String name;

    MainMenuList(final String name) {
        this.name = name;
    }
    /**
     * To string method.
     * @return name associate to each MainMenuOption
     */
    public String toString() {
        return name;
    }
}
