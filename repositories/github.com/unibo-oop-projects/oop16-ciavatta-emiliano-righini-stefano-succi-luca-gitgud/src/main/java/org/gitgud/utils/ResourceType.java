package org.gitgud.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * All possible bundles included in the application.
 */
public enum ResourceType {
    /**
     *
     */
    ERRORS("org.gitgud.bundles.Errors"), LABELS("org.gitgud.bundles.Labels"), MESSAGES("org.gitgud.bundles.Messages"), SETTINGS("org.gitgud.bundles.Settings");

    private final String path;

    ResourceType(final String path) {
        this.path = path;
    }

    /**
     * @param locale
     *            the locale of the bundle to obtain
     * @return the ResourceBundle associated
     */
    public ResourceBundle getBundle(final Locale locale) {
        return ResourceBundle.getBundle(path, locale);
    }

}
