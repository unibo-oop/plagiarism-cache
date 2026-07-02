package zombietsunami.model.mapmodel.impl;

import java.util.ArrayList;
import java.util.List;

import zombietsunami.model.mapmodel.api.TileElement;

/**
 * This class implements the TileElement interface
 * {@link zombietsunami.model.mapmodel.api.TileElement}.
 */
public final class TileElementImpl implements TileElement {

    private final List<String> element;

    private static final String DIRT = "dirt.png";
    private static final String SKY = "sky.png";
    private static final String STREET = "street.png";
    private static final String BULDING_LEFT = "buldingLeft.png";
    private static final String BULDING_NORTH_LEFT = "buldingNorthLeft.png";
    private static final String BULDING_NORTH = "buldingNorth.png";
    private static final String BULDING_NORTH_RIGHT = "buldingNorthRight.png";
    private static final String BULDING_RIGHT = "buldingRight.png";
    private static final String BULDING_DOOR = "buldingDoor.png";
    private static final String BULDING_WINDOW = "buldingWindow.png";
    private static final String FLAG = "flag.png";

    /**
     * Allows to create a new Array list and sets the List's elements.
     */
    public TileElementImpl() {
        this.element = new ArrayList<>();
        setTileElement();
    }

    /**
     * This method puts in a List the strings that represent the name of the tile's
     * file, and puts them into their respective index, which are the number values
     * that represent that tile into the map.
     */
    private void setTileElement() {
        int pos = 0;
        this.element.add(pos++, DIRT);
        this.element.add(pos++, STREET);
        this.element.add(pos++, SKY);
        this.element.add(pos++, BULDING_LEFT);
        this.element.add(pos++, BULDING_NORTH_LEFT);
        this.element.add(pos++, BULDING_NORTH);
        this.element.add(pos++, BULDING_NORTH_RIGHT);
        this.element.add(pos++, BULDING_RIGHT);
        this.element.add(pos++, BULDING_DOOR);
        this.element.add(pos++, BULDING_WINDOW);
        this.element.add(pos, FLAG);
    }

    @Override
    public List<String> getTileElement() {
        return new ArrayList<>(this.element);
    }

}
