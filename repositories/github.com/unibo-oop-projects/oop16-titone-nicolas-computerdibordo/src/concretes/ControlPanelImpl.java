package concretes;

import abstracts.AbsServiceWithUI;
import abstracts.ControlPanel;
import abstracts.ControlPanelDataSet;
import components.ComponentRef;
import components.SerializedComponent;
import components.SerializedComponentFactory;
import components.TComponentToString;
import main.Main;
import message.ControlPanelDataMessage;
import message.InitMessage;
import message.ServiceStructureUpdateMessage;

/**
 * This class represents a concrete implementation of a control panel service; it implements methods of {@link Composer}.
 * This control panel computes {@link ControlPanelDataSet) objects to update the values to be shown in the user interface.
 */
public class ControlPanelImpl extends AbsServiceWithUI implements ControlPanel{
	
	private int speed = 0;
	private final static String speedId = "speed";
	private ComponentRef labelRef;
	
	
	public ControlPanelImpl() {
		super();
		this.labelRef = new ComponentRef(speedId, this.getSelf().toString(), TComponentToString.TLabel.getType());
		this.updateStructure();
		Main.log("ControlPanel: started.");
	}
	
	@Override
	public void onReceive(Object message) throws Throwable {
		
		super.onReceive(message);
		
		if(message instanceof InitMessage) {
			InitMessage msg = (InitMessage)message;
			this.initMessageReceived(msg);
		}
		
		if(message instanceof ControlPanelDataMessage) {
			ControlPanelDataMessage msg = ((ControlPanelDataMessage)message);
			this.controlPanelDataMessageReceived(msg);
		}
	}
	
	@Override
	public void initMessageReceived(InitMessage message) {
		this.updateStructure();
		this.tellStructureToComposer();
		Main.log("ControlPanel: InitMessage received and computed; service structure told to Composer.");
	}
	
	@Override
	public void controlPanelDataMessageReceived(ControlPanelDataMessage msg) {
		if(this.isInitialized()) {
			ControlPanelDataSet s = msg.getValueAsDataSet();
			this.updateValues(s);
			this.updateStructure();
			this.tellLabelUpdateToComposer(String.valueOf(this.speed));
			Main.log("ControlPanel: DataMessage received and computed; values and structure updated and told to Composer.");
		}
	}
	
	/**
	 * Setter for speed value.
	 * @param speed the new speed value to set.
	 */
	private void setSpeed(int speed) {
		this.speed = speed;
	}
	
	private void updateStructure() {
		SerializedComponent label = SerializedComponentFactory.tLabel(speedId, null, String.valueOf(this.speed), this.getSelf().toString());
		SerializedComponent panel = SerializedComponentFactory
										.tPanel("panel", null, 300, 300, new SerializedComponent[]{label}, this.getSelf().toString());
		this.setStructure(panel);
	}
	
	/**
	 * This method is used to tell an update of values to the composer.
	 * @param value the value to be computed for the update.
	 * @see components.TSettable
	 */
	private void tellLabelUpdateToComposer(Object value) {
		if(this.isInitialized()) {
			this.tellComposer(new ServiceStructureUpdateMessage(value, this.labelRef));
		}
	}
	
	@Override
	public void updateValues(ControlPanelDataSet msg) {
		this.setSpeed(msg.getSpeed());
		this.updateStructure();
	}
}
