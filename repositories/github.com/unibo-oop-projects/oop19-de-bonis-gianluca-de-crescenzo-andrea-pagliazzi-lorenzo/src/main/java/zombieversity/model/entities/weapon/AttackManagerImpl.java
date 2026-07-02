package zombieversity.model.entities.weapon;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.BoundingBox;
import zombieversity.model.collisions.CollisionsUtils;
import zombieversity.model.entities.zombie.ZombieModel;

/**
 * Straight implementation of {@link AttackManager}.
 */
public class AttackManagerImpl implements AttackManager {

    private final Set<Attack> attacksActive;
    private final Set<Attack> deleted;
    private final ZombieModel zombieModel;
    private Set<BoundingBox> obstacles;

    /**
     * @param zombieModel
     *          The zombieModel with which the attack manager will be bonded to perform the game logic.
     */
    public AttackManagerImpl(final ZombieModel zombieModel) {
        this.attacksActive = new HashSet<>();
        this.zombieModel = zombieModel;
        this.deleted = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addAttack(final Attack a) {
        this.attacksActive.add(a);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removeAttack(final Attack a) {
        if (this.attacksActive.contains(a)) {
            this.attacksActive.remove(a);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Attack> getAttackActive() {
        return this.attacksActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        final Set<Attack> toDelete = new HashSet<>();

        this.attacksActive.forEach(a -> {
            a.update();
            if (a.hasEnded()) {
                toDelete.add(a);
            } else {
                this.zombieModel.getZombies().stream()
                                             .filter(z -> CollisionsUtils.isColliding(z.getBBox(), a.getBBox()))
                                             .forEach(z -> {
                                                    this.zombieModel.hitZombie(z, a.getDamage());
                                                    toDelete.add(a);
                                             });
                if (!toDelete.contains(a) && this.obstacles.stream().anyMatch(e -> CollisionsUtils.isColliding(e, a.getBBox()))) {
                    toDelete.add(a);
                }
            }
        });

        this.deleted.addAll(toDelete);
        toDelete.forEach(a -> this.attacksActive.remove(a));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Attack> getAttacksEnded() {
        final Set<Attack> tmp = new HashSet<>(deleted);
        this.deleted.clear();
        return tmp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setObstacles(final Set<BoundingBox> obstacles) {
        this.obstacles = obstacles;
    }


}
