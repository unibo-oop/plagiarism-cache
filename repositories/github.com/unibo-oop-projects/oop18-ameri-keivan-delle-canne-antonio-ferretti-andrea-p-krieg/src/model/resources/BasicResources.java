package model.resources;

import java.util.Optional;

/**
 * The Resources that consists of a name and could have a modifier.
 */
public enum BasicResources implements Resource {

    /**
     * The Resources.
     */
    GOLD("Gold", Optional.of(500)), WOOD("Wood", Optional.of(500)), POPULATION("Population", Optional.empty());

    private final String name;
    private final Optional<Integer> modifier;

    BasicResources(final String name, final Optional<Integer> modifier) {
        this.name = name;
        this.modifier = modifier;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Integer> getModifier() {
        return this.modifier;
    }

}
