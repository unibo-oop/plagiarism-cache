package model.match;

import java.util.Arrays;
import model.util.Pair;

/**
 * 
 * Exception representing situation where cell is already shot.
 * 
 */
public class CellAlreadyShotException extends CellsAlreadyUsedException {

    /**
     * 
     */
    private static final long serialVersionUID = 6085552272590623167L;

    public CellAlreadyShotException(final Pair<Integer, Integer> cellUsed) {
        super(Arrays.asList(cellUsed));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Cell already shot: [" + this.getCellsUsed().get(0).getX() + ", " 
                + this.getCellsUsed().get(0).getX() + "]" + super.toString(); 
    }

}
