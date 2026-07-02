package controller.inputoutput;

import java.lang.reflect.Type;
import java.util.Optional;

public interface RWobject<X> {
    /**
     * Method to read an object on file of a specific type.
     * @param type of object read
     * @return an optional of object that is read
     */
    Optional<X> readObj(Type type);
    /**
     * Method to write an object of a specific type on file.
     * @param obj that is write
     * @param type of object
     */
    void writeObj(X obj, Type type);

}