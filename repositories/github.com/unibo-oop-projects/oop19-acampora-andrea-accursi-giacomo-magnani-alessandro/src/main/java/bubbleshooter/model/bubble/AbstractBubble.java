package bubbleshooter.model.bubble;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import bubbleshooter.model.component.Component;
import bubbleshooter.model.component.ComponentType;
import bubbleshooter.model.component.ShootingComponent;
import javafx.geometry.Point2D;

/**
 * Abstract class which contains the main methods of {@link Bubble}.
 *
 */
public abstract class AbstractBubble implements Bubble {

    private Point2D position;
    private boolean isDestroyed;
    private BubbleType type;
    private BubbleColor color;
    private final List<Component> components;

    /**
     * 
     * @param type The {@link BubbleType} of the bubble.
     * @param position The position of the bubble.
     * @param color The {@link BubbleColor} of the bubble.
     */
    public AbstractBubble(final BubbleType type, final Point2D position, final BubbleColor color) {
        this.color = color; 
        this.position = position;
        this.isDestroyed = false;
        this.type = type;
        this.position = position;
        this.components = new LinkedList<>();
        this.setComponents();
    }

    /**
     * Template method used for choose {@link Component} in subclasses.
     */
    protected abstract void setComponents();

    @Override
    public final Optional<Component> getComponent(final ComponentType type) {
        return this.components.stream()
                              .filter(a -> a.getComponentType().equals(type))
                              .findFirst();
    }

    @Override
    public final void addComponent(final Component component) {
        this.components.add(component);
    }

    @Override
    public final Point2D getPosition() {
       return this.position;
    }

    @Override
    public final void setPosition(final Point2D position) {
       this.position = position;
    }

    @Override
    public final boolean isDestroyed() {
        return this.isDestroyed;
    }

    @Override
    public final void destroy() {
        this.isDestroyed = true;
    }

    @Override
    public final BubbleType getType() {
        return this.type;
    }

    @Override
    public final void setType(final BubbleType type) {
        this.type = type;
    }

    @Override
    public final BubbleColor getColor() {
        return this.color;
    }

    @Override
    public final void setColor(final BubbleColor color) {
    this.color = color;
    }

    @Override
    public void update(final double elapsed) {
    }

    @Override
    public final void setDirection(final Point2D direction) {
        if (this.getComponent(ComponentType.SHOOTINGCOMPONENT).isPresent()) {
            final ShootingComponent shooter = (ShootingComponent) this.getComponent(ComponentType.SHOOTINGCOMPONENT).get();
            shooter.setDirection(direction);
        }
    }

    @Override
    public final Optional<Point2D> getDirection() {
        if (this.getComponent(ComponentType.SHOOTINGCOMPONENT).isPresent()) {
            final ShootingComponent shooter = (ShootingComponent) this.getComponent(ComponentType.SHOOTINGCOMPONENT).get();
            return Optional.of(shooter.getDirection());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public final double getRadius() {
        return RADIUS; 
    }

    @Override
    public final double getWidth() {
        return RADIUS * 2; 
    }

    @Override
    public final double getHeight() {
        return RADIUS * 2; 
    }
}
