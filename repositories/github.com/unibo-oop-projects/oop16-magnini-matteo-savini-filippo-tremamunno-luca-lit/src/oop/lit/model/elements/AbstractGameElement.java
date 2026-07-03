package oop.lit.model.elements;

import java.util.Objects;
import java.util.Optional;

import oop.lit.util.ObservableImpl;

/**
 * A partial implementation of a GameElement, defining getName method.
 * Complete implementations need to implement getActions, getMainAction and getImage methods.
 */
public abstract class AbstractGameElement extends ObservableImpl implements GameElement {
    /**
     * 
     */
    private static final long serialVersionUID = -3877402256164264263L;
    private final String name;

    /**
     * Creates a new AbstractGameElement, assigning it automatically an id not used before.
     * @param name
     *      the name of this element.
     */
    protected AbstractGameElement(final Optional<String> name) {
        Objects.requireNonNull(name);
        this.name = name.orElse(null);
    }

    @Override
    public final Optional<String> getName() {
        return Optional.ofNullable(this.name);
    }

    @Override 
    public void removed() {
    }
}
