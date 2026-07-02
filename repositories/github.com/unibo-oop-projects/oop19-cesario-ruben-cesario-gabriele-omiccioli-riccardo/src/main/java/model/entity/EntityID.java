package model.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import utilities.exception.NotImplementedException;

/**
 * Models an identifier for an entity, describing the category
 * this entity belongs to.
 */
public enum EntityID {

    /** Identifies an entity by default, it refers to an entity with no particular properties. */
    DEFAULT_ENTITY,

    /** Identifies an entity belonging to the BasicSpaceShip category. */
    SPACESHIP_BASIC,

    /** Identifies a fragile BasicSpaceShip that hits hard. */
    FIGHTER,

    /** Identifies a slow but resilient BasicSpaceShip. */
    JUGGERNAUT,

    /** Identifies a fast BasicSpaceShip. */
    CUTTER,

    /** Identifies an entity belonging to the StandardProjectile category. */
    PROJECTILE_STANDARD;

    /**
     * Returns true if this entity belongs to the specified category.
     * @param category : the specified category 
     * @return true if this entity belongs to the specified category, false otherwise.
     */
    public boolean belongsTo(final EntityIDCategory category) {
        switch (category) {
        case SPACESHIPS:
            return EntityIDCategory.SPACESHIP_IDS.contains(this);
        case SPACESHIPS_BASIC:
            return EntityIDCategory.SPACESHIP_BASIC_IDS.contains(this);
        case PROJECTILES:
            return EntityIDCategory.PROJECTILE_IDS.contains(this);
        default:
            throw new NotImplementedException("The category [" + category + "] isn't yet considered in this method.");
        }
    }

    /**
     * Throw an IllegalArgumentException if the specified EntityID doesn't belong to the specified category.
     * @param modelID : the EntityID which needs to be checked
     * @param category : the specified category
     * @return the checked EntityID, if it refers to a spaceship.
     */
    public static EntityID requireBelonging(final EntityID modelID, final EntityIDCategory category) {
        if (modelID.belongsTo(category)) {
            return modelID;
        } else {
            throw new IllegalArgumentException("The EntityID [" + modelID + "] doesn't belong to the EntityIDCategory [" + category + "].");
        }
    }

    /**
     * Models a category of EntityIDs.
     */
    public enum EntityIDCategory {

        /** Identifies the category of all spaceships IDs. */
        SPACESHIPS, 

        /** Identifies the category of all BasicSpaceShip IDs. */
        SPACESHIPS_BASIC,

        /** Identifies the category of all projectiles IDs. */
        PROJECTILES;

        /** The set of EntityIDs that refer to a spaceship. */
        private static final Set<EntityID> SPACESHIP_IDS = new HashSet<>();
        /** The set of EntityIDs that refer to a BasicSpaceShip. */
        private static final Set<EntityID> SPACESHIP_BASIC_IDS = new HashSet<>();
        /** The set of EntityIDs that refer to a projectile. */
        private static final Set<EntityID> PROJECTILE_IDS = new HashSet<>();

        static {
            initializeData();
        }

        /**
         * Returns an unmodifiable set of the EntityIDs belonging to the specified category.
         * @param category : the specified category.
         * @return an unmodifiable set of the EntityIDs belonging to the specified category.
         */
        public static Set<EntityID> getEntityIDs(final EntityIDCategory category) {
            switch (category) {
            case SPACESHIPS:
                return Collections.unmodifiableSet(SPACESHIP_IDS);
            case SPACESHIPS_BASIC:
                return Collections.unmodifiableSet(SPACESHIP_BASIC_IDS);
            case PROJECTILES:
                return Collections.unmodifiableSet(PROJECTILE_IDS);
            default:
                throw new NotImplementedException("The category [" + category + "] isn't yet considered in this method.");
            } 
        }

        /**
         * Initialises the sets containing all the IDs belonging to the represented category. 
         */
        private static void initializeData() {
            SPACESHIP_BASIC_IDS.addAll(Set.of(
                    SPACESHIP_BASIC,
                    FIGHTER,
                    JUGGERNAUT,
                    CUTTER
            ));
            SPACESHIP_IDS.addAll(
                    SPACESHIP_BASIC_IDS
            );
            PROJECTILE_IDS.addAll(Set.of(
                    PROJECTILE_STANDARD
            ));
        }

    }
}
