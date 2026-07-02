package it.unibo.monopoli.controller;

/**
 * This is an Enumeration for the version of the game.
 */
public enum EVersion {
    /**
     * Type of version game.
     */
    NOT_SELECTABLE_OPTION(" - Select an Option - "), CLASSIC("Classic"), ITALIAN_VERSION("Italian Version");

    private String name; 

    EVersion(final String name) {
        this.name = name;

    }
    /**
     * This is a getter of text of Enumeration.
     * 
     * @return -the string of text.
     */
    public String getName() {
        return this.name;
    }
}
