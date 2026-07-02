package javarogue.utility;

/**
 * 
 * Functional Interface that can be used with lambda-expressions. Provides an
 * implementation of the Command design pattern. Operates on integers i and j,
 * supplied by the receiver.
 *
 *
 */
public interface MatrixCommand {

	/**
	 *  Executes passed code sequence. 
	 * @param i Current row index supplied by the receiver.
	 * @param j Current column index supplied by the receiver
	 */
	public void execute(int i, int j);

}
