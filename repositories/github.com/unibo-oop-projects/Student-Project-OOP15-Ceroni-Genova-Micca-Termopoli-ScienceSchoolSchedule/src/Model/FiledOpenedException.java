package Model;

/**
 * This class implements the exception belonging to the opening of the wrong
 * file
 * 
 * @author Francesco Ceroni
 * 
 */

public class FiledOpenedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -6742684089793536307L;

    /**
     * 
     * @return the messsage of this exception
     */

    public final String getMessage() {
        return "Attention, the file is already open!";
    }

}
