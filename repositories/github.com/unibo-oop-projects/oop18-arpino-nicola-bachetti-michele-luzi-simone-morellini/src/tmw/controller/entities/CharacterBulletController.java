package tmw.controller.entities;

import tmw.common.P2d;
import tmw.common.V2d;
import tmw.controller.world.WorldController;

/**
 * This Class represents the controller for a bullet shot by the main character.
 */
public class CharacterBulletController extends AbstarctBulletController {

    /**
     * Construct a new controller for a bullet shot by the main character.
     * 
     * @param worldController - The WorldController that is used by the controller
     *                        to communicate with the rest of the game
     * @param pos             - the initial position of the bullet as a {@link P2d}
     * @param vel             - the initial velocity of the bullet as a {@link V2d}
     * @param damage          - the damage of the bullet
     */
    public CharacterBulletController(final WorldController worldController, final P2d pos, final V2d vel,
            final int damage) {
        super(worldController, worldController.getFactory().createCharacterBullet(pos, vel, damage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkCollisions(final P2d newPosition) {

        for (final EntityController<?> e : this.getWorldController().getEntitiesLoaded()) {
            if (e.getEntity().intersect(this.getEntity())) {
                e.getEntity().takeDamage(this.getEntity().getDamage());
                return true;
            }
        }

        return false;
    }
}
