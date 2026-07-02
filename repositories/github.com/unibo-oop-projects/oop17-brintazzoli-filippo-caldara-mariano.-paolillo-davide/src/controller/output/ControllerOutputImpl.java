package controller.output;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import controller.utility.Convertitor;
import model.object.Projectile;
import model.object.Tank;
import model.utility.Pair;

/**
 * Concrete implementation of the {@link ControllerOutput} interface. This class
 * manage the output made by the {@link Controller}.
 */
public class ControllerOutputImpl implements ControllerOutput {

    private final List<Projectile> projectiles;
    private final Tank playerTank;
    private final Tank enemyTank;

    /**
     * Constructor.
     * 
     * @param projectiles
     *            the list of {@link Projectile}.
     * @param playerTank
     *            the player {@link Tank}.
     * @param enemyTank
     *            the enemy {@link Tank}.
     */
    public ControllerOutputImpl(final List<Projectile> projectiles, final Tank playerTank, final Tank enemyTank) {
        this.projectiles = projectiles;
        this.playerTank = playerTank;
        this.enemyTank = enemyTank;
    }

    @Override
    public final List<Pair<Double, Double>> getProjectiles() {
        return Collections.unmodifiableList(this.projectiles.stream().map(p -> Convertitor.modelToView(p.getPosition()))
                .collect(Collectors.toList()));
    }

    @Override
    public final Pair<Double, Double> getProjectileDimension() {
        return Convertitor.modelToView(this.projectiles.get(0).getBounds());
    }

    @Override
    public final Pair<Double, Double> getPlayerPosition() {
        return Convertitor.modelToView(this.playerTank.getPosition());
    }

    @Override
    public final Pair<Double, Double> getEnemyPosition() {
        return Convertitor.modelToView(this.enemyTank.getPosition());
    }

    @Override
    public final int getPlayerLifes() {
        return this.playerTank.getLifes();
    }

    @Override
    public final int getEnemyLifes() {
        return this.enemyTank.getLifes();
    }

    @Override
    public final Pair<Double, Double> getTankDimension() {
        return Convertitor.modelToView(Tank.getDimension());
    }

    @Override
    public final boolean isPlayerAlive() {
        return this.playerTank.isAlive();
    }

    @Override
    public final boolean isEnemyAlive() {
        return this.enemyTank.isAlive();
    }

    @Override
    public final Pair<Double, Double> getCannonDimension() {
        return Convertitor.modelToView(this.playerTank.getCannonDimension());
    }

    @Override
    public final Pair<Double, Double> getPlayerCannonPosition() {
        return Convertitor.modelToView(this.playerTank.getCannonPosition());
    }

    @Override
    public final Pair<Double, Double> getEnemyCannonPosition() {
        return Convertitor.modelToView(this.enemyTank.getCannonPosition());
    }

    @Override
    public final double getPlayerAngle() {
        return this.playerTank.getAngle();
    }

    @Override
    public final double getEnemyAngle() {
        return this.enemyTank.getAngle();
    }

}
