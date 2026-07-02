package bubbleshooter.model.component;

import bubbleshooter.model.bubble.Bubble;

/**
 * Interface used to add features to a {@link Bubble}.
 */
public interface Component {

    /**
     * Method used to update the {@link Component} of a {@link Bubble}.
     * @param elapsed the time elapsed after a GameLoop cycle.
     */
    void update(double elapsed);

    /**
     * @param parent The Bubble which contains this {@link Component}.
     */
    void setContainer(Bubble parent);

    /**
     * @param type the type of the component
     */
    void setType(ComponentType type);

    /**
     * @return the {@link Bubble} which contains this {@link Component}.
     */
    Bubble getContainer();

    /**
     * 
     * @return the type of this {@link Component}.
     */
    ComponentType getComponentType();
}
