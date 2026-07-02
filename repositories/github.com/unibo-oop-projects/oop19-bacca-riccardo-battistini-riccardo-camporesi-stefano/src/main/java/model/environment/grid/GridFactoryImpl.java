package model.environment.grid;

public final class GridFactoryImpl implements GridFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Grid<T> create(final int rows, final int columns) {
        return new GridImpl<>(rows, columns);
    }

}
