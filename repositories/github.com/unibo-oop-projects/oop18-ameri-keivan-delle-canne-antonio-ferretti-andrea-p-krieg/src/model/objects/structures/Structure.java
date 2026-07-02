package model.objects.structures;

import model.objects.GameObject;
import model.objects.terrains.Terrain;

/**
 * Stuffs that can be built on the terrain and can be used from everyone.
 */
public interface Structure extends GameObject {

    /**
     * @param terrain the terrain on which the structure has to be built
     * 
     * @return whether the structure can be built on the passed terrain
     */
    boolean canBeBuilt(Terrain terrain);
}
