package tq2.implementations;

import java.awt.Graphics2D;
import java.util.LinkedList;

import tq2.interfaces.Entity;
import tq2.interfaces.Game;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * The Class Handler is used to render Entities of a Level object, as well as update their status.
 * 
 * @author Francesco Gori
 */
public class HandlerImpl implements Handler {
	
	/** The game the Handler is relative to. */
	protected Game game;
	
	/** The pause menu that will be displayed when the game is paused. If it is null, no menu will be displayed. */
	protected LevelLayer pauseMenu;
	
	/** Whether the game is paused or not.*/
	protected Boolean paused;

	/** The list of Entities that reside on each layers. */
	protected LinkedList<LevelLayer> layers;
	
	
	/**
	 * Instantiates a new Handler relative to the specified Game object.
	 *
	 * @param game the game
	 */
	public HandlerImpl(Game game) {
		this.game = game;
		this.layers = new LinkedList<LevelLayer>();
		this.pauseMenu = null;
		this.paused = false;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		
		//the Handler will iterate through each object of each layer, and render it
		for (int i=0; i<this.getLayers().size();i++) {
			LevelLayer layer = this.getLayers().get(i);
			if (layer.isEnabled()) {
				//the scene will be translated of the parallax values specific to this layer
				g.translate( - (layer.getParallaxX() * this.getGame().getCamera().getX()), - (layer.getParallaxY() * this.getGame().getCamera().getY()));
				
				for (int j=0; j<layer.size();j++) {
					Entity e = layer.get(j);

					if (e.isEnabled() && e.getBounds().intersects(this.getGame().getWindowBounds(e.layer()))) {
						e.render(g);
					}
				}

				g.translate( layer.getParallaxX() * this.getGame().getCamera().getX(), layer.getParallaxY() * this.getGame().getCamera().getY());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#tick()
	 */
	@Override
	public void tick() {
		
		//if the game is paused, display the pause menu and don't update any other object
		if (this.isPaused()) {
			if (this.pauseMenu!=null) {
				for (int j=0; j<pauseMenu.size();j++) {
					Entity e = pauseMenu.get(j);
					if (e.isEnabled()) {
						e.tick();
					}
				}
			}
		}
	
		//otherwise, tick each object in each layer, if it's enabled
		else {
			for (int i=0; i<this.getLayers().size();i++) {
				LevelLayer layer = this.getLayers().get(i);
				if (layer.isEnabled()) {
					for (int j=0; j<layer.size();j++) {
						Entity e = layer.get(j);
						if (e.isEnabled()) {
							e.tick();
						}
					}
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#getGame()
	 */
	@Override
	public Game getGame() {
		return this.game;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#getLayers()
	 */
	@Override
	public LinkedList<LevelLayer> getLayers() {
		return this.layers;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#addEntity(com.tq2.interfaces.Entity, java.lang.Integer)
	 */
	@Override
	public void addEntity (Entity en, Integer i) {
		this.getLayers().get(i).add(en);
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#removeEntity(com.tq2.interfaces.Entity)
	 */
	@Override
	public void removeEntity (Entity en) {
		for (LevelLayer i : this.getLayers()) {
			if (i.contains(en)) {
				i.remove(en);
			}
		}
  	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#getLayerOf(com.tq2.interfaces.Entity)
	 */
	@Override
	public LevelLayer getLayerOf(Entity en) {
		LevelLayer ret = null;

		//search in any layer looking for the specified object
		for (int i=0; i < this.getLayers().size() && ret == null; i++) {
			if (this.getLayers().get(i).contains(en)) {
				ret = this.getLayers().get(i);
			}
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#getLayer(java.lang.Integer)
	 */
	@Override
	public LevelLayer getLayer(Integer index) {
		return this.getLayers().get(index);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#getGameScale()
	 */
	@Override
	public Integer getGameScale() {
		return this.getGame().getScale();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#clearLevel()
	 */
	@Override
	public void clearLevel() {
		for (int i=0; i<this.getLayers().size();i++) {
				this.getLayers().get(i).clear();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#setPauseMenu(com.tq2.interfaces.LevelLayer)
	 */
	@Override
	public void setPauseMenu(LevelLayer pauseMenu) {
		this.pauseMenu = pauseMenu;
		this.pauseMenu.setEnabled(false);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#pause()
	 */
	@Override
	public void pause() {
		this.paused = !this.isPaused();
		if (this.pauseMenu != null) {
			this.pauseMenu.setEnabled(this.isPaused());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#isPaused()
	 */
	@Override
	public Boolean isPaused() {
		return this.paused;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Handler#setPaused(java.lang.Boolean)
	 */
	@Override
	public void setPaused(Boolean paused) {
		this.paused = paused;
		if (this.pauseMenu != null) {
			this.pauseMenu.setEnabled(paused);
		}
	}
}
