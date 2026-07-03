package components;

/**
 * This interface provides a single method to set a settable value computing a {@link Object} newValue received.
 */
public interface TSettable {
	
	/**
	 * This method set a value computing a {@link Object} newValue received.
	 * @param newValue the new value
	 */
	public void setValue(Object newValue);
}