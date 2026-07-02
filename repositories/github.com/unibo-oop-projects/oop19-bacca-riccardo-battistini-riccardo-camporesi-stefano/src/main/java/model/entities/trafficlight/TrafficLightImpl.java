package model.entities.trafficlight;

import java.util.LinkedList;
import java.util.Queue;

import constraints.DirOfMovement;
import model.environment.Point;

/**
 *
 */
public final class TrafficLightImpl implements TrafficLight {

    private final Queue<Phases> phases;
    private Point position;
    private DirOfMovement sense;
    private Phases currentPhase;

    public Queue<Phases> getPhases() {
        return this.phases;
    }

    private TrafficLightImpl(final Point position, final DirOfMovement sense, final Phases startingPhase) {
        this.phases = new LinkedList<>();
        this.position = position;
        this.sense = sense;
        this.initializeQueue(startingPhase);
        this.updateQueue();
    }

    /**
     * Static factory to initialize the phases of the trafficLight in the order in
     * which they should succeed according to the constraints of the model.
     *
     * @param position of the traffichLight
     * @param sense of the lane that it's responsible
     * @param startingPhase phase of the start
     * @return a new {@link TrafficLight} which starting phase is Green.
     */
    public static TrafficLightImpl createTrafficLight(final Point position, final DirOfMovement sense,
            final Phases startingPhase) {
        return new TrafficLightImpl(position, sense, startingPhase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phases getCurrentPhase() {
        return this.currentPhase;
    }

    private void initializeQueue(final Phases startingPhase) {
        final Phases other = startingPhase.equals(Phases.RED) ? Phases.GREEN : Phases.RED;
        this.phases.add(startingPhase);
        this.phases.add(Phases.YELLOW);
        this.phases.add(other);
        this.phases.add(Phases.YELLOW);
    }

    private void updateQueue() {
        this.currentPhase = this.phases.poll();
        this.phases.add(this.currentPhase);
    }

    /**
     * switch the status of the traffic light following the sequence
     * GREEN-YELLOW-RED.
     */
    @Override
    public void updatePhase() {
        this.updateQueue();
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final Point position) {
        this.position = position;
    }

    /**
     * @return the sense
     */
    @Override
    public DirOfMovement getSense() {
        return this.sense;
    }

    /**
     * @param sense the sense to set
     */
    @Override
    public void setSense(final DirOfMovement sense) {
        this.sense = sense;
    }

}
