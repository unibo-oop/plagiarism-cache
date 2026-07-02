package controller.initialization;

import data.DataProgramImpl;
import model.entity.cell.standard.prefabCell.CellCreatorImpl;
import model.genome.decryptor.GeneDecryptor;
import model.genome.decryptor.GeneDecryptorImpl;
import model.genome.factory.GenesFactory;
import model.genome.factory.GenesFactoryImpl;
import model.genome.factory.GenesTable;
import model.genome.factory.GenesTableImpl;
import model.properties.cells.CellData;
import model.properties.cells.CellDataBuilder;
import model.properties.cells.CellDataBuilderImpl;
import model.properties.defaultdata.CellsDefaultDataEnum;
import model.properties.genes.GenesData;
import model.properties.genes.GenesDataBuilder;
import model.properties.genes.GenesDataBuilderImpl;
import model.world.World;
import model.world.WorldImp;
import model.world.initialization.Modality;
import model.world.initialization.WorldInitialization;
import view.start.world.Start;

/**
 * 
 * Initialize and launch simulation.
 *
 */
public class SimulationInitializator {

    /**
     * build and lunch simulation.
     */
    public SimulationInitializator() {
        final World world = new WorldImp(DataProgramImpl.getWorldWidth(), DataProgramImpl.getWorldHeight());
        final GenesDataBuilder genesDataBuilder = new GenesDataBuilderImpl(world);
        final GenesData genesData = genesDataBuilder
                                .setSunEnergy(DataProgramImpl.getMaxLight())
                                .setSunPenetration(DataProgramImpl.getSunPenetration())
                                .setMineralsDepth(DataProgramImpl.getMineralDepth())
                                .build();
        final GenesFactory genesFactory = new GenesFactoryImpl(genesData);
        final GenesTable genesTable = new GenesTableImpl(genesFactory);

        final GeneDecryptor decryptor = new GeneDecryptorImpl(
                CellsDefaultDataEnum.NUMBER_GENE_TYPES.getData().getDafaultValue().intValue(),
                genesTable);

        final CellDataBuilder cellDataBuilder = new CellDataBuilderImpl(decryptor, genesTable);
        final CellData cellData = cellDataBuilder
                                .setMaxAge(DataProgramImpl.getMaxAge())
                                .setGenomeSize(DataProgramImpl.getSizeGenoma())
                                .build();

        new WorldInitialization(world, new CellCreatorImpl(cellData), DataProgramImpl.getModProg());
        new Thread(new Start(world)).start();
    }

}
