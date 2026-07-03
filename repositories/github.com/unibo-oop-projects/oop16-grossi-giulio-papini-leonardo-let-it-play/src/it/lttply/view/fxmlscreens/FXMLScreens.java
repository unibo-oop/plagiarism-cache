package it.lttply.view.fxmlscreens;

/**
 * Enumerator of the .fxml resources for the view.
 *
 */
public enum FXMLScreens {

    /**
     * Screen of the Home.
     */
    HOME("/it/lttply/view/fxmlscreens/home.fxml", ""),

    /**
     * Screen of the Movies.
     */
    MOVIES("/it/lttply/view/fxmlscreens/movies.fxml", ""),
    /**
     * Screen of the TvSeries.
     */
    TVSERIES("/it/lttply/view/fxmlscreens/tvseries.fxml", "");

    private final String resourcePath;
    private final String cssPath;

    /**
     * @param path
     *            full qualified path of the .fxml
     * @param cssPath
     *            full qualified path of the .css
     */
    FXMLScreens(final String path, final String styleSheetPath) {
        resourcePath = path;
        cssPath = styleSheetPath;
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
