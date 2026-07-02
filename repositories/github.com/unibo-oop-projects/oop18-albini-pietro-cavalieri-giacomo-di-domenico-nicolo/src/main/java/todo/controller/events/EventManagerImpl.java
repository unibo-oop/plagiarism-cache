package todo.controller.events;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class EventManagerImpl implements EventManager {
    private final Map<Class<? extends Event>, List<Consumer<? extends Event>>> handlers;

    public EventManagerImpl() {
        this.handlers = new LinkedHashMap<>();
    }

    @Override
    public <T extends Event> void listen(final Class<T> type, final Consumer<T> handler) {
        this.handlers.computeIfAbsent(type, k -> new ArrayList<>());
        this.handlers.get(type).add(handler);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Event> void dispatch(final T event) {
        if (this.handlers.containsKey(Objects.requireNonNull(event).getClass())) {
            /*
             * The cast is always safe since all Consumers mapped in this.handlers consume
             * elements of the class of the respective key in the map; this is guaranteed as
             * the only way to add a Consumer in this.handlers is via the method listen
             * which requires a Consumer that accepts an element of the class that will be
             * its key in the map.
             */
            this.handlers.get(event.getClass()).forEach(handler -> ((Consumer<T>) handler).accept(event));
        }
    }

}
