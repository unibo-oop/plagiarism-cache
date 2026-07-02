package model.stats;

import model.enums.StatsInfo;
import model.players.Player;

/**
 * The class calculates all the values related to the winner player statistics.
 *
 */
public class WinnerStatsCalculator implements Statistics {

    private static final double DEFAULT_VAL = 0.00;

    private Player winner;
    private double matchScore;

    public WinnerStatsCalculator(final Player winner, final double score) {
        this.winner = winner;
        this.matchScore = score;
    }

    @Override
    public final void basicStats() {
        final double tot = getInfoValue(StatsInfo.TOTALS.getName()) + 1;
        final double wins = getInfoValue(StatsInfo.WINS.getName()) + 1;
        final double loss = getInfoValue(StatsInfo.LOSS.getName());

        bestScoreCheck();

        this.winner.updateStats(StatsInfo.TOTALS.getName(), tot);
        this.winner.updateStats(StatsInfo.WINS.getName(), wins); 
        this.winner.updateStats(StatsInfo.WINS_RATE.getName(), (wins / tot) * 100);
        this.winner.updateStats(StatsInfo.LOSS_RATE.getName(), (loss / tot) * 100);
    }

    private void bestScoreCheck() {
        double record = getInfoValue(StatsInfo.RECORD.getName());

        if (record < this.matchScore) {
            this.winner.updateStats(StatsInfo.RECORD.getName(), this.matchScore);
        }
    }

    private Double getInfoValue(final String name) {
        return this.winner.getStatistics().getOrDefault(name, DEFAULT_VAL);
    }

}
