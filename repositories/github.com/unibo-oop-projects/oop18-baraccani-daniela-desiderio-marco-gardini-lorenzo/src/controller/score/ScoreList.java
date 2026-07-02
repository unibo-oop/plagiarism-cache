package controller.score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Data structure for ordering the score based on the score in descending order.
 */
public class ScoreList {
    private final int maxSize;
    private final List<Score> priorityList;
    private final Comparator<? super Score> comparator;
    private boolean sorted;

    /**
     * Constructor.
     * @param maxSize max size of the list
     * @param comparator for sorting the list
     */
    public ScoreList(final int maxSize, final Comparator<? super Score> comparator) {
        this.maxSize = maxSize;
        this.sorted = false;
        this.comparator = Objects.requireNonNull(comparator);
        this.priorityList = new ArrayList<>(maxSize / 2);
    }

    /**
     * Add one score to the structure.
     * @param score to be added
     */
    public void add(final Score score) {
        this.priorityList.add(Objects.requireNonNull(score));
        this.priorityList.sort(this.comparator);
        this.sorted = false;

        for (int listSize = this.priorityList.size(); listSize > this.maxSize; listSize--) {
            this.priorityList.remove(listSize - 1);
        }
    }

    /**
     * Sort and return the score list.
     * @return the sorted list of scores
     */
    public List<Score> getList() {
        if (!this.sorted && this.priorityList.size() > 2) {
            this.sorted = true;
            this.priorityList.sort(this.comparator);
        }
        return Collections.unmodifiableList(this.priorityList);
    }
    /**
     * Clear the list.
     */
    public void clear() {
        this.priorityList.clear();
    }

    /**
     * Check if the list is empty.
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return this.priorityList.size() == 0;
    }
}
