package ballblast.model.events;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;

import ballblast.commons.events.EventType;

/**
 * Concrete implementation of {@link GameEventManager}.
 *
 */
public class GameEventManagerImpl implements GameEventManager {
    private List<EventType> events;

    /**
     * Class constructor.
     */
    public GameEventManagerImpl() {
        this.events = Collections.emptyList();
    }

    @Override
    public final void addGameEvent(final EventType event) {
        this.events = ImmutableList.<EventType>builder()
                .addAll(this.events)
                .add(event)
                .build();
    }

    @Override
    public final List<EventType> getGameEvents() {
        final List<EventType> t = this.events;
        this.events = Collections.emptyList();
        return t;
    }

}
