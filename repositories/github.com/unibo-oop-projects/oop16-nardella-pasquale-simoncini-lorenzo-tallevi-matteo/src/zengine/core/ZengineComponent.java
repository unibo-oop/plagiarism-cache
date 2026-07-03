package zengine.core;

/**
 * This interface represents a generic Zengine component that can be initialize,
 * reinitialized and linked with other components.
 */
interface ZengineComponent {

    /**
     * returns the reference of this component, it must be guaranteed that it is
     * a singleton.
     * 
     * @return the reference of this singleton component.
     */
    ZengineComponent getComponent();

    /**
     * Performs the needed operations to correctly initialize this component.
     */
    void initialize();

    /**
     * Performs the needed operations to restore the state of this component to
     * its inital status. It may or may not include a restoreDefaultValues().
     */
    void reinitialize();

    /**
     * Restores default values for internal properities of this component. Note
     * that this does not mean that the component has been reinitialized, it
     * just restores default values.
     */
    void restoreDefaultValues();

    /**
     * Links this component to other ZengineComponents. The meaning of the link
     * depends on the role of every component. Linking operations are usually
     * included inside initialize or reinitialize operations.
     */
    void link();

    /**
     * This method updates the ZengineComponent's status, according to the clock
     * ticks described by the game loop.
     */
    void update();
}
