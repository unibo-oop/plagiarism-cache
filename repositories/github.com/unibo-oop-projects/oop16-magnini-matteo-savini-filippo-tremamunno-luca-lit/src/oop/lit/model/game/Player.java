package oop.lit.model.game;

import java.io.Serializable;
import java.util.Objects;

import oop.lit.model.PlayerModel;

/**
 * A player implementation.
 */
public class Player implements Serializable, PlayerModel {
    /**
     * 
     */
    private static final long serialVersionUID = 5929879534705874146L;
    private final String name;
    /**
     * @param name
     *      the name of this player. Can't be null
     */
    public Player(final String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }
    /**
     * @return the name of the player.
     */
    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Player)) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
