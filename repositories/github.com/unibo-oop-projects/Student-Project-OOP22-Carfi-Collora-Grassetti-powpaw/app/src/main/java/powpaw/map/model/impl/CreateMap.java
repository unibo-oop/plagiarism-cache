package powpaw.map.model.impl;

import java.util.ArrayList;
import java.util.List;

import powpaw.map.model.Level;

/**
 * Class for the creation of the game map.
 * 
 * @author Giacomo Grassetti
 */

public class CreateMap {

    private final List<BlockImpl> terrains = new ArrayList<>();

    /**
     * Constructor of CreateMap.
     */
    public CreateMap() {
        createTerrains();
    }

    /**
     * The function creates terrains by iterating through a 2D matrix and adding
     * blocks to a list based
     * on the values in the matrix.
     */
    private void createTerrains() {
        for (int y = 0; y < Level.LEVEL2.length; y++) {
            final String row = Level.LEVEL2[y];
            for (int x = 0; x < row.length(); x++) {
                switch (row.charAt(x)) {
                    case '1':
                        final BlockImpl block = BlockFactory.createBlock(x, y);
                        terrains.add(block);
                        break;
                    default:
                        break;
                }
            }
        }

    }

    /**
     * Getter of terrains list.
     * 
     * @return An ArrayList of BlockImpl
     */
    public List<BlockImpl> getTerrains() {
        return terrains;
    }

}
