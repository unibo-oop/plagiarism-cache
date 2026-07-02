package model.entities.tankcomponents;

import model.BulletEngine;
import model.entities.Tank;

public class ShooterComponent implements TankComponent {

    private final BulletEngine shooter;
    private boolean shoot;
    private final Tank attacchedTank;

    public ShooterComponent(final BulletEngine shooter, final Tank attacchedTank) {
        super();
        this.shooter = shooter;
        this.shoot = false;
        this.attacchedTank = attacchedTank;
    }

    @Override
    public void useComponent() {
        if (this.shoot) {
            this.shooter.addBullet(attacchedTank);
        }
        this.shoot = false;
    }

    public void isShooting() {
        this.shoot = true;
    }

}
