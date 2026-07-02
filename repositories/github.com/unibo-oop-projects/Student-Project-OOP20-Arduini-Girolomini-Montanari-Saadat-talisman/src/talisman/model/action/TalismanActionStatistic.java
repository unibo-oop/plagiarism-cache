package talisman.model.action;

import java.util.Locale;

/**
 * The statistics on which an action can be applied.
 * 
 * @author Alberto Arduini
 *
 */
public enum TalismanActionStatistic {
    /**
     * 
     */
    NONE, GOLD, HEALTH, FAITH, CRAFT, STRENGTH;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
}
