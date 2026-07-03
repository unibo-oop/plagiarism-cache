package message;

import akka.actor.ActorRef;

/**
 * This class represent a basic implementation of {@link InitMessage}.
 * @see InitMessage
 */
public class InitMessageImpl extends MessageImpl implements InitMessage{

	public InitMessageImpl(ActorRef value) {
		super(value);
	}
	
	/**
	 * {@inheritDoc TimeInitMessage#getReference()}
	 */
	public ActorRef getReference() {
		return (ActorRef)this.getValue();
	}
}
