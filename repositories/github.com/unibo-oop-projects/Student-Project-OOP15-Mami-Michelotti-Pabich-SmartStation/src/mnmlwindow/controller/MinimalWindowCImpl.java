package mnmlwindow.controller;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import mnmlwindow.view.MinimalWindowView;

/**
 * Class that implements all the controller methods of the minimal window.
 */
public class MinimalWindowCImpl implements MinimalWindowC {
    
    private MinimalWindowView view;
    
    /**
     * Constructor for the controller of minimal window.
     */ 
    public MinimalWindowCImpl() {
    }
    
    //Controller
    @Override
    public void setView(final MinimalWindowView v) {
        this.view = v;
    }
    
    
    
    //Min/max/close
    @Override
    public void minimize() {
        this.view.minimizeWindow();
    }

    @Override
    public void maximize() {
        if (this.view.isWindowMaximized()) {
            this.view.normalizeWindow();
            this.view.setMovableWindow();
        } else {
            this.view.maximizeWindow();
            this.view.setNotMovableWindow();
        }
    }

    @Override
    public void close() {
        this.view.closeWindow();
        System.exit(0);
    }

    
    
    //Resize
    @Override
    public void updateValues(final MouseEvent e) {
        updateXY(e);
    }

    @Override
    public void setCursorToDefault(final MouseEvent e) {
        if (!e.isPrimaryButtonDown()) {
            this.view.setMouseCursor(Cursor.DEFAULT);
        }
    }

    @Override
    public void setCursorToResize() {
        this.view.setMouseCursor(Cursor.NW_RESIZE);
    }

    @Override
    public void resizeWindow(final MouseEvent e) {
        updateXY(e);
            
        if (this.view.getWindowActualX() > this.view.getWindowMinWidth()) {
            this.view.setWindowWidth(this.view.getWindowActualX());
        } else {
            this.view.setWindowWidth(this.view.getWindowMinWidth());
        }
        
        if (this.view.getWindowActualY() > this.view.getWindowMinHeight()) {
            this.view.setWindowHeight(this.view.getWindowActualY());
        } else {
            this.view.setWindowHeight(this.view.getWindowMinHeight());
        }
    }

    

    @Override
    public void dragWindow(final MouseEvent e) {
        if (this.view.isWindowMovable()) {
            this.view.setWindowX(e.getScreenX() - this.view.getWindowActualX());
            this.view.setWindowY(e.getScreenY() - this.view.getWindowActualY());
        }
    }

    @Override
    public void dragWindowOver() {
        if (!this.view.isWindowMaximized()) {
            this.view.setMovableWindow();
        }
    }  
    
    private void updateXY(final MouseEvent e) {      
        this.view.setWindowActualX(e.getSceneX());
        this.view.setWindowActualY(e.getSceneY());
    }
}
