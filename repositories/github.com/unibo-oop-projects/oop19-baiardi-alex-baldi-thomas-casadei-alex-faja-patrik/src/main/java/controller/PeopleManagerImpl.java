package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.people.Person;
import model.people.PersonImpl;
import model.people.Status;

/**
 *
 */
public class PeopleManagerImpl implements PeopleManager {

    private final double birthRate;
    private final double deathRate;
    private final double stayHomeRate;
    private double homeTendencyIncrement;
    private final Random rand = new Random();

    /**
     * Constructor method.
     * 
     * @param birthRate    Rate of birth
     * @param deathRate    Rate of death
     * @param stayHomeRate Tendency to stay at home
     */
    public PeopleManagerImpl(final double birthRate, final double deathRate, final double stayHomeRate) {
        this.birthRate = birthRate;
        this.deathRate = deathRate;
        this.stayHomeRate = stayHomeRate;
        this.homeTendencyIncrement = 0;
    }

    /**
     * 
     */
    @Override
    public List<Person> birth(final int totPerson) {
        final int peopleToGenerate = rand.nextInt((int) (totPerson * birthRate / 100) + 1);
        return generatePerson(peopleToGenerate);
    }

    /**
     * 
     */
    @Override
    public List<Person> death(final List<Person> list) {
        list.removeIf(p -> p.getStatus().equals(Status.INFECTED));
        final int peopleWhoDie = rand.nextInt((int) (list.size() * deathRate / 100) + 1);
        return selectRandomPeople(peopleWhoDie, list);
    }

    /**
     * 
     */
    @Override
    public List<Person> goHome(final List<Person> list) {
        final int peopleWhoGoHome = rand
                .nextInt((int) (list.size() * (stayHomeRate + homeTendencyIncrement) / 100) + 1);
        return selectRandomPeople(peopleWhoGoHome, list);
    }

    /**
     * generate a list of 'number' person.
     * 
     * @param number number of person to generate
     * @return list of person
     */
    private List<Person> generatePerson(final int number) {
        final List<Person> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(new PersonImpl());
        }
        return list;
    }

    /**
     * Select a 'num' of random people from the 'list'.
     * 
     * @param num  number of person to chose
     * @param list the list where the people be chosen
     * @return a list of random selected person
     */
    private List<Person> selectRandomPeople(final int num, final List<Person> list) {
        return Stream.generate(() -> new Random().nextInt(list.size())).distinct().limit(num).map(n -> list.get(n))
                .collect(Collectors.toList());
    }

    /**
     * Set the increment of the home tendency.
     * 
     * @param homeTendencyIncrement value of the increment
     */
    public void setHomeTendencyIncrement(final double homeTendencyIncrement) {
        this.homeTendencyIncrement = homeTendencyIncrement;
    }

}
