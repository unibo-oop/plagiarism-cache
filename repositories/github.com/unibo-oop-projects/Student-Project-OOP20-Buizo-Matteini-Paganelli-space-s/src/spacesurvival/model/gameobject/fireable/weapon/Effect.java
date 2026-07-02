package spacesurvival.model.gameobject.fireable.weapon;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import spacesurvival.model.gameobject.main.Status;
import spacesurvival.utilities.RandomUtils;

/**
 * Contains all possible effects applicable to game objects.
 */
public enum Effect {
    /**
     * Effect that causes normal status.
     */
    NONE(Status.NORMAL),
    /**
     * Effect that causes fire status.
     */
    FIRE(Status.ON_FIRE),
    /**
     * Effect that causes frozen status.
     */
    ICE(Status.FROZEN),
    /**
     * Effect that causes paralyzed status.
     */
    ELECTRIC(Status.PARALYZED);

    private Status status;

    private static final List<Effect> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();

    Effect(final Status status) {
        this.status = status;
    }

    /**
     * @return a random effect
     */
    public static Effect random()  {
        return VALUES.get(RandomUtils.RANDOM.nextInt(SIZE));
    }

    /**
     * @return the status caused by the effect
     */
    public Status getStatus() {
        return this.status;
    }
}
