package model.entities.tankcomponents;

import model.entities.Tank;

public class SpeedComponent implements TankComponent {

    private static final double DEFAULT_SPEED_BOOSTER = 2;
    private final Tank attachedTank;
    private final double speed;

    public SpeedComponent(final Tank attachedTank) {
        this(attachedTank, DEFAULT_SPEED_BOOSTER);
    }

    public SpeedComponent(final Tank attachedTank, final double speed) {
        super();
        this.attachedTank = attachedTank;
        this.speed = speed;
    }

    @Override
    public void useComponent() {
        this.attachedTank.getActualMovement().mul(speed);
    }

}
