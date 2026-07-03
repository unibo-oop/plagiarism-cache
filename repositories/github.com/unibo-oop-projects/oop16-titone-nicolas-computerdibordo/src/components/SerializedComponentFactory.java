package components;

/**
 * This class provides static methods to get {@link SerializedComponent} instances of each component of {@link TSubSet}
 * with specific values passed as parameters.
 * @see TSubSet
 * @see SerializedComponent
 */
public final class SerializedComponentFactory {
	
	/**
	 * Method to get a {@link SerializedComponent} of a {@link TButton}
	 * @param id
	 * @param text
	 * @param creator
	 * @return {@link SerializedComponent} representing the specified {@link TComponent}.
	 */
	public static SerializedComponent tButton(String id, String position, String text, String creator) {
		
		return new SerializedComponent(id, TComponentToString.TButton.getType(), position, 0, 0, text, null, creator);
	}
	
	/**
	 * Method to get a {@link SerializedComponent} of a {@link TPanel}
	 * @param id The id of the component as {@link String}
	 * @param position
	 * @param width
	 * @param height
	 * @param children
	 * @param creator
	 * @return {@link SerializedComponent} representing the specified {@link TComponent}.
	 */
	public static SerializedComponent tPanel(String id, String position, int width, 
												int height, SerializedComponent[] children, String creator) {
		
		return new SerializedComponent(id, TComponentToString.TPanel.getType(), position, width, height, null, children, creator);
	}
	
	/**
	 * Method to get a {@link SerializedComponent} of a {@link TFrame}
	 * @param id The id of the component as {@link String}
	 * @param width
	 * @param height
	 * @param children
	 * @param creator
	 * @return a {@link SerializedComponent} representing the specified {@link TComponent}.
	 */
	public static SerializedComponent tFrame(String id, int width, int height, SerializedComponent[] children,
												String creator) {
		
		return new SerializedComponent(id, TComponentToString.TFrame.getType(), null, width, height, null, children, creator);
	}
	
	/**
	 * Method to get a {@link SerializedComponent} of a {@link TLabel}
	 * @param id The id of the component as {@link String}
	 * @param text
	 * @param creator
	 * @return {@link SerializedComponent} representing the specified {@link TComponent}.
	 */
	public static SerializedComponent tLabel(String id, String position, String text, String creator) {
		
		return new SerializedComponent(id, TComponentToString.TLabel.getType(), position, 0, 0, text, null, creator);
	}
}
