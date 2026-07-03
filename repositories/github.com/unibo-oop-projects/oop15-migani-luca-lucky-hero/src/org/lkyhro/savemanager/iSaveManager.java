package org.lkyhro.savemanager;

import org.lkyhro.Hero;

//import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Migani Luca on 18/02/2016.
 */
public interface ISaveManager {
    /**
     * Method used to save the progress of the hero in a file
     * @param hero Hero used by the player
     * @throws IOException if there's some problem with writing the saving file
     */
    void saveGame(Hero hero) throws IOException;

    /**
     * Method used to load hero's progress from a previous game
     * @return Hero used by the player, with his characteristic loaded from a previous game
     * @throws IOException if there's some problem with reading the saving file
     */
    Hero loadGame() throws IOException;
}
