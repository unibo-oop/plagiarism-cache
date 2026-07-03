package it.unibo.coinquify.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Langue messages.
 */
public final class Messages {
    private static final String BUNDLE_NAME = "it.unibo.coinquify.utils.MessagesBundle"; //$NON-NLS-1$

    private Locale currentLocale;
    private ResourceBundle resourceBoundle;
    private static final Messages SINGLETON = new Messages();

    private Messages() {
    }

    /**
     * 
     * @param key
     *            of resource
     * @return string in this key
     */
    public String getString(final String key) {
        try {
            return resourceBoundle.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    /**
     * 
     * @param l
     *            to set.
     */
    public void setCurrentLocale(final Locale l) {
        this.currentLocale = l;
        this.resourceBoundle = ResourceBundle.getBundle(BUNDLE_NAME, l);
    }

    /**
     * 
     * @return current locale
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * 
     * @return Singleton of messages
     */
    public static Messages getMessages() {
        return SINGLETON;
    }
}
