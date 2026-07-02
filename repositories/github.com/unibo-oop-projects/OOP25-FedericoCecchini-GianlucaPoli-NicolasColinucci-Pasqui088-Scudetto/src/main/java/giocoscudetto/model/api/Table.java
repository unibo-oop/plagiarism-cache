package giocoscudetto.model.api;

import java.util.List;

import giocoscudetto.model.api.match.Club;

/**
 * Interface that defines the correct position of each club in the table.
 */
public interface Table {

    /**
     * @param clubs are all the clubs that partecipate to matches
     *              so will be added to the table.
     */
    void addAllClubs(List<Club> clubs);

    /**
     * Updates each club position in the table.
     * Clubs are ordered by:
     * 1) points,
     * 2) goal difference,
     * 3) club name in reverse alphabetical order (it's the only way that come to 
     *    my mind to select a position for each club assuming they have same points and net diff ).
     */
    void updateClubRank();

    /**
     * @return the List with all the team in the correct order.
     */
    List<Club> showPosition();

    /**
     * Method to clear the table list, removing teams from it.
     */
    void reset();

}
