package abstracts;

import message.ServiceStructureUpdateMessage;
import message.StructureMessage;
import message.StructureRepaintMessage;

/**
 * Each method of this interface specifies the Renderer behavior for different classes of message.
 */
public interface Renderer {
	
	public void structureMessageReceived(StructureMessage message);
	
	public void serviceStructureUpdateMessageReceived(ServiceStructureUpdateMessage message);
	
	public void structureRepaintMessageReceived(StructureRepaintMessage message);
}
