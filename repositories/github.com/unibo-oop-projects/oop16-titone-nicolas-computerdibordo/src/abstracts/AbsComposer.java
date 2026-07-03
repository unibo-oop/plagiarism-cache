package abstracts;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import message.ControlPanelDataMessage;
import message.InitMessage;
import message.ServiceStructureMessage;
import message.ServiceStructureUpdateMessage;

/**
 * This class represent an abstract implementation of a {@link Composer}. It provides methods to manage services.
 * @see Composer
 * @see UntypedActor
 */
public abstract class AbsComposer extends UntypedActor implements Composer{

	/**
	 * The ActorSystem used by the Composer to run services.
	 */
	private final ActorSystem system = ActorSystem.create("secondSystem");
	
	/**
	 * A map of services reference and a boolean representing the initialization with the composer (if false is not initialized).
	 */
	private final Map<ActorRef, Boolean> activeServices = new HashMap<>();
	
	public AbsComposer() {
		this.activeServices.put(this.getSelf(), true);
		this.init();
	}
	
	/**
	 * This method specifies the Composer behavior for different types of messages.
	 */
	@Override
	public void onReceive(Object message) throws Throwable {
		
		if(message instanceof InitMessage) {
			this.initMessageReceived((InitMessage)message);
		}
		
		if(message instanceof ServiceStructureMessage) {
			this.serviceStructureMessageReceived((ServiceStructureMessage)message);
		}
		
		if(message instanceof ServiceStructureUpdateMessage) {
			this.serviceStructureUpdateMessageReceived((ServiceStructureUpdateMessage)message);
		}
		
		if(message instanceof ControlPanelDataMessage) {
			this.controlPanelDataMessageReceived((ControlPanelDataMessage)message);
		}
	}
	
	/**
	 * This method is used to run services or doing something when this composer is created.
	 */
	protected abstract void init();
	
	/**
	 * This method is used to get a collection of the initialized services. Initialized means the {@link #initialize(ActorRef)}
	 * has been correctly invoked on a present service.
	 * @return a collection of the initialized services.
	 */
	protected Collection<ActorRef> getActiveServices() {
		return Collections.unmodifiableCollection(this.activeServices.entrySet().stream()
																	 .filter((x) -> x.getValue())
																	 .map((x) -> x.getKey())
																	 .collect(Collectors.toList()));
	}

	/**
	 * This method is used to run a service and add it in {@link #activeServices} if correctly run.
	 * The added service is not initialized. Initialized means that  {@link #getActiveServices()} doesn't return
	 * the service ActorRef in the result when called.
	 * @param service the ActorRef of the service you want to run.
	 */
	protected ActorRef run(Props service, String name) {
		try {
			ActorRef s = this.system.actorOf(service, name);
			this.activeServices.put(s, false);
			return s;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * This method initialize a service if present. Present means that {@link #isPresent(ActorRef)} return true
	 * if you pass as parameter the ActorRef of the service. 
	 */
	protected void initialize(ActorRef service) {
		if(this.isPresent(service)) {
			this.activeServices.put(service, true);
		}
	}
	
	/**
	 * This method is used to know if a service ActorRef is present in {@link #activeServices}
	 * @param service the ActorRef of the service you want to look for.
	 * @return true if the service is present, otherwise false.
	 */
	protected boolean isPresent(ActorRef service) {
		return this.activeServices.containsKey(service);
	}
	
	/**
	 * This method is used to stop a service and remove it from {@link #activeServices} if present.
	 * Present means that {@link #isPresent(ActorRef)} return true if you pass as parameter the ActorRef of the service. 
	 * @param service the ActorRef of the service you want to stop.
	 */
	protected void stop(ActorRef service) {
		if(this.activeServices.containsKey(service)) {
			this.system.stop(service);
			this.activeServices.remove(service);
		}
	}
}
