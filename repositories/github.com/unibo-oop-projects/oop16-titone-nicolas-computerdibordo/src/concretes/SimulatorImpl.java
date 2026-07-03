package concretes;

import abstracts.AbsServiceWithUI;
import abstracts.Simulator;
import components.SerializedComponent;
import components.SerializedComponentFactory;
import jsons.JsonModelImpl;
import main.Main;
import message.ControlPanelDataMessage;
import message.InitMessage;
import message.ServiceStructureMessage;
import message.UserInteractionMessage;

/**
 * This class represent a service that simulate and send data sets to be computed by the Control Panel Service.
 * You can press the button "SIMULATE" in the user interface to do a simulation, if rendered.
 *@see AbsServiceWithUI
 */
public class SimulatorImpl extends AbsServiceWithUI implements Simulator{
	
	/**
	 * This field represents the button id of the simulator to identify the button of the structure
	 */
	private final static String BUTTON_ID = "simulator";
	
	/**
	 * This field represents the panel id of the simulator to identify the panel of the structure
	 */
	private final static String PANEL_ID = "buttPanel";
	
	/**
	 * This field represent the text to be shown on the user interface of the Simulator
	 */
	private final static String BUTTON_TEXT = "SIMULATE";
	
	/**
	 * This field represent the current speed
	 */
	private int speed = 0;
	
	public SimulatorImpl() {
		super();
		this.init();
		Main.log("Simulator: started. Id: "+this.getSelf().toString());
	}
	
	/**
	 * @see #initMessageReceived(InitMessage)
	 * @see #userInteractionMessageReceived(UserInteractionMessage)
	 */
	@Override
	public void onReceive(Object message) throws Throwable {
		
		super.onReceive(message);
		
		if(message instanceof InitMessage){
			InitMessage msg = (InitMessage)message;
			this.initMessageReceived(msg);
		}
		
		if(message instanceof UserInteractionMessage) {
			UserInteractionMessage msg = (UserInteractionMessage)message;
			this.userInteractionMessageReceived(msg);
		}
	}
	
	@Override
	public void userInteractionMessageReceived(UserInteractionMessage msg) {
		if(msg.getValueAsStringId().equals(BUTTON_ID)) {
			this.speed = this.speed + 1;
			this.tellComposer(new ControlPanelDataMessage(new ControlPanelDataSetImpl(this.speed)));
			Main.log("Simulator: UserInteractionMessage received and computed; ControlPanelDataSet simulated and told to Composer.");
		}
	}
	
	@Override
	public void initMessageReceived(InitMessage msg) {
		if(this.isInitialized() && this.isStructureInitialized()) {
			this.tellComposer(new ServiceStructureMessage(JsonModelImpl.of(this.getStructure())));
			Main.log("Simulator: InitMessage received and computed; service structure told to Composer.");
		}	
	}
	
	/**
	 * This method is used when a new {@link SimulatorImpl} instance is created to initialize the service structure.
	 */
	private void init() {
		SerializedComponent butt = SerializedComponentFactory.tButton(BUTTON_ID, null, BUTTON_TEXT, this.getSelf().toString());
		SerializedComponent buttPanel = SerializedComponentFactory.tPanel(PANEL_ID, null, 300, 300, new SerializedComponent[]{butt}, this.getSelf().toString());
		this.setStructure(buttPanel);
	}

}
