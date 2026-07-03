package org.lkyhro;

/**
 * Created by Luca on 26/02/2016.
 */
public interface EncounterObserver {
    /**
     *
     * @param condition for which the battle has ended, it can be VICTORY, DEFEAT or RUN
     */
    void encounterEnded(Encounter.EndingCondition condition);
}
