package model.room;

public interface Room {

    /**
     * @return the room id
     */
    int getNumber();

    /**
     * @return the room type
     */
    RoomType getType();

}
