package model.util.storage;

import java.io.IOException;

import java.lang.reflect.Type;

/**
 * Contains generic method to access into file system and work with desired
 * object type.
 *
 * @param <X> desired object Class
 */
public interface ObjectManager<X> {

    /**
     * save an object instance of Class X into file system.
     * 
     * @param args object instance
     */
    void save(X args);

    /**
     * load instance of X Class into file system.
     * 
     * @param classOfX Class of object instance to rebuild
     * @return object instance of Class X
     * @throws IOException if problems encountered working with file system
     */
    X load(Class<X> classOfX) throws IOException;

    /**
     * load instance of X Type into file system.
     * 
     * @param typeOfCollection Type of object instance to rebuild
     * 
     * @return object instance
     * @throws IOException if problems encountered working with file system
     */
    X load(Type typeOfCollection) throws IOException;

}
