package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Shape;
import org.dyn4j.collision.Fixture;

import model.environment.EnvironmentLogic;
import model.environment.EnvironmentLogicImpl;
import model.people.Person;
import model.places.Home;
import model.places.Hospital;
import model.places.HospitalizationOutcome;
import model.places.MeetingPlace;

/**
 * 
 */
public class EnvironmentManagerImpl implements EnvironmentManager {

    //Entities have height and width equals
    private static final double MAP_SIZE = 1000;
    private static final double HOME_SIZE = 80;
    private static final double HOSPTITAL_SIZE = 90;
    private static final double MEETING_PLACE_SIZE = 60;
    private static final double PERSON_SIZE = 25;

    private final List<Fixture> peopleFixtures = new ArrayList<>();
    private final List<Fixture> meetingPlacesFixtures = new ArrayList<>();
    private final Fixture homeFixture;
    private final Fixture hospitalFixture;
    private final EnvironmentLogic environmentMap = new EnvironmentLogicImpl();

    /**
     * Constructor method.
     * @param home
     * @param hospital
     */
    public EnvironmentManagerImpl(final Home home, final Hospital hospital) {
        this.environmentMap.setBounds(0, 0, MAP_SIZE, MAP_SIZE);
        this.homeFixture = createFixture(
                (Convex) this.environmentMap.translateRandomlyInAcceptableArea(new Rectangle(HOME_SIZE, HOME_SIZE)),
                home);
        this.hospitalFixture = createFixture((Convex) this.environmentMap
                .translateRandomlyInAcceptableArea(new Rectangle(HOSPTITAL_SIZE, HOSPTITAL_SIZE)), hospital);
        this.environmentMap.addObstacle(this.homeFixture.getShape());
        this.environmentMap.addObstacle(this.hospitalFixture.getShape());
    }

    private Fixture createFixture(final Convex shape, final Object data) {
        final Fixture f = new Fixture(shape);
        f.setUserData(data);
        return f;
    }

    /**
     * 
     */
    @Override
    public void movePeople() {
        peopleFixtures.forEach(p -> environmentMap.move(p.getShape()));
    }

    /**
     * 
     */
    @Override
    public void moveToHome(final Person person, final int time) {
        this.removePerson(person);
        ((Home) this.homeFixture.getUserData()).enter(person, time);
    }

    /**
     * 
     */
    @Override
    public void moveToHospital(final Person person, final int time) {
        this.removePerson(person);
        ((Hospital) this.hospitalFixture.getUserData()).enter(person, time);
    }

    /**
     * 
     */
    @Override
    public void addMeetingPlace(final MeetingPlace meetingPlace) {
        final Shape shape = this.environmentMap
                .translateRandomlyInAcceptableArea(new Rectangle(MEETING_PLACE_SIZE, MEETING_PLACE_SIZE));
        final Fixture fixtureMeetingPlace = new Fixture((Convex) shape);
        fixtureMeetingPlace.setUserData(meetingPlace);
        this.meetingPlacesFixtures.add(fixtureMeetingPlace);
        this.environmentMap.addObstacle(fixtureMeetingPlace.getShape());
    }

    /**
     * 
     */
    @Override
    public void exitFromHome(final int time) {
        ((Home) this.homeFixture.getUserData()).exit(time).forEach(p -> {
            Rectangle rect = new Rectangle(PERSON_SIZE, PERSON_SIZE);
            rect = (Rectangle) environmentMap.translateRandomlyInAcceptableArea(rect);
            this.peopleFixtures.add(createFixture(rect, p));
        });
    }

    /**
     * 
     */
    @Override
    public void exitFromMeetingPlaces(final int time) {
        this.meetingPlacesFixtures.forEach(mPlaceFixture -> this
                .exitFromPlace(((MeetingPlace) mPlaceFixture.getUserData()).exit(time), mPlaceFixture));
    }

    /**
     * 
     */
    @Override
    public HospitalizationOutcome exitFromHospital(final int time) {
        final HospitalizationOutcome hospOut = ((Hospital) this.hospitalFixture.getUserData()).exitWithOutcome(time);
        this.exitFromPlace(hospOut.getRecoveredPeople(), hospitalFixture);
        return hospOut;
    }

    /**
     * Puts the given people on the map around the place.
     * @param people
     *              people to put in the map
     * @param placeFixture
     *              place where people exit
     */
    private void exitFromPlace(final List<Person> people, final Fixture placeFixture) {
        people.forEach(p -> {
            Rectangle rect = new Rectangle(PERSON_SIZE, PERSON_SIZE);
            rect = (Rectangle) environmentMap.translateAroundPosition(rect, placeFixture.getShape().getCenter(),
                    placeFixture.getShape().getRadius() + rect.getRadius() * 2);
            this.peopleFixtures.add(createFixture(rect, p));
        });
    }

    /**
     * 
     */
    @Override
    public void addPerson(final Person person) {
        Rectangle rect = new Rectangle(PERSON_SIZE, PERSON_SIZE);
        rect = (Rectangle) environmentMap.translateRandomlyInAcceptableArea(rect);
        peopleFixtures.add(createFixture(rect, person));
    }

    /**
     * 
     */
    @Override
    public void removePerson(final Person person) {
        if (this.peopleFixtures.stream().map(f -> f.getUserData()).filter(p -> p.equals(person)).findFirst()
                .isPresent()) {
            this.peopleFixtures.removeIf(p -> p.getUserData().equals(person));
        } else if (((Home) this.homeFixture.getUserData()).checkPresence(person)) {
            ((Home) this.homeFixture.getUserData()).exitSinglePerson(person);
        } else {
            this.meetingPlacesFixtures.stream().map(f -> (MeetingPlace) f.getUserData())
                    .filter(m -> m.checkPresence(person)).findFirst().ifPresent(m -> m.exitSinglePerson(person));
        }
    }

    /**
     * 
     */
    @Override
    public void setAlertState(final double homeTendencyIncrement) {
        this.meetingPlacesFixtures
                .forEach(mpF -> exitFromPlace(((MeetingPlace) mpF.getUserData()).close(), mpF));
        ((Home) this.homeFixture.getUserData()).setHomeTendencyIncrement(homeTendencyIncrement);
    }

    /**
     * 
     */
    @Override
    public void unsetAlertState() {
        this.meetingPlacesFixtures.stream().map(f -> (MeetingPlace) f.getUserData()).forEach(mp -> mp.open());
        ((Home) this.homeFixture.getUserData()).setHomeTendencyIncrement(0);
    }

    /**
     * 
     */
    @Override
    public Fixture getHomeFixture() {
        return this.homeFixture;
    }

    /**
     * 
     */
    @Override
    public Fixture getHospitalFixture() {
        return this.hospitalFixture;
    }

    /**
     * 
     */
    @Override
    public List<Fixture> getPeopleFixtures() {
        return this.peopleFixtures;
    }

    /**
     * 
     */
    @Override
    public List<Fixture> getMeetingPlaceFixtures() {
        return this.meetingPlacesFixtures;
    }

    /**
     * 
     */
    @Override
    public List<Person> getPeopleOutside() {
        return this.peopleFixtures.stream().map(pf -> (Person) pf.getUserData()).collect(Collectors.toList());
    }

    /**
     * 
     */
    @Override
    public List<Person> getAllPeople() {
        final List<Person> people = new ArrayList<>();
        people.addAll(((Home) homeFixture.getUserData()).getAllPeople());
        people.addAll(((Hospital) hospitalFixture.getUserData()).getAllPeople());
        people.addAll(meetingPlacesFixtures.stream()
                .flatMap(mpF -> ((MeetingPlace) mpF.getUserData()).getAllPeople().stream())
                .collect(Collectors.toList()));
        people.addAll(this.getPeopleOutside());
        return people;
    }

    /**
     * 
     */
    @Override
    public boolean isHospitalEmpty() {
        return !((Hospital) hospitalFixture.getUserData()).isAnyoneInHospital();
    }

    /**
     * 
     */
    @Override
    public double getMapSize() {
        return EnvironmentManagerImpl.MAP_SIZE;
    }

    /**
     * 
     */
    @Override
    public int getPeopleHospital() {
        return ((Hospital) hospitalFixture.getUserData()).getAllPeople().size();
    }

    /**
     * 
     */
    @Override
    public int getPeopleMeetingPlace() {
        return meetingPlacesFixtures.stream().map(p -> ((MeetingPlace) p.getUserData()).getAllPeople().size()).reduce((x, y) -> x + y).orElse(0);
    }

    /**
     * 
     */
    @Override
    public int getPeopleHome() {
        return ((Home) homeFixture.getUserData()).getAllPeople().size();
    }
}
