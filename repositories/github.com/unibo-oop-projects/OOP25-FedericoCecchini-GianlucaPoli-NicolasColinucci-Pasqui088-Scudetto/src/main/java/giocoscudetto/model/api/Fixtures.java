package giocoscudetto.model.api;

import java.util.List;
import java.util.Set;

import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.api.match.Scoreboard;

/**
 * Interface that represents the fixture of the championship, it is responsible for generating the matches
 * and for providing the next match to be played.
 */
public interface Fixtures {

    /**
     * Method that generates the fixture of the championship, it creates a list of pairs 
     * of the clubs that will play against each other, and from that it creates a map to 
     * also store the results of the matches.
     * 
     * @param listOClubs the list of clubs that will play in the championship
     */
    void fixtureGeneration(List<Club> listOClubs);

    /**
     * Method that returns the next match to be played, it returns a pair of clubs that will play against each other.
     * 
     * @return the next match to be played
     */
    Match nextMatch();

    /**
     * @return the current match being played
     */
    Match getCurrentMatch();

    /**
     * Method that when given a match returns the next match to be played, without changing the value of the iterator.
     * 
     * @param match the match for which you want to see the next match
     * @return the match after the match given
     */
    Match seeNextMatch(Match match);

    /**
     * Method that updates the score of the related match.
     * 
     * @param match refers to the match where we need to update the score
     * @param score is the updated score for the match
     */
    void setScore(Match match, Scoreboard score);

    /**
     * @return the list of matches of the fixture
     */
    Set<Match> getListOfMatches();

    /**
     * @return true if the fixture is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Method that resets the fixture, clearing all matches and scores.
     */
    void resetFixture();
}
