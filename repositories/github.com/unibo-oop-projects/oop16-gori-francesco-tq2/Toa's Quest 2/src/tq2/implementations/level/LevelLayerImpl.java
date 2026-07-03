package tq2.implementations.level;

import java.util.Iterator;
import java.util.LinkedList;

import tq2.implementations.Id;
import tq2.interfaces.Entity;
import tq2.interfaces.LevelLayer;

/**
 * The Class LevelLayerImpl.
 * 
 * @author Francesco Gori
 */
public class LevelLayerImpl implements Iterable<Entity>, LevelLayer {
	
	/** The list of Entities which reside in this level. */
	LinkedList<Entity> layer;
	
	/** The X parallax of this layer. */
	Double parallaxX;
	
	/** The Y parallax of this layer. */
	Double parallaxY;
	
	/** The index of the layer in the list of layers of the Handler. */
	Integer index;
	
	/** Whether the layer is enabled. */
	Boolean enabled;

	/**
	 * Instantiates a new LevelLayerImpl.
	 *
	 * @param parallaxX the X parallax of this layer
	 * @param parallaxY the Y parallax of this layer
	 * @param enabled whether the layer is enabled
	 * @param index the index of this layer in the list of layers of the Handler
	 */
	public LevelLayerImpl(Double parallaxX, Double parallaxY, Boolean enabled, Integer index) {
		this.layer = new LinkedList<Entity>();
		this.parallaxX = parallaxX;
		this.parallaxY = parallaxY;
		this.index = index;
		this.enabled = enabled;
	}
	
	/**
	 * Instantiates a new LevelLayerImpl.
	 *
	 * @param parallaxX the X parallax of this layer
	 * @param parallaxY the Y parallax of this layer
	 * @param index the index of this layer in the list of layers of the Handler
	 */
	public LevelLayerImpl(Double parallaxX, Double parallaxY, Integer index) {
		this(parallaxX, parallaxY, true, index);
	}
	
	/**
	 * Instantiates a new LevelLayerImpl.
	 *
	 * @param index the index of this layer in the list of layers of the Handler
	 */
	public LevelLayerImpl(Integer index) {
		this(1.0, 1.0, index);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#get(java.lang.Integer)
	 */
	@Override
	public Entity get(Integer index) {
		return this.layer.get(index);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#add(com.tq2.interfaces.Entity)
	 */
	@Override
	public void add(Entity en) {
		this.layer.add(en);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#contains(com.tq2.interfaces.Entity)
	 */
	@Override
	public Boolean contains(Entity e) {
		return this.layer.contains(e);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#remove(com.tq2.interfaces.Entity)
	 */
	@Override
	public void remove(Entity e) {
		this.layer.remove(e);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#size()
	 */
	@Override
	public Integer size() {
		return this.layer.size();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#isEnabled()
	 */
	@Override
	public Boolean isEnabled() {
		return this.enabled;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#getParallaxX()
	 */
	@Override
	public Double getParallaxX() {
		return this.parallaxX;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#getParallaxY()
	 */
	@Override
	public Double getParallaxY() {
		return this.parallaxY;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#getFirstOccurrence(com.tq2.implementations.Id)
	 */
	@Override
	public Entity getFirstOccurrence(Id id) {
		
		Entity firstOccurrance = null;
		Boolean loop = true;
		
		for (int i=0; i<this.size() && loop ;i++) {
			firstOccurrance = this.get(i);
			loop = (this.get(i).getId() != id);
		}
		return(firstOccurrance);
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#getIndex()
	 */
	@Override
	public Integer getIndex() {
		return this.index;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#setEnabled(java.lang.Boolean)
	 */
	@Override
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#toggle()
	 */
	@Override
	public void toggle() {
		this.enabled = !this.isEnabled();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Entity> iterator() {
		return this.layer.iterator();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.LevelLayer#clear()
	 */
	@Override
	public void clear() {
		this.layer.clear();
	}
}

