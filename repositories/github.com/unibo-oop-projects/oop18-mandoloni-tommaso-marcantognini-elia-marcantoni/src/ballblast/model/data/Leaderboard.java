package ballblast.model.data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * Keeps track of users {@link RecordData}.
 */
public class Leaderboard {

    private static final int MAX_SCORES = 10;
    private static final Comparator<RecordData> COMPARATOR = (o1, o2) -> o2.getScore() - o1.getScore();

    private List<RecordData> recordList;

    /**
     * Empty constructor to serialize the object in a xml file.
     */
//    public Leaderboard() {
//    }

    /**
     * Adds a {@link RecordData} if is greater than other on top 10 records.
     * 
     * @param name  the name of the user who submitted the record.
     * @param score the score reached at the end of the game session.
     */
    public void addRecord(final String name, final int score) {
        if (this.recordList.size() < MAX_SCORES || this.isRecord(score)) {
            final RecordData rec = new RecordData();
            rec.setName(name);
            rec.setScore(score);
            this.recordList.add(rec);
            if (this.recordList.size() > MAX_SCORES) {
                this.recordList.remove(this.recordList.stream().max(COMPARATOR).get());
            }
            this.recordList.sort(COMPARATOR);
        }
    }

    private Stream<RecordData> getRecords() {
        return this.recordList.stream();
    }

    /**
     * Checks if a record can be saved in the leaderboard.
     * 
     * @param score the score to verify.
     * @return true if the score is an high score.
     */
    private boolean isRecord(final int score) {
        return score > this.getRecords().max(COMPARATOR).get().getScore();
    }

    /**
     * Getter of the record list.
     * 
     * @return the {@link List} of {@link RecordData}.
     */
    public List<RecordData> getRecordList() {
        return recordList;
    }

    /**
     * Setter for the record list.
     * 
     * @param records the {@link List} of {@link RecordData} to set.
     */
    public void setRecordList(final List<RecordData> records) {
        this.recordList = records;
    }

    /**
     * Initialize the record list with empty {@link RecordData}s.
     * 
     * @return the empty record list.
     */
    public List<RecordData> initList() {
        final List<RecordData> list = Lists.newArrayList();
        for (int i = 1; i <= MAX_SCORES; i++) {
            final RecordData rec = new RecordData();
            rec.setName("---");
            rec.setScore(0);
            list.add(rec);
        }
        return list;
    }

}
