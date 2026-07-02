package model.objects.terrains;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.abilities.Ability;
import model.abilities.BasicAbilities;
import model.objects.AbstractGameObject;

/**
 * It's the terrain without any particular thing.
 */
public class BasicTerrain extends AbstractGameObject implements Terrain {

    private static final String ID = "Base";

    /**{@inheritDoc}**/@Override
    public Set<Ability> getRequiredAbilities() {
        final Set<Ability> required = new HashSet<>();
        required.add(BasicAbilities.WALKONLAND);
        return Collections.unmodifiableSet(required);
    }

    /**{@inheritDoc}**/@Override
    public String getId() {
        return ID;
    }

    /**{@inheritDoc}**/@Override
    public String getDescription() {
        return ID;
    }

}
