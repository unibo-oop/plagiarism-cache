package model.places;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import model.people.Person;
import model.people.Status;

/**
 *
 */
public class HospitalImpl extends AbstractPlace implements Hospital {

    /**
     * 
     */
    @Override
    public void enter(final Person person, final int time) {
        if (person.getVirus().isEmpty()) {
            throw new IllegalArgumentException();
        }
        super.enter(person, time + person.getVirus().get().getRecoveryPeriod());
    }

    /**
     * 
     */
    @Override
    public HospitalizationOutcome exitWithOutcome(final int time) {
        final List<Person> people = super.exit(time);
        final List<Person> healed = people.stream().filter(p -> outcome(p))
                .peek(p -> p.setStatus(Status.RECOVERED))
                .collect(Collectors.toList());
        healed.stream().forEach(p -> people.remove(p));
        return new HospitalizationOutcome() {
            @Override
            public List<Person> getRecoveredPeople() {
                return healed;
            }

            @Override
            public List<Person> getDeadPeople() {
                return people;
            }
        };
    }

    /**
     * Method that calculates if a person is dead or recovered.
     * 
     * @param person The person to be analyzed.
     * @return true if a person is recovered 
     *         false if a person is dead
     */
    private boolean outcome(final Person person) {
        final Random r = new Random();
        return r.nextInt(100) > person.getVirus().get().getMortality();
    }

    /**
     * 
     */
    @Override
    public boolean isAnyoneInHospital() {
        return !super.getAllPeople().isEmpty();
    }
}
