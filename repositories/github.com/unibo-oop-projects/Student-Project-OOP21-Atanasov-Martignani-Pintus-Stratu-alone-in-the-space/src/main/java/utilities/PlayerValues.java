package utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * default player ship values stored here for convenience’s sake
 */
public enum PlayerValues {

    MAIN_SHIP(100, 3, 2);

    private final Map<String, Integer> shipValues = new HashMap<>();

    PlayerValues(final int maxHealth, final int maxSpeed, final int rotationSpeed) {
        shipValues.put("MAXHEALTH", maxHealth);
        shipValues.put("MAXSPEED", maxSpeed);
        shipValues.put("ROTATIONSPEED", rotationSpeed);
    }

    public int getValueFromKey(String key) {
        return shipValues.get(key);
    }
}

