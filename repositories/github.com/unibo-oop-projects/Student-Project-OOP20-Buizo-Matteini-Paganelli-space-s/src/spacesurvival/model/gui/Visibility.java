package spacesurvival.model.gui;

public enum Visibility {
    /**
     * Visible element.
     */
    VISIBLE(true),
    /**
     * Hidden element.
     */
    HIDDEN(false);

    /**
     * Current state.
     */
    private final boolean state;

    Visibility(final boolean state) {
        this.state = state;
    }

    /**
     * Return true if visible.
     * 
     * @return true if visible
     */
    public boolean isVisible() {
        return this.state;
    }

    /**
     * Return string description of the visibility.
     * 
     * @return the string description
     */
    @Override
    public String toString() {
        return "Visibility{" 
                + "state=" 
                + state 
                + '}';
    }
}
