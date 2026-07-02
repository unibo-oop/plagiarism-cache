package model.entity.cell.standard.action;

import model.entity.cell.standard.CellStandard;
import model.entity.cell.standard.CellStandardBuilderImpl;
import model.entity.cell.standard.CellStandardImpl;
import model.genome.genes.Gene;
import model.genome.genes.MineralsAbsorptionGene;
import model.genome.genes.externals.DeathGene;
import model.genome.genes.externals.ReproductionGene;
import model.properties.cells.CellData;

public class ActionsImpl implements ActionsManipulation {

    private final DeathGene deathGene;
    private final ReproductionGene reproductionGene;
    private final MineralsAbsorptionGene mineralsAbsorptionGene;

    public ActionsImpl(final Gene deathGene, final Gene reproductionGene, final Gene mineralsAbsorptionGene) {
        this.deathGene = (DeathGene) deathGene;
        this.reproductionGene = (ReproductionGene) reproductionGene;
        this.mineralsAbsorptionGene = (MineralsAbsorptionGene) mineralsAbsorptionGene;
    }

    @Override
    public final CellStandard dupicate(final CellStandardImpl cell, final CellData cellData) {
        return new CellStandardBuilderImpl(cellData)
                .setDirection(cell.getDirection())
                .setEnergy(cell.getEnergy())
                .setMineral(cell.getMineral())
                .setGenome(cell.getGenome())
                .setActions(cell.getAction())
                .setGeneration(cell.getGeneration() + 1)
                .build();
    }

    @Override
    public final void reproduce(final CellStandard cell) {
        this.reproductionGene.launch(cell);
    }

    @Override
    public final void becomeDead(final CellStandard cell) {
        this.deathGene.launch(cell);
    }

    @Override
    public final ActionsManipulation getAction() {
        return this;
    }

    @Override
    public final void absorbMinerals(final CellStandard cell) {
        this.mineralsAbsorptionGene.launch(cell);
    }
}
