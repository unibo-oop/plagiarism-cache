package it.unibo.oop.model;

/**
 * Interface for a a class managing the record score.
 */
public interface Record {

    /**
     * @return current record value.
     */
    Score getValue();

    /**
     * @param value
     *            of the record.
     */
    void setValue(Score value);

    /**
     * Reset player's record-score to minimum {@link Score} value.
     */
    void reset();

    /**
     * @param value
     *            value of new record.
     */
    void setRecord(Score value);

    /**
     * @return true if player has made a new record.
     */
    boolean isRecord();

}