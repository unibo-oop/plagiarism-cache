package model.entity.cell.standard;

import java.util.List;
import java.util.stream.Collectors;

import model.direction.Direction;
import model.direction.DirectionEnum;
import model.entity.cell.standard.action.ActionsManipulation;
import model.entity.cell.standard.age.AgeImpl;
import model.entity.cell.standard.age.AgeManipulation;
import model.entity.cell.standard.genome.GenomeImpl;
import model.entity.cell.standard.genome.GenomeManipulation;
import model.properties.cells.CellData;
/**
 * 
 *the builder to create a cell.
 *
 */
public class CellStandardBuilderImpl implements CellStandardBuilder {

    private int x;
    private int y;
    private Direction direction = DirectionEnum.NORTH;
    /**
     * the energy of the cell.
     */
    private int energy;
    /**
     * the mineral of the cell.
     */
    private int mineral;
    /**
     * the age of the cell.
     */
    private AgeManipulation age;
    /**
     * the GENOME.
     * 
     * a class with a method for genes manipulation and all genes data.
     */
    private GenomeManipulation genome;
    /**
     * Actions are some action that the cell can do.
     */
    private ActionsManipulation actions;
    private final CellData cellData;
    private int generation;

    public CellStandardBuilderImpl(final CellData cellData) {
        this.cellData = cellData;
    }

    /**
     * @param direction the direction to set
     * @return the builder
     */
    public CellStandardBuilder setDirection(final Direction direction) {
        this.direction = direction;
        return this;
    }

    /**
     * @param energy the energy to set
     * @return the builder
     */
    public CellStandardBuilder setEnergy(final int energy) {
        this.energy = energy;
        return this;
    }

    /**
     * @param mineral the mineral to set
     * @return the builder
     */
    public CellStandardBuilder setMineral(final int mineral) {
        this.mineral = mineral;
        return this;
    }

    /**
     * @param genome the genome to set
     * @return the builder
     */
    public CellStandardBuilder setGenome(final List<Integer> genome) {
        List<Integer> clonedList = genome.stream().collect(Collectors.toList());
        this.genome = new GenomeImpl(this.cellData.getGeneDecryptor(), this.cellData.getNumberOfGenes(), clonedList);

        return this;
    }

    /**
     * @param actions the actions to set
     * @return the builder
     */
    public CellStandardBuilder setActions(final ActionsManipulation actions) {
        this.actions = actions;
        return this;
    }

    @Override
    public final CellStandardBuilder setX(final int x) {
        this.x = x;
        return this;
    }

    @Override
    public final CellStandardBuilder setY(final int y) {
        this.y = y;
        return this;
    }
    @Override
    public final CellStandardBuilder setGeneration(final int generation) {
        this.generation = generation;
        return this;
    }

    @Override
    public final CellStandard build() {
        if (this.actions == null || this.genome == null) {
            throw new IllegalStateException();
        }

        if (this.age == null) {
            this.age = new AgeImpl(this.cellData.getMaxAge());
        }

        return new CellStandardImpl(this.x, this.y, this.direction, this.energy, this.mineral, this.cellData,
                this.actions, this.genome, this.generation);
    }


}
