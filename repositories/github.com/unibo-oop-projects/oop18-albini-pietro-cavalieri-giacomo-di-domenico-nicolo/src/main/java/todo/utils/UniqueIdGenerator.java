package todo.utils;

/**
 * This class generates identifiers unique across the execution of the program.
 */
public class UniqueIdGenerator {
    private int counter;

    private UniqueIdGenerator() {
        this.counter = 0;
    }

    /**
     * Get the next unique identifier.
     *
     * @return a unique identifier
     */
    public UniqueId next() {
        final UniqueId id = new UniqueIdImpl(this.counter);
        this.counter += 1;
        return id;
    }

    /**
     * Get the instance of this singleton.
     *
     * @return the global instance of UniqueIdGenerator
     */
    public static UniqueIdGenerator getInstance() {
        return SingletonHolder.GENERATOR;
    }

    private static class SingletonHolder {
        private static final UniqueIdGenerator GENERATOR = new UniqueIdGenerator();
    }

    private static final class UniqueIdImpl implements UniqueId {
        private final int id;

        private UniqueIdImpl(final int id) {
            this.id = id;
        }

        @Override
        public boolean equals(final Object other) {
            return other instanceof UniqueIdImpl && ((UniqueIdImpl) other).id == this.id;
        }

        @Override
        public String toString() {
            return "UNIQUE_ID(" + this.id + ")";
        }

        @Override
        public int hashCode() {
            return this.id;
        }
    }
}
