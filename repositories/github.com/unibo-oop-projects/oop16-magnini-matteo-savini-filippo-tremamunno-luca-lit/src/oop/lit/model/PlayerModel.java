package oop.lit.model;

import java.io.Serializable;

/**
 * A player.
 */
public interface PlayerModel extends Serializable {

    /**
     * @return the name of the player.
     */
    String getName();

}