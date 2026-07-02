package todo.controller.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import todo.controller.events.Event;
import todo.controller.events.EventManager;
import todo.controller.events.EventManagerImpl;

public class EventManagerTest {
    private final List<String> log;
    private EventManager manager;

    public EventManagerTest() {
        this.log = new ArrayList<>();
    }

    @Before
    public void prepareManager() {
        this.log.clear();
        this.manager = new EventManagerImpl();
    }

    @Test
    public void testListen() {
        this.manager.listen(DummyEvent1.class, e -> this.log.add(e.getMessage()));
        this.manager.listen(DummyEvent2.class, e -> this.log.add(e.getMessage()));
        this.manager.listen(DummyEvent1.class, e -> this.log.add(e.getMessage() + "bis"));

        this.manager.dispatch(new DummyEvent1());
        assertEquals(Arrays.asList("1", "1bis"), this.log);
        this.manager.dispatch(new DummyEvent2());
        assertEquals(Arrays.asList("1", "1bis", "2"), this.log);
        this.manager.dispatch(new DummyEvent2());
        assertEquals(Arrays.asList("1", "1bis", "2", "2"), this.log);
    }

    @Test
    public void testListenWithNoHandlers() {
        this.manager.dispatch(new DummyEvent1());
        assertTrue(this.log.isEmpty());
    }

    private class DummyEvent1 implements Event {
        @Override
        public String getMessage() {
            return "1";
        }
    }

    private class DummyEvent2 implements Event {
        @Override
        public String getMessage() {
            return "2";
        }
    }
}
