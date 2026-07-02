package model.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import model.properties.genes.GenesData;
import model.properties.genes.GenesDataBuilder;
import model.properties.genes.GenesDataBuilderImpl;
import model.world.WorldImp;

/**
 * Al moment serve per testare GenesDataBuilder.
 * In futuro questa classe sarà sostituita con un'altra.
 */
public class TestProperties {

    /**
     * Test for GenesDataBuilder.
     */
    @Test
    public void genesBuilderTest() {
        final int cost = 200;
        final int absorption = 3;
        final int depth = 50;
        final int mutation = 20;
        final int nutrition = 300;
        final int sun = 15;
        final int penetration = 50;
        final GenesDataBuilder builder = new GenesDataBuilderImpl(new WorldImp(10, 10));
        final GenesData data = builder.setReproductionCost(cost)
                                .setMineralsAbsorption(absorption)
                                .setMineralsDepth(depth)
                                .setMutationRate(mutation)
                                .setNutritionOfDeadCell(nutrition)
                                .setSunEnergy(sun)
                                .setSunPenetration(penetration)
                                .build();
        assertEquals(cost, data.getReproductionCost(), "test reproduntion cost");
        assertEquals(absorption, data.getMineralsAbsorption(), "test minerals absorption");
        assertEquals(depth, data.getMineralsDepth(), "test minerals depth");
        assertEquals(mutation, data.getMutationRate(), "test mutation rate");
        assertEquals(nutrition, data.getNutritionOfDeadCell(), "test nutrition of det dead cell");
        assertEquals(sun, data.getSunEnergy(), "test sun energy");
        assertEquals(penetration, data.getSunPenetration(), "test sun penetrations");

    }

    /**
     * Test for GenesDataBuilder exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void genesBuilderExeptionTest() {
        final GenesDataBuilder builder = new GenesDataBuilderImpl(new WorldImp(10, 10));
        builder.setReproductionCost(-1).build();
    }

}
