package tq2.interfaces;

import tq2.implementations.Id;

/**
 * The Interface LevelLayer.
 * 
 * @author Francesco Gori
 */
public interface LevelLayer {
	
	/**
	 * Gets the Entity at the specified index.
	 *
	 * @param index the index
	 * @return the Entity
	 */
	public abstract Entity get(Integer index);

	/**
	 * Adds the Entity to the layer.
	 *
	 * @param en the Entity
	 */
	public abstract void add(Entity en);

	/**
	 * Checks whether the layer contains the specified Entity.
	 *
	 * @param en the Entity
	 * @return the boolean
	 */
	public abstract Boolean contains(Entity en);

	/**
	 * Removes the specified Entity from the layer, if present.
	 *
	 * @param en the Entity
	 */
	public abstract void remove(Entity en);

	/**
	 * Returns the number of Entities inside this layer.
	 *
	 * @return the number of Entities
	 */
	public abstract Integer size();

	/**
	 * Checks if the layer is enabled.
	 *
	 * @return the whether the layer is enabled
	 */
	public abstract Boolean isEnabled();

	/**
	 * Gets the parallax X.
	 *
	 * @return the parallax X
	 */
	public abstract Double getParallaxX();

	/**
	 * Gets the Y parallax relative to this layer.
	 *
	 * @return the Y parallax
	 */
	public abstract Double getParallaxY();

	/**
	 * Gets the first occurrence of an Entity with the specified id.
	 *
	 * @param id the id
	 * @return the first occurrence
	 */
	public abstract Entity getFirstOccurrence(Id id);

	/**
	 * Gets the z-index of this layer.
	 *
	 * @return the index
	 */
	public abstract Integer getIndex();

	/**
	 * Sets whether the layer is enabled.
	 *
	 * @param enabled whether the layer is enabled
	 */
	public abstract void setEnabled(Boolean enabled);

	/**
	 * Toggles the layer, enabling it if it's disabled and vice versa.
	 */
	public abstract void toggle();

	/**
	 * Removes all the objects contained in the layer.
	 */
	public abstract void clear();

}