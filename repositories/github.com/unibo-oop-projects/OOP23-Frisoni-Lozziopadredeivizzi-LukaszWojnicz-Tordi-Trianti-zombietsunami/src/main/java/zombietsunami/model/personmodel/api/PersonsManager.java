package zombietsunami.model.personmodel.api;

import java.util.List;

import zombietsunami.Pair;

/**
 * Interface whose purpose is to manage all Persons.
 */
public interface PersonsManager {

    /**
     * Removes the "index" Person from the list.
     * 
     * @param index the index of the Person in the list.
     */
    void removePersonFromList(int index);

    /**
     * Returns the Person list.
     * 
     * @return the Person list.
     */
    List<Person> getPersonList();

    /**
   * Sets the Person list from the map.
   * @param personlist The list of Person.
   * @param coords The coordinates of Person.
   * @param strenght The strenght of Person.
   */
    void setPersonFromMap(List<Integer> personlist, List<Pair<Integer, Integer>> coords, Integer strenght);

    /**
     * Adds a Person into the Person list.
     * 
     * @param person the Person that needs to be added.
     */
    void addPerson(Person person);
}
