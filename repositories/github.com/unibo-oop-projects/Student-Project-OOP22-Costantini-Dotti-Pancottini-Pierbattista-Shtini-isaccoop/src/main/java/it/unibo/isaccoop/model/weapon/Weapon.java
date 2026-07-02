package it.unibo.isaccoop.model.weapon;

import java.util.List;

import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.Removable;
import it.unibo.isaccoop.model.common.Vector2D;

/**
 * Weapon interface that models the weapons concept.
 * */
public interface Weapon extends Removable {

    /**
     * Perform a weapon shoot.
     * @param startPosition the origin position
     * @param direction the direction where fire the shot
     * */
    void shoot(Point2D startPosition, Vector2D direction);

    /**
     * Update weapon shots state.
     * */
    void tickShots();

    /**
     * Get weapon shots collection.
     * @return weapon shots list
     * */
    List<WeaponShot> getWeaponShots();

    /**
     * Update weapon interval, used to all time interval weapons.
     *
     * @param interval new time interval between two shots
     * */
    void setWeaponInterval(double interval);

}
