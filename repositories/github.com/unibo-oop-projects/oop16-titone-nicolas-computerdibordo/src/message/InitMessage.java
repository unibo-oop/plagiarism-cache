package message;

import akka.actor.ActorRef;

/**
 * This interface represent a message sent by services and composer to do the first initialization.
 * For more information about initialization look {@link AbsService}.
 * @see AbsService
 * @see Message
 * @see ActorRef
 */
public interface InitMessage extends Message{
	
	/**
	 * This method is used to get the reference took by this message.
	 * @return an {@link ActorRef} Object.
	 */
	public ActorRef getReference();
}