package tmw.model.world;

import javafx.scene.shape.Rectangle;

/**
 * This class represents the first Level of the game. Level is made of multiple GameRooms
 * that basically are classes which implements {@link WorldDispenser}. 
 * 
 * @version 1.1
 *
 */
public class Level extends AbstractWorld {

    /**
     * Public constructor.
     * 
     * @param area {@link Rectangle} represents dimension of world ( game world area )
     */
    public Level(final Rectangle area) {
        super(area);
    }

}

