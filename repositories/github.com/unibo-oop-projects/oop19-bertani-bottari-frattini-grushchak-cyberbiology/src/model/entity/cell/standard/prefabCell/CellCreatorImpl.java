package model.entity.cell.standard.prefabCell;

import java.util.List;
import java.util.Random;

import model.direction.Direction;
import model.direction.DirectionDecryptor;
import model.entity.cell.standard.CellStandard;
import model.entity.cell.standard.CellStandardBuilder;
import model.entity.cell.standard.CellStandardBuilderImpl;
import model.entity.cell.standard.action.ActionsImpl;
import model.entity.cell.standard.action.ActionsManipulation;
import model.entity.cell.standard.genome.CircularLinkedList;
import model.genome.factory.GenesEnum;
import model.properties.cells.CellData;

public class CellCreatorImpl implements CellCreator {

    private final CellStandardBuilder firstCell;
    private final Random rand;
    private final ActionsManipulation action;
    private final CellData cellData;

    public CellCreatorImpl(final CellData cellData) {
        this.cellData = cellData;
        this.firstCell = new CellStandardBuilderImpl(cellData);
        this.rand = new Random();
        this.action = new ActionsImpl((cellData.getGenesTable().getGene(GenesEnum.DEATH)),
                        (cellData.getGenesTable().getGene(GenesEnum.REPRODUCTION)),
                        (cellData.getGenesTable().getGene(GenesEnum.MINERALS_ABSORPTION)));
    }


    private List<Integer> createAllPhotosyntesisGenoma() {
        List<Integer> list = new CircularLinkedList<Integer>();
        for (int i = 0; i < this.cellData.getGenomeSize(); i++) {
            list.add(this.cellData.getGeneDecryptor().getIndexOfGene(this.cellData.getGenesTable().getGene(GenesEnum.PHOTOSYNTHESIS)));
        }
        return list;
    }
    private Direction getRandomDirection() {
        return DirectionDecryptor.getDirection(Math.abs(rand.nextInt()));
    }

    @Override
    public final CellStandard newAllPhotosyntesisCell(final int x, final int y) {
        return this.firstCell
                .setX(x)
                .setY(y)
                .setEnergy(this.cellData.getMaxEnergy())
                .setMineral(0)
                .setActions(this.action)
                .setDirection(getRandomDirection())
                .setGenome(createAllPhotosyntesisGenoma())
                .build();
    }

    @Override
    public final CellStandard newAllRandomCell(final int x, final int y) {
        return this.firstCell
                .setX(x)
                .setY(y)
                .setEnergy(this.cellData.getMaxEnergy())
                .setMineral(0)
                .setActions(this.action)
                .setDirection(getRandomDirection())
                .setGenome(createAllRandomGenoma())
                .build();
    }


    private List<Integer> createAllRandomGenoma() {
        List<Integer> list = new CircularLinkedList<Integer>();
        for (int i = 0; i < this.cellData.getGenomeSize(); i++) {
            list.add(this.rand.nextInt(this.cellData.getNumberOfGenes()));
        }
        return list;
    }
}
