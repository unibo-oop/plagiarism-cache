package model.bonus;

/**
 * 
 * Enumeration that contains existing bonus.
 *
 */
public enum Bonus {
    /**
     * List of the possible bonus.
     */
    JOLLY("Jolly", 3), CAVALRY("Cavalry", 2), INFANTRY("Infantry", 1), ARTILLERY("Artillery", 0);

    private final String name;
    private final int priority;

    Bonus(final String name, final int priority) {
        this.name = name;
        this.priority = priority;
    }

    /**
     * @return the priority level of a specified bonus.
     */
    public Integer getPriority() {
        return this.priority;
    }

    /**
     * @return a string containing the bonus name.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
