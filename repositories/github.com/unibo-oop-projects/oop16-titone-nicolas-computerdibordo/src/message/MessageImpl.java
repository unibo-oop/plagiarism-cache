package message;

/**
 * This class is a simple implementation of a {@link Message} that gives a {@link Object} information by the method implemented.
 *@see Message
 */
public class MessageImpl implements Message{
	/**
	 * the value of the information given by this message. It can't be modified.
	 */
	private final Object value;
	
	/**
	 * This constructor is used to set the information when instantiate. It can't be possible to set the {@link value} again.
	 * @param value the information you want to put inside this Message
	 */
	public MessageImpl(Object value) {
		this.value = value;
	}
	
	/**
	 * {@inheritDoc TimeMessage#getValue()}
	 */
	public Object getValue() {
		return this.value;
	}
}
