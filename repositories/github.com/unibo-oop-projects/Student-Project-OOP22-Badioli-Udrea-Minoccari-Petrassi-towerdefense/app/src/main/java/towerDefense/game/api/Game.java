package towerDefense.game.api;

public interface Game {

    /**
     * @return the panel which is currently showing on screen
     */
    public Panel getCurrentPanel();  

    /**
     * @return the class of the current panel
     */
    public Class<? extends Panel> getCurrentPanelClass();
}
