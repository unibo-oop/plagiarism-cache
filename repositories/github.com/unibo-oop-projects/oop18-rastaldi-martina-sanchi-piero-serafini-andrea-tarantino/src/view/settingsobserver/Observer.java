package view.settingsobserver;

/**
 * Chiara Tarantino.
 * Observer class for options in settings menu.
 *
 */
public interface Observer {
    /**
     * Informs controller about changes made by user in user interface.
     */
    void updateSettings();
}
