package giocoscudetto.controller.api;

import java.util.List;

import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Table;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.view.impl.result.FixtureModel;
import giocoscudetto.view.impl.result.LeagueTableModel;

/**
 * Controller Interface to cominicate between model and view in pre match
 * configuation, table and fixture creation and update.
 */
public interface CreateUpdateController {

    /**
     * @param clubsName is the list of name to be verify.
     * @param colors is the set of boolean to verify if all clubs have a color selected.
     * @return false if there are duplicate/empty name and missing club color,
     *         true if all information are present.
     */
    boolean isClubInfoComplete(List<String> clubsName, List<Boolean> colors);

    /**
     * Method to create the clubs that will play the incoming match, the table
     * to memorize their position and the fixture.
     * 
     * @param clubsName contians the clubs name that will play the match.
     * @param pawnName contains the club's pawn color.
     */
    void createClubs(List<String> clubsName, List<Integer> pawnName);

    /**
     * Method to update each club position in the table.
     */
    void updateClubActualRank();

    /**
     * @return the list containg the club in the right position.
     */
    List<Club> getClubActualRank();

    /**
     * @return the list of clubs.
     */
    List<Club> getClubs();

    /**
     * @return the table.
     */
    Table getTable();

    /**
     * @return the fixture.
     */
    Fixtures getFixture();

    /**
     * Method to reset everything and start a new league with new clubs.
     */
    void reset();

    /**
     * @return the fixture table model.
     */
    FixtureModel getFixtureTableModel();

    /**
     * @return the league table model.
     */
    LeagueTableModel getLeagueTableModel();

    /**
     * Method to restart the league with the same clubs and colors.
     */
    void restartLeague();

    /**
     * @return a list with the clubs names in the order of the table.
     */
    List<String> getTableNames();

    /**
     * @return a list with the clubs points in the order of the table.
     */
    List<Integer> getTablePoints();

    /**
     * @return a list with the clubs net differences in the order of the table.
     */
    List<Integer> getTableNetDiff();

    /**
     * @return a list with a string representation of the matches in the order of the fixture.
     */
    List<String> getFixtureMatchesString();

    /**
     * @return a list with the scores of the matchsa in the order of the fixture.
     */
    List<String> getFixtureScoresString();

    /**
     * This method sets the positions of all pawns to zero.
     */
    void setPositionsZero();
}
