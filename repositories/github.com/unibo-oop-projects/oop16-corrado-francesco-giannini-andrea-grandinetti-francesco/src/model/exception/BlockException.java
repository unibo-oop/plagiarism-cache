package model.exception;
import utility.Position;
/**
 * 
 *
 */
public class BlockException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final Position pos;
    /**
     * 
     * @param pos position.
     */
    public BlockException(final Position pos) {
        super();
        this.pos = pos;
    }
    /**
     * 
     * @return pos position
     */
    public Position getPos() {
        return this.pos;
    }

}
