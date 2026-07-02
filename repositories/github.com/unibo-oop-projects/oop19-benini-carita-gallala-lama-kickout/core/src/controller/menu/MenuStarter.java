package controller.menu;

import com.badlogic.gdx.Game;
import model.menu.Assets;
import view.menu.MapGUI;
import view.menu.MenuScreen;
import view.menu.PlayerGUI;
/**
 * Starts the menu instantiating the elements that will be used, as the main game, the GUI of the player etc.
 */
public class MenuStarter {

    private final Game mainScreen;
    private PlayerGUI player1;
    @SuppressWarnings("unused")
	private ImageSelector managerPl1;
    private Assets playersAssets;
    private PlayerGUI player2;
    @SuppressWarnings("unused")
	private ImageSelector managerPl2;
    private MapGUI map;
    @SuppressWarnings("unused")
	private ImageSelector managermap;
    private Assets mapassets;

    /**
     * Links all the given arguments and starts the menu screen
     * @param mainScreen
     * 			The game for which the screen will be changed
     */
    public MenuStarter(final Game mainScreen) {
        this.mainScreen = mainScreen;
        this.playersAssets = new Assets("PlayerImage");

        this.player1 = new PlayerGUI();
        this.managerPl1 = new ImageSelector(this.player1, this.playersAssets);

        this.player2 = new PlayerGUI();
        this.managerPl2 = new ImageSelector(this.player2, this.playersAssets);

        this.mapassets = new Assets("Map_images");
        this.map = new MapGUI();
        this.managermap = new ImageSelector(this.map, this.mapassets);

        this.mainScreen.setScreen(new MenuScreen(this.mainScreen, this.player1, this.player2, this.map));
    }
}
