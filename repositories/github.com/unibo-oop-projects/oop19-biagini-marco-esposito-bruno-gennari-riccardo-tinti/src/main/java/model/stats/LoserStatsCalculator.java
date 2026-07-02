package model.stats;

import model.enums.StatsInfo;
import model.players.Player;

/**
 * The class calculates all the values related to the loser player statistics.
 * 
 */
public class LoserStatsCalculator implements Statistics {

    private static final double DEFAULT_VAL = 0.00;

    private Player loser;
    private double matchScore;

    public LoserStatsCalculator(final Player loser, final double score) {
        this.loser = loser;
        this.matchScore = score;
    }

    @Override
    public final void basicStats() {
        final double tot = getInfoValue(StatsInfo.TOTALS.getName()) + 1;
        final double loss = getInfoValue(StatsInfo.LOSS.getName()) + 1;
        final double wins = getInfoValue(StatsInfo.WINS.getName());

        bestScoreCheck();

        this.loser.updateStats(StatsInfo.TOTALS.getName(), tot);
        this.loser.updateStats(StatsInfo.LOSS.getName(), loss);
        this.loser.updateStats(StatsInfo.LOSS_RATE.getName(), (loss / tot) * 100);
        this.loser.updateStats(StatsInfo.WINS_RATE.getName(), (wins / tot) * 100);
    }

    private void bestScoreCheck() {
        double record = getInfoValue(StatsInfo.RECORD.getName());

        if (record < this.matchScore) {
            this.loser.updateStats(StatsInfo.RECORD.getName(), this.matchScore);
        }
    }

    private Double getInfoValue(final String name) {
        return this.loser.getStatistics().getOrDefault(name, DEFAULT_VAL);
    }

}
