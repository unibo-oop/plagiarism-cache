package model.properties.cells;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import model.genome.decryptor.GeneDecryptor;
import model.genome.decryptor.GeneDecryptorImpl;
import model.genome.factory.GenesFactory;
import model.genome.factory.GenesFactoryImpl;
import model.genome.factory.GenesTable;
import model.genome.factory.GenesTableImpl;
import model.properties.defaultdata.CellsDefaultDataEnum;
import model.properties.genes.GenesData;
import model.properties.genes.GenesDataBuilderImpl;
import model.world.WorldImp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 
 * Tests of {@link CellDataBuilderImpl}.
 *
 */
public class TestCellData {
    private static final  int NUM_GENES = 36; 
    private CellDataBuilder builder;

    /**
     * Before each.
     */
    @BeforeEach
    public void init() {
        final GenesData genesData = new GenesDataBuilderImpl(new WorldImp(10, 10)).build();
        final GenesFactory factory = new GenesFactoryImpl(genesData);
        final GenesTable table = new GenesTableImpl(factory);
        final GeneDecryptor decryptor = new GeneDecryptorImpl(NUM_GENES, table);
        this.builder =  new CellDataBuilderImpl(decryptor, table);
    }

    /**
     * Test correct working of the builder.
     */
    @Test
    public void testCellBuilder() {
        final int age = 666;
        System.out.println(this.builder);
        final CellData data = this.builder.setMaxAge(age).build();

        assertEquals(CellsDefaultDataEnum.TURN_COST.getData().getDafaultValue(), data.getTurnCost(), "test turn cost");
        assertEquals(CellsDefaultDataEnum.GENOME_SIZE.getData().getDafaultValue(), data.getGenomeSize(), "test genome size");
        assertEquals(age, data.getMaxAge(), "test max age");
    }

    /**
     * Test correct launch of IllegalArgumenException and IllegalStateException.
     */
    @Test
    public void testCellBuilderExceptions() {
        assertThrows(IllegalArgumentException.class, () -> this.builder.setTurnCost(-1).build());
        assertThrows(IllegalArgumentException.class, () -> this.builder.setNumberOfGenes(1000).build());
        assertThrows(IllegalArgumentException.class, () -> this.builder.setNumberOfGenes(10).build()); //is not multiple of 8
        assertThrows(IllegalArgumentException.class,
                () -> this.builder.setMaxAge(CellsDefaultDataEnum.MAX_AGE.getData().getMaximumValue().intValue() + 1).build());
        this.builder.build();
        assertThrows(IllegalStateException.class, () -> {
            this.builder.setMaxAge(1000);
        });
        assertThrows(IllegalStateException.class, () -> {
            this.builder.setGenomeSize(1000);
        });
        assertThrows(IllegalStateException.class, () -> {
            this.builder.setTurnCost(1);
        });

    }


}
