package message;

import java.util.Arrays;
import java.util.Collection;

import akka.actor.ActorRef;
import jsons.JsonModel;

/**
 * This class represent a message usually sent by the Composer to the Renderer. It represent the user interface structure to be rendered.
 * It extends the concept of {@link ServiceStructureMessage} adding {@link #creators}. Creators are the owner {@link ActorRef} references
 * of the components in structure.
 */
public class StructureMessage extends ServiceStructureMessage{
	
	/**
	 * An array of ActorRef representing components owners.
	 */
	private ActorRef[] creators;

	public StructureMessage(JsonModel value, ActorRef[] creators) {
		super(value);
		this.creators = creators;
	}
	
	/**
	 * Method used to get the creators as collection.
	 * @see StructureMessage
	 * @return a collection of ActorRef.
	 */
	public Collection<ActorRef> getCreators() {
		return Arrays.asList(this.creators);
	}
}
