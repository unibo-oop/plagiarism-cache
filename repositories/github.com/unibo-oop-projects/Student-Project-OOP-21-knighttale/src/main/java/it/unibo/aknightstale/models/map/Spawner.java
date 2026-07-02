package it.unibo.aknightstale.models.map;

import javafx.util.Pair;

import java.util.Map;

public interface Spawner {

    /**
     *
     * @return updated map
     */
    Map<Pair<Integer, Integer>, Integer> getMap();

}
