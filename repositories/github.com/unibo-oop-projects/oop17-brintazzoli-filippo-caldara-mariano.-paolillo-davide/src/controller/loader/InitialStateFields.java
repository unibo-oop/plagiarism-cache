package controller.loader;

/**
 * Enumeration for the initial fields to initialize the model world.
 */
public enum InitialStateFields {

    /**
     * The list of the initial fields to set the two {@link Tank}.
     */
    POSX("X"), POSY("Y"), SPEED("speed"), LIFES("lifes"), P_SPEED("pSpeed");

    private String fieldName;

    /**
     * Private constructor.
     * 
     * @param fieldName
     *            the name of the field.
     */

    InitialStateFields(final String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Getter of the levels' name.
     * 
     * @return the level's name.
     */
    public String getName() {
        return this.fieldName;
    }

}
