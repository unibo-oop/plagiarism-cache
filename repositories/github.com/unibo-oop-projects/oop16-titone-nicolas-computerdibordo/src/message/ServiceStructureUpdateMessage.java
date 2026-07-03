package message;

import components.ComponentRef;

/**
 * This class represent a typed message usually sent by a service to manipulate a specific {@link TComponent} owned
 * referenced by its {@link ComponentRef}.
 * @see MessageImpl
 * @see ComponentRef
 */
public class ServiceStructureUpdateMessage extends MessageImpl{
	
	/**
	 * The reference of the component to be manipulated.
	 */
	private final ComponentRef ref;
	
	public ServiceStructureUpdateMessage(Object value, ComponentRef ref) {
		super(value);
		this.ref = ref;
	}
	
	/**
	 * This is a getter method for the component reference.
	 * @return a {@link ComponentRef} object representing the component.
	 */
	public ComponentRef getRef() {
		return this.ref;
	}
}
