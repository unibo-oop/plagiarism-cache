package model.activity;

import java.util.List;

import model.person.ticket.PersonTicket;
import view.model.activity.ActivityType;

public interface Activity {

    /**
     * 
     * @return type of activity.
     */
    ActivityType getActivityType();

    /**
     * 
     * @param person
     * Add people to the people's list of every single Activity.
     */
    void addPerson(PersonTicket person);

    /**
     * Clear people's list.
     */
    void removePerson();

    /**
     * 
     * @return list of the people of every single race of every single Activity.
     */
    List<PersonTicket> getPeopleList();

    /**
     * 
     * @return number of people who have been in each single Activity.
     */
    int getTotPeople();
}

