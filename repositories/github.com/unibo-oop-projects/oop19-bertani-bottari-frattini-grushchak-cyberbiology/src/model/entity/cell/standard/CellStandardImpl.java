package model.entity.cell.standard;

import java.util.List;

import model.direction.Direction;
import model.entity.cell.AbstractCell;
import model.entity.cell.CellTypeNameEnum;
import model.entity.cell.standard.action.ActionsManipulation;
import model.entity.cell.standard.age.AgeImpl;
import model.entity.cell.standard.age.AgeManipulation;
import model.entity.cell.standard.genome.GenomeManipulation;
import model.entity.cell.standard.history.HistoryImpl;
import model.entity.cell.standard.history.HistoryManipulation;
import model.entity.cell.standard.obtainable.EnergyTypeEnum;
import model.properties.cells.CellData;
/**
 * 
 * the core implementation of this game.
 * all the cell data and action.
 *
 */
public class CellStandardImpl extends AbstractCell implements CellStandard {

    private final CellData cellData;
    /**
     * the age of the cell.
     */
    private final AgeManipulation age;
    /**
     * the GENOME.
     * 
     * a class with a method for genes manipulation and all genes data.
     */
    private final GenomeManipulation genome;
    /**
     * Actions are some action that the cell can do.
     */
    private final ActionsManipulation actions;
    /**
     * the hystory memory obj to store all the info.
     * 
     */
    private final HistoryManipulation history;
    /**
     * the direction of the cell.
     */
    private Direction direction;
    /**
     * the energy of the cell.
     */
    private int energy;
    /**
     * the mineral of the cell.
     */
    private int mineral;
    private boolean active = true;

    public CellStandardImpl(final int x, final int y, final Direction direction, final int energy, final int mineral,
            final CellData cellData, final ActionsManipulation action, final GenomeManipulation genome, final int generation) {
        super(x, y);
        this.direction = direction;
        this.energy = energy;
        this.mineral = mineral;
        this.cellData = cellData;
        this.actions = action;
        this.age = new AgeImpl(this.cellData.getMaxAge());
        this.genome = genome;
        this.active = true;
        history = new HistoryImpl(generation);
    }

    @Override
    public final CellTypeNameEnum getCellTypeName() {
        return CellTypeNameEnum.CELL_STANDARD_ALIVE;
    }

    @Override
    public final void setX(final int x) {
        this.check();
        super.setX(x);
    }

    @Override
    public final void setY(final int y) {
        this.check();
        super.setY(y);
    }

    @Override
    public final Direction getDirection() {
        return this.direction;
    }

    @Override
    public final void setDirection(final Direction direction) {
        this.check();
        this.direction = direction;
    }

    @Override
    public final void run() {
        this.check();
        // age
        this.age.increment();
        if (this.age.isDead()) {
            suicide();
        } else {
            // reproduce
            if (this.energy == this.cellData.getMaxEnergy()) {
                this.actions.reproduce(this);
            }
            // energy and gene
            if (this.active) {
                if (energyDrain()) {
                    this.actions.absorbMinerals(this);
                    this.genome.startGene(this);
                }
            }
        }
    }

    private void suicide() {
        this.actions.becomeDead(this);
    }

    private boolean energyDrain() {
        this.energy -= this.cellData.getTurnCost();
        if (this.energy < 0) {
            suicide();
            return false;
        }
        return true;
    }


    // ______________________________________________________________
    // GENOME
    // ______________________________________________________________
    @Override
    public final void mutateGene(final int index, final int gene) {
        this.check();
        this.genome.mutateGene(index, gene);
    }
    @Override
    public final int getGeneValue(final int index) {
        return this.genome.getGeneValue(index);
    }
    @Override
    public final int getGeneIndex(final int value) {
        return this.genome.getGeneIndex(value);
    }
    @Override
    public final int getGeneValueWithOffsetAndJump(final int offset) {
        return this.genome.getGeneValueWithOffsetAndJump(offset);
    }
    @Override
    public final void setGene(final int index, final int value) {
        this.check();
        this.genome.setGene(index, value);
    }

    @Override
    public final int getAge() {
        return this.age.getAge();
    }

    @Override
    public final int getNumberOfGenes() {
        return this.genome.getNumberOfGenes();
    }
    public final List<Integer> getGenome() {
        return this.genome.getGenome();
    }
    @Override
    public final void changeSideLength(final int value) {
        this.genome.changeSideLength(value);
    }

    // ______________________________________________________________
    // OBTAINABLE
    // ______________________________________________________________
    @Override
    public final void decrementEnergy(final int value) {
        this.check();
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        this.energy -= value;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }
    @Override
    public final void decrementMineral(final int value) {
        this.check();
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        this.mineral -= value;
        if (this.mineral < 0) {
            this.mineral = 0;
        }
    }
    @Override
    public final int getMineral() {
        return this.mineral;
    }
    @Override
    public final int getEnergy() {
        return this.energy;
    }

    @Override
    public final void incrementMineral(final int value) {
        this.check();
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        this.mineral += value;
        if (this.mineral >= this.cellData.getMaxMinerals()) {
            this.mineral = this.cellData.getMaxMinerals();
        }
    }

    @Override
    public final void incrementEnergy(final int value, final EnergyTypeEnum energyType) {
        this.check();
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        this.energy += value;
        this.history.addEnergy(value, energyType);
        if (this.energy >= this.cellData.getMaxEnergy()) {
            this.energy = this.cellData.getMaxEnergy();
        }
    }

    // ______________________________________________________________
    // AGE
    // ______________________________________________________________
    @Override
    public final void resetAge() {
        this.age.resetAge();
    }

    // ______________________________________________________________
    // ACTION
    // ______________________________________________________________
    @Override
    public final ActionsManipulation getAction() {
        return this.actions.getAction();
    }
    @Override
    public final CellStandard makeChild() {
        this.check();
        this.energy = this.energy / 2;
        this.mineral = this.mineral / 2;
        var newCell = this.actions.dupicate(this, this.cellData);
        newCell.resetAge();

        return newCell;
    }
    // ______________________________________________________________
    // STATE
    // ______________________________________________________________
    @Override
    public final boolean isActive() {
        return this.active;
    }

    @Override
    public final void deactive() {
        this.active = false;
    }

    private void check() {
        if (!this.active) {
            throw new IllegalCellDeactiveException("you can't call me, I'm dead, leave me alone!");
        }
    }
    // ______________________________________________________________
    // HISTORY
    // ______________________________________________________________
    @Override
    public final int getTotalEnergyGained() {
        return this.history.getTotalEnergyGained();
    }

    @Override
    public final int getSpecificEnergyGained(final EnergyTypeEnum energyType) {
        return this.history.getSpecificEnergyGained(energyType);
    }
    @Override
    public final int getGeneration() {
        return this.history.getGeneration();
    }
    // ______________________________________________________________
    // AUTO GENERATED CODE
    // ______________________________________________________________
    @Override
    public final String toString() {
        return "CellStandardImpl [active=" + active + ", direction=" + direction + ", energy=" + energy + ", mineral="
                + mineral + ", age=" + age + ", genome=" + genome + "]";
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actions == null) ? 0 : actions.hashCode());
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((cellData == null) ? 0 : cellData.hashCode());
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + energy;
        result = prime * result + ((genome == null) ? 0 : genome.hashCode());
        result = prime * result + ((history == null) ? 0 : history.hashCode());
        result = prime * result + mineral;
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CellStandardImpl other = (CellStandardImpl) obj;
        if (actions == null) {
            if (other.actions != null) {
                return false;
            }
        } else if (!actions.equals(other.actions)) {
            return false;
        }
        if (active != other.active) {
            return false;
        }
        if (age == null) {
            if (other.age != null) {
                return false;
            }
        } else if (!age.equals(other.age)) {
            return false;
        }
        if (cellData == null) {
            if (other.cellData != null) {
                return false;
            }
        } else if (!cellData.equals(other.cellData)) {
            return false;
        }
        if (direction == null) {
            if (other.direction != null) {
                return false;
            }
        } else if (!direction.equals(other.direction)) {
            return false;
        }
        if (energy != other.energy) {
            return false;
        }
        if (genome == null) {
            if (other.genome != null) {
                return false;
            }
        } else if (!genome.equals(other.genome)) {
            return false;
        }
        if (history == null) {
            if (other.history != null) {
                return false;
            }
        } else if (!history.equals(other.history)) {
            return false;
        }
        if (mineral != other.mineral) {
            return false;
        }
        return true;
    }
}
