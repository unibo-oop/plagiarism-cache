package todo.view.drawables;

import java.util.HashMap;
import java.util.Map;

import todo.view.drawables.level.DrawableFloor;
import todo.view.drawables.level.DrawableInputBelt;
import todo.view.drawables.level.DrawableMemoryArea;
import todo.view.drawables.level.DrawableOutputBelt;
import todo.view.drawables.level.DrawablePlayer;
import todo.view.drawables.level.DrawableValueBox;
import todo.view.entities.Entity;
import todo.view.entities.EntityVisitor;
import todo.view.entities.level.Floor;
import todo.view.entities.level.InputBelt;
import todo.view.entities.level.MemoryArea;
import todo.view.entities.level.OutputBelt;
import todo.view.entities.level.Player;
import todo.view.entities.level.ValueBox;

/**
 * This class is an implementation of the {@link DrawableFactory}. It uses the
 * {@link EntityVisitor} to visit each entity and get its corresponding
 * {@link Drawable}.
 */
public final class DrawableFactoryImpl implements DrawableFactory {
    private final Map<Entity, Drawable<? extends Entity>> drawableEntities;
    private final EntityVisitor<Drawable<? extends Entity>> visitor;

    public DrawableFactoryImpl() {
        this.drawableEntities = new HashMap<>();
        this.visitor = getVisitor();
    }

    @Override
    public Drawable<? extends Entity> fromEntity(final Entity entity) {
        if (this.drawableEntities.containsKey(entity)) {
            return this.drawableEntities.get(entity);
        } else {
            final Drawable<? extends Entity> drawable = entity.visit(this.visitor);
            this.drawableEntities.put(entity, drawable);
            return drawable;
        }
    }

    private EntityVisitor<Drawable<? extends Entity>> getVisitor() {
        return new EntityVisitor<Drawable<? extends Entity>>() {
            @Override
            public Drawable<? extends Entity> visit(final InputBelt belt) {
                return new DrawableInputBelt(belt);
            }

            @Override
            public Drawable<? extends Entity> visit(final OutputBelt belt) {
                return new DrawableOutputBelt(belt);
            }

            @Override
            public Drawable<? extends Entity> visit(final Player player) {
                return new DrawablePlayer(player);
            }

            @Override
            public Drawable<? extends Entity> visit(final MemoryArea area) {
                return new DrawableMemoryArea(area);
            }

            @Override
            public Drawable<? extends Entity> visit(final ValueBox box) {
                return new DrawableValueBox(box);
            }

            @Override
            public Drawable<? extends Entity> visit(final Floor floor) {
                return new DrawableFloor(floor);
            }
        };
    }
}
