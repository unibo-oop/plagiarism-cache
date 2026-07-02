/**
 * 
 */
package model.entities;

/**
 * Represents the entity type.
 */
public enum EntityType {
    /**
     * Represents a hero.
     */
    HERO(Constants.DEFAULT_HERO_TEXT),
    /**
     * Represents an obstacle.
     */
    OBSTACLE(Constants.DEFAULT_OBSTACLE_TEXT);

    private String text;

    EntityType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    private static class Constants {
        public static final String DEFAULT_HERO_TEXT = "Hero";
        public static final String DEFAULT_OBSTACLE_TEXT = "Obstacle";
    }
}
