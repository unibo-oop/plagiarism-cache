package todo.view.rooms;

import todo.utils.Checks;

public class AnimationsSpeedImpl implements AnimationsSpeed {
    private final float base;
    private final float increments;
    private final int min;
    private final int max;
    private int current;

    public AnimationsSpeedImpl(final float base, final float increments, final int min, final int max) {
        this.base = base;
        this.increments = increments;
        this.min = min;
        this.max = max;
        this.current = 0;
    }

    @Override
    public float baseSpeed() {
        return this.base - this.increments * this.current;
    }

    @Override
    public void increase() {
        Checks.require(canIncrease(), IllegalStateException.class);
        this.current += 1;
    }

    @Override
    public void decrease() {
        Checks.require(canDecrease(), IllegalStateException.class);
        this.current -= 1;
    }

    @Override
    public boolean canIncrease() {
        return this.current < this.max;
    }

    @Override
    public boolean canDecrease() {
        return this.current > this.min;
    }
}
