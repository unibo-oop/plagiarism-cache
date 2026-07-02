package javagotchi.view.home;
/**
 * Class useful to select the area to show (it's part of the strategy pattern).
 * @author elisa
 *
 */
public class GameArea {

    private final GameView area;

    /**
     * GameArea Constructor. It selects the area to show.
     * @param area
     *             the area to be shown. 
     */
    public GameArea(final GameView area) {
        this.area = area;
    }

    /**
     * Method to launch the view of the selected area. 
     */
    public void showArea() {
        this.area.show();
    }


}
