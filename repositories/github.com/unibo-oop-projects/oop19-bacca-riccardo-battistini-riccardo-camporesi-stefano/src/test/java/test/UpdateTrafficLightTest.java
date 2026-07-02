package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constraints.DirOfMovement;
import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.PhaseManagerImpl;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.trafficlight.TrafficLightImpl;
import model.environment.Point;

public class UpdateTrafficLightTest {

    private LinkedList<Pair<Phases, Integer>> list;
    private PhaseManager<Phases> phMan;
    private List<TrafficLight> trafficLights;

    private final TrafficLight tr1 = TrafficLightImpl.createTrafficLight(Point.of(1, 2), DirOfMovement.EAST_WEST,
            Phases.GREEN);
    private final TrafficLight tr2 = TrafficLightImpl.createTrafficLight(Point.of(1, 2), DirOfMovement.EAST_WEST,
            Phases.RED);
    private final TrafficLight tr3 = TrafficLightImpl.createTrafficLight(Point.of(1, 2), DirOfMovement.EAST_WEST,
            Phases.GREEN);

    @BeforeEach
    public final void initialize() {

        list = new LinkedList<>();

        list.add(Pair.of(Phases.GREEN, 3));
        list.add(Pair.of(Phases.YELLOW, 1));
        list.add(Pair.of(Phases.RED, 2));
        list.add(Pair.of(Phases.YELLOW, 1));

        phMan = new PhaseManagerImpl<>(list);

        trafficLights = new ArrayList<>();

        trafficLights.add(tr1);
        trafficLights.add(tr2);
        trafficLights.add(tr3);

        phMan.attach(tr1);
        phMan.attach(tr2);
        phMan.attach(tr3);
    }

    @Test
    public void testSinglePhaseChange() {

        for (int i = 0; i < 3; i++) {
            phMan.update();
        }
        assertEquals(Phases.YELLOW, tr1.getCurrentPhase());

        for (int i = 0; i < 1; i++) {
            phMan.update();
        }
        assertEquals(Phases.RED, tr1.getCurrentPhase());

        for (int i = 0; i < 10; i++) {
            phMan.update();
        }
        assertEquals(Phases.GREEN, tr1.getCurrentPhase());

        for (int i = 0; i < 4; i++) {
            phMan.update();
        }
        assertEquals(Phases.RED, tr1.getCurrentPhase());

    }

    @Test
    public void testSyncronizedPhaseChange() {
        for (int i = 0; i < 3; i++) {
            phMan.update();
        }
        assertEquals(Phases.YELLOW, tr1.getCurrentPhase());
        assertEquals(Phases.YELLOW, tr2.getCurrentPhase());

        for (int i = 0; i < 1; i++) {
            phMan.update();
        }
        assertEquals(Phases.RED, tr1.getCurrentPhase());
        assertEquals(Phases.GREEN, tr2.getCurrentPhase());

        for (int i = 0; i < 10; i++) {
            phMan.update();
        }
        assertEquals(Phases.GREEN, tr1.getCurrentPhase());
        assertEquals(Phases.RED, tr2.getCurrentPhase());

    }
}
