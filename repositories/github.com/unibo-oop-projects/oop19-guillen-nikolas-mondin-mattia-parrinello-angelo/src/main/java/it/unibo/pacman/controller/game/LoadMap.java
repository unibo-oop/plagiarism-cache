package it.unibo.pacman.controller.game;

import static it.unibo.pacman.model.utilities.EntityType.BLINKY;
import static it.unibo.pacman.model.utilities.EntityType.CLYDE;
import static it.unibo.pacman.model.utilities.EntityType.INKY;
import static it.unibo.pacman.model.utilities.EntityType.PACMAN;
import static it.unibo.pacman.model.utilities.EntityType.PILL;
import static it.unibo.pacman.model.utilities.EntityType.PINKY;
import static it.unibo.pacman.model.utilities.EntityType.POWERPILL;
import static it.unibo.pacman.model.utilities.EntityType.WALL;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import it.unibo.pacman.controller.utilities.MapIO;
import it.unibo.pacman.model.entities.Entity;
import it.unibo.pacman.model.entities.EntityFactory;
import it.unibo.pacman.model.entities.EntityFactoryImpl;
import it.unibo.pacman.model.utilities.Difficulty;
import it.unibo.pacman.model.utilities.Position;

/**
 * Used to create all game's entities from MapIO input.
 */
public class LoadMap {
    private final Set<Entity> entities;
    private static final int SCALE = MapIO.getScale();
    private final Difficulty difficulty;

    /**
     * Creates a loadMap object, which fills a Set of game entities.
     * 
     * @param mapName Name of the map
     * @param difficulty of ghosts
     * @throws IOException if map's txt file is not found
     */
    public LoadMap(final String mapName, final Difficulty difficulty) throws IOException {
        entities = new HashSet<>();
        this.difficulty = difficulty;
        MapIO.readMap(mapName);
        build();
    }

    private void build() throws IOException {
        final EntityFactory ef = new EntityFactoryImpl();
        String current;
        char value;

        for (int i = 0; i < MapIO.getRows(); i++) {
            current = MapIO.getNext();
            for (int j = 0; j < MapIO.getColumns(); j++) {
                value = current.charAt(j);
                if (value == WALL.getValue()) {
                    entities.add(ef.createWall(new Position(j * SCALE, i * SCALE)));
                }
                if (value == PILL.getValue()) {
                    entities.add(ef.createPill(new Position(j * SCALE, i * SCALE)));
                }
                if (value == POWERPILL.getValue()) {
                    entities.add(ef.createPowerPill(new Position(j * SCALE, i * SCALE)));
                }
                if (value == PACMAN.getValue()) {
                    entities.add(ef.createPacMan(new Position(j * SCALE, i * SCALE)));
                }
                if (value == BLINKY.getValue()) {
                    entities.add(ef.createBlinky(new Position(j * SCALE, i * SCALE), difficulty));
                }
                if (value == PINKY.getValue()) {
                    entities.add(ef.createPinky(new Position(j * SCALE, i * SCALE), difficulty));
                }
                if (value == INKY.getValue()) {
                    entities.add(ef.createInky(new Position(j * SCALE, i * SCALE), difficulty));
                }
                if (value == CLYDE.getValue()) {
                    entities.add(ef.createClyde(new Position(j * SCALE, i * SCALE), difficulty));
                }
            }
        }
    }

    /**
     * Get a set of entities.
     * 
     * @return an entity set
     */
    public Set<Entity> getEntities() {
        return this.entities;
    }
}
