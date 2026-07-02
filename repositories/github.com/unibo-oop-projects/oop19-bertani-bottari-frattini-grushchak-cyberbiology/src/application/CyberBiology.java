package application;

import view.menu.language.LanguageMenuImpl;

/**
 * 
 * The main class of application.
 *
 */
final class CyberBiology {

    private CyberBiology() {
        //unused
    }

    /**
     * @param arg unused.
     */
    public static void main(final String... arg) {
         new LanguageMenuImpl().showLanguageMenu();
    }
}
