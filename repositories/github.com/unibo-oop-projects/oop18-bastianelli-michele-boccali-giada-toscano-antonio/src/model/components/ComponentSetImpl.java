package model.components;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ComponentSetImpl<T> implements ComponentSet<T> {

    private final Map<Class<?>, T> componentSet = new LinkedHashMap<>();

    @Override
    public final <C extends T> void remove(final Class<C> type) {
        componentSet.keySet().remove(type);
    }

    @Override
    public final <C extends T> void remove(final C component) {
        componentSet.values().remove(component);
    }

    @Override
    public final void add(final T component) {
        if (componentSet.containsKey(component.getClass())) {
            throw new IllegalArgumentException("Component already included: " + component.toString());
        } else {
            componentSet.put(component.getClass(), component);
        }
    }

    @Override
    public final void clear() {
        componentSet.clear();
    }

    @Override
    public final Stream<T> stream() {
        return componentSet.values().stream();
    }

    @Override
    public final <C extends T> C getComponent(final Class<C> type) {
        if (componentSet.containsKey(type)) {
            return type.cast(componentSet.get(type));
        } else {
            throw new IllegalArgumentException("This class is not included into the set: " + type.toString());
        }
    }

    @Override
    public final <C extends T> boolean isPresent(final Class<C> type) {
        return componentSet.containsKey(type);
    }

}
