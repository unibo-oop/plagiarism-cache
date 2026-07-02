package zombieversity.controller.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javafx.geometry.BoundingBox;
import zombieversity.controller.core.GameWorld;
import zombieversity.model.entities.EntityType;
import zombieversity.model.entities.weapon.Attack;
import zombieversity.model.entities.weapon.AttackManager;
import zombieversity.model.entities.weapon.AttackManagerImpl;
import zombieversity.model.entities.weapon.KnifeAttack;
import zombieversity.view.entities.AttackView;
import zombieversity.view.entities.BulletView;
import zombieversity.view.entities.KnifeAttackView;

/**
 * Straight implementation of the AttackController interface.
 */
public class AttackControllerImpl implements AttackController {

    private final AttackManager model;
    private final Map<Attack, AttackView> attacksActiveView;
    private final GameWorld world;

    /**
     * @param gameWorld
     *          The GameWorld in which this AttackController will be used.
     */
    public AttackControllerImpl(final GameWorld gameWorld) {
        this.world = gameWorld;
        this.model = new AttackManagerImpl(gameWorld.getZombieController().getZombieModel());
        this.attacksActiveView = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Attack> getAttacksModel() {
        return this.model.getAttackActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setObstacles(final Set<BoundingBox> obstacles) {
        this.model.setObstacles(obstacles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<Attack, AttackView> getAttacks() {
        return this.attacksActiveView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addAttack(final Optional<Attack> attack) {
        if (attack.isPresent()) {
            AttackView view;
            if (attack.get() instanceof KnifeAttack) {
                view = new KnifeAttackView(this.world.getPlayerPos());
                view.setDirection(Math.toDegrees(attack.get().getDirection()));
            } else {
                view = new BulletView();
                view.setDirection(Math.toDegrees(attack.get().getDirection() + Math.PI / 2));
            }
            this.model.addAttack(attack.get());
            this.attacksActiveView.put(attack.get(), view);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        this.model.update();
        this.updateView();
    }

    private void updateView() {
        this.updateMap();
        this.attacksActiveView.entrySet().stream()
                                         .filter(e -> e.getKey().getType().equals(EntityType.MELEE_ATTACK))
                                         .forEach(e -> e.getValue().setRotation(this.world.getPlayerPos(), e.getKey().getPosition()));
    }

    private void updateMap() {
        Set<Attack> toDelete = new HashSet<>();
        toDelete = this.model.getAttacksEnded();
        toDelete.stream().filter(a -> this.attacksActiveView.containsKey(a))
                         .forEach(this.attacksActiveView::remove);
    }

}
