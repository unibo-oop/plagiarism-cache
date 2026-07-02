package tmw.model.item;

/**
 * Interface to represent a type of item that, when used, improves a character's
 * feature for a limited period of time.
 * <p>
 * Extension of {@link tmw.model.item.Item}
 */
public interface TemporaryItem extends Item {

    /**
     * Method to set the feature to the character.
     */
    void activeEffect();

    /**
     * Method to stop the effect.
     */
    void cancelEffect();

    /**
     * Method to get the duration of the feature (in seconds).
     * 
     * @return the duration
     */
    int getDuration();
}

