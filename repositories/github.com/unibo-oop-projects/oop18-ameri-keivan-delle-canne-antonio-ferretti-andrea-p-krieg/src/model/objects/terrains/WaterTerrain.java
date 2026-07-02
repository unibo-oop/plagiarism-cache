package model.objects.terrains;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.abilities.Ability;
import model.abilities.BasicAbilities;
import model.objects.AbstractGameObject;

/**
 * The water structure on which only ships can go and only ports can be built
 * on.
 */
public class WaterTerrain extends AbstractGameObject implements Terrain {

    private static final String ID = "Water";

    /** {@inheritDoc} **/
    @Override
    public Set<Ability> getRequiredAbilities() {
        final Set<Ability> required = new HashSet<>();
        required.add(BasicAbilities.WALKONWATER);
        return Collections.unmodifiableSet(required);
    }

    /** {@inheritDoc} **/
    @Override
    public String getId() {
        return ID;
    }

    /** {@inheritDoc} **/
    @Override
    public String getDescription() {
        return ID; // POI DA SISTEMARE, MAGARI CON DESCRIZIONE DEL TERRENO E DELLE SUE
                   // CARATTERISTICHE
    }

}
