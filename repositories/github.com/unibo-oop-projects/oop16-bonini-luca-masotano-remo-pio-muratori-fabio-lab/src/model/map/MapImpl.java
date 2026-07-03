package model.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.entities.Enemies;
import model.entities.Enemy;
import model.entities.CharacterType;
import model.hitbox.Hitbox;
import model.hitbox.HitboxImpl;
import model.utils.Direction;

/**
 * 
 * Defines the map of the game, made up of all the GameRooms created.
 *
 */
public class MapImpl implements Serializable, Map {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = -4658690721410765112L;
    private final List<Room> gameMap;
    private int currentRoom;
    private int bossRoom;

    /**
     * Constructs a new instance of MapImpl. It randomly generates the map of
     * the game.
     */
    public MapImpl() {
        gameMap = new ArrayList<>();
        currentRoom = 0;
        generate();
    }

    @Override
    public void changeRoomByDoor(final Direction c) {
        currentRoom = gameMap.get(currentRoom).getConnection(c);
    }

    @Override
    public List<Room> getAllRooms() {
        return Collections.unmodifiableList(gameMap);
    }

    @Override
    public Room getCurrentRoom() {
        return gameMap.get(currentRoom);
    }

    @Override
    public boolean isBossRoom() {
        return this.bossRoom == currentRoom;
    }

    @Override
    public boolean isBossDead() {
        return gameMap.get(this.bossRoom).getEnemies().isEmpty();
    }

    private void generate() {
        final Random renemies = new Random();
        final Random rdoors = new Random();
        final ArrayList<Enemy> e = new ArrayList<>();
        List<Direction> doors = new ArrayList<>();
        /*
         * A list of position to support the creation of rooms. Only in the
         * generation process every room is associated with a position.
         */
        final ArrayList<HitboxImpl> position = new ArrayList<>();
        Direction d;
        int maxEnemies;
        final int maxEnemiesGeneratedPerRoom = 5;

        final int limitRoom = 10;
        int roomCreated = 1;

        Room m = new GameRoom();
        position.add(new HitboxImpl(0, 0));
        gameMap.add(m);

        /*
         * The algorithm keeps generating between 1 and 4 doors for every room
         * created until it reach limitRoom or roomCreated. roomCreated is
         * incremented every time a room is generated. Rooms are generated after
         * the doors and only if the position where the room should be created
         * is free. If no rooms can be generated the algorithm stops. limitRoom
         * is only an upper-bound, the rooms created an be less than limitRoom.
         */
        for (int i = 0; i < roomCreated; i++) {
            m = gameMap.get(i);

            /* Generation of doors */
            /* Between 1 and 4 (inclusive) */
            int maxDoors = 1 + rdoors.nextInt(Integer.MAX_VALUE) % 4;
            /* Can return duplicate values */
            doors = Direction.getRandomDirections();
            doors.removeAll(m.getDoors());
            if (maxDoors > 4 - m.getDoors().size()) {
                maxDoors = 4 - m.getDoors().size();
            }

            for (int j = 0; j < maxDoors; j++) {
                d = doors.get(j);

                final Hitbox h = position.get(gameMap.indexOf(m));
                Room m2 = new GameRoom();

                if (!m.getDoors().contains(d) && !position.contains(positionOf(h, d)) && roomCreated <= limitRoom) {
                    /*
                     * The actual room doesn't have this door yet and this
                     * position is free Generation of enemies
                     */
                    maxEnemies = 1 + renemies.nextInt(maxEnemiesGeneratedPerRoom);
                    for (int k = 0; k < maxEnemies; k++) {
                        e.add(Enemies.getEnemy(CharacterType.getRandomEnemy()));
                    }
                    m.addDoors(d);
                    position.add(positionOf(h, d));
                    m2.addDoors(Direction.getOpposite(d));
                    m2.addConnection(gameMap.indexOf(m));
                    m2.addEnemies(e);
                    gameMap.add(m2);
                    m.addConnection(gameMap.indexOf(m2));
                    roomCreated++;
                    e.clear();

                } else if (!m.getDoors().contains(d) && position.contains(positionOf(h, d))) {
                    /*
                     * The actual room doesn't have this door yet and this
                     * position is not free. So the room that occupy this
                     * position is connected with the actual room.
                     */
                    m2 = gameMap.get(position.indexOf(positionOf(h, d)));
                    m.addDoors(d);
                    m.addConnection(gameMap.indexOf(m2));
                    m2.addDoors(Direction.getOpposite(d));
                    m2.addConnection(gameMap.indexOf(m));
                }
            }

        }

        m.getEnemies().clear();

        bossRoom = roomCreated - 1;
        m.getEnemies().add(Enemies.getEnemy(CharacterType.BOSS));

        position.clear();
    }

    private HitboxImpl positionOf(final Hitbox h, final Direction door) {
        switch (door) {
        case UP:
            return new HitboxImpl(h.getX(), h.getY() + 1);
        case DOWN:
            return new HitboxImpl(h.getX(), h.getY() - 1);
        case LEFT:
            return new HitboxImpl(h.getX() - 1, h.getY());
        default: /* case RIGHT */
            return new HitboxImpl(h.getX() + 1, h.getY());
        }
    }

}
