package todo.view.rooms;

/**
 * Implementations of RoomEventListener act on the events received by the
 * controller.
 */
public interface RoomEventListener {
    /**
     * Tell the event listeners the current animations just completed. Then all the
     * batched dialogs will be shown.
     */
    void animationsCompleted();
}
