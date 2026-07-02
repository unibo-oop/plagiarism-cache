package it.unibo.aknightstale.views.map;

import it.unibo.aknightstale.models.entity.EntityType;

/**
 * A class that represents "physical" tiles, ie tiles that moving entities can collide with.
 * So this type of tile represents obstacles on the game map.
 * */
public class SolidTile extends AbstractTile {

    /**
     * @param url the image's url of the tile
     * @param index the unique index of the tile
     * @param entityType the tile's type
     * */
    public SolidTile(final String url, final int index, final EntityType entityType) {
        super(url, index, entityType);
        super.setCollidable(true);
    }

}
