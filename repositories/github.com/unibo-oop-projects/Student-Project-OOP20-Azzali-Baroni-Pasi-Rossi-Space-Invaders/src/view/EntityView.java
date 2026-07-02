package view;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import controller.GameLoop;
import model.Entity;
import model.SpecialEffect;
import model.Bullet;
import controller.ChronometerEntity;
import model.powerup.PowerUpImpl;
import model.powerup.PowerUpT;
import utility.Pair;
import utility.ImageLoader;

/**
 * The Class EntityView.
 */
public class EntityView {
	
	/** The Constant FRAME_IMAGE. */
	public static final int FRAME_IMAGE = GameLoop.FPS / 20;
	
	/** The loader. */
	private final ImageLoader loader;
	
	/** The current image. */
	private final Map<Entity, Integer> currentImage;
	
	/** The Time last image. */
	private final Map <Entity, Integer> TimeLastImage;
	
	/** The tick. */
	private int tick;
	
	/**
	 * Instantiates a new entity view.
	 */
	public EntityView () {
		this.tick = 0;
		this.loader = ImageLoader.getImageLoader();
		this.currentImage = new HashMap<>();
		this.TimeLastImage = new HashMap<>();
		
		
	}
	
	/**
	 * Load image.
	 *
	 * @param e the e
	 * @return the image
	 */
	public Image loadImage(final Entity e) {
		if (tick > GameLoop.FPS * 10) {
			this.removeUselessEntities();
			this.tick = 0;
			
		}
		switch (e.getID()) {
		case PLAYER_BULLET :
			return loader.getBulletImages().get(new Pair<>(e.getID(), ((Bullet) e).getOwner())).get(0);
		case EFFECT :
			addIfNotPresent(e);
			return getRightImage(e);
		case POWER_UP :
			if(!((PowerUpImpl) e).isActivated()) {
				return loader.getPowerUpImages().get(new Pair <>(e.getID(), ((PowerUpImpl) e).getType())).get(0);
				} else {
					if (((PowerUpImpl) e).getType() != PowerUpT.HEALTH &&((PowerUpImpl) e).getType() != PowerUpT.SHIELD) {
						addIfNotPresent (e);
						return getRightImage(e);
					}
					return null;
					
				}
			default : 
				return loader.getEntityImages().get(e.getID()).get(0);
			
			
		}
	}
	
	/**
	 * Removes the useless entities.
	 */
	private void removeUselessEntities() {
		final List<Entity> uselessEntities = this.currentImage.keySet().stream().filter(e -> e.isDead()).collect(Collectors.toList());
		uselessEntities.forEach(e -> {
			this.currentImage.remove(e);
			this.TimeLastImage.remove(e);
		});
		
	}

	
	
/**
 * Gets the right image.
 *
 * @param entity the entity
 * @return the right image
 */
private Image getRightImage(final Entity entity) {		
	final ChronometerEntity CEntity = (ChronometerEntity) entity;
	final int oTime = TimeLastImage.get(CEntity);
		final int cTime = CEntity.getTimeLeft();
		final List<Image> images = new ArrayList<>();
	switch (entity.getID()) {
		case POWER_UP :
			images.addAll(loader.getAnimationsPowerUp().get(new Pair<>(entity.getID(), ((PowerUpImpl) entity).getType())));
			break;
		case EFFECT :
			images.addAll(loader.getAnimationsEffect().get(new Pair<>(entity.getID(), ((SpecialEffect) entity).getType())));
			break;
			default: System.out.println("Error in getting the right image");
		}
	if (oTime - cTime > FRAME_IMAGE) {
		TimeLastImage.put(CEntity, oTime - FRAME_IMAGE);
		currentImage.put(CEntity, (currentImage.get(CEntity) + 1) % images.size());
	}
	return images.get(currentImage.get(entity));
}
	
	/**
	 * Adds the if not present.
	 *
	 * @param entity the entity
	 */
	private void addIfNotPresent(final Entity entity) {
		if (!this.currentImage.containsKey(entity)) {
			switch (entity.getID()) {
			case POWER_UP :
				this.TimeLastImage.put(entity, ((PowerUpImpl) entity).getTimeLeft());
				break;
			case EFFECT :
				this.TimeLastImage.put(entity, ((SpecialEffect) entity).getTimeLeft());
				break;
			default : System.out.println("Error in addIfNotPresent");
			}
			this.currentImage.put(entity, 0);
		}
	}
}


