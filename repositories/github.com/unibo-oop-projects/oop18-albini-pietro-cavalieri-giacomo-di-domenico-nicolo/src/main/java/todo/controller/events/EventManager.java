package todo.controller.events;

import java.util.function.Consumer;

/**
 * This interface represents an eventManager used to handle the possible events
 * that could take place in a level of the game.
 */
public interface EventManager {
    /**
     * This method allows to associate different consumers to different type of
     * Events, thus performing the required actions whenever of a handled type takes
     * place.
     *
     * @param type specifies the {@link Class} of the {@link Event} that will
     *            trigger the handler to perform the associated action
     * @param handler a {@link Consumer} that will consume an {@link Event} of type
     *            T when the method {@link #dispatch} is called with an Event of
     *            type T as parameter
     * @param <T> the type of the Event
     */
    <T extends Event> void listen(Class<T> type, Consumer<T> handler);

    /**
     * This method is used to notify whenever an {@link Event} that has to be
     * handled by this manger takes place; calling this method will result in all
     * the {@link Consumer}s previously associated with the Event type T (via the
     * method {@link #listen}) to consume the Event passed as a parameter.
     *
     * @param event an {@link Event} that took place and has to be handled by the
     *            manager
     * @param <T> the type of Event
     */
    <T extends Event> void dispatch(T event);
}
