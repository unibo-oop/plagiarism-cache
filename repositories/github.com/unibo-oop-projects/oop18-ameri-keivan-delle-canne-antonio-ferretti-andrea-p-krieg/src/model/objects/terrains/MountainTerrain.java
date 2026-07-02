package model.objects.terrains;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.abilities.Ability;
import model.abilities.BasicAbilities;
import model.objects.AbstractGameObject;

/**
 * The terrain on which mines can be built.
 */
public class MountainTerrain extends AbstractGameObject implements Terrain {

    private static final String ID = "Mountain";

    /**{@inheritDoc}**/@Override
    public Set<Ability> getRequiredAbilities() {
        final Set<Ability> required = new HashSet<>();
        required.add(BasicAbilities.WALKONMOUNTAIN);
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
