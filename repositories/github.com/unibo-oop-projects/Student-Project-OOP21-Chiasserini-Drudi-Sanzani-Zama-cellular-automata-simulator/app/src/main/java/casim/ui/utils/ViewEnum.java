package casim.ui.utils;

import java.util.Locale;

/**
 * View types enumeration.
 */
public enum ViewEnum {
    /**
     * Automaton simulation view.
     */
    AUTOMATON_VIEW,
    /**
     * Automaton configuration view.
     */
    AUTOMATON_CONFIG_MENU;

    private static final String EXT = ".fxml"; 

    /**
     * Returns the resource name of the given view.
     * 
     * @return the resource name string.
     */
    public String getResourceName() {
        return "/" + this.name().toLowerCase(Locale.ENGLISH) + EXT;
    }
}
