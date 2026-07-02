package it.unibo.aknightstale.utils;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.aknightstale.controllers.entity.EntityController;
import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.models.entity.Direction;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollisionManagerImpl implements CollisionManager {

    private final List<EntityController<? super Character, ? super AnimatedEntityView>> entities;
    private double widthScreen;
    private double heightScreen;

    @SuppressFBWarnings("EI_EXPOSE_REP2") // can be modified later
    public CollisionManagerImpl(final List<EntityController<? super Character, ? super AnimatedEntityView>> entities,
                                final double width, final double height) {
        super();
        this.entities = entities;
        this.widthScreen = width;
        this.heightScreen = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EntityController<? super Character, ? super AnimatedEntityView>> checkCollision(
            final EntityController<? super Character, ? super AnimatedEntityView> ec) {
        final var entity = new BordersImpl(ec.getModel().getPosition().getX() - 1.0,
                ec.getModel().getPosition().getY() - 1.0, ec.getModel().getBorders().getWidth() + 1.0,
                ec.getModel().getBorders().getHeight() + 1.0);

        return this.entities.stream()
                .filter(e -> !e.equals(ec)
                        && e.getModel().isCollidable() 
                        && entity.intersects(e.getModel().getBorders()))
                .collect(Collectors.toList());
    }

    private boolean intersectsBorders(final Character en, final double x, final double y, final double width, final double height) {
        final var borders = new BordersImpl(x, y, width, height);
        return this.entities.stream()
                            .filter(e -> !e.getModel().equals(en) 
                                    && e.getModel().isCollidable()
                                    && borders.intersects(e.getModel().getBorders()))
                            .count() == 0;
    }

    private void collisionsMovement(final Character e, final List<Direction> list) {
        final var borders = e.getBorders();
        if (borders.getX() + borders.getWidth() < this.widthScreen
                && intersectsBorders(e, borders.getX(), borders.getY(), borders.getWidth() + 1.0, borders.getHeight())) {
            list.add(Direction.RIGHT);
        }
        if (borders.getX() > 0 
                && intersectsBorders(e, borders.getX() - 1.0, borders.getY(), 1.0, borders.getHeight())) {
            list.add(Direction.LEFT);
        }
        if ((borders.getY() + borders.getHeight()) < this.heightScreen
                && intersectsBorders(e, borders.getX(), borders.getY(), borders.getWidth(), borders.getHeight() + 1.0)) {
            list.add(Direction.DOWN);
        }
        if (borders.getY() > 0 
                && intersectsBorders(e, borders.getX(), borders.getY() - 1.0, borders.getWidth(), 1.0)) {
            list.add(Direction.UP);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Direction> checkDirections(
            final EntityController<? extends Character, ? extends AnimatedEntityView> ec) {
        final var list = new ArrayList<Direction>();
        collisionsMovement(ec.getModel(), list); 
        return List.copyOf(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<EntityController<? super Character, ? super AnimatedEntityView>>> update() {
        final List<List<EntityController<? super Character, ? super AnimatedEntityView>>> list = new ArrayList<>();
        this.entities.stream().filter(e -> e.getModel().isCollidable()).forEach(e -> {
            final var l = this.checkCollision(e);
            if (!l.isEmpty()) {
                l.add(e);
                list.add(l);
            }
        });
        return List.copyOf(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidthScreen(final double widthScreen) {
        this.widthScreen = widthScreen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeightScreen(final double heightScreen) {
        this.heightScreen = heightScreen;
    }

}
