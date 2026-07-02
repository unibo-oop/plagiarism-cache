package javawulf.model.map.factory;

import javawulf.model.map.Room;

/**
 * Implementation of RoomFactory, which is used to create default rooms. Useful
 * in BiomeFactories or standalone in tests.
 */
public final class RoomFactoryImpl implements RoomFactory {

    private static final int MEDIUM_DEFAULT_DIM = 5;

    @Override
    public Room getSquaredRoom() {
        return new Room(MEDIUM_DEFAULT_DIM, MEDIUM_DEFAULT_DIM);
    }

}
