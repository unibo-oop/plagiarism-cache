package todo.view.entities.level;

import java.util.Optional;

import todo.view.entities.BaseEntity;
import todo.view.entities.BaseTaskAcceptingEntity;
import todo.view.entities.EntityVisitor;
import todo.view.entities.level.builders.PlayerBuilder;
import todo.view.entities.tasks.QueuedLoopableTaskManager;
import todo.view.entities.tasks.SequentialLoopableTaskManager;

/**
 * This class represents a {@link Player} in the game.
 */
public final class PlayerImpl extends BaseTaskAcceptingEntity<SequentialLoopableTaskManager> implements Player {
    private static final float DEFAULT_HEIGHT = 128f;

    private final QueuedLoopableTaskManager taskManager;
    private Optional<ValueBox> hand;
    private float height;

    private PlayerImpl() {
        super();
        this.taskManager = new QueuedLoopableTaskManager();
        this.hand = Optional.empty();
        this.height = DEFAULT_HEIGHT;
    }

    @Override
    public void fallbackUpdate(final float deltaTime) {
        // Do nothing
    }

    @Override
    public SequentialLoopableTaskManager getLoopableTaskManager() {
        return this.taskManager;
    }

    @Override
    public Optional<ValueBox> getHand() {
        return this.hand;
    }

    @Override
    public void setHand(final ValueBox valueBox) {
        this.hand = Optional.of(valueBox);
    }

    @Override
    public void discardHand() {
        this.hand = Optional.empty();
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public void setHeight(final float height) {
        this.height = height;
    }

    @Override
    public <T> T visit(final EntityVisitor<T> visitor) {
        return visitor.visit(this);
    }

    /**
     * This class is responsible for building the player.
     */
    public static final class Builder extends BaseEntity.Builder<Builder, Player> implements PlayerBuilder<Builder> {
        private Optional<ValueBox> currentHand = Optional.empty();

        @Override
        public Builder hand(final ValueBox valueBox) {
            this.currentHand = Optional.of(valueBox);
            return this;
        }

        @Override
        protected Player additionalBuild(final Player p) {
            this.currentHand.ifPresent(h -> p.setHand(this.currentHand.get()));
            return p;
        }

        @Override
        protected Player callConstructor() {
            return new PlayerImpl();
        }
    }
}
