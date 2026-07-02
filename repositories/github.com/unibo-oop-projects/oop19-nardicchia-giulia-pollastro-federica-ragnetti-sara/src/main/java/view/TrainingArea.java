package view;

import java.util.Set;

/**
 * Enumeration for all the training area with the correspondent list of
 * minigames.
 *
 */
public enum TrainingArea {

    /**
     * Attention training area.
     * 
     */
    ATTENTION("Attention"),

    /**
     * Brain speed training area.
     * 
     */
    BRAIN_SPEED("Brain Speed"),

    /**
     * Memory training area.
     * 
     */
    MEMORY("Memory"),

    /**
     * Reasoning training area.
     * 
     */
    REASONING("Reasoning"),

    /**
     * Visual skill training area.
     * 
     */
    VISUAL_SKILL("Visual Skill");

    private String name;

    TrainingArea(final String name) {
       this.name = name;
   }

    /**
     * 
     * @return the name of the area
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return the minigame list
     */
    public Set<String> getMiniGameSet() {
        return ScanMiniGames.getMiniGames(this);
    }

}
