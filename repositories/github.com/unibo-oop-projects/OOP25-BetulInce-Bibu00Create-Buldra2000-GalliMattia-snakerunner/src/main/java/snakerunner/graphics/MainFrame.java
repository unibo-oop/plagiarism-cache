package snakerunner.graphics;

import java.awt.event.KeyListener;

import snakerunner.graphics.panel.BasePanel;

/**
 * MainFrame Interface for the MainView.
 */
public interface MainFrame {
    //test

    /**
     * Adds the specified key listener to receive key events from this component.
     * 
     * @param l the key listener to be added.
     */
    void addKeyListener(KeyListener l);

    /**
     * Show Frame.
     */
    void display();

    /**
     * Show MenuPanel.
     */
    void showMenu();

    /**
     * Show GamePanel.
     */
    void showGame();

    /**
     * Show Tutorial.
     */
    void showTutorial();

    /**
     * Set Panel.
     * 
     * @param menuPanel Set MenuPanel.
     * @param gamePanel Set GamePanel.
     * @param optionPanel Set OptionPanel.
     * @param tutorialPanel Set TutorialPanel.
     */
    void setPanels(BasePanel menuPanel, BasePanel gamePanel, BasePanel optionPanel, BasePanel tutorialPanel);

    /**
     * Show OptionPanel.
     */
    void showOption();

    /**
     * Show JDialog "You won!".
     */
    void won();

    /**
     * Show JDialog "You lose!".
     */
    void lose();

    /**
     * Show JDialog when level ends.
     */
    void levelComplete();

    /**
     * Update.
     */
    void refresh();
}
