package zombieversity.view;

/**
 * Interface to manage round view.
 *
 */
public interface RoundView {

    /**
     * Used to set the round number label and show it.
     * @param roundCounter current round.
     */
    void setRound(int roundCounter);

    /**
     * Used to set the break label and show it.
     */
    void breakRound();

    /**
     * Used to update break label countdown.
     * @param missingTime time missing to start the round.
     */
    void updateTimerLabel(double missingTime);

}
