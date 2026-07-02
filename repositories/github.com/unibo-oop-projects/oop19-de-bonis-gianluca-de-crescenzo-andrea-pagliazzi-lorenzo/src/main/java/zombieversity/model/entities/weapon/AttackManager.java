package zombieversity.model.entities.weapon;

import java.util.Set;

import javafx.geometry.BoundingBox;

/**
 * Handler of all the attacks present in the game as a whole.
 */
public interface AttackManager {

    /**
     * @param a
     *          To be called to add an attack to the manager.
     */
    void addAttack(Attack a);

    /**
     * @param a
     *          To be called to remove an attack from the manager.
     */
    void removeAttack(Attack a);

    /**
     * @return
     *          A set with all the currently active attacks in the game.
     */
    Set<Attack> getAttackActive();

    /**
     * To be called to update the inner logic of the AttackManager.
     */
    void update();

    /**
     * @return
     *          The set of attacks ended since the last reading. Note: once read the set should be reset.
     */
    Set<Attack> getAttacksEnded();

    /**
     * @param obstacles
     *          The set of obstacles present in the world with which the attacks can collide.
     */
    void setObstacles(Set<BoundingBox> obstacles);

}
