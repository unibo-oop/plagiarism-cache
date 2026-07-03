package components;

/**
 * This enumeration values are all the possible renderable component types. It provides a method to get a String representation
 * of the component type.
 *
 */
public enum TComponentToString {
	
	TButton("tbutton"), TPanel("tpanel"), TFrame("tframe"), TLabel("tlabel");
	
	/**
	 * a String representation of the components
	 */
	private String type;
	
	private TComponentToString(String s) {
		this.type = s;
	}
	
	/**
	 * This method is used to get a String representation of the type of the component represented by this enum.
	 * @return a String representing the component type
	 */
	public String getType() {
		return this.type;
	}
	
}
