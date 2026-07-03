package message;

import jsons.JsonModel;

/**
 * This class represent a typed message usually sent by a service to the Composer with the complete service structure of the user interface.
 * It provides a method to get directly the value of the message as {@link JsonModel} object.
 */
public class ServiceStructureMessage extends MessageImpl{

	public ServiceStructureMessage(JsonModel value) {
		super(value);
	}
	
	/**
	 * This method can be used to get the value of this message as a a {@link JsonModel} object.
	 * @return the value contained by this message a {@link JsonModel} object.
	 */
	public JsonModel getValueAsJsonModel() {
		return (JsonModel)this.getValue();
	}
}
