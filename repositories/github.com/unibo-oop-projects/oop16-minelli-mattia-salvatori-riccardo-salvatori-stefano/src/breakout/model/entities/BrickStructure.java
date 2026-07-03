package breakout.model.entities;

import java.util.function.Function;

/**
 * Enum for all the possible brick structure. A brick structure defines the life
 * of a brick and the damage recived when hit
 */
public enum BrickStructure {

    /**
     * A brick that is destroyed with one hit.
     */
    SIMPLE(1, (x) -> x - 1),

    /**
     * A brick that is destroyed with 3 hit.
     */
    HARD(3, (x) -> x - 1),

    /**
     * This type is undestroyable by a normal ball hit.
     */
    UNBREAKABLE(1, (x) -> x);

    private int life;
    private Function<Integer, Integer> decrementFunction;

    /**
     * Instanciate a brick structure
     * 
     * @param life
     *            the number of hits needed to destroy this type of brick
     * @param decrementFunction
     *            how much life this type of brick loses when hit
     */
    BrickStructure(final int life, final Function<Integer, Integer> decrementFunction) {
        this.life = life;
        this.decrementFunction = decrementFunction;
    }

    /**
     * @return the initial value of life (the number of hits needed to destroy
     *         it) of this brick
     */
    public int getLife() {
        return this.life;
    }

    /**
     * @return a function defining how much life this type of brick loses when
     *         hit
     */
    public Function<Integer, Integer> getDecrementFunction() {
        return this.decrementFunction;
    }
}