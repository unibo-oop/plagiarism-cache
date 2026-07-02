package it.unibo.isaccoop.model.weapon;

import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.Vector2D;

/**
 * BaseWeaponShot class that models a base weapon shot.
 * */
public class BaseWeaponShot extends AbstractWeaponShot {

    /**
     * BaseWeaponShot constructor.
     *
     * @param startPosition shot start position
     * @param direction shot direction vector
     * @param component
     * */
    public BaseWeaponShot(final Point2D startPosition, final Vector2D direction, final GraphicsComponent component) {
        super(startPosition, direction, component);
    }

    /**
     * Method to increment the position of the shot by SPEED through a defined vector
     * computed by sub between player position and shot start position.
     * */
    @Override
    public void tickShot() {
        super.setCoords(super.getCoords().sum(this.getShotVector().getNormalized().mul(super.getSpeed())));
    }

}
