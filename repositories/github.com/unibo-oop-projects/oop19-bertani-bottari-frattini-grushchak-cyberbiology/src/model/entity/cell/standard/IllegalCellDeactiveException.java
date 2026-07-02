package model.entity.cell.standard;
/**
 * 
 * when u call a standard cell for make some action and the cell is dead.
 *
 */
public class IllegalCellDeactiveException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constructs an instance of this class.
     * @param string 
     */
    public IllegalCellDeactiveException(final String string) {
        super(string);
    }

}
