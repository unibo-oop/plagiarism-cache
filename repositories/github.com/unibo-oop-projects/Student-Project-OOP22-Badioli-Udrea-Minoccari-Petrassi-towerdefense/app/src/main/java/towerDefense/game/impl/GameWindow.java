package towerDefense.game.impl;

import javax.swing.JFrame;

import towerDefense.Constants;
import towerDefense.game.api.Panel;

public class GameWindow {

    private JFrame jFrame;

    /**
     * Creates a new game window and sets up the frames
     * 
     * @param gamePanel
     *          current panel to be shown
     */
    public GameWindow(Panel gamePanel) {
        
        jFrame = new JFrame("Tower Defense");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize((int)Constants.width, (int)Constants.height);
        jFrame.add(gamePanel);
        jFrame.setVisible(true);
    }   

    /**
     * Changes the window to another panel
     * 
     * @param gamePanel
     *          panel that is going to be shown
     */
    public void changeWindow(Panel gamePanel) {
        jFrame.dispose();
        jFrame.setVisible(false);
        jFrame = new JFrame("Tower Defense");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize((int)Constants.width, (int)Constants.height);
        jFrame.add(gamePanel);
        jFrame.setVisible(true);
    }
}
