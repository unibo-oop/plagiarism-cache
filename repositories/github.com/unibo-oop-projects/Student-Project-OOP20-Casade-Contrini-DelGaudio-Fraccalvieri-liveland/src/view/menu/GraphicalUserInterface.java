package view.menu;

public interface GraphicalUserInterface {

    /**
     * It makes the main frame visible. 
     */
    void display();

    /**
     * It closes the frame.
     */
    void dispose();

    /**
     * @return the {@link WelcomePanel}
     */
    WelcomePanel getWelcomePanel();

    /**
     * @return the {@link MenuPanel}
     */
    MenuPanel getMenuPanel();

    /**
     * @return the {@link BottomPanel}
     */
    BottomPanel getBottomPanel();

}
