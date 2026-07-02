package it.unibo.model.menu.api;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Abstract base class for menu states.
 * Provides a common structure and holds a reference to the Menu context.
 */
public abstract class AbstractMenuState implements StateOfMenu {

    // CHECKSTYLE: VisibilityModifier OFF
    // The rule is disabled because this class is a template and classes that extend
    // it need to access the menu field to change the state of the menu.

    /**
     * The menu context that this state belongs to.
     */
    protected final Menu menu;

    // CHECKSTYLE: VisibilityModifier ON

    /**
     * Constructs a new AbstractMenuState.
     * 
     * @param menu the Menu context
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2", 
        justification = "Menu is a mutable object, but it is necessary to pass it to the state to comply to the state pattern."
    )
    public AbstractMenuState(final Menu menu) {
        this.menu = menu;
    }
}
