package mnmlwindow.controller;


import javafx.scene.input.MouseEvent;
import mnmlwindow.view.MinimalWindowView;

/**
 * Interface that has all methods needed for a window.
 */
public interface MinimalWindowC {
    
    /**
     * Set the view for the controller.
     * @param v View that will be controlled
     */
    void setView(MinimalWindowView v);
    
    
    
    /**
     * Perform the operations to minimize the window.
     */
    void minimize();
    
    /**
     * Perform the operations to maximize or normalize the window.
     */
    void maximize();
    
    /**
     * Perform the operations to close the window.
     */
    void close();
    
     

    /**
     * Setting the cursor to default value.
     * @param e MouseEvent given from the view
     */
    void setCursorToDefault(MouseEvent e);
    
    /**
     * Setting the cursor to resize value.
     */
    void setCursorToResize();
    
    
    /**
     * Update values needed to window management.
     * @param e MouseEvent given from the view
     */
    void updateValues(MouseEvent e);
    
     
    
    /**
     * Perform the resizing of the window.
     * @param e MouseEvent given from the view
     */
    void resizeWindow(MouseEvent e);
                        
    /**
     * Perform the dragging of the window.
     * @param e MouseEvent given from the view
     */
    void dragWindow(MouseEvent e);
   
    /**
     * Perform the operations when window is released.
     */
    void dragWindowOver();
}
