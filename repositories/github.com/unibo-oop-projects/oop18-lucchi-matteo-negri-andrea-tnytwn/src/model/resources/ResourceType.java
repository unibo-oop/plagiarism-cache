package model.resources;

/**
 * This enumerator contains the list of the resources.
 */
public enum ResourceType {
    /**
     * ENERGY display the electric energy produced.
     */
    ENERGY("Energy", "The electric energy is necessary for your city!", true),
    /**
     * WATER display the water produced.
     */
    WATER("Water", "How can a city go on without any water?", true),
    /**
     * MONEY display the money used in different situations.
     */
    MONEY("Money", "The currency of TinyTown!", true),
    /**
     * PEOPLE display the number of the people in different situations.
     */
    PEOPLE("People", "How many people live in your city?", true);

    /**
     * The name of the resource.
     */
    private final String name;
    /**
     * A short description of the resource.
     */
    private final String description;
    /**
     * Indicates if the resource has a bottom (0) or can have a negative value.
     */
    private final boolean bottom;

    /**
     * Default Constructor.
     * @param name
     *          The name of the resource.
     * @param description
     *          A short description of the resource.
     */
    ResourceType(final String name, final String description, final boolean bottom) {
        this.name = name;
        this.description = description;
        this.bottom = bottom;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @return if the Resource has a bottom (0)
     */
    public boolean isBottom() {
        return bottom;
    }
}
