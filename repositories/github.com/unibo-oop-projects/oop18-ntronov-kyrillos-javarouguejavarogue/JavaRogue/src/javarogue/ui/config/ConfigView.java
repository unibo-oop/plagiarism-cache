package javarogue.ui.config;

/**
 * 
 * View of the Configuration Window, handles configuration options selection.
 *
 */
public interface ConfigView {

	/**
	 * Links Controller.
	 * 
	 * @param controller
	 */
	public void setController(ConfigController controller);

	/**
	 * Shows the configuration UI.
	 */
	public void open();

	/**
	 * Closes the configuration UI.
	 */
	public void close();

}
