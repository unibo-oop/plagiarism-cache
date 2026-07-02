package utilities;

import utilitiesimpl.Row;

public interface Seat {
    /**
     * Return row.
     * @return row
     */
    Row getRow();
    /**
     * Return intenger of column.
     * @return column
     */
    Integer getColumn();
}
