package model.genome.genes;


import model.entity.cell.standard.CellStandard;

/**
 * 
 * Abstract class that implement the method toString.
 *
 */
public abstract class AbstractGeneDescription implements Gene {

    @Override
    public abstract void launch(CellStandard cell);

    @Override
    public abstract String getDescription();

    @Override
    public final String toString() {
        return getDescription();
    }

}
