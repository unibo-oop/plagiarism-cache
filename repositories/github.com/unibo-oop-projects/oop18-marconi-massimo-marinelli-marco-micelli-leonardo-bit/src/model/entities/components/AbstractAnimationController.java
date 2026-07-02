package model.entities.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import model.entities.GameEntityClasses;
import model.entities.animations.AnimationSetterImpl;

/**
 * Implements a generic AnimationController. Should be subclassed in order to create controllers 
 * for specific entities.
 */
public abstract class AbstractAnimationController extends Component implements AnimationController {

    private final AnimationSetterImpl animSetter = new AnimationSetterImpl();
    private final Moveset moveset = new MovesetImpl();
    private final AnimatedTexture texture;

    /**
     * A generic controller for animated entities.
     * 
     * @param entityClass
     *                          a proper moveset for the entityClass.
     */
    public AbstractAnimationController(final GameEntityClasses entityClass) {
        super();
        animSetter.addAnimationsToMoveset(entityClass, moveset);
        texture = new AnimatedTexture(moveset.getAnimation("idle"));
    }

    /**
     * Registers the animation in the Moveset object.
     * @param animName
     *                  the animation's name.
     * @param animation
     *                  the AnimationChannel for the animation.
     */
    public void registerAnimation(final String animName, final AnimationChannel animation) {
        moveset.addAnimation(animName, animation);
    }

    /**
     * Loops the animation.
     * @param animName
     *                  the animation's name in the moveset.
     */
    public void loopAnimation(final String animName) {
        texture.loopAnimationChannel(moveset.getAnimation(animName));
    }

    /**
     * 
     * @param animName
     *                  the animation's name in the moveset.
     * @return the corresponding animation channel.
     */
    public AnimationChannel getAnimation(final String animName) {
        return moveset.getAnimation(animName);
    }

    /**
     * 
     * @return the current animation channel the texture is looping.
     */
    public AnimationChannel getCurrentAnimation() {
        return texture.getAnimationChannel();
    }

    /**
     * 
     * @return  the animated texture.
     */
    public AnimatedTexture getTexture() {
        return texture;
    }
}
