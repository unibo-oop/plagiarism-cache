package starcatraz.controller;

import starcatraz.application.StarcatrazApp;

/**
 * Abstract class to be extended by every starcatraz controller.
 * @author gianni
 *
 */
public abstract class StarcatrazController {

    /**
     *App of Starcatraz.
     */
    private StarcatrazApp app;

    /**
     * Get Starcatraz app.
     * @return Starcatraz app
     */
    public StarcatrazApp getStarcatrazApp() {
        return this.app;
    }

    /**
     * Set Starcatraz app.
     * @param app value to set the Starcatraz app
     */
    public void setStarcatrazApp(final StarcatrazApp app) {
        this.app = app;
    }
}
