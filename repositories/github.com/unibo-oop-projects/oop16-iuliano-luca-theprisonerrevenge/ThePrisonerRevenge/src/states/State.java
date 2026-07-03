package states;

import java.awt.Graphics2D;

import model.KeyDetector;

/**
 * This interface extend {@link KeyDetector}. It represent the STATE in the
 * STATE PATTERN. It include the methods to update the elements add them on
 * Graphics2D and the methods to accomplish user request intercepted.
 * 
 * @author Luca
 *
 */
public interface State extends KeyDetector {
	/**
	 * Call the relative methods to update the state of this State.
	 */
	void update();

	/**
	 * This method add elements ready to be print in Graphics2D.
	 * 
	 * @param g
	 *            a Graphics2D where print the graphic resource present on this
	 *            StateEnum.
	 */
	void print(Graphics2D g);
}
