package abstracts;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import message.InitMessage;
import message.InitMessageImpl;

import java.util.Objects;

/**
 * This abstract class represent a simple service of the HMI system.
 * A simple service can tell messages to itself and, after being initialized, to the composer.
 * Initialization starts when a {@link InitMessageImpl} is received and {@link #onReceive(Object message) onReceive} is called.
 * To implement this class is necessary to look at {@link UntypedActor}
 * @see UntypedActor 
 * @see ActorRef 
 * @see InitMessageImpl
 */
public abstract class AbsService extends UntypedActor{
	
	/**
	 * the reference to the composer
	 */
	private ActorRef composer;
	
	/**
	 * the constructor without parameters is the only permitted; it set the {@link #composer} to null.
	 */
	public AbsService() {
		super();
		this.composer = null;
	}
	
	
	/**
	 * This method implements the {@link #composer} initialization and represent the behaviors of this service for messages.
	 * @param message The message received as a {@link Object}.
	 * @see InitMessageImpl
	 */
	@Override
	public void onReceive(Object message) throws Throwable {	
		if(!this.isInitialized() && message instanceof InitMessage) {
			this.setComposer(((InitMessage)message).getReference());
			this.tellComposer(new InitMessageImpl(this.getSelf()));
		}
	}
	
	/**
	 * This method is used by the service to send an {@link Object} message to itself.
	 * @param message the message to send.
	 */
	protected void selfTell(Object message) {
		this.getSelf().tell(message, this.getSelf());
	}
	
	/**
	 * Method used to set the Composer reference. It can be done one once.
	 * @param composer the {@link ActorRef} value of the composer.
	 */
	private void setComposer(ActorRef composer) {
		if(!this.isInitialized()) {
			this.composer = composer;
		}
	}
	
	/**
	 * This method is used to send message to the Composer if {@link #composer} is set, looking at it using {@link #isInitialized() isInitialized}.
	 * @param message the message to send.
	 */
	protected void tellComposer(Object message) {
		if(this.isInitialized()) {
			this.composer.tell(message, this.getSelf());
		}
	}
	
	/**
	 * This method compares the given reference with {@link #composer}.
	 * @param reference the reference to be compared with.
	 * @return true if comparison succeed and false if it doesen't.
	 */
	protected boolean isComposer(ActorRef reference) {
		return reference.equals(this.composer);
	}
	
	
	/**
	 * This method must be used to know if {@link #composer} is set.
	 * @return true if {@link composer.concretes}, otherwise false.
	 */
	protected boolean isInitialized() {
		return Objects.nonNull(this.composer);
	}
}
