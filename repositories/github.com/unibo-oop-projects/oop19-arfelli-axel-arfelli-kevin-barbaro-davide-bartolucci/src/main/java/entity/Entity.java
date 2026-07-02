package entity;

public interface Entity {

    /**
     *  Update the status of the entity.
<<<<<<< HEAD
     *  If is an enemy it also moves and tries to shoot.
=======
>>>>>>> 75fe6e22ae8d158667f4c4b88a29b37670a6ec95
     */
    void update();

    /**
     * @return returns a boolean whenever the entity is alive.
     */
    boolean isAlive();

    /**
     * @return the type of entity.
     */
    String getType();


    /**
     * @return the VirtualBody of the entity
     */
    VirtualBody getBody();

    /**
     * @return The Unique identifier of the entity
     */
    String getID();
}
