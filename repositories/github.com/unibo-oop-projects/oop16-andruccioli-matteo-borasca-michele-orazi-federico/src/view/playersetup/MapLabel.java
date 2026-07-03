package view.playersetup;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javafx.scene.control.Label;
import utils.enumerations.MapType;

/**
 * 
 * Custom label that display in map chooser a map.
 * Each map is linked with his specified {@link MapType}.
 * <p>
 * @see MapType
 *
 */
public final class MapLabel extends Label {

    private final String mapPath;
    private final MapType mapType;

    private static final List<MapLabel> MAPLIST = new ArrayList<>();
    private static final MapLabel CLASSIC = new MapLabel(MapType.ClassicMap);

    private MapLabel(final MapType map) {
       this.setText(map.toString());
       this.mapPath = map.getMapInitFile();
       this.mapType = map;
       MAPLIST.add(this);
    }

    /**
     * 
     * Instance method to get map file path.
     * 
     * @return
     *          Map file path as a String object.
     * 
     */
    public  String getFilePath() {
        return mapPath;
    }

    /**
     * 
     * Instance method to get MapType object.
     * 
     * @return
     *          MapType linked to the object.
     * 
     */
    public  MapType getMapType() {
        return this.mapType;
    }

    /**
     * 
     * Static method that returns classic map.
     * 
     * @return
     *          Risk classic map.
     * 
     */
    public static MapLabel getClassicMap() {
        return MapLabel.CLASSIC;
    }

    /**
     * 
     * Returns a map from map list based on what MapType is passed as input.
     * 
     * @param mapType
     *                  MapType on which search for a Map object.
     * @return
     *                  The Map associated to mapType
     * 
     * @throws NoSuchElementException
     *                  Exception thrown if a Map cannot be found.
     * 
     */
    public static MapLabel getMapFromMapType(final MapType mapType) throws NoSuchElementException {
        return MapLabel.MAPLIST.stream().filter(m -> m.getMapType().equals(mapType)).findFirst().orElseThrow(NoSuchElementException::new);
    }
}
