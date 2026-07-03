package view;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.GameLoop;
import game.Bullet;
import game.GameObject;
import game.SpecialEffect;
import game.TimedEntity;
import game.buffs.PowerUpImpl;
import game.buffs.PowerUpType;
import utilities.ImageLoader;
import utilities.Pair;

/**
 * The class used to animate images.
 */
public class EntityAnimator {
    /**
     * The "time" each image need to be shown for.
     */
    public static final int FRAMES_FOR_EACH_IMAGE = GameLoop.FPS / 20;
    private final ImageLoader loader;
    private final Map<GameObject, Integer> currentImage;
    private final Map<GameObject, Integer> timeOfLastImage;
    private int tick;

    /**
     * Creates a new Animator.
     */
    public EntityAnimator() {
        this.tick = 0;
        this.loader = ImageLoader.getLoader();
        this.currentImage = new HashMap<>();
        this.timeOfLastImage = new HashMap<>();
    }

    /**
     * @param e the object that needs to be displayed
     * @return the image for this entity. If it has an animation it returns the first image of the animation, if the 
     * animation is already started it returns the next image in the sequence
     * 
     */
    public Image loadImage(final GameObject e) {
        if (tick > GameLoop.FPS * 10) {
            this.removeUnusedEntities();
            this.tick = 0;
        }
        switch (e.getID()) {
        case BULLET : 
            return loader.getBulletImages().get(new Pair<>(e.getID(), ((Bullet) e).getOwner())).get(0);
        case EFFECT :
            addIfNotPresent(e);
            return getRightImage(e);
        case POWER_UP :
            if (!((PowerUpImpl) e).isActivated()) {
                return loader.getPowerUpImages().get(new Pair<>(e.getID(), ((PowerUpImpl) e).getType())).get(0);
            } else {
                if (((PowerUpImpl) e).getType() != PowerUpType.HEALTH_RECOVERY && ((PowerUpImpl) e).getType() != PowerUpType.NUKE && ((PowerUpImpl) e).getType() != PowerUpType.SHIELD) {
                    addIfNotPresent(e);
                    return getRightImage(e);
                }
                return null;
            }
        default :
            return loader.getEntityImages().get(e.getID()).get(0);
        }
    }

    private void removeUnusedEntities() {
        final List<GameObject> unusedEntities = this.currentImage.keySet().stream().filter(e -> e.isDead()).collect(Collectors.toList());
        unusedEntities.forEach(e -> {
            this.currentImage.remove(e);
            this.timeOfLastImage.remove(e);
        });
    }

    private Image getRightImage(final GameObject entity) {
        final TimedEntity tEntity = (TimedEntity) entity;
        final int oldTime = timeOfLastImage.get(tEntity);
        final int currentTime = tEntity.getTimeLeft();
        final List<Image> images = new ArrayList<>();
        switch (entity.getID()) {
        case POWER_UP : 
            images.addAll(loader.getPowerUpAnimations().get(new Pair<>(entity.getID(), ((PowerUpImpl) entity).getType())));
            break;
        case EFFECT :
            images.addAll(loader.getSpecialEffectsAnimations().get(new Pair<>(entity.getID(), ((SpecialEffect) entity).getType())));
            break;
        default : System.out.println("Error in getting the right image");
        }
        if (oldTime - currentTime > FRAMES_FOR_EACH_IMAGE) {
            timeOfLastImage.put(tEntity, oldTime - FRAMES_FOR_EACH_IMAGE);
            currentImage.put(tEntity, (currentImage.get(tEntity) + 1) % images.size());
        }
        return images.get(currentImage.get(entity));
    }

    private void addIfNotPresent(final GameObject entity) {
        if (!this.currentImage.containsKey(entity)) {
            switch (entity.getID()) {
            case POWER_UP : 
                this.timeOfLastImage.put(entity, ((PowerUpImpl) entity).getTimeLeft());
                break;
            case EFFECT :
                this.timeOfLastImage.put(entity, ((SpecialEffect) entity).getTimeLeft());
                break;
            default : System.out.println("Error in addIfNotPresent");
            }
            this.currentImage.put(entity, 0);
        }
    }
}
