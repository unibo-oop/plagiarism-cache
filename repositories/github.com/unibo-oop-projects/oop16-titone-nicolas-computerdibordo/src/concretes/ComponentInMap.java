package concretes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import components.ComponentRef;
import components.TComponent;

/**
 * This class contains informations about a specific {@link TimeComponent). It provides methods to manage the component and a collection
 * of component references added to it.
 */
public class ComponentInMap {
	
	/**
	 * the TimeComponent
	 */
	private TComponent component;
	
	/**
	 * A list of children of this component
	 */
	private final List<ComponentRef> children = new ArrayList<>();
	
	public ComponentInMap() {
		this.component = null;
	}
	
	/**
	 * Setter for this class {@link TComponent} value
	 * @param component the component to be set
	 */
	public void setComponent(TComponent component) {
		this.component = component;
	}
	
	/**
	 * Getter for the component contained in this class
	 * @return a {@link TComponent}
	 */
	public TComponent getComponent() {
		return this.component;
	}
	
	/**
	 * Getter for the collection of children references
	 * @return a collection o {@link ComponentRef}
	 */
	public Collection<ComponentRef> getChildren() {
		return Collections.unmodifiableCollection(this.children);
	}
	
	/**
	 * Add a reference to the collection of component references
	 * @param child
	 */
	public void addChild(ComponentRef child) {
		this.children.add(child);
	}
	
	/**
	 * This method clears the collection of references
	 */
	public void clearChildren() {
		this.children.clear();
	}
}
