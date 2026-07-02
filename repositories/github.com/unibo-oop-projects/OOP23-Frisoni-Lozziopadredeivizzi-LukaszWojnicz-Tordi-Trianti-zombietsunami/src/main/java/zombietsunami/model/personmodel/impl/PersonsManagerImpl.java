package zombietsunami.model.personmodel.impl;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import zombietsunami.Pair;
import zombietsunami.model.personmodel.api.Person;
import zombietsunami.model.personmodel.api.PersonsManager;

/**
 * Class that manages everthing about Persons.
 */
public class PersonsManagerImpl implements PersonsManager {

    private List<Person> personList = new ArrayList<>();

    /**
     * Method that allows to remove a Person from the list.
     * 
     * @param index index of the Person.
     */
    @Override
    public void removePersonFromList(final int index) {
        personList.set(index, null);
    }

    /**
     * Returns the Person list.
     * 
     * @return the Person list.
     */
    @SuppressFBWarnings(justification = "Persons inside personList must be" 
        + " set null when colliding in CollisionImpl, otherwise the code won't work.")
    @Override
    public List<Person> getPersonList() {
        return personList;
    }

    /**
     * Returns the list of Person from the map.
     */
    @Override
    public void setPersonFromMap(final List<Integer> personlist, final List<Pair<Integer, Integer>> coords,
            final Integer strenght) {
        personList = new ArrayList<>();
        for (int i = 0; i < personlist.size(); i++) {
            if (personlist.get(i) == 1 && coords.get(i) != null) {
                final Person person = new PersonImpl();

                person.setX(coords.get(i).getX());
                person.setY(coords.get(i).getY());

                personList.add(i, person);
            } else {
                personList.add(i, null);
            }
        }
    }

    /**
     * Adds a Person into the Person list.
     * 
     * @param person the Person that needs to be added.
     */
    @Override
    public void addPerson(final Person person) {
        if (person != null) {
            personList.add(person);
        }
    }

}
