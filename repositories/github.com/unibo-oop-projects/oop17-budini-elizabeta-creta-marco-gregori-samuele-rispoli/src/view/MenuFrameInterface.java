package view;

public interface MenuFrameInterface {
    /**
     * disposes the menu frame when a new game is starting
     */
    public void dispose();

    /**
     * It displays the menu Frame
     */
    public void showMenu();

    /**
     * This method initializes the MenuFrame setting all the MenuCards
     */
    public void initialize();
}
