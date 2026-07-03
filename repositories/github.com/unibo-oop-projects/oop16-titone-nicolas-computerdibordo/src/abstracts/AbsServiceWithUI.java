package abstracts;

import java.util.Objects;

import components.SerializedComponent;
import jsons.JsonModelImpl;
import message.ServiceStructureMessage;

/**
 * This abstract class extends the concept of {@link AbsService}. It represent a service with a user interface 
 * represented by a {@link SerializedComponent}.
 * To implement this class is necessary to look at {@link AbsService}.
 * @see AbsService
 * @see SerializedComponent
 */
public abstract class AbsServiceWithUI extends AbsService{
	
	/**
	 * The user interface structure of this service. Must be initialized in the implementation of this class.
	 * @see #onReceive(Object)
	 */
	private SerializedComponent structure;
	
	public AbsServiceWithUI() {
		super();
		this.structure = null;
	}
	
	/**
	 * This method extends {@link abstract.service.TAbsService#onReceive(Object)}.
	 * In this method is implemented the {@link #structure} initialization.
	 * This class provides the method {@link #isStructureInitialized() isStructureInitialized} to verify the initialization.
	 * @see {@link #isStructureInitialized()}
	 */
	public void onReceive(Object message) throws Throwable {
		super.onReceive(message);
	}
	
	/**
	 * This method return the user interface structure of this service
	 * @return a {@link SerializedComponent} representing the user interface of this service
	 */
	protected SerializedComponent getStructure() {
		return this.isStructureInitialized() ? this.structure : null;
	}
	
	/**
	 * This method is used to update the structure of this service. Set {@link #structureInitialized} to true if it's not.
	 * @param newStructure the {@link SerializedComponent} to be used as user interface of this service.
	 */
	protected void setStructure(SerializedComponent newStructure) {
		this.structure = newStructure;
	}
	
	/**
	 * This method is used to tell the composer the current representation of the user interface of this service.
	 * If this service is initialized, using {@link #isInitialized()}, and {@link #structure} is initialized,
	 * send a {@link ServiceStructureMessage} to the composer.
	 * @see concretes.ComposerImpl
	 */
	protected void tellStructureToComposer() {
		if(this.isInitialized() && this.isStructureInitialized()) {
			this.tellComposer(new ServiceStructureMessage(JsonModelImpl.of(this.getStructure())));
		}
	}
	
	/**
	 * This method must be used to know if {@link #structure} is null.
	 * @return true if {@link structure} is set, otherwise false.
	 */
	protected boolean isStructureInitialized() {
		return Objects.nonNull(this.structure);
	}
}
