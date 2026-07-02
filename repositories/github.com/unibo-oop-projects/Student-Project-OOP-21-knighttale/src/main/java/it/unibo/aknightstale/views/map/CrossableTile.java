package it.unibo.aknightstale.views.map;


import it.unibo.aknightstale.models.entity.EntityType;

/**
 * A class representing the tiles that can be stepped on by moving entities.
 * */
public class CrossableTile extends AbstractTile {

    /**
     * @param url the image's url of the tile
     * @param index the unique index of the tile
     * @param entityType the tile's type
     * */
    public CrossableTile(final String url, final int index, final EntityType entityType) {
        super(url, index, entityType);
        super.setCollidable(false);
    }
}
