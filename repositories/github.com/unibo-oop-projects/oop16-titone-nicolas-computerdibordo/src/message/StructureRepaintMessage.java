package message;

import akka.actor.ActorRef;
import jsons.JsonModel;

/**
 * This class represent a typical message sent to the Renderer to repaint a specific part of the structure.
 */
public class StructureRepaintMessage extends StructureMessage{

	public StructureRepaintMessage(JsonModel value, ActorRef[] creators) {
		super(value, creators);
	}
	
	
}
