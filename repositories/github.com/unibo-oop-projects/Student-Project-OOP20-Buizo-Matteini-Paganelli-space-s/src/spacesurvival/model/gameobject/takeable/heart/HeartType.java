package spacesurvival.model.gameobject.takeable.heart;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import spacesurvival.model.gameobject.main.Status;
import spacesurvival.utilities.RandomUtils;
import spacesurvival.utilities.gameobject.StatusUtils;
import spacesurvival.utilities.path.animation.AnimationPerk;

public enum HeartType {

    /**
     * Causes a healed status to the ship.
     */
    HEAL(Status.HEALED, StatusUtils.HEAL_AMOUNT, AnimationPerk.LIST_LIFE),
    /**
     * Causes a healed status to the ship.
     */
    LIFE_UP(Status.LIVES_INCREASED, StatusUtils.LIFE_UP_AMOUNT_HEAL, AnimationPerk.LIST_HEART);

    private final Status status;
    private final int amount;
    private final List<String> animation;

    private static final List<HeartType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();

    HeartType(final Status status, final int amount, final List<String> animation) {
        this.status = status;
        this.amount = amount;
        this.animation = animation;
    }

    /**
     * @return a random heart type
     */
    public static HeartType random()  {
        return VALUES.get(RandomUtils.RANDOM.nextInt(SIZE));
    }

    /**
     * @return the amount of the heart healing
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * @return list of string for the heart animation
     */
    public List<String> getAnimation() {
        return this.animation;
    }

    /**
     * @return the status which applies the heart type
     */
    public Status getStatus() {
        return status;
    }

}
