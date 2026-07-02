package levelgenerator;

/**
 * States for the grid. Each state represents a different entity. The color are
 * used to separate the concept of entity from button states.
 */
public enum States {
    /**
     * Color states of the buttons.
     */
    GRAY("Default"), RED("Wall"), GREEN("Wall"), ORANGE("Hero"), BLACK("Enemy");

    private final String entity;

    States(final String entity) {
        this.entity = entity;
    }

    /**
     * It Gets the String of the correspondent color. It's used for building the
     * JSON file.
     * 
     * @return the entity as a String
     */
    public String getEntity() {
        return this.entity;
    }

}
