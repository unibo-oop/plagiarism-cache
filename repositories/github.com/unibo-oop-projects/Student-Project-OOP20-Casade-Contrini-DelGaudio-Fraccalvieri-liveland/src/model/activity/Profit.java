package model.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.person.ticket.PersonTicket;
import view.model.activity.ActivityType;

public class Profit implements Activity {

    private final String name;
    private int totMoney;
    private int totPeople;
    private final int min;
    private final int max;
    private final ActivityType activityType;
    private final List<PersonTicket> personList = new ArrayList<>();

    /**
     * 
     * @param name profit name
     * @param min profit min
     * @param max profit max
     * @param activityType activity type
     */
    public Profit(final String name, final int min, final int max, final ActivityType activityType) {
        this.name = name;
        this.activityType = activityType;
        this.min = min;
        this.max = max;
    }

    /**
     * 
     * @param name profit name
     * @param min profit min
     * @param max profit max
     * @param activityType activity type
     * @param visitors visitors number
     */
    public Profit(final String name, final int min, final int max, final ActivityType activityType, final int visitors) {
        this.name = name;
        this.activityType = activityType;
        this.min = min;
        this.max = max;
        this.totPeople = visitors;
    }

    /**
     * {@inheritDoc}
     */
    public final void addPerson(final PersonTicket person) {
        personList.add(person);
        amount();
        totPeople++;
    }

    /**
     * {@inheritDoc}
     */
    public final void removePerson() {
        personList.clear();
    }

    /**
     * 
     * @param money money
     * 
     */
    public final void setProfit(final int money) {
        this.totMoney = money;
    }

    /**
     * 
     * @return money earned in one race
     */
    public final int getProfit() {
        return totMoney;
    }

    /**
     * {@inheritDoc}
     */
    public final int getTotPeople() {
        return totPeople;
    }

    /**
     * Money spend by each person at random.
     */
    public final void amount() {
        int money;
        final Random r = new Random();
        do {
            money = r.nextInt(max);
        } while (money > min);

        setProfit(getProfit() + money);

    }

    /**
     * {@inheritDoc}
     */
    public final ActivityType getActivityType() {
        return this.activityType;
    }

    /**
     * 
     * @return name of the restaurant or shop
     */
    public final String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    public final List<PersonTicket> getPeopleList() {
        return personList;

    }

}
