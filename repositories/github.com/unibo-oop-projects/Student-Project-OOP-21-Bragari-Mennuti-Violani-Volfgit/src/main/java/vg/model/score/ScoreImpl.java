package vg.model.score;

public class ScoreImpl implements Score {

    private int score;
    private int round;
    private String name;

    public ScoreImpl(final String name, final int score, final int round) {
        this.round = round;
        this.score = score;
        this.name = name;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int getRound() {
        return this.round;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "ScoreImpl{" +
                "score=" + score +
                ", round=" + round +
                ", name='" + name + '\'' +
                '}';
    }
}
