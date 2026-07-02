package it.unibo.oop.manpac.view.maze;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.view.maze.entities.Pacman;
import it.unibo.oop.manpac.view.maze.entities.PhantomImpl;

/**
 * Implementation of MazeCreator interface.
 */
public class MazeCreatorImpl implements MazeCreator {
    /**
     * Static variable for layers of the TiedMap.
     */
    public static final int MAPS_LAYER = 10;
    /**
     * Static variable for layer 0, the background layer.
     */
    public static final int BACKGROUND_LAYER = 0;
    /**
     * Static variable for layer 1, the maze tiles layer.
     */
    public static final int MAZE_TILES_LAYER = 1;
    /**
     * Static variable for layer 2, the wall object layer.
     */
    public static final int WALL_LAYER = 2;
    /**
     * Static variable for layer 3, the pills tiles layer.
     */
    public static final int PILLS_TILES_LAYER = 3;
    /**
     * Static variable for layer 4, the pills object layer.
     */
    public static final int PILLS_LAYER = 4;
    /**
     * Static variable for layer 5, the power pills tiles layer.
     */
    public static final int POWER_PILLS_TILES_LAYER = 5;
    /**
     * Static variable for layer 6, the power pills object layer.
     */
    public static final int POWER_PILLS_LAYER = 6;
    /**
     * Static variable for layer 7, the portal layer.
     */
    public static final int PORTAL_LAYER = 7;
    /**
     * Static variable for layer 8, the gate layer.
     */
    public static final int GATE_LAYER = 8;
    /**
     * Static variable for layer 9, the pacman spawn point layer.
     */
    public static final int PACMAN_SPAWN_LAYER = 9;
    /**
     * Static variable for layer 10, the phantoms spawn point layer.
     */
    public static final int PHANTOMS_SPAWN_LAYER = 10;
    /**
     * Static variable for the pixel size of a single tile.
     */
    public static final int TILES_SIZE_IN_PIXELS = 8;

    private final Pacman pacman;
    private Vector2 pacmanSpawn;
    private final List<PhantomImpl> phantoms;
    private final List<Vector2> phantomsSpawn = new ArrayList<>();
    private final int totalPills;
    private float leftPortalX;
    private float rightPortalX;

    /**
     * Constructor for MazeCreatorImpl class.
     * 
     * @param mundus  The world to which the maze is created in
     * @param tileMap The map from which the maze is created
     */
    public MazeCreatorImpl(final World mundus, final TiledMap tileMap) {
        boolean isLeftPortalAssigned = false;
        final World world = mundus;
        final TiledMap map = tileMap;
        int pills = 0;
        for (int i = 2; i <= MAPS_LAYER; i++) {
            if (i != PILLS_TILES_LAYER && i != POWER_PILLS_TILES_LAYER) {
                // for each map layer, i take all the objects in that layer and create a
                // specific entity in the maze
                for (final RectangleMapObject object : map.getLayers().get(i).getObjects()
                        .getByType(RectangleMapObject.class)) {
                    final MazeEntity prop = new MazeEntityImpl(world, object.getRectangle(), map);
                    switch (i) {
                    case WALL_LAYER:
                        prop.setCollisionProperties(Collision.WALL_BIT, Entity.WALL);
                        break;

                    case PILLS_LAYER:
                        prop.setCollisionProperties(Collision.PILL_BIT, Entity.PILL);
                        pills++;
                        break;

                    case POWER_PILLS_LAYER:
                        prop.setCollisionProperties(Collision.POWER_BIT, Entity.POWERPILL);
                        pills++;
                        break;

                    case PORTAL_LAYER:
                        prop.setCollisionProperties(Collision.PORTAL_BIT, Entity.PORTAL);
                        // i need to remember the X position of both portals
                        if (!isLeftPortalAssigned) {
                            this.leftPortalX = object.getRectangle().x;
                            isLeftPortalAssigned = true;
                        } else {
                            this.rightPortalX = object.getRectangle().x;
                        }
                        break;

                    case GATE_LAYER:
                        prop.setCollisionProperties(Collision.GATE_BIT, Entity.WALL);
                        break;

                    case PACMAN_SPAWN_LAYER:
                        prop.setCollisionProperties(Collision.SPAWN_BIT, Entity.SPAWN);
                        this.pacmanSpawn = new Vector2(object.getRectangle().x, object.getRectangle().y + 4);
                        break;

                    case PHANTOMS_SPAWN_LAYER:
                        prop.setCollisionProperties(Collision.SPAWN_BIT, Entity.SPAWN);
                        this.phantomsSpawn.add(new Vector2(object.getRectangle().x, object.getRectangle().y));
                        break;

                    default:
                        break;

                    }
                }
            }
        }
        this.totalPills = pills;
        // after creating the maze, pacman and phantoms will be created
        pacman = new Pacman(world, pacmanSpawn);
        this.phantoms = new ArrayList<>();
        this.phantoms.add(new PhantomImpl(world, this.phantomsSpawn.get(0), Entity.BLINKY));
        this.phantoms.add(new PhantomImpl(world, this.phantomsSpawn.get(1), Entity.PINKY));
        this.phantoms.add(new PhantomImpl(world, this.phantomsSpawn.get(2), Entity.INKY));
        this.phantoms.add(new PhantomImpl(world, this.phantomsSpawn.get(3), Entity.CLYDE));

    }

    /**
     * {@inheritDoc}
     */
    public float getLeftPortalX() {
        return this.leftPortalX;
    }

    /**
     * {@inheritDoc}
     */
    public float getRightPortalX() {
        return this.rightPortalX;
    }

    /**
     * {@inheritDoc}
     */
    public Vector2 getPacmanSpawn() {
        return this.pacmanSpawn;
    }

    /**
     * {@inheritDoc}
     */
    public List<Vector2> getPhantomsSpawns() {
        return this.phantomsSpawn;
    }

    /**
     * {@inheritDoc}
     */
    public int getTotalPills() {
        return this.totalPills;
    }

    /**
     * {@inheritDoc}
     */
    public Pacman getPacman() {
        return this.pacman;
    }

    /**
     * {@inheritDoc}
     */
    public List<PhantomImpl> getPhantoms() {
        return this.phantoms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(leftPortalX);
        result = prime * result + ((pacmanSpawn == null) ? 0 : pacmanSpawn.hashCode());
        result = prime * result + ((phantomsSpawn == null) ? 0 : phantomsSpawn.hashCode());
        result = prime * result + totalPills;
        result = prime * result + Float.floatToIntBits(rightPortalX);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final MazeCreator other = (MazeCreator) obj;
        if (Float.floatToIntBits(leftPortalX) != Float.floatToIntBits(other.getLeftPortalX())) {
            return false;
        }
        if (Float.floatToIntBits(rightPortalX) != Float.floatToIntBits(other.getRightPortalX())) {
            return false;
        }
        if (!pacmanSpawn.equals(other.getPacmanSpawn())) {
            return false;
        }
        if (!phantomsSpawn.equals(other.getPhantomsSpawns())) {
            return false;
        }
        return (totalPills != other.getTotalPills());
    }

}
