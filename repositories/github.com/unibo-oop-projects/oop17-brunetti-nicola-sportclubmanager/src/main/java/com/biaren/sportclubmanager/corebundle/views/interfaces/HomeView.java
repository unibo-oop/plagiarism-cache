package com.biaren.sportclubmanager.corebundle.views.interfaces;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.HomeController;
import javafx.scene.layout.Pane;

/**
 * Home view interface, provides method to control the view
 * and change the current content and change only the "works area"
 * of the entire GUI.
 * @author nbrunetti
 *
 */
public interface HomeView {
    
    /**
     * Attach a controller to this panel to control it
     * @param controller {@link HomeController} to controls panel
     */
    void attachViewObserver(final HomeController controller);
    
    /**
     * Set current content to switch the works area dynamically
     * with user input preferences
     * @param content {@link Pane} to set on the works area content
     */
    void setCurrentContent(final Pane content);
}
