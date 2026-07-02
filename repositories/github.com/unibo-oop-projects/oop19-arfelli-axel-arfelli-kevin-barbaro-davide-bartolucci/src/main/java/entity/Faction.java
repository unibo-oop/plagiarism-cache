package entity;

public enum Faction {

    /**
     * Represents the ENEMY faction.
     */
    ENEMY,
    /**
     * Represents NEUTRAL faction. 
     */
    NEUTRAL,
    /**
     * Represents Player Faction.
     */
    ALLY;

    /**
     * Return if a Faction is against another one.
     * @param faction to evaluate
     * @return return boolean representing if against
     */
    public Boolean isOpposed(final Faction faction) {
        if (faction == Faction.NEUTRAL) {
            return false;
        }
        return this != faction;
    }
}
