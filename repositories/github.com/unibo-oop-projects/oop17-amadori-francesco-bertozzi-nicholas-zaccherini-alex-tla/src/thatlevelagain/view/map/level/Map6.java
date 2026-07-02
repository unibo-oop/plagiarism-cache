package thatlevelagain.view.map.level;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.map.MapImpl;
import thatlevelagain.view.state.GameStateManagerImpl;

/**
 * Level 6 Map.
 *
 */
public class Map6 extends MapImpl {

    private static final int ACTUAL_LEVEL = 6;

    /**
     * constructor.
     * @param manager
     *         actual manager.
     */
    public Map6(final GameStateManagerImpl manager) {
        super(ImageManager.getListLoader().get(ImagePath.BASE1.getPosition()), manager);
        this.setLevel(ACTUAL_LEVEL);
        this.setTrophy1(true);
        this.setTrophy2(true);
        this.setTrophy3(true);
        this.setTrophy4(true);
        this.setTrophy5(true);
        this.setTrophy6(false);
        this.setTrophy7(false);
        this.setTrophy8(false);
        this.setTrophy9(false);
        this.setTrophy10(false);
        this.setTrophy11(false);
        this.setTrophy12(false);
        this.setSkipLevel(true);
    }
    /**
     * set the next state.
     */
    public void nextLevel() { }

}

