package controller;

/**
 * A static factory used to get a {@link SurrogateController} or a {@link Controller} depending
 * on the requirement.
 */
public final class ControllerFactory {

    private ControllerFactory() { }

    /**
     * @return a {@link Controller} instance.
     */
    public static Controller getController() {
        return ControllerImpl.getController();
    }

    /**
     * @return a {@link SurrogateController} instance.
     */
    public static SurrogateController getSurrogateController() {
        return ControllerImpl.getController();
    }

}
