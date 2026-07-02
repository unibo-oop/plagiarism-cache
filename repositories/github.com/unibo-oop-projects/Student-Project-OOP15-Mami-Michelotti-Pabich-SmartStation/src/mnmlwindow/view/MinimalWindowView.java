package mnmlwindow.view;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mnmlwindow.controller.MinimalWindowC;

/**
 * A complete interface with methods that a window must have to work.
 */
public interface MinimalWindowView {

    /** 
     * Set the controller for the view.
     * @param controller MinimalWindowCImpl object that will be the controller for the view 
     */
    void setControllerer(MinimalWindowC controller);
    
    /**
     * Set the logo for the window.
     * @param logo Image that will be used as logo
     */
    void setWindowLogo(Image logo);
    
    /** 
     * Get the logo of the window.
     * @return Image that contains the logo of the window
     */
    ImageView getWindowLogo();
    
    /** 
     * Set the title of the window.
     * @param title string that will be the title of the app
     */
    void setWindowTitle(String title);
    
    /** 
     * Get the title of the window.
     * @return A string that contains the title
     */
    String getWindowTitle();
    
    /** 
     * Set the footer of the window.
     * @param footer The string that contains the footer
     */
    void setWindowFooter(String footer);
    
    /** 
     * Get the logo of the window.
     * @return Image that contains the logo of the window
     */
    String getWindowFooter();
    
    /** 
     * Set the main content of the window that should be GridPane, StackPane, BorderPane...
     * @param node A GridPane, StackPane, BorderPane that will be the main content of the window
     */
    void setWindowContent(Node node);
    
    /** 
     * Get the main content of the window, returned as a Node.
     * @return main content of the window
     */
    Node getWindowContent();
    
    
    
    
    /** 
     * Tell if the window can be moved on the screen.
     * @return TRUE if you can move it; FALSE if you cannot
     */
    boolean isWindowMovable();
    
    /** 
     * Set the window to NOT MOVABLE.
     */
    void setNotMovableWindow();
    
    /** 
     * Set the window to MOVABLE.
     */
    void setMovableWindow();
    
    
    
    /** 
     * Get the actualX position of the window. This value is the updated value of the
     * X position of the window, when it's clicked, dragged and changing size.
     * @return actualX position of the window
     */
    double getWindowActualX();
    
    /** 
     * Set the actualX position of the window.
     * @param x = a double value to override the old value
     */
    void setWindowActualX(double x);
    
    /** 
     * Get the x position of the window.
     * @return x position of the window
     */
    double getWindowX();
    
    /** 
     * Set the x position of the window.
     * @param x = a double value to override the old value
     */
    void setWindowX(double x);
    
    
    
    /** 
     * Get the actualY position of the window. This value is the updated value of the
     * Y position of the window, when it's clicked, dragged and changing size.
     * @return actualY position of the window
     */
    double getWindowActualY();
    
    /** 
     * Set the actualY position of the window.
     * @param y = a double value to override the old value
     */
    void setWindowActualY(double y);
    
    /** 
     * Get the y position of the window.
     * @return y position of the window
     */
    double getWindowY();
    
    /** 
     * Set the y position of the window.
     * @param y a double value to override the old value
     */
    void setWindowY(double y);
    
    
    
    /** 
     * Get the distance between the begin of shadow and start of the main window.
     * @return a double value that represents the distance between the begin of shadow and start of the main window
     */
    int getWindowShadowSize();
    
    
    
    /** 
     * Set the status of the window to MINIMIZED.
     */
    void minimizeWindow();
    
    /** 
     * Set the status of the window to MAXIMIZED.
     */
    void maximizeWindow();
    
    /** 
     * Set the window to "NORMAL" size, taked before the MAX/MIN status.
     */
    void normalizeWindow();
    
    /** 
     * Tell if the window is in the MAXIMIZED status.
     * @return TRUE if it's MAXIMIZED; FALSE otherwise
     */
    boolean isWindowMaximized();
    
    
    
    
    /** 
     *      Set the width on the window.
     *      @param width = a double value that overrides the width of the window
     */
    void setWindowWidth(double width);
    
    /** 
     *      Get the minimum width on the window.
     *      @return a double value representing the minimum width on the window
     */
    double getWindowMinWidth();
    
    /** 
     *      Get the width on the window.
     *      @return a double value representing the width on the window
     */
    double getWindowWidth();
    
    
    
    /** 
     *      Set the height on the window.
     *      @param height = a double value that overrides the width of the window
     */
    void setWindowHeight(double height);
    
    /** 
     *      Get the minimum height on the window.
     *      @return a double value representing the minimum height on the window
     */
    double getWindowMinHeight();
    
    /** 
     *      Get the height on the window.
     *      @return a double value representing the height on the window
     */
    double getWindowHeight();
    
    
    
    /** 
     *      Set the mouse cursor visible on the screen.
     *      @param cursor = a Cursor value that represents the new cursor
     */
    void setMouseCursor(Cursor cursor);
    
    /** 
     *      Close the window (not the entire app!).
     */
    void closeWindow();
    
}
