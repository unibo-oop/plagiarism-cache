package model.entities.animations;

import java.util.HashMap;
import java.util.Map;

import model.entities.GameEntityClasses;
import model.entities.animations.enemies.BossAnimations;
import model.entities.animations.enemies.GoblinAnimations;
import model.entities.animations.enemies.SkeletonAnimations;
import model.entities.animations.player.ArcherAnimations;
import model.entities.animations.player.KnightAnimations;
import model.entities.animations.player.ThiefAnimations;
import model.entities.components.Moveset;

/**
 * Initialises the controller's moveset with the proper one depending on the player class.
 */
public class AnimationSetterImpl implements AnimationSetter {

    private final Map<GameEntityClasses, Moveset> animations = new HashMap<>();

    /**
     * Returns a PlayerAnimationSetter.
     */
    public AnimationSetterImpl() {
        animations.put(GameEntityClasses.KNIGHT, new KnightAnimations().getAnimations());
        animations.put(GameEntityClasses.ARCHER, new ArcherAnimations().getAnimations());
        animations.put(GameEntityClasses.THIEF, new ThiefAnimations().getAnimations());
        animations.put(GameEntityClasses.SKELETON, new SkeletonAnimations().getAnimations());
        animations.put(GameEntityClasses.GOBLIN, new GoblinAnimations().getAnimations());
        animations.put(GameEntityClasses.BOSS, new BossAnimations().getAnimations());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAnimationsToMoveset(final GameEntityClasses entityClass, final Moveset moveset) {
       moveset.addMoveset(animations.get(entityClass));
    }
}
