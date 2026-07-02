package controller.gameoptions;

import java.util.Locale;

/**
 * Enumeration representing different game's packs.
 */
public enum Pack {
     /**
      * Classic pack.
      */
    CLASSIC(),
    /**
     * Pulp fiction pack.
     */
    PULP_FICTION();

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ITALIAN).replaceAll("_", " ");
    }
}
