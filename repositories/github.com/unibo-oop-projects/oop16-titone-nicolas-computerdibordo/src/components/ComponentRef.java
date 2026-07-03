package components;

/**
 * This class is a {@link TComponent} reference that identify a single component by its id, creator and type.
 */
public class ComponentRef {
	
	/**
	 * The id of the component
	 */
	private final String id;
	
	/**
	 * String representation of the {@link ActorRef} of the creator of the component
	 */
	private final String creator;
	
	/**
	 * String representation of the type of the component
	 */
	private final String type;
	
	public ComponentRef(String id, String creator, String type) {
		this.id = id;
		this.creator = creator;
		this.type = type;
	}
	
	/**
	 * Getter for the id
	 * @return the id of the component
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Getter for the creator
	 * @return a String representation of the creator
	 */
	public String getCreator() {
		return this.creator;
	}
	
	/**
	 * Getter for the type
	 * @return a String representation of the type of the component
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * This method implementation is based on a method found on GitHub.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + 
			((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result + 
			((this.creator == null) ? 0 : this.creator.hashCode());
		result = prime * result + 
				((this.type == null) ? 0 : this.type.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ComponentRef) {
			ComponentRef r = (ComponentRef)o;
			if(this.getId().equals(r.getId()) 
					&& this.getCreator().equals(r.getCreator()) 
					&& this.getType().equals(r.getType())) {
				return true;
			}
		}
		return false;
	}
}
