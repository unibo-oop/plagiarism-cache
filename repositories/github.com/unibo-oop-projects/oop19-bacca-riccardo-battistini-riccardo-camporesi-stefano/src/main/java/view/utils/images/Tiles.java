package view.utils.images;

import org.apache.commons.lang3.tuple.Pair;

import javafx.scene.paint.Color;

/**
 * 
 */
public enum Tiles {

    /**
     * 
     */
    ROAD(Pair.of(Color.rgb(64, 64, 64), false)),

    /**
     * 
     */
    VEHICLE(Pair.of(Color.ORANGE, true)),

    /**
     * 
     */
    FLASHING_VEHICLE(Pair.of(Color.PURPLE, true)),

    /**
     * 
     */
    RED_TRAFFIC_LIGHT(Pair.of(Color.RED, true)),

    /**
     * 
     */
    YELLOW_TRAFFIC_LIGHT(Pair.of(Color.YELLOW, true)),

    /**
     * 
     */
    GREEN_TRAFFIC_LIGHT(Pair.of(Color.GREEN, true));

    private Pair<Color, Boolean> info;

    Tiles(final Pair<Color, Boolean> info) {
        this.info = info;
    }

    public Boolean isPolished() {
        return this.info.getRight();
    }

    public Color getColor() {
        return this.info.getLeft();
    }

}
