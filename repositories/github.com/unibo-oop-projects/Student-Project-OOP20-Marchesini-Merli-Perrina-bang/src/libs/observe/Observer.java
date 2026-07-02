package libs.observe;

/**
 * An interface implementing an observer of elements.
 * 
 * @author Mattia Marchesini
 *
 */
@FunctionalInterface
public interface Observer {

	/**
	 * Executes actions after being notifyed by an observable.
	 */
	void update();
}
