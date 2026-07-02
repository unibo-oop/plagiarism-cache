package controller.menu;

import javafx.scene.Scene;

/**
 * Generic controller. It must control a view
 */
public interface Controller {

    /**
     * Send a MsgEvent to the controller. This message are used to implements
     * communication between view and controller
     */
    void sendMsg(String msg);

    /**
     * @return scene from the view
     */
    Scene getScene();
}