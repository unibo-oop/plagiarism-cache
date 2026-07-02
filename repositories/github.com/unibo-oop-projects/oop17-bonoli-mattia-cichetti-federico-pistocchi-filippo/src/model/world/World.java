package model.world;

import java.util.List;

import model.den.Den;
import model.lane.Lane;


/**
 * This interface represents the main game field.
 * It consists of lanes and dens.
 */
public interface World {

    /**
     * @return a list of lane.
     */
    List<Lane> getLane();

    /**
     * @return a list of Den.
     */
    List<Den> getDen();

}
