package giocoscudetto.controller.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Table;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.impl.FixturesImpl;
import giocoscudetto.model.impl.TableImpl;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.view.impl.result.FixtureModel;
import giocoscudetto.view.impl.result.LeagueTableModel;

/**
 * Implementation of CreateController interface.
 */
public class CreateUpdateControllerImpl implements CreateUpdateController {

    private final List<Club> clubs = new LinkedList<>();
    private final Table table = new TableImpl();
    private final Fixtures fixture = new FixturesImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClubInfoComplete(final List<String> clubsName, final List<Boolean> colors) {
        //Removing spaces from names, to avoid equal names that jst differs for some spaces
        final List<String> namesWithoutSpaces = clubsName.stream()
                        .map(n -> n.replaceAll("\\s+", ""))
                        .toList();
        //namesWithoutSpaces.stream().forEach(n -> System.out.println(n));

        return !namesWithoutSpaces.stream().anyMatch(String::isEmpty) 
            && namesWithoutSpaces.stream().distinct().toList().size() == namesWithoutSpaces.size() 
            && colors.stream().allMatch(i -> i);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createClubs(final List<String> clubsName, final List<Integer> pawnRGB) {

        int i = 0;
        for (; i < clubsName.size(); i++) {
            clubs.add(new ClubImpl(clubsName.get(i), pawnRGB.get(i)));
        }

        this.table.addAllClubs(this.clubs);

        this.fixture.fixtureGeneration(this.clubs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClubActualRank() {
        this.table.updateClubRank();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Club> getClubActualRank() {
        return this.table.showPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Club> getClubs() {
        return Collections.unmodifiableList(clubs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings("EI_EXPOSE_REP")
    public Table getTable() {
        //I suppress the warning because i want to return the table assigned to the match, not a copy.
        return this.table;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings
    public Fixtures getFixture() {
        //System.out.println("fixture" + this.fixture.toString());
        return this.fixture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.clubs.clear();
        this.fixture.resetFixture();
        this.table.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FixtureModel getFixtureTableModel() {
        return new FixtureModel(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeagueTableModel getLeagueTableModel() {
        this.table.updateClubRank();
        return new LeagueTableModel(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restartLeague() {
        final List<Integer> pawns = new ArrayList<>();
        final List<String> clubsname = new ArrayList<>();
        for (final Club club : this.getClubs()) {
            pawns.add(club.getPawn().getPawnRGB());
            clubsname.add(club.getName());
        }
        this.reset();
        this.createClubs(clubsname, pawns);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getTableNames() {
        return this.getTable().showPosition()
                .stream()
                .map(Club::getName)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getTablePoints() {
        return this.getTable().showPosition()
                .stream()
                .map(Club::getPoints)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getTableNetDiff() {
        return this.getTable().showPosition()
                .stream()
                .map(Club::getNetDiff)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getFixtureMatchesString() {
        return this.getFixture().getListOfMatches()
                .stream()
                .map(Match::toString)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getFixtureScoresString() {
        return this.getFixture().getListOfMatches()
                .stream()
                .map(Match::getScore)
                .map(Object::toString)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public void setPositionsZero() {
        clubs.forEach(club -> club.getPawn().setPosition(0));
    }
}
