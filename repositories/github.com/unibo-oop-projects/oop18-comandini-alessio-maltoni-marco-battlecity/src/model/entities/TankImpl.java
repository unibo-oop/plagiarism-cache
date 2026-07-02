package model.entities;

import java.util.ArrayList;
import java.util.List;

import enums.Sprite;
import model.common.MovementImpl;
import model.common.Position;
import model.common.PositionImpl;
import model.entities.tankcomponents.ShooterComponent;
import model.entities.tankcomponents.TankComponent;

public final class TankImpl extends AbstractGameEntity implements Tank {

    private final List<TankComponent> componentList;

    public TankImpl(final Sprite sprite, final Position position) {
        super(sprite, position, new MovementImpl(), DEFAULT_TANK_DIMENSION);
        componentList = new ArrayList<>();

    }

    public TankImpl(final Sprite sprite) {
        this(sprite, new PositionImpl());
    }

    @Override
    public void shoot() {
        this.componentList.stream().filter(c -> c instanceof ShooterComponent)
                .forEach(e -> ((ShooterComponent) e).isShooting());
    }

    @Override
    public List<TankComponent> getComponents() {
        return this.componentList;
    }

    @Override
    public void updateState() {
        this.componentList.stream().forEach(TankComponent::useComponent);
        super.updateState();
        this.setMovement(new MovementImpl());
    }

    @Override
    public Tank attach(final TankComponent component) {
        this.componentList.add(component);
        return this;
    }

}
