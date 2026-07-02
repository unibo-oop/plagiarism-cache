package model.attribute;

import java.util.*;

import static model.attribute.Feature.*;
import utilities.ToString;

/**
 * Enumeration representing supported Traits, each one has a Set of possible Features.
 */
public enum Trait {

    /***/
    HAIRS(Color.class, Length.class, HairStyle.class), 
    /***/
    @ToString(article = "a")
    BEARD(Color.class), 
    /***/
    MOUSTACHE(Color.class), 
    /***/
    @ToString(article = "a")
    HAT, 
    /***/
    GLASSES, 
    /***/
    GENDER(Gender.class),
    /***/
    @ToString(article = "a")
    NOSE(Dimension.class),
    /***/
    EYES(EyeColor.class),
    /***/
    @ToString(article = "a")
    MOUTH(Dimension.class),
    /***/
    COMPLEXION(Complexion.class),
    /***/
    @ToString(article = "a")
    MASK,
    /***/
    EARRING;

    private final Set<Class<?>> features;

    Trait(final Class<?>... features) {
        this.features = new HashSet<>(Arrays.asList(features));
    }

    /**
     * @return the Set of possible Features
     */
    public Set<Class<?>> getPossibleFeatures() {
        return Collections.unmodifiableSet(features);
    }

    /**
     * @return a String representation of this Trait
     */
    public String toString() {
        return name().toLowerCase(Locale.ITALIAN);
    }

}
