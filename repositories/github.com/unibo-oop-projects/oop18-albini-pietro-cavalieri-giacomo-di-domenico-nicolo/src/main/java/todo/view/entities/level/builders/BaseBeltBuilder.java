package todo.view.entities.level.builders;

import java.util.Objects;
import java.util.Optional;

import com.badlogic.gdx.math.Vector2;

import todo.utils.Checks;
import todo.view.entities.BaseEntity;
import todo.view.entities.level.Belt;
import todo.view.rooms.AnimationsSpeed;

/**
 * This abstract class is used to simplify the creation of the builders that
 * create the belts.
 *
 * @param <S> is the self type
 * @param <E> is the type of belt that will be built
 */
public abstract class BaseBeltBuilder<S extends BaseBeltBuilder<? extends S, E>, E extends Belt>
        extends BaseEntity.Builder<S, E> implements BeltBuilder<S, E> {

    private Optional<Vector2> endPos = Optional.empty();
    private float boxToBeltMargin;
    private float boxToBoxMargin;
    private AnimationsSpeed currentAnimationsSpeed;

    @SuppressWarnings("unchecked")
    @Override
    public S endPosition(final Vector2 endPosition) {
        this.endPos = Optional.of(endPosition);
        return (S) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public S valueBoxBoundaries(final float boxToBeltHorizontalMargin, final float boxToBoxVerticalMargin) {
        this.boxToBeltMargin = boxToBeltHorizontalMargin;
        this.boxToBoxMargin = boxToBoxVerticalMargin;
        return (S) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public S animationsSpeed(final AnimationsSpeed animationsSpeed) {
        this.currentAnimationsSpeed = Objects.requireNonNull(animationsSpeed);
        return (S) this;
    }

    protected final Vector2 getEndPosition() {
        Checks.require(this.endPos.isPresent(), IllegalArgumentException.class, "An end position must be provided");
        return this.endPos.get();
    }

    protected final float getHorizontalMargin() {
        return this.boxToBeltMargin;
    }

    protected final float getVerticalMargin() {
        return this.boxToBoxMargin;
    }

    protected final AnimationsSpeed getAnimationsSpeed() {
        return this.currentAnimationsSpeed;
    }
}
