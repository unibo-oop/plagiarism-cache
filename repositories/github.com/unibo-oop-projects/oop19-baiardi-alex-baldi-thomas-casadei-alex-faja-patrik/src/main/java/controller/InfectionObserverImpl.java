package controller;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.collision.AbstractCollidable;
import org.dyn4j.collision.Collidable;
import org.dyn4j.collision.Fixture;
import org.dyn4j.collision.broadphase.BroadphaseDetector;
import org.dyn4j.collision.broadphase.BroadphaseItem;
import org.dyn4j.collision.broadphase.BroadphasePair;
import org.dyn4j.collision.broadphase.Sap;
import org.dyn4j.collision.narrowphase.NarrowphaseDetector;
import org.dyn4j.collision.narrowphase.Sat;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;

import model.people.Person;
import model.people.Status;
import model.places.MeetingPlace;

/**
 *
 */
public class InfectionObserverImpl implements InfectionObserver {

    private static final double MEETING_PLACE_ENTRY_SIZE = 15;
    /**
     * 
     */
    @Override
    public List<Person> detectInfectionBeetweenPeople(final List<Fixture> peopleFixture) {
        final List<Person> infected = new ArrayList<Person>();
        final BroadphaseDetector<Collidable<Fixture>, Fixture> bp = new Sap<>();
        initializeBroadphaseDetector(bp, peopleFixture);
        final NarrowphaseDetector nd = new Sat();
        for (final BroadphasePair<Collidable<Fixture>, Fixture> bPair : bp.detect()) {
            Person p1 = (Person) bPair.getFixture1().getUserData();
            Person p2 = (Person) bPair.getFixture2().getUserData();
            if (((p1.getStatus() == Status.INFECTED && p2.getStatus() == Status.SUSCEPTIBLE)
                    || (p1.getStatus() == Status.SUSCEPTIBLE && p2.getStatus() == Status.INFECTED))
                            && nd.detect(bPair.getFixture1().getShape(), new Transform(),
                                    bPair.getFixture2().getShape(), new Transform())) {
                if (p1.getStatus() != Status.INFECTED) {
                    final Person temp = p1;
                    p1 = p2;
                    p2 = temp;
                }
                if (p1.tryToInfect()) {
                    p2.infect(p1.getVirus().get().duplicate());
                    infected.add(p2);
                }
            }
        }
        return infected;
    }

    private void initializeBroadphaseDetector(final BroadphaseDetector<Collidable<Fixture>, Fixture> bp,
            final List<Fixture> fixtures) {
        fixtures.forEach(f -> {
            final Collidable<Fixture> coll = new MyCollidable();
            bp.add(coll.addFixture(f));
        });
    }

    /**
     * 
     */
    @Override
    public List<Person> tryToEnterAndDetectInfectionInMeetingPlace(final List<Fixture> peopleFixture,
            final List<Fixture> meetingPlacesFixture, final int time) {
        final List<Person> infected = new ArrayList<Person>();
        final BroadphaseDetector<Collidable<Fixture>, Fixture> bp = new Sap<>();
        initializeBroadphaseDetector(bp, peopleFixture);
        meetingPlacesFixture.stream()
                            .filter(mpFix -> ((MeetingPlace) mpFix.getUserData()).isOpen()
                                    && !((MeetingPlace) mpFix.getUserData()).isFull())
                            .forEach(mPlaceFixture -> {
                                final MeetingPlace mPlace = (MeetingPlace) mPlaceFixture.getUserData();
                                for (final BroadphaseItem<Collidable<Fixture>, Fixture> bi : bp
                                        .detect(mPlaceFixture.getShape().createAABB().getExpanded(MEETING_PLACE_ENTRY_SIZE))) {
                                    infected.addAll(mPlace.enterAndInfect((Person) bi.getFixture().getUserData(), time));
                                    peopleFixture.remove(bi.getFixture());
                                    bp.remove(bi.getCollidable());
                                    if (mPlace.isFull()) {
                                        break;
                                    }
                                }
                            });
        return infected;
    }

    private class MyCollidable extends AbstractCollidable<Fixture> {

        @Override
        public Collidable<Fixture> addFixture(final Fixture fixture) {
            super.fixtures.add(fixture);
            return this;
        }

        @Override
        public Fixture addFixture(final Convex convex) {
            final Fixture f = new Fixture(convex);
            super.fixtures.add(f);
            return f;
        }

        @Override
        public Vector2 getLocalCenter() {
            return getWorldCenter();
        }

        @Override
        public Vector2 getWorldCenter() {
            return fixtures.get(0).getShape().getCenter();
        }
    }

}
