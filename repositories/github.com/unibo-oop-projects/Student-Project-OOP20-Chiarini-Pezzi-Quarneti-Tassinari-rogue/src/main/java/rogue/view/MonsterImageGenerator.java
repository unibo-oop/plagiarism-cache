package rogue.view;

import javafx.scene.image.Image;
import rogue.model.creature.Monster;

public interface MonsterImageGenerator {

    /**
     * @param monster
     *            the image of the monster 
     * @return the image of the requested item.
     */
    Image getImage(Monster monster);

}
