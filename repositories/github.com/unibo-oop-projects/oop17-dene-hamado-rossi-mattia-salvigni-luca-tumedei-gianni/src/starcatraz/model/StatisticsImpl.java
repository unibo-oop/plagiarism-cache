package starcatraz.model;

import java.time.LocalTime;

public class StatisticsImpl implements Statistics {
    
    private int victories;
    private int defeats;
    private int gamesplayed;
    private LocalTime gametime;
    private int robotsdefeated;
    private int cardstreak;
    private int cardsleft;
    
    @Override
    public int getVictories() {
        return this.victories;
    }

    @Override
    public int getDefeats() {
        return this.defeats;
    }

    @Override
    public int getPlayedGames() {
        return this.gamesplayed;
    }

    @Override
    public LocalTime getGameTimeRecord() {
        return this.gametime;
    }

    @Override
    public int getCardStreak() {
        return this.cardstreak;
    }

    @Override
    public int getDefeatedRobotsCount() {
        return this.robotsdefeated;
    }
    
    @Override
    public int getVictoryWithFewestCards() {
        return this.cardsleft;
    }

    @Override
    public void incrementVictories() {
        this.victories++;   
    }

    @Override
    public void incrementDefeats() {
        this.defeats++; 
    }

    @Override
    public void incrementPlayedGames() {
       this.gamesplayed++;   
    }

    @Override
    public void setVictories(final int victories) {
        this.victories = victories;
    }

    @Override
    public void setDefeats(final int defeats) {
        this.defeats = defeats;
    }

    @Override
    public void setPlayedGames(final int gamesplayed) {
        this.gamesplayed = gamesplayed;
    }

    @Override
    public void setGameTimeRecord(final String gametime) {
        this.gametime = LocalTime.parse(gametime);
    }

    @Override
    public void setCardStreak(final int cardstreak) {
        this.cardstreak = cardstreak;
    }

    @Override
    public void setDefeatedRobotsCount(final int robotsdefeated) {
        this.robotsdefeated = robotsdefeated;
    }

    @Override
    public void setVictoryWithFewestCards(final int cardsleft) {
        this.cardsleft = cardsleft;
    }
}
