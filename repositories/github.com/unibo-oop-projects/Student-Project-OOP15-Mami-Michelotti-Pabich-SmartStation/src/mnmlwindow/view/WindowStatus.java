package mnmlwindow.view;

import javafx.geometry.Insets;

/**
 * Enum that has some features to manage the maximize-normal-minimize status of the window.
 */
public enum WindowStatus {
    /**
     * When the window is in "normal" mode.
     */
    NORMAL(false, true, "-fx-background-image: url('/mnmlwindow/view/resources/square.png');", new Insets(5, 5, 5, 5)),
    
    /**
     * When the window is in "maximized" mode.
     */
    MAXIMIZED(true, false, "-fx-background-image: url('/mnmlwindow/view/resources/dSquare.png');", new Insets(0, 0, 0, 0));
	
    
    private final boolean windowMaximizedStatus;
    private final boolean resizeButtonVisibility;
    private final String maximizeLogoPath;
    private final Insets shadowSpace;
	
    /**
     * Internal costructor.
     * @param wms window maximized status
     * @param rbv resize button visibility
     * @param mlp maximize logo path
     * @param sp shadow space
     */
    WindowStatus(final boolean wms, final boolean rbv, final String mlp, final Insets sp) {
        this.windowMaximizedStatus = wms;
        this.resizeButtonVisibility = rbv;
        this.maximizeLogoPath = mlp;
        this.shadowSpace = sp;
    }
	
    /**
     * Get the status of the window.
     * @return TRUE if it's maximized, FALSE otherwise
     */
    public boolean getWindowMaximizedStatus() {
        return this.windowMaximizedStatus;
    }
	
    /**
     * Get the resize button visibility.
     * @return TRUE if it must be visible, FALSE otherwise
     */
    public boolean getResizeButtonVisibility() {
        return this.resizeButtonVisibility;
    }
	
    /**
     * Get the path for the maximize image.
     * @return a path to the right resize image
     */
    public String getMaximizeLogoPath() {
        return this.maximizeLogoPath;
    }
	
    /**
     * Get the shadow space.
     * @return shadow space (shadow-window distance)
     */
    public Insets getShadowSpace() {
        return this.shadowSpace;
    }
	
}
