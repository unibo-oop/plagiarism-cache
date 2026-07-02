package zombieversity.controller.entities;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javafx.geometry.BoundingBox;
import zombieversity.model.entities.weapon.Attack;
import zombieversity.view.entities.AttackView;

/**
 * Controller that will handle the attacks using {@link AttackManager} and {@link AttackView}.
 */
public interface AttackController {

    /**
     * 
     * @return
     *          A set containing all the {@link Attack} active at the moment.
     */
    Set<Attack> getAttacksModel();

    /**
     * Used to set the obstacles with which the attacks can collide.
     * @param obstacles
     */
    void setObstacles(Set<BoundingBox> obstacles);

    /**
     * @return
     *          A map containing all the attacks currently active mapped with its view;
     */
    Map<Attack, AttackView> getAttacks();

    /**
     * Used to add new attacks to be added both to the model and view.
     * @param attack
     *          A new attack that will be handled by the controller.
     */
    void addAttack(Optional<Attack> attack);

    /**
     * Used to update the inner logic of the controller.
     */
    void update();

}
