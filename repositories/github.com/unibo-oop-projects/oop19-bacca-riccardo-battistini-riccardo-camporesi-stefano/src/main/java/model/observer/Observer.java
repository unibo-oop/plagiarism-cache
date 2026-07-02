package model.observer;

public interface Observer {

    /**
     * To take updates from the Subject.
     * 
     * @param object the object to be returned
     */
    void update(Object object);
}
