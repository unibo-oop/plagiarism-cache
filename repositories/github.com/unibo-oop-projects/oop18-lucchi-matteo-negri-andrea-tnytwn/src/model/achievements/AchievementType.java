package model.achievements;

/**
 * This enumerator contains the different types of Achievements.
 */
public enum AchievementType {
    /**
     * Indicates that the Achievement require only some resources.
     */
    RESOURCES,
    /**
     * Indicates that the Achievement require only some buildings.
     */
    BUILDINGS,
    /**
     * Indicates that the Achievement require both buildings and resources.
     */
    BUILDINGS_AND_RESOURCES;
}
