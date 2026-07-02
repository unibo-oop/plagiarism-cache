package model.entity.cell.standard.age;

public class AgeImpl implements AgeManipulation {
    /**
     * the age of the cell.
     */
    private int age;
    /**
     * the max age of the cell.
     */
    private final int maxAge;

    public AgeImpl(final int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public final int getAge() {
        return this.age;
    }

    @Override
    public final void increment() {
        this.age++;
    }

    @Override
    public final boolean isDead() {
        return this.age > this.maxAge;
    }

    @Override
    public final void resetAge() {
        this.age = 0;
    }

}
