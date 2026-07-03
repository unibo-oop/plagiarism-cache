package com.jlearn.view.screens;

import javax.swing.text.html.CSS;

import javafx.fxml.FXML;

/**
 * Enumerator of the .fxml resources for the view.
 *
 */
public enum FXMLScreens {

    /**
     * Menu {@link FXMLScreens} and {@link CSS}.
     */
    MENU("/screens/Menu.fxml", "/sheets/MenuSheets.css"),

    /**
     * Theory {@link FXMLScreens}.
     */
    THEORY("/screens/Theory.fxml", "/sheets/TheorySheets.css"),

    /**
     * Exercise {@link FXMLScreens} and {@link CSS}.
     */
    EXERCISE("/screens/Exercise.fxml", "/sheets/ExerciseSheets.css"),

    /**
     * Statistics {@link FXMLScreens} adn {@link CSS}.
     */
    STATISTICS("/screens/Statistics.fxml", "/sheets/StatisticsSheets.css"),

    /**
     * Drawer {@link FXMLScreens} and {@link CSS}.
     */
    DRAWER("/screens/MenuDrawer.fxml", "/sheets/DrawerSheets.css"),

    /**
     * Person {@link FXML} and {@link CSS}.
     */
    PERSON("/screens/Person.fxml", ""),

    /**
     * Dialog style {@link CSS}.
     */
    DIALOG("", "/sheets/DialogSheets.css"),
    /**
     * Radial style {@link CSS}.
     */
    RADIAL_MENU("", "/sheets/RadialMenuStyle.css"),
    /**
     * Drawer Style {@link CSS}.
     */
    DRAWER_EXERCISE("", "/sheets/DrawerExerciseSheets.css"),
    /**
     * Exercise Tabs Style {@link CSS}.
     */
    EXERCISE_TAB("", "/sheets/ExerciseTabSheets.css");

    private final String resourcePath;
    private final String cssPath;

    FXMLScreens(final String path, final String styleSheetPath) {
        this.resourcePath = path;
        this.cssPath = styleSheetPath;
    }

    /**
     * Get the path of the {@link FXML}.
     *
     * @return full qualified path of the {@link FXML}
     */
    public String getPath() {
        return this.resourcePath;
    }

    /**
     * Get the path of the {@link CSS}.
     *
     * @return full qualified path of the {@link CSS}
     */
    public String getCssPath() {
        return this.cssPath;
    }
}
