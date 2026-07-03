package model.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.entities.Enemy;
import model.entities.ItemImpl;
import model.hitbox.HitboxRectangle;
import model.utils.Direction;

/**
 * 
 * Defines the GameRoom which contains doors and enemies.
 */
public class GameRoom implements Serializable, Room {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = -5753259252379074133L;
    private List<Enemy> e;
    private final List<Direction> d;
    private final List<Integer> connections;
    private final Collection<ItemImpl> items;

    /**
     * Constructs a new instance of GameRoom.
     * 
     * @param d
     *            A list of doors.
     * @param c
     *            A list of connections.
     * @param e
     *            A list of enemies.
     */
    public GameRoom(final List<Direction> d, final List<Integer> c, final List<Enemy> e) {
        connections = c;
        this.d = d;
        this.e = e;
        items = new ArrayList<>();
    }

    /**
     * Construct an empty new instance of GameRoom.
     */
    public GameRoom() {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public void addAllItems(final Collection<ItemImpl> items) {
        this.items.addAll(items);
    }

    @Override
    public Collection<ItemImpl> getAllItems() {
        return this.items;
    }

    @Override
    public List<Enemy> getEnemies() {
        return this.e;
    }

    @Override
    public int getConnection(final Direction door) {
        return this.connections.get(d.indexOf(door));
    }

    @Override
    public void addConnection(final int i) {
        this.connections.add(i);
    }

    @Override
    public void addDoors(final Direction d) {
        if (!this.d.contains(d)) {
            this.d.add(d);
        }
    }

    @Override
    public void addEnemies(final List<Enemy> e) {
        this.e.addAll(e);
    }

    @Override
    public void addAllDoors(final List<Direction> d) {
        d.forEach(t -> {
            this.addDoors(t);
        });
    }

    @Override
    public List<Direction> getDoors() {
        return this.d;
    }

    @Override
    public List<HitboxRectangle> getWalls() {
        return StandardRoom.getWalls();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((connections == null) ? 0 : connections.hashCode());
        result = prime * result + ((d == null) ? 0 : d.hashCode());
        result = prime * result + ((e == null) ? 0 : e.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameRoom other = (GameRoom) obj;
        if (connections == null) {
            if (other.connections != null) {
                return false;
            }
        } else if (!connections.equals(other.connections)) {
            return false;
        }
        if (d == null) {
            if (other.d != null) {
                return false;
            }
        } else if (!d.equals(other.d)) {
            return false;
        }
        if (e == null) {
            if (other.e != null) {
                return false;
            }
        } else if (!e.equals(other.e)) {
            return false;
        }
        if (items == null) {
            if (other.items != null) {
                return false;
            }
        } else if (!items.equals(other.items)) {
            return false;
        }
        return true;
    }

}
