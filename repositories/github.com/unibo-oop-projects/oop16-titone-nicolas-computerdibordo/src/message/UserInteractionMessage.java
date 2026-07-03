package message;

/**
 * This class represents a message typically sent by the renderer to signal a user interaction event to a service.
 * It contains the String value of the {@link TComponent} related to the user interaction catched.
 */
public class UserInteractionMessage extends MessageImpl{
	
	
	public UserInteractionMessage(String id) {
		super(id);
	}
	
	/**
	 * This method return the information value of this message as a {@String}.
	 * @return A string representing the information.
	 */
	public String getValueAsStringId() {
		return (String)this.getValue();
	}
}
