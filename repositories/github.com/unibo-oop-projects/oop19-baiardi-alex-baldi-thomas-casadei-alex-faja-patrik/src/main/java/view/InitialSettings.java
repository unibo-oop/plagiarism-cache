package view;

/**
 * Class that contains the values to set the simulation. 
 */
public class InitialSettings {

    private final int nInitialPeople;

    private final int nInitialInfected;

    private final int nMeetingPlace;

    private final double birthRate;

    private final double deathRate;

    private final double tendencyToHome;

    /**
     * Constructor method that sets initial values.
     * @param people    initial number of people
     * @param infected  initial number of infected people
     * @param place     number of meeting place
     * @param birthRate rate of birth
     * @param deathRate rate of death
     * @param tendency  tendency of the people to stay at home
     */
    public InitialSettings(final int people, final int infected, final int place, final double birthRate,
            final double deathRate, final double tendency) {

        this.nInitialPeople = people;
        this.nInitialInfected = infected;
        this.nMeetingPlace = place;
        this.birthRate = birthRate;
        this.deathRate = deathRate;
        this.tendencyToHome = tendency;

    }

    /**
     * Gets the initial number of people.
     * @return  Initial number of people
     */
    public int getnInitialPeople() {
        return nInitialPeople;
    }

    /**
     * Gets the initial number of infected people.
     * @return Initial number of infected people
     */
    public int getnInitialInfected() {
        return nInitialInfected;
    }

    /**
     * Gets the number of meeting place.
     * @return The number of meeting place
     */
    public int getnMeetingPlace() {
        return nMeetingPlace;
    }

    /**
     * Gets the birth rate.
     * @return Birth rate
     */
    public double getBirthRate() {
        return birthRate;
    }

    /**
     * Gets the death rate.
     * @return Death rate
     */
    public double getDeathRate() {
        return deathRate;
    }

    /**
     * Gets the tendency of people to go home.
     * @return Tendency of people to go home
     */
    public double getTendencyToHome() {
        return tendencyToHome;
    }

}
