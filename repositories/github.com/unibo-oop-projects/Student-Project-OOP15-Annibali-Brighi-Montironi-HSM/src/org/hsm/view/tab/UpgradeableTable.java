package org.hsm.view.tab;

/**
 * This interface contains the basic methods to operate on a table that can be
 * upgraded whithout change the table state.
 *
 * @param <X>
 *            the row identifier type
 */
public interface UpgradeableTable<X> extends Table<X> {

    /**
     * Insert the row in the table without change the state of the table.
     * 
     * @param row
     *            the row to insert
     */
    void updateRow(final Object... row);

}
