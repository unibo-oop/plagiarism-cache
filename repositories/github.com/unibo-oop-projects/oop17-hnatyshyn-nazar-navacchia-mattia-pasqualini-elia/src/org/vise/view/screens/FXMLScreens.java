package org.vise.view.screens;

/**
 * 
 * Enumerator of the .fxml resources for the view.
 *
 */
public enum FXMLScreens {

    /**
     * Player View.
     */
    PLAYER("/org/vise/view/screens/Player.fxml", "/org/vise/view/screens/Player.css"),
    /**
     * RLForm View.
     */
    RLFORM("/org/vise/view/screens/RLForm.fxml", "/org/vise/view/screens/RLForm.css");

    private final String resourcePath;
    private final String cssPath;

    /**
     * @param path
     *            full qualified path of the .fxml
     * @param cssPath
     *            full qualified path of the .css
     */
    FXMLScreens(final String path, final String styleSheetPath) {
        this.resourcePath = path;
        this.cssPath = styleSheetPath;
    }

    /**
     * @return full qualified path of the .fxml
     */
    public String getPath() {
        return resourcePath;
    }

    /**
     * @return full qualified path of the .css
     */
    public String getCssPath() {
        return cssPath;
    }
}
