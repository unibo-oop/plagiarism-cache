package view.HUD;

import java.awt.Graphics;

import controllers.camera.Camera;

public interface HUDInterface {

    /**
     * @param g
     * @param camera
     * 
     * method to show HUD
     */
    void displayHUD(Graphics g, Camera camera);

}
