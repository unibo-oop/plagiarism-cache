package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;
/**
 * Component of the windows.
 */
public class FixedWindowsComponent extends AbstractComponent {
    private boolean isFixed;
    /**
     * Constructor.
     * @param state
     */
    public FixedWindowsComponent(final boolean state) {
        this.isFixed = state;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.FIXEDWINDOWS;
    }
    /**
     * Set the boolean variable.
     */
    public void setFixed() {
        this.isFixed = true;
    }
    /**
     * Getter for the boolean that indicate if a window is fixed or not.
     * @return isFixed variable.
     */
    public boolean isFixed() {
        return this.isFixed;
    }
}
