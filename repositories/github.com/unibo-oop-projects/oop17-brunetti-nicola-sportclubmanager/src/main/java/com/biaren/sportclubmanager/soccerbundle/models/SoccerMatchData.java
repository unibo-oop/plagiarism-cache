package com.biaren.sportclubmanager.soccerbundle.models;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Represent data match. Store date, teams, result and
 * goals, warnings and expulsion of society's team.
 * @author nbrunetti
 *
 */
@JsonDeserialize(builder = SoccerMatchData.Builder.class)
public class SoccerMatchData {

    private final LocalDate date;
    private final String homeTeam;
    private final String awayTeam;
    private int homeTeamGoal;
    private int awayTeamGoal;
    private final List<SoccerPlayer> matchPlayersList;
    private final List<SoccerPlayer> warnings;
    private final List<SoccerPlayer> expulsions;
    private final List<SoccerPlayer> goals;
    
    /**
     * Constructor
     * @param builder
     */
    protected SoccerMatchData(final Builder builder) {
        this.date = builder.date;
        this.homeTeam = builder.homeTeam;
        this.awayTeam = builder.awayTeam;
        this.homeTeamGoal = builder.homeTeamGoal;
        this.awayTeamGoal = builder.awayTeamGoal;
        this.matchPlayersList = builder.matchPlayersList;
        this.warnings = builder.warning;
        this.expulsions = builder.expulsion;
        this.goals = builder.goals;
    }
    
    /**
     * Builds a new {@link SoccerMatchData}
     * @author nbrunetti
     *
     */
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder {
        private LocalDate date;
        private String homeTeam;
        private String awayTeam;
        private int homeTeamGoal;
        private int awayTeamGoal;
        private List<SoccerPlayer> matchPlayersList;
        private List<SoccerPlayer> warning;
        private List<SoccerPlayer> expulsion;
        private List<SoccerPlayer> goals;
        
        public Builder() {
            
        }
        
        public Builder date(final LocalDate date) {
            this.date = date;
            return this;
        }
        
        public Builder homeTeam(final String homeTeam) {
            this.homeTeam = homeTeam;
            return this;
        }
        
        public Builder awayTeam(final String awayTeam) {
            this.awayTeam = awayTeam;
            return this;
        }
        
        public Builder homeTeamGoal(final int i) {
            this.homeTeamGoal = i;
            return this;
        }
        
        public Builder awayTeamGoal(final int i) {
            this.awayTeamGoal = i;
            return this;
        }
        
        public Builder matchPlayersList(final List<SoccerPlayer> l) {
            this.matchPlayersList = l;
            return this;
        }
        
        public Builder warnings(final List<SoccerPlayer> l) {
            this.warning = l;
            return this;
        }
        
        public Builder expulsions(final List<SoccerPlayer> l) {
            this.expulsion = l;
            return this;
        }
        
        public Builder goals(final List<SoccerPlayer> l) {
            this.goals = l;
            return this;
        }
        
        public SoccerMatchData build() {
            return new SoccerMatchData(this);
        }
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return the homeTeam
     */
    public String getHomeTeam() {
        return homeTeam;
    }

    /**
     * @return the awayTeam
     */
    public String getAwayTeam() {
        return awayTeam;
    }

    /**
     * @return the homeTeamGoal
     */
    public int getHomeTeamGoal() {
        return homeTeamGoal;
    }

    /**
     * @return the awayTeamGoal
     */
    public int getAwayTeamGoal() {
        return awayTeamGoal;
    }

    /**
     * @return the matchPlayersList
     */
    public List<SoccerPlayer> getMatchPlayersList() {
        return matchPlayersList;
    }

    /**
     * @return the warning
     */
    public List<SoccerPlayer> getWarnings() {
        return warnings;
    }

    /**
     * @return the expulsion
     */
    public List<SoccerPlayer> getExpulsions() {
        return expulsions;
    }

    /**
     * @return the goals
     */
    public List<SoccerPlayer> getGoals() {
        return goals;
    }
    
    /**
     * Get total made goals
     * @param list of {@link SoccerMatchData}
     * @return total made goals
     */
    public static int getTotalMadeGoals(final List<SoccerMatchData> list) {
        return list.stream()
                .mapToInt(SoccerMatchData::getHomeTeamGoal)
                .sum();
    }
    
    /**
     * Get total concede goals
     * @param list of {@link SoccerMatchData}
     * @return total concede goals
     */
    public static int getTotalConcededGoal(final List<SoccerMatchData> list) {
        return list.stream()
                .mapToInt(SoccerMatchData::getAwayTeamGoal)
                .sum();
    }
    
    /**
     * Get total warnings
     * @param list of {@link SoccerMatchData}
     * @return total warnings
     */
    public static int getTotalWarnings(final List<SoccerMatchData> list) {
        return list.stream()
                .map(SoccerMatchData::getWarnings)
                .mapToInt(List::size)
                .sum();
    }
    
    /**
     * Get total expulsions
     * @param list of {@link SoccerMatchData}
     * @return total expulsions
     */
    public static int getTotalExpulsion(final List<SoccerMatchData> list) {
        return list.stream()
                .map(SoccerMatchData::getExpulsions)
                .mapToInt(List::size)
                .sum();
    }
}
