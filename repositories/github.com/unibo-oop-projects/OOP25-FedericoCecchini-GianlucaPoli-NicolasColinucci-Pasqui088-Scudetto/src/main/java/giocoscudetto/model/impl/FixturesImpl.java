package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.api.match.Scoreboard;
import giocoscudetto.model.impl.match.MatchImpl;
import giocoscudetto.model.api.Fixtures;

/**
 * This class implements the Fixtures interface, it is responsible for generating the matches of the championship 
 * and for providing the next match to be played.
 */
public class FixturesImpl implements Fixtures {

    private final List<Club> listOfClubs = new LinkedList<>();
    private final List<Match> listOfMatches = new ArrayList<>();
    private Iterator<Match> listOfMatchesIterator;
    private final Map<Match, Scoreboard> fixture = new LinkedHashMap<>();
    private Match currentMatch;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fixtureGeneration(final List<Club> listOClubs) {
        this.listOfClubs.addAll(listOClubs);
        int i;
        int j;
        for (i = 0; i < listOfClubs.size(); i++) {
            for (j = 0; j < listOfClubs.size(); j++) {
                if (i != j) {
                    listOfMatches.add(new MatchImpl(listOfClubs.get(i), listOfClubs.get(j)));
                }
            }
        }
        java.util.Collections.shuffle(listOfMatches);
        for (final Match match : listOfMatches) {
            fixture.put(match, null);
        }
        this.listOfMatchesIterator = listOfMatches.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings
    public Match nextMatch() {
        if (this.listOfMatchesIterator == null) {
            this.listOfMatchesIterator = listOfMatches.iterator();
        }
        if (this.listOfMatchesIterator.hasNext()) {
            this.currentMatch = this.listOfMatchesIterator.next();
            return this.currentMatch;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings
    public Match getCurrentMatch() {
        return this.currentMatch;
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public Match seeNextMatch(final Match match) {
        if (listOfMatches.indexOf(match) >= listOfMatches.size() - 1) {
            return null;
        } else {
            return listOfMatches.get(listOfMatches.indexOf(match) + 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScore(final Match match, final Scoreboard score) {
        this.fixture.replace(match, null, score);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Match> getListOfMatches() {
        return this.fixture.keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return this.fixture.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetFixture() {
        this.fixture.clear();
        this.listOfMatches.clear();
        this.listOfClubs.clear();
    }

    /*
    @Override
    public String toString() {
    final StringBuilder sb = new StringBuilder();

    sb.append("--- CALENDARIO E RISULTATI ---\n");

    if (fixture.isEmpty()) {
        sb.append("Nessuna partita in programma.");
        return sb.toString();
    }

    for (Map.Entry<Match, Scoreboard> entry : fixture.entrySet()) {
       final Match match = entry.getKey();
        final Scoreboard result = entry.getValue();

        // Costruzione della riga
        sb.append(String.format("%-15s vs %15s", 
                  match.getClubHome().getName(),
                  match.getClubAway().getName()));

        sb.append("  |  Risultato: ");


        if (result == null) {
            sb.append("DA GIOCARE");
        } else {
            sb.append(result.getHomeScore())
              .append(" - ")
              .append(result.getGuestScore());
        }
        sb.append("\n");
    }
    sb.append("-------------------------------");
    return sb.toString();
    }
    */

}
