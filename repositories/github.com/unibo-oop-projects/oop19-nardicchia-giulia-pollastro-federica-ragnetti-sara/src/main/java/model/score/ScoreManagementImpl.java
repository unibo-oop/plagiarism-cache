package model.score;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This class implements {@link ScoreManagment}.
 *
 */
public class ScoreManagementImpl implements ScoreManagement {

    /**
     * The weigh of the new score gained.
     */
    private static final double ALPHA = 0.125;

    /**
     * The max number of dates allowed.
     */
    private static final int HISTORY_SIZE = 7;

    private Map<String, Integer> miniGameHistory;
    private int counter;
    private int sum;
    private int average;
    private int best;

    /**
     * Constructor for {@link ScoreManagement}.
     */
    public ScoreManagementImpl() {
        this.miniGameHistory = new HashMap<>();
    }

    private String createDate() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.ITALY);
        return dateFormat.format(new Date());
    }

    private void checkBest(final int score) {
        if (score > this.best) {
            this.best = score;
        }
    }

    private void updateAverage(final int score) {
        this.average = this.average == 0 ? score : (int) ((1 - ALPHA) * this.average + ALPHA * score);
    }

    private int computeDailyAverage() {
        return (int) this.sum / this.counter;
    }

    private void checkFullHistory() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.ITALY);

        while (this.miniGameHistory.size() >= HISTORY_SIZE) {

            this.miniGameHistory.remove(this.miniGameHistory.keySet().stream().min((o1, o2) -> {
                try {
                    return dateFormat.parse(o1).compareTo(dateFormat.parse(o2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }).get());
        }
    }

    private void addScore(final int score) {
        final String date = this.createDate();

        if (!this.miniGameHistory.containsKey(date)) {
            this.sum = score;
            this.counter = 1;
            this.checkFullHistory();
        } else {
            this.sum = this.sum + score;
            this.counter = this.counter + 1;
        }
        this.miniGameHistory.put(date, this.computeDailyAverage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> getMiniGameHistory() {
        return this.miniGameHistory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMiniGameHistory(final Map<String, Integer> miniGameHistory) {
        this.miniGameHistory = miniGameHistory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAverage() {
        return this.average;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAverage(final int average) {
        this.average = average;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBest() {
        return this.best;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBest(final int best) {
        this.best = best;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(final int counter) {
        this.counter = counter;
    }

    /**
     * @return the sum
     */
    public int getSum() {
        return this.sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(final int sum) {
        this.sum = sum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScore(final int score) {
        this.addScore(score);
        this.checkBest(score);
        this.updateAverage(score);
    }
}
