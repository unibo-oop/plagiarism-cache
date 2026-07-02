package javawulf.controller;

import java.util.logging.Logger;

import javawulf.model.map.Map;
import javawulf.view.MapDrawer;

/**
 * Utility class used for print Map informations.
 */
public final class LogInfo {

    private LogInfo() {
        throw new UnsupportedOperationException("This class cannot be instantiated (Utility class)");
    }

    /**
     * 
     * @param map of current game match
     */
    public static void print(final Map map) {
        if (map.getPlayerRoom().isPresent()) {
            // In case of PMD warnings, use logger instead println.
            final Logger log = Logger.getLogger(MapDrawer.class.getName());
            log.fine("GameObjects stanza corrente: " + map.getRoomElements(map.getPlayerRoom().get()));
            // System.out.println("GameObjects stanza corrente: " +
            // map.getRoomElements(map.getPlayerRoom().get()));
        }
    }
}
