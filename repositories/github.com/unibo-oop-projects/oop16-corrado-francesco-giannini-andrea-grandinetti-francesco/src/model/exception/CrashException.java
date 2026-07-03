package model.exception;

import java.util.Map;

import utility.Driver;
import utility.Position;
/**
 * 
 * Exception for crashing cars.
 *
 */
public class CrashException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final Map<Driver, Boolean> crashMap; 
    private final Position pos;
    /**
     * Constructor.
     * @param crashMap map
     * @param pos crashed car's position
     */
    public CrashException(final Map<Driver, Boolean> crashMap, final Position pos) {
        this.crashMap = crashMap;
        this.pos = pos;
    }
    /**
     * Getter.
     * @return a map with drivers who had an accident and if they have retired
     */
    public Map<Driver, Boolean> getCrashMap() {
        return crashMap;
    }
    /**
     * Getter.
     * @return crashed car's position
     */
    public Position getPos() {
        return pos;
    }
}
