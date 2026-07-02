package mnmlwindow;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Interface with useful methods for a simple custom window implementation.
 * 
 * 
 */
public interface MinimalWindow {  
    
    /**
     * Set the title of the window.
     * @param title the title that will be visible
     */
    void setTitle(String title);
    
    /**
     * Get the title of the window.
     * @return the title of the window
     */
    String getTitle();
    
    /**
     * Set the logo of the window.
     * @param logo image that will be the logo
     */
    void setLogo(Image logo);
    
    /**
     * Get the logo of the window.
     * @return the logo of the window
     */
    ImageView getLogo();
    
    /**
     * Set the footer of the window.
     * @param footer string that will be the footer
     */
    void setFooter(String footer);
    
    /**
     * Get the footer of the window.
     * @return the footer of the window
     */
    String getFooter();
    
    /**
     * Set the content of the window, using a Node.
     * @param node the content of the window
     */
    void setContent(Node node);
    
    /**
     * Show the window. It's like use .show() on a stage.
     */
    void showWindow();
}
