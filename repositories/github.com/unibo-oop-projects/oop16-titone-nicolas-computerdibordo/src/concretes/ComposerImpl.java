package concretes;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import abstracts.AbsComposer;
import abstracts.Composer;
import akka.actor.ActorRef;
import akka.actor.Props;
import components.SerializedComponent;
import jsons.JsonModelImpl;
import main.Main;
import message.ControlPanelDataMessage;
import message.InitMessage;
import message.InitMessageImpl;
import message.StructureRepaintMessage;
import message.ServiceStructureMessage;
import message.ServiceStructureUpdateMessage;
import message.StructureMessage;
import message.UserInteractionMessage;

/**
 * This class is a concrete implementation of {@link AbsComposer} and implements also methods of {@link Composer}.
 * This composer init a renderer and two services. The complete structure is send to the renderer 
 * only if complete (see {@link StructureBuilder#canBuild()}).
 * @see AbsComposer
 * @see RendererImpl
 * @see ControlPanelImpl
 * @see SimulatorImpl
 */
public class ComposerImpl extends AbsComposer implements Composer{
	
	/**
	 * This is a boolean value set "true" when the first structure is told to the renderer.
	 */
	boolean b = false;
	/**
	 * a constant representing the string id of the {@link TFrame} of the component user interface structure.
	 */
	private final static String ID_FR = "frame";
	
	/**
	 * a constant representing the string id of the TimePanel of the service1 in the user interface structure.
	 */
	private final static String ID_S1 = "service1";
	
	/**
	 * a constant representing the string id of the TimePanel of the service2 in the user interface structure.
	 */
	private final static String ID_S2 = "service2";
	
	/**
	 * a constant representing the string id of the TimePanel of the menu in the user interface structure.
	 */
	private final static String ID_MENU = "menu";
	
	/**
	 * a constant representing the string id of the TimeButton of the menu in the user interface structure.
	 */
	private final static String SIM_ID = "simulator";
	
	private Map<String, String> menubuttons;
	
	/**
	 * This is the reference to the renderer
	 */
	private ActorRef renderer;
	
	/**
	 * This is a reference to the first active service.
	 */
	private ActorRef s1;
	
	/**
	 * This is a reference to the second actor service.
	 */
	private ActorRef s2;
	
	/**
	 * This is the structure manager
	 */
	private StructureBuilder builder;
	
	public ComposerImpl() {
		super();
	}
	
	@Override
	public void onReceive(Object message) throws Throwable {
		super.onReceive(message);
		if(message instanceof UserInteractionMessage) {
			this.timeUserInteractionMessageReceived((UserInteractionMessage)message);
		}
	}
	
	@Override
	public void initMessageReceived(InitMessage message) {
		this.initialize(message.getReference());
		Main.log("Composer: InitMessage received, service initialized.");
	}

	/**
	 * This method is to be used when a {@link UserInteractionMessage} is received. It represents the event
	 * of button pressed on a button created by the composer.
	 * Behavior: stop the second service and starts the one associated with the button pressed.
	 * @param msg message to be computed
	 */
	public void timeUserInteractionMessageReceived(UserInteractionMessage msg) {
		if(msg.getValueAsStringId().equals(SIM_ID)) {
			this.stop(this.s2);
			this.builder.setService2Structure(null);
			this.s2 = this.run(Props.create(SimulatorImpl.class), IdGenerator.getInstance().getId());
			this.s2.tell(new InitMessageImpl(this.getSelf()), this.getSelf());
			Main.log("Composer: UserInteractionMessage received and computed; current service stopped, other service started.");
		}
	}
	
	@Override
	public void serviceStructureMessageReceived(ServiceStructureMessage message) {
		SerializedComponent s = message.getValueAsJsonModel().getDeserializedValue(SerializedComponent.class);
		this.updateStructure(s, this.getSender());
		if(this.builder.canBuild() && !b) {
			this.tellStructureToRenderer(this.builder.build());
			b = true;
		}
		if(b) {
			this.tellRepaintStructureToRenderer(this.builder.build());
		}
		
		Main.log("Composer: ServiceStructureMessageReceived and computed; updated structure told to Renderer.");
	}

	@Override
	public void serviceStructureUpdateMessageReceived(ServiceStructureUpdateMessage message) {
		this.renderer.tell(message, this.getSelf());
		Main.log("Composer: ServiceStructureUpdateMessage received and computed; message told to Renderer");
	}

	@Override
	public void controlPanelDataMessageReceived(ControlPanelDataMessage message) {
		this.s1.tell(message, this.getSelf());
		Main.log("Composer: ControlPanelMessage received and computed; message told to ControlPanel");
	}
	
	/**
	 * This method update the structure of the service changing it with the new structure s. If the service
	 * is not present (see {@link #isPresent(ActorRef)}) nothing is done.
	 * @param s the new {@link SerializedComponent} structure to change.
	 * @param service the {@link ActoRef} reference of the service whose structure has to be updated.
	 */
	private void updateStructure(SerializedComponent s, ActorRef service) {
		if(this.isPresent(service)) {
			if(service.equals(s1)) {
				builder.setService1Structure(s);
			} else if(service.equals(s2)) {
				builder.setService2Structure(s);
			}
		}
	}
	
	/**
	 * This method is used by the Composer to send the complete structure to the Renderer.
	 * @param structure the structure to render
	 */
	private void tellStructureToRenderer(SerializedComponent structure) {
		this.renderer.tell(new StructureMessage(JsonModelImpl.of(structure), 
													this.getActiveServices().toArray(new ActorRef[this.getActiveServices().size()])), 
													this.getSelf());
	}
	
	private void tellRepaintStructureToRenderer(SerializedComponent structure) {
		this.renderer.tell(new StructureRepaintMessage(JsonModelImpl.of(structure), 
				this.getActiveServices().toArray(new ActorRef[this.getActiveServices().size()])), 
				this.getSelf());
	}
	
	/**
	 * {@inheritDoc TimeAbsComposer#init()}
	 * This method is used by the {@link ComposerImpl} to run the renderer, other services and start the initialization with them all.
	 * @see AbsService
	 */
	@Override
	protected void init() {
		
		Main.log("Composer: started");
		//initialization of the builder
		this.menubuttons = new HashMap<>();
		this.menubuttons.put(SIM_ID, SIM_ID);
		this.builder = new StructureBuilder(this.getSelf(), ID_S1, ID_S2, ID_FR, ID_MENU, this.menubuttons);
		
		//run of services
		ActorRef r = this.run(Props.create(RendererImpl.class), "renderer");	
		ActorRef cp = this.run(Props.create(ControlPanelImpl.class), "controlPanel");	
		ActorRef ex = this.run(Props.create(SimulatorImpl.class), "datasimulator");
	
		//initialization
		InitMessageImpl m = new InitMessageImpl(this.getSelf());	
		this.renderer = r;
		this.s1 = cp;
		this.s2 = ex;
		cp.tell(m, this.getSelf());
		r.tell(m, this.getSelf());
		ex.tell(m, this.getSelf());
	}
	
	/**
	 * This is a private class used by the composer to provides new id for services.
	 *
	 */
	private static class IdGenerator {
		
		/**
		 * the singleton instance of IdGenerator
		 */
		private static IdGenerator generator;
		
		/**
		 * a special character used to provide ids.
		 */
		private String id = "a";
		
		/**
		 * Static method to access the single instance of the idGenerator
		 * @return
		 */
		public static IdGenerator getInstance() {
			
			if(Objects.isNull(generator)) {
				generator = new IdGenerator();
			}
			
			return generator;
		}
		
		/**
		 * This method is used to get a unic String Id.
		 * @return a unic String id.
		 */
		public String getId() {
			this.id+="a";
			return this.id;
		}
	}
}
