package message;

import components.ComponentRef;
import components.SerializedComponent;

/**
 * This class represents a message typically sent by a service to the composer if it wants to change values of a rendered component.
 * It contains a Object value and a reference to the component to be changed.
 * @see SerializedComponent
 * @see ComponentRef
 * @see ServiceStructureUpdateMessage
 */
public class StructureUpdateMessage extends ServiceStructureUpdateMessage{

	public StructureUpdateMessage(SerializedComponent value, ComponentRef ref) {
		super(value, ref);
	}
}
