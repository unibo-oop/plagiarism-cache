package zombietsunami.model;

import zombietsunami.api.GameOver;

/**
 * This class implemets the GameOver interface
 * {@link zombietsunami.api.GameOver}.
 */
public class GameOverImpl implements GameOver {

    /**
     * @return true if the zombie's strenght is not enough to break the breakable
     *         object in the map
     */
    @Override
    public boolean isNotEnough(final boolean isEnough) {
        return isEnough;
    }

    /**
     * @return true if the zombie's strenght is zero
     */
    @Override
    public boolean isStrenghtZero(final int strenght) {
        return strenght == 0;
    }

}
